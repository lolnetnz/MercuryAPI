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

package nz.co.lolnet.mercuryapi.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import nz.co.lolnet.mercuryapi.api.lolcon.LolCon;
import nz.co.lolnet.mercuryapi.entries.Data;
import nz.co.lolnet.mercuryapi.util.ConsoleOutput;

public class API {
	
	private final String uniqueId;
	private final String token;
	private final LolCon lolCon;
	
	public API(String uniqueId, String token) {
		this.uniqueId = uniqueId;
		this.token = token;
		lolCon = new LolCon(this);
	}
	
	public String request(String endpoint, String message) {
		try {
			Data data = new Data(getUniqueId(), doEncrypt(message, getToken()));
			
			String request = Base64.getEncoder().encodeToString(new Gson().toJson(data).getBytes());
			ConsoleOutput.info(request);
			URLConnection urlConnection = new URL("http://api.lolnet.co.nz/v0.1/" + endpoint + "/" + request).openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setConnectTimeout(10000);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String response = bufferedReader.readLine();
			bufferedReader.close();
			
			if (response == null) {
				return null;
			}
			
			data = new Gson().fromJson(new JsonParser().parse(response).getAsJsonObject(), Data.class);
			
			if (data == null) {
				return null;
			}
			
			return doDecrypt(data.getMessage(), getToken());
		} catch (IOException | RuntimeException ex) {
			ex.printStackTrace();
		}
		return null;
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
			ConsoleOutput.error("Exception during doEncrypt!");
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
			ConsoleOutput.error("Exception during doDecrypt!");
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
	
	private String getUniqueId() {
		return uniqueId;
	}
	
	private String getToken() {
		return token;
	}
	
	public LolCon getLolCon() {
		return lolCon;
	}
}