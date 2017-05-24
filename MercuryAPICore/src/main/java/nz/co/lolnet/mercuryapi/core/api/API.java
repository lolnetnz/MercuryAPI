/*
 * Copyright 2017 lolnet.co.nz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nz.co.lolnet.mercuryapi.core.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import nz.co.lolnet.mercuryapi.core.entries.APIResponse;
import nz.co.lolnet.mercuryapi.core.entries.Account;
import nz.co.lolnet.mercuryapi.core.entries.Data;
import nz.co.lolnet.mercuryapi.core.util.LogHelper;
import nz.co.lolnet.mercuryapi.core.util.TypeAdapter;

public abstract class API extends APIResponse {
	
	private transient final Account account;
	
	public API(String uniqueId, String token) {
		this(new Account(uniqueId, token));
	}
	
	public API(Account account) {
		this.account = account;
	}
	
	public abstract API execute();
	
	public abstract String getEndpoint();
	
	protected API get() {
		try {
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			Data data = new Data(getAccount().getUniqueId(), doEncrypt(gson.toJson(this, getClass()), getAccount().getToken()));
			String request = Base64.getUrlEncoder().encodeToString(gson.toJson(data, Data.class).getBytes());
			
			URL url = new URL("http://api.lolnet.co.nz/v0.1/" + getEndpoint() + "/" + request);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setRequestMethod("GET");
			
			if (!isResponseCodeSuccessful(httpURLConnection.getResponseCode()) && httpURLConnection.getErrorStream() != null) {
				return getResponse(getClass(), httpURLConnection.getErrorStream(), httpURLConnection.getResponseCode());
			}
			
			if (isResponseCodeSuccessful(httpURLConnection.getResponseCode()) && httpURLConnection.getInputStream() != null) {
				gson = new GsonBuilder().registerTypeAdapter(getClass(), new TypeAdapter(this)).create();
				data = getResponse(Data.class, httpURLConnection.getInputStream(), httpURLConnection.getResponseCode());
				String decrypted = doDecrypt(data.getMessage(), getAccount().getToken());
				return gson.fromJson(decrypted, getClass());
			}
		} catch (IOException | RuntimeException ex) {
			LogHelper.error("Encountered an error processing 'request' in '" + getClass().getSimpleName() + "' - " + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	protected API post() {
		try {
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			Data data = new Data(getAccount().getUniqueId(), doEncrypt(gson.toJson(this, getClass()), getAccount().getToken()));
			String request = Base64.getUrlEncoder().encodeToString(gson.toJson(data, Data.class).getBytes());
			
			URL url = new URL("http://api.lolnet.co.nz/v0.1/" + getEndpoint() + "/" + request);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			
			if (!sendRequest(httpURLConnection.getOutputStream(), request)) {
				return null;
			}
			
			if (!isResponseCodeSuccessful(httpURLConnection.getResponseCode()) && httpURLConnection.getErrorStream() != null) {
				return getResponse(getClass(), httpURLConnection.getErrorStream(), httpURLConnection.getResponseCode());
			}
			
			if (isResponseCodeSuccessful(httpURLConnection.getResponseCode()) && httpURLConnection.getInputStream() != null) {
				gson = new GsonBuilder().registerTypeAdapter(getClass(), new TypeAdapter(this)).create();
				data = getResponse(Data.class, httpURLConnection.getInputStream(), httpURLConnection.getResponseCode());
				String decrypted = doDecrypt(data.getMessage(), getAccount().getToken());
				return gson.fromJson(decrypted, getClass());
			}
		} catch (IOException | RuntimeException ex) {
			LogHelper.error("Encountered an error processing 'request' in '" + getClass().getSimpleName() + "' - " + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	private <T> T getResponse(Class<T> classOfT, InputStream inputStream, int responseCode) {
		if (inputStream == null || responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
			return null;
		}
		
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			String response = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
			if (response == null || response.trim().equals("")) {
				return null;
			}
			
			JsonElement jsonElement = new JsonParser().parse(response);
			if (jsonElement == null) {
				return null;
			}
			
			return new GsonBuilder().registerTypeAdapter(getClass(), new TypeAdapter(this)).create().fromJson(jsonElement, classOfT);
		} catch (IOException | RuntimeException ex) {
			LogHelper.error("Encountered an error processing 'getResponse' in '" + getClass().getSimpleName() + "' - " + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	private boolean sendRequest(OutputStream outputStream, String request) {
		if (outputStream == null || request == null) {
			return false;
		}
		
		try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
			dataOutputStream.write(request.getBytes(StandardCharsets.UTF_8));
			return true;
		} catch (IOException | RuntimeException ex) {
			LogHelper.error("Encountered an error processing 'sendRequest' in '" + getClass().getSimpleName() + "' - " + ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}
	
	private boolean isResponseCodeSuccessful(int responseCode) {
		//200 = OK, 201 = Created, 202 = Accepted, 204 = No Content.
		if (responseCode == 200 || responseCode == 201 || responseCode == 202 || responseCode == 204) {
			return true;
		}
		return false;
	}
	
	private String doEncrypt(String decrypted, String password) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			byte[] input = new String(decrypted).getBytes(StandardCharsets.UTF_8);
			byte[] secret = getSecret(password.toCharArray());
			byte[] ivBytes = cipher.getParameters().getParameterSpec(IvParameterSpec.class).getIV();
			
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secret, "AES"), new IvParameterSpec(ivBytes));
			byte[] encrypted = new byte[cipher.getOutputSize(input.length)];
			int enc_len = cipher.update(input, 0, input.length, encrypted, 0);
			enc_len += cipher.doFinal(encrypted, enc_len);
			
			byte[] byteArray = new byte[ivBytes.length + encrypted.length];
			System.arraycopy(ivBytes, 0, byteArray, 0, ivBytes.length);
			System.arraycopy(encrypted, 0, byteArray, ivBytes.length, encrypted.length);
			
			return Base64.getEncoder().encodeToString(byteArray);
		} catch (GeneralSecurityException | RuntimeException ex) {
			LogHelper.error("Encountered an error processing 'doEncrypt' in '" + getClass().getSimpleName() + "' - " + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	private String doDecrypt(String encrypted, String password) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			byte[] input = Base64.getDecoder().decode(encrypted);
			byte[] secret = getSecret(password.toCharArray());
			byte[] ivBytes = new byte[16];
			
			byte[] byteArray = new byte[input.length - ivBytes.length];
			System.arraycopy(input, 0, ivBytes, 0, 16);
			System.arraycopy(input, 16, byteArray, 0, byteArray.length);
			
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secret, "AES"), new IvParameterSpec(ivBytes));
			byte[] decrypted = new byte[cipher.getOutputSize(byteArray.length)];
			int dec_len = cipher.update(byteArray, 0, byteArray.length, decrypted, 0);
			dec_len += cipher.doFinal(decrypted, dec_len);
			
			return new String(decrypted, StandardCharsets.UTF_8).trim();
		} catch (GeneralSecurityException | RuntimeException ex) {
			LogHelper.error("Encountered an error processing 'doDecrypt' in '" + getClass().getSimpleName() + "' - " + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	private byte[] getSecret(char[] password) throws GeneralSecurityException, RuntimeException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(new String(password).getBytes(StandardCharsets.UTF_8));
		byte[] secret = new byte[16];
		System.arraycopy(messageDigest.digest(), 0, secret, 0, secret.length);
		return secret;
	}
	
	private Account getAccount() {
		return account;
	}
}