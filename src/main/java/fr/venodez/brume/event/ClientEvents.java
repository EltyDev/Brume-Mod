package fr.venodez.brume.event;

import cofh.lib.fluid.SimpleTankInv;
import cofh.thermal.expansion.tileentity.machine.MachineBottlerTile;
import fr.venodez.brume.block.PurifiedWaterBlock;
import fr.venodez.brume.fluid.BrumeFluids;
import fr.venodez.brume.item.BrumeItems;
import fr.venodez.brume.tag.BrumeTags;
import fr.venodez.brume.utils.MathUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import toughasnails.api.item.TANItems;
import toughasnails.core.ToughAsNails;
import toughasnails.item.PurifiedWaterBottleItem;

public class ClientEvents {

    @SubscribeEvent
    public static void onBottleUsed(PlayerInteractEvent event) {
        ItemStack itemStack = event.getItemStack();
        Item item = itemStack.getItem();
        PlayerEntity player = event.getPlayer();
        RayTraceResult raytraceresult = MathUtils.rayTrace(player.world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
        BlockPos blockPos = ((BlockRayTraceResult)raytraceresult).getPos();

        if (item instanceof GlassBottleItem) {
            if (raytraceresult.getType() == RayTraceResult.Type.BLOCK && player.world.getFluidState(blockPos).isTagged(BrumeTags.Fluid.PURIFIED_WATER)) {
                player.world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                if (!player.isCreative()) {
                    player.addStat(Stats.ITEM_USED.get(item));
                    if (itemStack.getCount() > 1)
                        DrinkHelper.fill(itemStack, player, TANItems.PURIFIED_WATER_BOTTLE.getDefaultInstance());
                    else
                        player.setHeldItem(Hand.MAIN_HAND, TANItems.PURIFIED_WATER_BOTTLE.getDefaultInstance());
                    BlockState blockState = player.world.getBlockState(blockPos);
                    int value = blockState.get(PurifiedWaterBlock.QUANTITY) - 250;
                        if (value <= 0)
                            player.world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 11);
                        else
                            player.world.setBlockState(blockPos, blockState.with(PurifiedWaterBlock.QUANTITY, value), 2);
                }
                event.setCanceled(true);
                event.setCancellationResult(ActionResultType.SUCCESS);
            }
        }
        else if (item instanceof PurifiedWaterBottleItem){
            if (player.world.getBlockState(blockPos).getBlock().getRegistryName().equals(new ResourceLocation("thermal", "machine_bottler"))) {
                MachineBottlerTile tile = (MachineBottlerTile) player.world.getTileEntity(blockPos);
                SimpleTankInv tankInv = tile.getTankInv();
                FluidStack fluidStack = tankInv.get(0);
                if (tankInv.getTankCapacity(0) >= fluidStack.getAmount() + 250) {
                    if (!fluidStack.isEmpty()) {
                        if (fluidStack.getFluid().getDefaultState().isTagged(BrumeTags.Fluid.PURIFIED_WATER)) {
                            if (!player.isCreative())
                                player.setHeldItem(Hand.MAIN_HAND, Items.GLASS_BOTTLE.getDefaultInstance());
                            player.world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                            tankInv.set(0, new FluidStack(BrumeFluids.PURIFIED_WATER_FLUID.get(), fluidStack.getAmount() + 250));
                            event.setCanceled(true);
                            event.setCancellationResult(ActionResultType.SUCCESS);

                        }
                    } else {
                        if (!player.isCreative())
                            player.setHeldItem(Hand.MAIN_HAND, Items.GLASS_BOTTLE.getDefaultInstance());
                        player.world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        tankInv.set(0, new FluidStack(BrumeFluids.PURIFIED_WATER_FLUID.get(), 250));
                        event.setCanceled(true);
                        event.setCancellationResult(ActionResultType.SUCCESS);
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void onCanteenUsed(PlayerInteractEvent event) {
        ItemStack itemStack = event.getItemStack();
        Item item = itemStack.getItem();
        PlayerEntity player = event.getPlayer();
        RayTraceResult raytraceresult = MathUtils.rayTrace(player.world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
        BlockPos blockPos = ((BlockRayTraceResult)raytraceresult).getPos();

        if (item.getRegistryName().equals(new ResourceLocation(ToughAsNails.MOD_ID, "purified_water_canteen"))){
            if (player.world.getBlockState(blockPos).getBlock().getRegistryName().equals(new ResourceLocation("thermal", "machine_bottler"))) {
                MachineBottlerTile tile = (MachineBottlerTile) player.world.getTileEntity(blockPos);
                SimpleTankInv tankInv = tile.getTankInv();
                FluidStack fluidStack = tankInv.get(0);
                if (tankInv.getTankCapacity(0) >= fluidStack.getAmount() + 1250) {
                    if (!fluidStack.isEmpty()) {
                        if (fluidStack.getFluid().getDefaultState().isTagged(BrumeTags.Fluid.PURIFIED_WATER)) {
                            if (!player.isCreative())
                                player.setHeldItem(Hand.MAIN_HAND, TANItems.EMPTY_CANTEEN.getDefaultInstance());
                            player.world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                            tankInv.set(0, new FluidStack(BrumeFluids.PURIFIED_WATER_FLUID.get(), fluidStack.getAmount() + 1250));
                            event.setCanceled(true);
                            event.setCancellationResult(ActionResultType.SUCCESS);

                        }
                    } else {
                        if (!player.isCreative())
                            player.setHeldItem(Hand.MAIN_HAND, TANItems.EMPTY_CANTEEN.getDefaultInstance());
                        player.world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        tankInv.set(0, new FluidStack(BrumeFluids.PURIFIED_WATER_FLUID.get(), 1250));
                        event.setCanceled(true);
                        event.setCancellationResult(ActionResultType.SUCCESS);
                    }
                }
            }
        } else if (item.getRegistryName().equals(new ResourceLocation(ToughAsNails.MOD_ID, "empty_canteen"))){
            if (player.world.getBlockState(blockPos).getBlock().getRegistryName().equals(new ResourceLocation("thermal", "machine_bottler"))) {
                MachineBottlerTile tile = (MachineBottlerTile) player.world.getTileEntity(blockPos);
                SimpleTankInv tankInv = tile.getTankInv();
                FluidStack fluidStack = tankInv.get(0);
                if (fluidStack.getAmount() >= 1250) {
                    if (!fluidStack.isEmpty()) {
                        if (fluidStack.getFluid().getDefaultState().isTagged(BrumeTags.Fluid.PURIFIED_WATER)) {
                            if (!player.isCreative())
                                player.setHeldItem(Hand.MAIN_HAND, TANItems.PURIFIED_WATER_CANTEEN.getDefaultInstance());
                            player.world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                            tankInv.set(0, new FluidStack(BrumeFluids.PURIFIED_WATER_FLUID.get(), fluidStack.getAmount() - 1250));
                            event.setCanceled(true);
                            event.setCancellationResult(ActionResultType.SUCCESS);

                        }
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void onBucketUsed(PlayerInteractEvent event) {
        ItemStack itemStack = event.getItemStack();
        Item item = itemStack.getItem();
        PlayerEntity player = event.getPlayer();
        RayTraceResult raytraceresult = MathUtils.rayTrace(player.world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
        BlockPos blockPos = ((BlockRayTraceResult)raytraceresult).getPos();

        if (item instanceof BucketItem && raytraceresult.getType() == RayTraceResult.Type.BLOCK && player.world.getFluidState(blockPos).isTagged(BrumeTags.Fluid.PURIFIED_WATER)) {
            BlockState blockState = player.world.getBlockState(blockPos);
            if (blockState.get(PurifiedWaterBlock.QUANTITY) < 4) {
                player.setHeldItem(Hand.MAIN_HAND, Items.BUCKET.getDefaultInstance());
                event.setCanceled(true);
                event.setCancellationResult(ActionResultType.FAIL);
            }
            else {
                if(itemStack.getCount() > 1) {
                    player.inventory.addItemStackToInventory(BrumeItems.PURIFIED_WATER_BUCKET.get().getDefaultInstance());
                } else {
                    player.setHeldItem(Hand.MAIN_HAND, BrumeItems.PURIFIED_WATER_BUCKET.get().getDefaultInstance());
                }
                player.world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 11);
                event.setCanceled(true);
                event.setCancellationResult(ActionResultType.SUCCESS);
            }
        }

    }

}
