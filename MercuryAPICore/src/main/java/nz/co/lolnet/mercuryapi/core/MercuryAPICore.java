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

import nz.co.lolnet.mercuryapi.core.api.forum.Forum;
import nz.co.lolnet.mercuryapi.core.api.lolcon.LolCon;
import nz.co.lolnet.mercuryapi.core.entries.Account;

public class MercuryAPICore {
	
	private Account account;
	
	public Forum getForum() {
		return (Forum) new Forum().setAccount(getAccount());
	}
	
	public LolCon getLolCon() {
		return (LolCon) new LolCon().setAccount(getAccount());
	}
	
	protected Account getAccount() throws IllegalArgumentException {
		if (account != null) {
			return account;
		}
		throw new IllegalArgumentException("Account is null!");
	}
	
	public MercuryAPICore setAccount(Account account) {
		this.account = account;
		return this;
	}
	
	public MercuryAPICore setAccount(String uniqueId, String token) {
		this.account = new Account(uniqueId, token);
		return this;
	}
}