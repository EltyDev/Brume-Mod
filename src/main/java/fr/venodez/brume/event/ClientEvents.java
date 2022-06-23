package fr.venodez.brume.event;

import fr.venodez.brume.block.PurifiedWaterBlock;
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
import toughasnails.api.item.TANItems;
import toughasnails.item.EmptyCanteenItem;

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
                    int value = blockState.get(PurifiedWaterBlock.QUANTITY) - 1;
                    if (value <= 0)
                        player.world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 11);
                    else
                        player.world.setBlockState(blockPos, blockState.with(PurifiedWaterBlock.QUANTITY, value), 2);
                }
                event.setCanceled(true);
                event.setCancellationResult(ActionResultType.SUCCESS);
            }
        }
        else if(item instanceof EmptyCanteenItem) {
               if(raytraceresult.getType() == RayTraceResult.Type.BLOCK && player.world.getFluidState(blockPos).isTagged(BrumeTags.Fluid.PURIFIED_WATER))
               {
                  if(!player.isCreative())
                  {
                      player.world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                      player.addStat(Stats.ITEM_USED.get(item));

                      player.setHeldItem(Hand.MAIN_HAND, TANItems.PURIFIED_WATER_CANTEEN.getDefaultInstance());
                      BlockState blockState = player.world.getBlockState(blockPos);
                      int value = blockState.get(PurifiedWaterBlock.QUANTITY) - 2;

                      if(value <= 0)
                          player.world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 11);
                      else
                          player.world.setBlockState(blockPos, blockState.with(PurifiedWaterBlock.QUANTITY, value), 2);
                  }
                  event.setCanceled(true);
                  event.setCancellationResult(ActionResultType.SUCCESS);
               }
        }
        else {
            if (player.world.getBlockState(blockPos).getBlock().getRegistryName().equals(new ResourceLocation("thermal", "machine_bottler"))) {
                // ???
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
            if (blockState.get(PurifiedWaterBlock.QUANTITY) < 2) {
                player.setHeldItem(Hand.MAIN_HAND, Items.BUCKET.getDefaultInstance());
                event.setCanceled(true);
                event.setCancellationResult(ActionResultType.FAIL);
            }
            else {
                player.world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                if(itemStack.getCount() > 1)
                {
                    player.inventory.addItemStackToInventory(BrumeItems.PURIFIED_WATER_BUCKET.get().getDefaultInstance());
                }
                else
                {
                    player.setHeldItem(Hand.MAIN_HAND, BrumeItems.PURIFIED_WATER_BUCKET.get().getDefaultInstance());
                }
                player.world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 11);
                event.setCanceled(true);
                event.setCancellationResult(ActionResultType.SUCCESS);
            }
        }

    }

}
