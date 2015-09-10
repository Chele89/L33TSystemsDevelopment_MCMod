package net.l33tsystems.mcmod.versions;

import java.util.ArrayList;

import net.l33tsystems.mcmod.blocks.CompressedBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Version100 {

	private static boolean isEnabled;
	private static int maxCompressionTier;
	private static ArrayList<Block> blockBases = new ArrayList<Block>();
	private static ArrayList<Block> blocks = new ArrayList<Block>();
	
	public static void config(Configuration config) {
		isEnabled = true;
		
		setMaxCompressionTier(config);
		if (maxCompressionTier < 1) { maxCompressionTier = 1; }
		String blockNames = getBlockNames(config);
		if (blockNames.length() < 10) { blockNames = "minecraft:sand, minecraft:dirt, minecraft:cobblestone, minecraft:sandstone"; }
		setBlockList(blockNames.split(", "));
	}
	
	public static void disable(Configuration config) {
		isEnabled = false;
		
		setMaxCompressionTier(config);
		if (maxCompressionTier > 0) { maxCompressionTier = 0; }
		getBlockNames(config);
		
	}
	
	private static void setBlockList(String[] names) {
		for(int i = 0; i < names.length; i++) {
			blockBases.add(Block.getBlockFromName(names[i]));
		}
	}
	
	private static void setMaxCompressionTier(Configuration config) {
		maxCompressionTier = config.getInt("maxCompressionTier", "Version 1.0.0", 4, 1, 8, "Number of tiers of compression available.");
	}
	
	private static String getBlockNames(Configuration config) {
		return config.getString("baseBlockList", "Version 1.0.0", "minecraft:sand, minecraft:dirt, minecraft:cobblestone, minecraft:stone", 
			"This should be a list of names of blocks in the VANILLA minecraft game seperated by commas.  I will add functionality later for adding other mod's blocks.");
	}
	
	public static void init() {
		// Create & Register Blocks & Items
		int count = -1;
		for (int i = 0; i < blockBases.size(); i++) {
			// Create First Compression Level
			String name = "Compressed " + blockBases.get(i).getLocalizedName();
			String unName = "xCompressed" + blockBases.get(i).getLocalizedName();
			blocks.add(new CompressedBlock(blockBases.get(i), 1 + unName, 1 + "x " + name)); count += 1;
			addBlock(name, 1, blocks.get(count), blockBases.get(i));
			for (int t = 2; t < maxCompressionTier; t++) {
				// Create Incremental Compression Levels
				blocks.add(new CompressedBlock(blocks.get(count), i + unName, i + "x " + name)); count += 1;
				addBlock(name, t, blocks.get(count), blocks.get(count-1));
			}
		}
	}
	
	private static void addBlock(String name, int increment, Block output, Block input) {
		GameRegistry.registerBlock(output, increment + "x " + name);
		GameRegistry.addRecipe(new ItemStack(output), new Object[] {"BBB", "BBB", "BBB", 'B', input});
	}
	
	public static boolean isEnabled() { return isEnabled; }
}
