package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.ListStringData;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.CompoundNBT;

public class ListStringSaving {
    private static final String LOC = Ref.MODID + ":list_string";

    public static ListStringData Load(CompoundNBT nbt) {
        return LoadSave.Load(ListStringData.class, new ListStringData(), nbt, LOC);

    }

    public static void Save(CompoundNBT nbt, ListStringData data) {

        if (nbt == null) {
            return;
        }

        if (data != null) {
            LoadSave.Save(data, nbt, LOC);
        }

    }

}
