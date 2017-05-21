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

package nz.co.lolnet.mercuryapi.api.forum.response;

import java.util.ArrayList;
import java.util.List;

public class ForumGroupsResponse {
    
    private String forumGroups;
    
    public List<Integer> getForumGroupsList() {
        List<Integer> forumGroupsList = new ArrayList<>();
        String[] split = this.forumGroups.split("~");
        for (int i = 0; i < split.length; i++) {
            forumGroupsList.add(Integer.parseInt(split[i]));
            
        }
        return forumGroupsList;
    }
    
    public String getForumGroups() {
        return forumGroups;
    }
}