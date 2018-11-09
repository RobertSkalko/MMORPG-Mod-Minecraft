package com.robertx22.onevent.Item;

import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.GearSaving;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class OnTooltip {

	@SubscribeEvent
	public static void onTooltip(ItemTooltipEvent event) {

		if (event.getEntityPlayer() == null || event.getEntityPlayer().world == null
				|| !event.getEntityPlayer().world.isRemote) {
			return;
		}

		ItemStack item;

		item = event.getItemStack();

		if (item == null) {
			return;
		}
		if (!item.hasTagCompound()) {
			return;
		}

		if (GuiScreen.isCtrlKeyDown() == false) {
			GearItemData data = GearSaving.Load(item);

			Unit unit = UnitSaving.Load(event.getEntityPlayer());

			if (data != null && unit != null) {
				data.BuildTooltip(event, unit);

				if (GuiScreen.isShiftKeyDown() == false) {

					event.getToolTip().add("Press shift for more info");
				}
			}
		}

	}

}
