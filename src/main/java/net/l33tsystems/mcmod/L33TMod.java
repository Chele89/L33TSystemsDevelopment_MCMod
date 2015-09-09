package net.l33tsystems.mcmod;

import net.l33tsystems.mcmod.info.Information;
import net.l33tsystems.mcmod.info.ModConfig;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Information.MODID, version=Information.VERSION)
public class L33TMod {

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// Create & Register Blocks, Event Handler, Config, Etc.
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		ModConfig.init(config);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		// Register Recipes, Rendering, Etc.
	}
}