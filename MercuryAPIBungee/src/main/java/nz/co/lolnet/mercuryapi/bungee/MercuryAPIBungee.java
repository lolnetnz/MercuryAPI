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

package nz.co.lolnet.mercuryapi.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import nz.co.lolnet.mercuryapi.bungee.config.Config;
import nz.co.lolnet.mercuryapi.bungee.util.Reference;
import nz.co.lolnet.mercuryapi.core.MercuryAPICore;

public class MercuryAPIBungee extends Plugin {
	
	private static MercuryAPIBungee instance;
	private Config config;
	private MercuryAPICore mercuryAPICore;
	
	public MercuryAPIBungee() {
		instance = this;
	}
	
	@Override
	public void onEnable() {
		config = new Config();
		config.loadConfig();
		mercuryAPICore = new MercuryAPICore();
		getMercuryAPICore().setAccount(getConfiguration().getString("MercuryAPIBungee.UniqueId"), getConfiguration().getString("MercuryAPIBungee.Token"));
		getLogger().info(Reference.PLUGIN_NAME + " v" + Reference.VERSION + " loaded.");
	}
	
	@Override
	public void onDisable() {
		mercuryAPICore = null;
		config = null;
		getLogger().info(Reference.PLUGIN_NAME + " v" + Reference.VERSION + " unloaded.");
	}
	
	public static MercuryAPIBungee getInstance() {
		return instance;
	}
	
	private Configuration getConfiguration() {
		return config.getConfiguration();
	}
	
	public MercuryAPICore getMercuryAPICore() {
		return mercuryAPICore;
	}
}