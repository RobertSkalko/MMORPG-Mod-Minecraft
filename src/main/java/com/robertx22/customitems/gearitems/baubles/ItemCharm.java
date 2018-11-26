package com.robertx22.customitems.gearitems.baubles;

import java.util.HashMap;

import com.robertx22.customitems.gearitems.bases.BaseBaublesItem;
import com.robertx22.db_lists.Rarities;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import baubles.api.BaubleType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ItemCharm extends BaseBaublesItem {
	public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

	public ItemCharm(int i, HashMap<Integer, Item> map) {
		super(i, map);

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		Rarities.Items.forEach((x) -> Items.put(x.Rank(), new ItemCharm(x.Rank(), Items)));
		Items.values().forEach((x) -> event.getRegistry().register(x));
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		Items.values().forEach((x) -> RegisterUtils.registerRender(x));
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.CHARM;
	}

	@Override
	public String Name() {
		return "Charm";
	}

}
