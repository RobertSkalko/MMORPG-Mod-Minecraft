package com.robertx22.database.sets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.database.gearitemslots.Charm;
import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.stats.mods.percent.EnergyRegenPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Set;
import com.robertx22.stats.StatMod;

public class Limitless extends Set {

	@Override
	public String Name() {
		return "Limitless";
	}

	@Override
	public HashMap<Integer, StatMod> AllMods() {

		return new HashMap<Integer, StatMod>() {
			{
				{
					put(2, new EnergyRegenPercent());

				}
			}
		};
	}

	@Override
	public List<GearItemSlot> GearTypes() {
		return Arrays.asList(new Ring(), new Charm());
	}

}