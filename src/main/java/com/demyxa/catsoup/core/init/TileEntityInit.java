package com.demyxa.catsoup.core.init;

import com.demyxa.catsoup.CatSoup;
import com.demyxa.catsoup.common.TE.CatBedTileEntity;
import com.demyxa.catsoup.common.block.CatBedBlock;
import net.minecraft.tileentity.BedTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, CatSoup.MOD_ID);

    public static RegistryObject<TileEntityType<CatBedTileEntity>> CAT_BED_TILE_ENTITY = TILE_ENTITIES.register("cat_bed_tile_entity",
            () -> TileEntityType.Builder.of(CatBedTileEntity::new, BlockInit.CAT_BED_BLOCK.get()).build(null));
}
