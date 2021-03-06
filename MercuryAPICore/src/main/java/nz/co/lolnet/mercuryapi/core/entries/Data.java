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

public class Data {
	
	@Expose
	private String uniqueId;
	@Expose
	private String message;
	
	public Data() {
	}
	
	public Data(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	public Data(String uniqueId, String message) {
		this.uniqueId = uniqueId;
		this.message = message;
	}
	
	public String getUniqueId() {
		return uniqueId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}