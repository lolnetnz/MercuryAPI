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

package nz.co.lolnet.mercuryapi.sponge;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameLoadCompleteEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import nz.co.lolnet.mercuryapi.core.MercuryAPICore;
import nz.co.lolnet.mercuryapi.sponge.config.Config;
import nz.co.lolnet.mercuryapi.sponge.util.Reference;

@Plugin(
	id = Reference.PLUGIN_ID,
	name = Reference.PLUGIN_NAME,
	version = Reference.VERSION,
	description = "MercuryAPI",
	authors = {"LolnetNZ"},
	url = "https://www.lolnet.co.nz"
)
public class MercuryAPISponge {
	
	private static MercuryAPISponge instance;
	
	@Inject
	private Logger logger;
	
	@Inject
	@DefaultConfig(sharedRoot = true)
	private Path path;
	
	private Config config;
	private MercuryAPICore mercuryAPICore;
	
	public MercuryAPISponge() {
		instance = this;
	}
	
	@Listener
	public void onStart(GameLoadCompleteEvent event) {
		config = new Config();
		config.loadConfig();
		mercuryAPICore = new MercuryAPICore();
		getMercuryAPICore().setAccount(getConfigurationNode().getNode("general", "uniqueId").getString(), getConfigurationNode().getNode("general", "token").getString());
		getLogger().info(Reference.PLUGIN_NAME + " v" + Reference.VERSION + " loaded.");
	}
	
	public static MercuryAPISponge getInstance() {
		return instance;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public Path getPath() {
		return path;
	}
	
	public Config getMercuryConfig() {
		return config;
	}
	
	public CommentedConfigurationNode getConfigurationNode() {
		if (getMercuryConfig() != null) {
			return getMercuryConfig().getConfigurationNode();
		}
		return null;
	}
	
	public MercuryAPICore getMercuryAPICore() {
		return mercuryAPICore;
	}
}