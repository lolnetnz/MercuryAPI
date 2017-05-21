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
import nz.co.lolnet.mercuryapi.api.forum.request.ForumGroupsRequest;
import nz.co.lolnet.mercuryapi.api.forum.request.ForumUserForumGroupsRequest;
import nz.co.lolnet.mercuryapi.api.forum.response.ForumGroupsResponse;
import nz.co.lolnet.mercuryapi.api.forum.response.ForumUserForumGroupsResponse;
import nz.co.lolnet.mercuryapi.api.lolcon.request.AddTempCommandRequest;
import nz.co.lolnet.mercuryapi.api.lolcon.request.ChangePlayerNameRequest;
import nz.co.lolnet.mercuryapi.api.lolcon.request.ChangePlayerUUIDRequest;
import nz.co.lolnet.mercuryapi.api.lolcon.request.PlayerBalanceRequest;
import nz.co.lolnet.mercuryapi.api.lolcon.response.AddTempCommandResponse;
import nz.co.lolnet.mercuryapi.api.lolcon.response.ChangePlayerNameResponse;
import nz.co.lolnet.mercuryapi.api.lolcon.response.ChangePlayerUUIDResponse;
import nz.co.lolnet.mercuryapi.api.lolcon.response.PlayerBalanceResponse;
import nz.co.lolnet.mercuryapi.util.ConsoleOutput;

public class MercuryAPI {

    private final String uniqueId = "";
    private final String token = "";

    public void loadMercuryAPI() {
        API api = new API(uniqueId, token);
        
        PlayerBalanceResponse response = api.getLolCon().getPlayerBalance(new PlayerBalanceRequest("LX_Gaming"));
        ConsoleOutput.info(response.getPlayerName() + " - " + response.getPlayerBalance());
        
        boolean debug = false;
        
        if (debug) {
            AddTempCommandResponse response1 = api.getLolCon().addTempCommand(new AddTempCommandRequest("james137137", "testingTheAPI"));
            ConsoleOutput.info("" + response1.getsuccess());
            
            ChangePlayerNameResponse response2 = api.getLolCon().changePlayerName(new ChangePlayerNameRequest("James137137", "96513543-3da9-4ec4-8b29-31b542921da1"));
            ConsoleOutput.info("" + response2.getsuccess());
            
            ChangePlayerUUIDResponse response3 = api.getLolCon().changePlayerUUID(new ChangePlayerUUIDRequest("James137137", "96513543-3da9-4ec4-8b29-31b542921da1"));
            ConsoleOutput.info("" + response3.getSuccess());
            
            ForumGroupsResponse response4 = api.getForum().getForumGroups(new ForumGroupsRequest());
            ConsoleOutput.info(response4.getForumGroupsList().toString());
        }
        ForumUserForumGroupsResponse response5 = api.getForum().getForumUserForumGroups(new ForumUserForumGroupsRequest(415));
        for (Integer i : response5.getForumGroups()) {
        	ConsoleOutput.info("" + i.intValue());
        }
    }
}