/*
 * Copyright 2017 Alex Thomson
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

package nz.co.lolnet.mercuryapi.api.forum;

import com.google.gson.Gson;

import nz.co.lolnet.mercuryapi.api.API;
import nz.co.lolnet.mercuryapi.api.forum.request.ForumGroupsRequest;
import nz.co.lolnet.mercuryapi.api.forum.request.ForumUserForumGroupsRequest;
import nz.co.lolnet.mercuryapi.api.forum.response.ForumGroupsResponse;
import nz.co.lolnet.mercuryapi.api.forum.response.ForumUserForumGroupsResponse;

public class Forum {
	
    private final API api;

    public Forum(API api) {
        this.api = api;
    }
    
    public ForumGroupsResponse getForumGroups(ForumGroupsRequest forumGroupsRequest) {
        return new Gson().fromJson(getAPI().request("forum/getforumgroup", new Gson().toJson(forumGroupsRequest)), ForumGroupsResponse.class);
    }
    
    public ForumUserForumGroupsResponse getForumUserForumGroups(ForumUserForumGroupsRequest forumUserForumGroupsRequest) {
        return new Gson().fromJson(getAPI().request("forum/getforumuserforumgroups", new Gson().toJson(forumUserForumGroupsRequest)), ForumUserForumGroupsResponse.class);
    }
    
    private API getAPI() {
        return api;
    }
}