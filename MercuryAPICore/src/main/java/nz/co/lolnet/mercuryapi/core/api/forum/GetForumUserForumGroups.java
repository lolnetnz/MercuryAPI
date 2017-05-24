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

package nz.co.lolnet.mercuryapi.core.api.forum;

import java.util.List;

import com.google.gson.annotations.Expose;

import nz.co.lolnet.mercuryapi.core.api.API;
import nz.co.lolnet.mercuryapi.core.entries.Account;

public class GetForumUserForumGroups extends API {
	
	@Expose
	private int userForumId;
	private List<Integer> forumGroups;
	
	public GetForumUserForumGroups(Account account) {
		super(account);
	}
	
	@Override
	public GetForumUserForumGroups execute() {
		return (GetForumUserForumGroups) super.get();
	}
	
	@Override
	public String getEndpoint() {
		return "forum/getforumuserforumgroups";
	}
	
	public int getUserForumId() {
		return userForumId;
	}
	
	public GetForumUserForumGroups setUserForumId(int userForumId) {
		this.userForumId = userForumId;
		return this;
	}
	
	public List<Integer> getForumGroups() {
		return forumGroups;
	}
}