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

public class ChangePlayerName extends API {
	
	@Expose
	private String playerName;
	@Expose
	private String playerUUID;
	
	public ChangePlayerName(Account account) {
		super(account);
	}
	
	@Override
	public ChangePlayerName execute() {
		return (ChangePlayerName) super.post();
	}
	
	@Override
	public String getEndpoint() {
		return "lolcon/changeplayername";
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public ChangePlayerName setPlayerName(String playerName) {
		this.playerName = playerName;
		return this;
	}
	
	public String getPlayerUUID() {
		return playerUUID;
	}
	
	public ChangePlayerName setPlayerUUID(String playerUUID) {
		this.playerUUID = playerUUID;
		return this;
	}
}