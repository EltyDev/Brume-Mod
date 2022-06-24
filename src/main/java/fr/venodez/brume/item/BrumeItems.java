package fr.venodez.brume.item;

import fr.venodez.brume.Brume;
import fr.venodez.brume.armor.NagaArmorItem;
import fr.venodez.brume.fluid.BrumeFluids;
import fr.venodez.brume.itemgroup.BrumeItemGroup;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import twilightforest.enums.TwilightArmorMaterial;

public class BrumeItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Brume.ID);

    public static final RegistryObject<Item> PURIFIED_WATER_BUCKET = ITEMS.register("purified_water_bucket",
            () -> new BucketItem(() -> BrumeFluids.PURIFIED_WATER_FLUID.get(),
                    new Item.Properties().maxStackSize(1).group(BrumeItemGroup.BRUME_GROUP)));

    public static final RegistryObject<Item> NAGA_SCALE_HELMET = ITEMS.register("naga_helmet",
            () -> new NagaArmorItem(TwilightArmorMaterial.ARMOR_NAGA, EquipmentSlotType.HEAD, new Item.Properties().maxStackSize(1).group(BrumeItemGroup.BRUME_GROUP))); //<-- Changer le groupe apres

    public static final RegistryObject<Item> NAGA_SCALE_BOOT = ITEMS.register("naga_boots",
            () -> new NagaArmorItem(TwilightArmorMaterial.ARMOR_NAGA, EquipmentSlotType.FEET, new Item.Properties().maxStackSize(1).group(BrumeItemGroup.BRUME_GROUP)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
