package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.data_packs.compatible_items.CompatibleItem;
import com.robertx22.mine_and_slash.registry.FilterListWrap;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnContainerCompatibleItem {

    @SubscribeEvent
    public static void onContainerCompatibleItem(PlayerContainerEvent event) {

        try {

            if (ModConfig.INSTANCE.Server.USE_COMPATIBILITY_ITEMS.get() == false) {
                return;
            }
            if (event.getPlayer().world.isRemote) {
                return;
            }

            UnitData data = null;

            for (ItemStack stack : event.getPlayer().inventory.mainInventory) {

                if (stack.isEmpty()) {
                    continue;
                }

                // fast check for every item
                if (Gear.has(stack) == false) {

                    String reg = stack.getItem()
                        .getRegistryName()
                        .toString();

                    FilterListWrap<CompatibleItem> matchingItems = SlashRegistry.CompatibleItems()
                        .getFilterWrapped(x -> x.item_id.equals(reg.toString()) && !x.only_add_stats_if_loot_drop);

                    if (!matchingItems.list.isEmpty()) {

                        CompatibleItem config = matchingItems.random();

                        if (data == null) {
                            data = Load.Unit(event.getPlayer());
                        }

                        // slow check to make absolutely sure it doesnt have stats
                        GearItemData gear = Gear.Load(stack);
                        if (gear == null) {
                            stack = config.create(stack, data.getLevel());
                            event.getPlayer().inventory.markDirty();
                        }

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
