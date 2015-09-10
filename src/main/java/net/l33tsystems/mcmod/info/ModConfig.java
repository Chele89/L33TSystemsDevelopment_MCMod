package net.l33tsystems.mcmod.info;

import net.l33tsystems.mcmod.versions.Version100;
import net.minecraftforge.common.config.Configuration;

public class ModConfig {

	private static Configuration config;
	
	public static void init(Configuration c) {
		config = c;
		config.load();
		
		versionConfig();
		config.setCategoryRequiresWorldRestart(config.CATEGORY_GENERAL, true);
		
		config.save();
	}
	
	private static void versionConfig() {
		if(config.getBoolean("Version 1.0.0", "enableVersion_1.0.0", true, "If this is disabled, no components for the Version 1.0.0 release will be included in the game.  Default:  true")) { Version100.config(config); } 
		else { Version100.disable(config); }
	}
	
}
