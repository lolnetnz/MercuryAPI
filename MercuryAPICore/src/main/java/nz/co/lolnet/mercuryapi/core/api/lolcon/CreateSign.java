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

package nz.co.lolnet.mercuryapi.core.api.lolcon;

import com.google.gson.annotations.Expose;

import nz.co.lolnet.mercuryapi.core.api.API;
import nz.co.lolnet.mercuryapi.core.entries.Account;

public class CreateSign extends API {
	
	@Expose
	private String playerName;
	@Expose
	private String location;
	@Expose
	private String details;
	
	public CreateSign(Account account) {
		super(account);
	}
	
	@Override
	public CreateSign execute() {
		return (CreateSign) super.post();
	}
	
	@Override
	public String getEndpoint() {
		return "lolcon/createsign";
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public CreateSign setPlayerName(String playerName) {
		this.playerName = playerName;
		return this;
	}
	
	public String getLocation() {
		return location;
	}
	
	public CreateSign setLocation(String location) {
		this.location = location;
		return this;
	}
	
	public String getDetails() {
		return details;
	}
	
	public CreateSign setDetails(String details) {
		this.details = details;
		return this;
	}
}