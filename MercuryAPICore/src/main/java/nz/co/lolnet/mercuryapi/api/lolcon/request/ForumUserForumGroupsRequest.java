/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.mercuryapi.api.lolcon.request;

/**
 *
 * @author James
 */
public class ForumUserForumGroupsRequest {
    private final int userForumID;

    public ForumUserForumGroupsRequest(int userForumID) {
        this.userForumID = userForumID;
    }

    public int getUserForumID() {
        return userForumID;
    }
    
}
