/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.mercuryapi.api.lolcon.response;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class ForumUserForumGroupsResponse {
    
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
