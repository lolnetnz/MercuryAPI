/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.mercuryapi.api.lolcon.response;

import com.google.gson.JsonArray;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class ForumGroupsResponse {

    private JsonArray forumGroups;

    public List<String> getForumGroups() {
        List<String> forumGroupsList = new ArrayList<>();
        for (int i = 0; i < this.forumGroups.size(); i++) {
            forumGroupsList.add(this.forumGroups.get(i).getAsString());
            
        }
        return forumGroupsList;
    }
}
