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

package nz.co.lolnet.mercuryapi.core.entries;

import com.google.gson.annotations.Expose;

public abstract class APIResponse {
	
	@Expose
	private long creationTime;
	private String info;
	private String infoMessage;
	private String error;
	private String errorMessage;
	
	public ResponseTypes getResponseType() {
		if (!hasInfo() && !hasError()) {
			return ResponseTypes.NONE;
		}
		
		if (hasInfo() && !hasError()) {
			return ResponseTypes.INFO;
		}
		
		if (!hasInfo() && hasError()) {
			return ResponseTypes.ERROR;
		}
		return ResponseTypes.UNKNOWN;
	}
	
	private boolean hasInfo() {
		if (getInfo() != null && !getInfo().trim().equals("") && getInfoMessage() != null && !getInfoMessage().trim().equals("")) {
			return true;
		}
		return false;
	}
	
	private boolean hasError() {
		if (getError() != null && !getError().trim().equals("") && getErrorMessage() != null && !getErrorMessage().trim().equals("")) {
			return true;
		}
		return false;
	}
	
	public String getResponse(ResponseTypes responseType) {
		if (responseType != null && responseType.equals(ResponseTypes.INFO)) {
			return getInfo();
		}
		
		if (responseType != null && responseType.equals(ResponseTypes.ERROR)) {
			return getError();
		}
		return null;
	}
	
	public String getResponseMessage(ResponseTypes responseType) {
		if (responseType != null && responseType.equals(ResponseTypes.INFO)) {
			return getInfoMessage();
		}
		
		if (responseType != null && responseType.equals(ResponseTypes.ERROR)) {
			return getErrorMessage();
		}
		return null;
	}
	
	public long getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}
	
	public String getInfo() {
		return info;
	}
	
	public String getInfoMessage() {
		return infoMessage;
	}
	
	public String getError() {
		return error;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public enum ResponseTypes {
		INFO, ERROR, NONE, UNKNOWN;
	}
}