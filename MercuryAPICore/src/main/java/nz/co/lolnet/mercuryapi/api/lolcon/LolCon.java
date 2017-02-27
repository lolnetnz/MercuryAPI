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
package nz.co.lolnet.mercuryapi.api.lolcon;

import com.google.gson.Gson;

import nz.co.lolnet.mercuryapi.api.API;
import nz.co.lolnet.mercuryapi.api.lolcon.request.*;
import nz.co.lolnet.mercuryapi.api.lolcon.response.*;

public class LolCon {

    private final API api;

    public LolCon(API api) {
        this.api = api;
    }

    public PlayerBalanceResponse getPlayerBalance(PlayerBalanceRequest playerBalanceRequest) {
        return new Gson().fromJson(getAPI().request("lolcon/getplayerbalance", new Gson().toJson(playerBalanceRequest)), PlayerBalanceResponse.class);
    }

    public AddTempCommandResponse addTempCommand(AddTempCommandRequest addTempCommandRequest) {
        return new Gson().fromJson(getAPI().request("lolcon/addtempcommand", new Gson().toJson(addTempCommandRequest)), AddTempCommandResponse.class);
    }

    public ChangePlayerNameResponse changePlayerName(ChangePlayerNameRequest changePlayerNameRequest) {
        return new Gson().fromJson(getAPI().request("lolcon/changeplayername", new Gson().toJson(changePlayerNameRequest)), ChangePlayerNameResponse.class);
    }
    
    public ChangePlayerUUIDResponse changePlayerUUID(ChangePlayerUUIDRequest changePlayerUUIDRequest) {
        return new Gson().fromJson(getAPI().request("lolcon/changeplayeruuid", new Gson().toJson(changePlayerUUIDRequest)), ChangePlayerUUIDResponse.class);
    }

    private API getAPI() {
        return api;
    }
}
