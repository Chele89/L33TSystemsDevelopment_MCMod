package net.l33tsystems.mcmod.blocks;

import net.l33tsystems.mcmod.info.Information;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class CompressedBlock extends Block {

	private Block baseBlock;
	private String name;
	
	public CompressedBlock(Block block, String n, String ln) {
		super(block.getMaterial());
		this.setHardness(0.5F).setStepSound(Block.soundTypeGravel).setCreativeTab(CreativeTabs.tabBlock);
		this.setUnlocalizedName(Information.MODID + "_" + n);
		this.name = ln;
	}
	
	public String getName() { return name; }

}
