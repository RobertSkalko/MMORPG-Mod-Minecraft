package com.robertx22.generation;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.db_lists.Rarities;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.PrefixData;
import com.robertx22.saveclasses.gearitem.PrimaryStatsData;
import com.robertx22.saveclasses.gearitem.SecondaryStatsData;
import com.robertx22.saveclasses.gearitem.SuffixData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.item.ItemStack;

public class GearGen {

	public static GearItemData CreateData(GearBlueprint blueprint) {
		GearItemSlot gearslot = blueprint.GetGearType();

		ItemRarity rarity = Rarities.Items.get(blueprint.GetRarity());

		GearItemData data = new GearItemData();

		data.level = blueprint.GetLevel();
		data.gearTypeName = gearslot.Name();
		data.Rarity = rarity.Rank();
		data.name = gearslot.Name();

		data.primaryStats = new PrimaryStatsData();
		data.primaryStats.RerollFully(data);

		data.secondaryStats = new SecondaryStatsData();
		data.secondaryStats.RerollFully(data);

		if (RandomUtils.roll(rarity.AffixChance())) {

			data.suffix = new SuffixData();
			data.suffix.RerollFully(data);

		}
		if (RandomUtils.roll(rarity.AffixChance())) {

			data.prefix = new PrefixData();
			data.prefix.RerollFully(data);

		}

		data.set = blueprint.GenerateSet();

		return data;
	}

	public static ItemStack CreateStack(GearBlueprint schema) {

		GearItemData data = CreateData(schema);

		ItemStack stack = GearTypes.All.get(data.gearTypeName).GetItemForRarity(data.GetRarity().Rank());

		Gear.Save(stack, data);

		stack.setStackDisplayName(data.GetDisplayName());

		return stack;

	}

	public static ItemStack CreateStack(GearItemData data) {

		ItemStack stack = GearTypes.All.get(data.gearTypeName).GetItemForRarity(data.GetRarity().Rank());

		Gear.Save(stack, data);

		stack.setStackDisplayName(data.GetDisplayName());

		return stack;

	}

}
