package fr.venodez.brume.fluid;

import fr.venodez.brume.Brume;
import fr.venodez.brume.block.BrumeBlocks;
import fr.venodez.brume.block.PurifiedWaterBlock;
import fr.venodez.brume.item.BrumeItems;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BrumeFluids {

    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Brume.ID);

    public static final RegistryObject<FlowingFluid> PURIFIED_WATER_FLUID = FLUIDS.register("purified_water_fluid", () -> new ForgeFlowingFluid.Source(BrumeFluids.PURIFIED_WATER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> PURIFIED_WATER_FLOWING = FLUIDS.register("purified_water_flowing", () -> new ForgeFlowingFluid.Flowing(BrumeFluids.PURIFIED_WATER_PROPERTIES));

    public static final ForgeFlowingFluid.Properties PURIFIED_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> PURIFIED_WATER_FLUID.get(), () -> PURIFIED_WATER_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
            .density(15).luminosity(2).viscosity(100).sound(SoundEvents.ITEM_BUCKET_FILL).overlay(WATER_OVERLAY_RL)
            .color(0x4bdae9ff)).slopeFindDistance(4).levelDecreasePerBlock(2)
            .block(() -> BrumeFluids.PURIFIED_WATER_BLOCK.get())
            .bucket(() -> BrumeItems.PURIFIED_WATER_BUCKET.get());

    public static final RegistryObject<FlowingFluidBlock>  PURIFIED_WATER_BLOCK = BrumeBlocks.BLOCKS.register("purified_water",
            () -> new PurifiedWaterBlock());

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
