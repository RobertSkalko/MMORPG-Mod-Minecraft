package com.robertx22.mine_and_slash.event_hooks.data_gen;

import com.robertx22.mine_and_slash.data_generation.DimConfigsDatapackManager;
import com.robertx22.mine_and_slash.data_generation.EntityConfigsDatapackManager;
import com.robertx22.mine_and_slash.data_generation.affixes.AffixDataPackManager;
import com.robertx22.mine_and_slash.data_generation.base_gear_types.BaseGearTypeDatapackManager;
import com.robertx22.mine_and_slash.data_generation.compatible_items.CompatibleItemDataPackManager;
import com.robertx22.mine_and_slash.data_generation.mob_affixes.MobAffixDataPackManager;
import com.robertx22.mine_and_slash.data_generation.models.ItemModelManager;
import com.robertx22.mine_and_slash.data_generation.rarities.GearRarityManager;
import com.robertx22.mine_and_slash.data_generation.rarities.SkillGemRarityManager;
import com.robertx22.mine_and_slash.data_generation.tiers.TierDatapackManager;
import com.robertx22.mine_and_slash.data_generation.unique_gears.UniqueGearDatapackManager;
import com.robertx22.mine_and_slash.event_hooks.data_gen.providers.SlashRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class OnGatherData {

    public void onGatherData(GatherDataEvent event) {

        DataGenerator gen = event.getGenerator();

        if (event.includeServer()) {

            gen.addProvider(new BaseGearTypeDatapackManager().getDataPackCreator(gen));
            gen.addProvider(new MobAffixDataPackManager().getDataPackCreator(gen));
            gen.addProvider(new TierDatapackManager().getDataPackCreator(gen));
            gen.addProvider(new AffixDataPackManager().getDataPackCreator(gen));
            gen.addProvider(new UniqueGearDatapackManager().getDataPackCreator(gen));
            gen.addProvider(new CompatibleItemDataPackManager().getDataPackCreator(gen));
            gen.addProvider(new DimConfigsDatapackManager().getDataPackCreator(gen));
            gen.addProvider(new EntityConfigsDatapackManager().getDataPackCreator(gen));

            gen.addProvider(new SlashRecipeProvider(gen));

            gen.addProvider(new GearRarityManager().getProvider(gen));
            gen.addProvider(new SkillGemRarityManager().getProvider(gen));

        }

        if (event.includeClient()) {
            gen.addProvider(new ItemModelManager(gen, event.getExistingFileHelper()));
        }

    }

}

