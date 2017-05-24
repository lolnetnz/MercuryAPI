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

package nz.co.lolnet.mercuryapi.core;

import nz.co.lolnet.mercuryapi.core.api.lolcon.GetPlayerBalance;
import nz.co.lolnet.mercuryapi.core.entries.APIResponse.ResponseTypes;
import nz.co.lolnet.mercuryapi.core.entries.Account;
import nz.co.lolnet.mercuryapi.core.util.LogHelper;

public class Main {
	
	public static void main(String[] args) {
		recommended();
	}
	
	public static void recommended() {
		MercuryAPICore mercuryAPI = new MercuryAPICore();
		mercuryAPI.setAccount("", "");
		
		GetPlayerBalance getPlayerBalance = mercuryAPI.getLolCon().getPlayerBalance();
		getPlayerBalance.setPlayerName("");
		getPlayerBalance.execute();
		
		if (getPlayerBalance.getResponseType().equals(ResponseTypes.INFO) || getPlayerBalance.getResponseType().equals(ResponseTypes.ERROR)) {
			LogHelper.info(getPlayerBalance.getResponse(getPlayerBalance.getResponseType()) + " - " + getPlayerBalance.getResponseMessage(getPlayerBalance.getResponseType()));
			//API has returned an info or error response.
			return;
		}
		LogHelper.info(getPlayerBalance.getPlayerName() + " - " + getPlayerBalance.getPlayerBalance());
	}
	
	public static void methodOne() {
		MercuryAPICore mercuryAPI = new MercuryAPICore().setAccount("", "");
		
		LogHelper.info("" + mercuryAPI.getLolCon().getPlayerBalance().setPlayerName("").execute().getPlayerBalance());
	}
	
	public static void methodTwo() {
		MercuryAPICore mercuryAPI = new MercuryAPICore();
		mercuryAPI.setAccount("", "");
		
		GetPlayerBalance getPlayerBalance = mercuryAPI.getLolCon().getPlayerBalance();
		getPlayerBalance.setPlayerName("");
		getPlayerBalance.execute();
		
		LogHelper.info("" + getPlayerBalance.getPlayerBalance());
	}
	
	public static void methodThree() {
		//LogHelper.info(new GetPlayerBalance(new Account("", "")).setPlayerName("").execute().getPlayerBalance());
	}
	
	public static void methodFour() {
		Account account = new Account("", "");
		GetPlayerBalance getPlayerBalance = new GetPlayerBalance(account);
		getPlayerBalance.setPlayerName("");
		getPlayerBalance.execute();
		LogHelper.info("" + getPlayerBalance.getPlayerBalance());
	}
}