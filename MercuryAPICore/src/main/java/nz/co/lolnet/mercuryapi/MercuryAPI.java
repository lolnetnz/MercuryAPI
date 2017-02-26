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

package nz.co.lolnet.mercuryapi;

import nz.co.lolnet.mercuryapi.api.API;
import nz.co.lolnet.mercuryapi.api.lolcon.request.PlayerBalanceRequest;
import nz.co.lolnet.mercuryapi.api.lolcon.response.PlayerBalanceResponse;
import nz.co.lolnet.mercuryapi.util.ConsoleOutput;

public class MercuryAPI {
	
	private final String uniqueId = "";
	private final String token = "";
	
	public void loadMercuryAPI() {
		API api = new API(uniqueId, token);
		
		PlayerBalanceResponse response = api.getLolCon().getPlayerBalance(new PlayerBalanceRequest("LX_Gaming"));
		ConsoleOutput.info(response.getPlayerName() + " - " + response.getPlayerBalance());
	}
}