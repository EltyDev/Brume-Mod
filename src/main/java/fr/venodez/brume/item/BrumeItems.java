package fr.venodez.brume.item;

import fr.venodez.brume.Brume;
import fr.venodez.brume.fluid.BrumeFluids;
import fr.venodez.brume.itemgroup.BrumeItemGroup;
import net.minecraft.item.BucketItem;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BrumeItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Brume.ID);

    public static final RegistryObject<Item> PURIFIED_WATER_BUCKET = ITEMS.register("purified_water_bucket",
            () -> new BucketItem(() -> BrumeFluids.PURIFIED_WATER_FLUID.get(),
                    new Item.Properties().maxStackSize(16).group(BrumeItemGroup.BRUME_GROUP)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

//TODO Extend la bottle de purified water et ajouter un nbt_tag avec "public void AddInformation"


}
