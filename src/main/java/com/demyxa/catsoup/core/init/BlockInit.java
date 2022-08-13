package com.demyxa.catsoup.core.init;

import com.demyxa.catsoup.CatSoup;

import com.demyxa.catsoup.common.block.CatBedBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			CatSoup.MOD_ID);

	public static final RegistryObject<Block> CATBLOCK = BLOCKS.register("catblock",
			() -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_GREEN)
					.strength(5f, 6f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE)));

	public static final RegistryObject<Block> FELINUM_STORAGE = BLOCKS.register("felinum_storage",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW)
					.harvestTool(ToolType.PICKAXE).strength(5f, 6f).harvestLevel(2).sound(SoundType.METAL)));
	
	public static final RegistryObject<Block> CATNIP_FLOWER = BLOCKS.register("catnip_flower",
			() -> new TallFlowerBlock(AbstractBlock.Properties.copy(Blocks.SUNFLOWER)));


	public static final RegistryObject<Block> CAT_BED_BLOCK = BLOCKS.register("cat_bed",
			() -> new CatBedBlock());
}
