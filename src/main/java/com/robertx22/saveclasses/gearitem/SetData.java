package com.robertx22.saveclasses.gearitem;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.lists.Sets;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.gearitem.gear_bases.Set;
import com.robertx22.stats.StatMod;

public class SetData {

	public String baseSet;

	public Set GetSet() {

		return Sets.All.get(baseSet);
	}

	public List<StatModData> GetAllStats(int level, Unit unit) {

		Set base = GetSet();

		List<StatModData> list = new ArrayList<StatModData>();

		for (int i = 0; i < base.GetObtainedMods(unit).size(); i++) {

			StatMod mod = base.GetObtainedMods(unit).get(i);

			list.add(StatModData.Load(mod, 100));
		}

		return list;

	}

}
