package com.robertx22.mine_and_slash.registry;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;

import java.util.List;

public interface ISlashRegistryEntry<C> extends IGUID, IWeighted, ITiered, IRarity {

    SlashRegistryType getSlashRegistryType();

    // this could be used for stuff like setdata, affixdata etc?  prototype
    default C getFromRegistry() {
        return (C) SlashRegistry.get(getSlashRegistryType(), this.GUID());
    }

    default void registerToSlashRegistry() {
        SlashRegistry.getRegistry(getSlashRegistryType())
            .register(this);
    }

    default void unregisterFromSlashRegistry() {
        SlashRegistry.getRegistry(getSlashRegistryType())
            .unRegister(this);
    }

    default boolean isUnique() {
        return getRarityRank() == IRarity.Unique;
    }

    default void unregisterDueToInvalidity() {
        SlashRegistry.getRegistry(getSlashRegistryType())
            .unRegister(this);
        try {
            throw new Exception("Registry Entry: " + GUID() + " is invalid! Unregistering");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default boolean isFromDatapack() {
        return false;
    }

    // true for configs cus they sync from server to client, so unregister on loadFromJsons, then registerForgeConfigs
    default boolean unregisterBeforeConfigsLoad() {
        return false;
    }

    @Override
    default int Weight() {
        return getRarity().Weight();
    }

    default boolean isRegistryEntryValid() {
        // override with an implementation of a validity test
        return true;
    }

    default boolean checkStatModsValidity(List<StatMod> mods) {
        if (mods
            .stream()
            .anyMatch(x -> {
                if (!SlashRegistry.StatMods()
                    .isRegistered(x.GUID())) {
                    System.out.println(x.GUID() + " is not a valid StatMod.");
                    return true;
                } else {
                    return false;
                }
            })) {
            return false;
        }
        return true;
    }

    @Override
    default int getRarityRank() {
        return 0;
    }

    @Override
    default Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());
    }

    @Override
    default int Tier() {
        return 0;
    }

    default String getInvalidGuidMessage() {
        return "Non [a-z0-9_.-] character in Mine and Slash GUID: " + GUID() + " of type " + getSlashRegistryType().name();
    }

}
