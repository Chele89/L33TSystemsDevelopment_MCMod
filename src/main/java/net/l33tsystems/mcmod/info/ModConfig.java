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
		config.addCustomCategoryComment("Version 1.0.0", "All items in this section pretain to only to items added to the mod during Version 1.0.0");
		if(config.get("Version 1.0.0", "enableVersion_1.0.0", true).getBoolean()) { Version100.config(config); } 
		else { Version100.disable(config); }
	}
	
}
