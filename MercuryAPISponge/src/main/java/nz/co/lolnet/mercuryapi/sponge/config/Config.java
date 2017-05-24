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

package nz.co.lolnet.mercuryapi.sponge.config;

import java.io.IOException;

import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import nz.co.lolnet.mercuryapi.sponge.MercuryAPISponge;

public class Config {
	
	private ConfigurationLoader<CommentedConfigurationNode> configurationLoader;
	private ConfigurationOptions configurationOptions;
	private CommentedConfigurationNode configurationNode;
	
	public void loadConfig() {
		try {
			configurationLoader = HoconConfigurationLoader.builder().setPath(MercuryAPISponge.getInstance().getPath()).build();
			configurationOptions = ConfigurationOptions.defaults().setShouldCopyDefaults(true);
			configurationNode = getConfigurationLoader().load(getConfigurationOptions());
			MercuryAPISponge.getInstance().getLogger().info("Successfully loaded configuration file.");
		} catch (IOException ex) {
			configurationNode = getConfigurationLoader().createEmptyNode(getConfigurationOptions());
			MercuryAPISponge.getInstance().getLogger().error("Encountered an error processing 'loadConfig' in '" + getClass().getSimpleName() + "' - " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public ConfigurationLoader<CommentedConfigurationNode> getConfigurationLoader() {
		return configurationLoader;
	}
	
	public ConfigurationOptions getConfigurationOptions() {
		return configurationOptions;
	}
	
	public CommentedConfigurationNode getConfigurationNode() {
		return configurationNode;
	}
}