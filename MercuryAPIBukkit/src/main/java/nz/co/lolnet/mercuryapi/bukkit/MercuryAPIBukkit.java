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

package nz.co.lolnet.mercuryapi.bukkit;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import nz.co.lolnet.mercuryapi.bukkit.config.Config;
import nz.co.lolnet.mercuryapi.bukkit.util.Reference;
import nz.co.lolnet.mercuryapi.core.MercuryAPICore;

public class MercuryAPIBukkit extends JavaPlugin {
	
	private static MercuryAPIBukkit instance;
	private Config config;
	private MercuryAPICore mercuryAPICore;
	
	public MercuryAPIBukkit() {
		instance = this;
	}
	
	@Override
	public void onEnable() {
		config = new Config();
		config.loadConfig();
		mercuryAPICore = new MercuryAPICore();
		getMercuryAPICore().setAccount(getConfiguration().getString("MercuryAPIBukkit.UniqueId"), getConfiguration().getString("MercuryAPIBukkit.Token"));
		getLogger().info(Reference.PLUGIN_NAME + " v" + Reference.VERSION + " loaded.");
	}
	
	@Override
	public void onDisable() {
		mercuryAPICore = null;
		config = null;
		getLogger().info(Reference.PLUGIN_NAME + " v" + Reference.VERSION + " unloaded.");
	}
	
	public static MercuryAPIBukkit getInstance() {
		return instance;
	}
	
	private Config getMercuryConfig() {
		return config;
	}
	
	public YamlConfiguration getConfiguration() {
		if (getMercuryConfig() != null) {
			return getMercuryConfig().getConfiguration();
		}
		return null;
	}
	
	public MercuryAPICore getMercuryAPICore() {
		return mercuryAPICore;
	}
}