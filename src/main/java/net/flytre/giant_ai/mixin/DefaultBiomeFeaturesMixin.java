package net.flytre.giant_ai.mixin;

import net.flytre.flytre_lib.api.config.ConfigHandler;
import net.flytre.giant_ai.Config;
import net.flytre.giant_ai.GiantAI;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {

    @Inject(method = "addMonsters", at = @At(value = "HEAD"))
    private static void addGiant(SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight, boolean drowned, CallbackInfo ci) {
        ConfigHandler<Config> handler = GiantAI.CONFIG_HANDLER;

        if(handler.getConfig() == null)
            handler.handle();

        if (handler.getConfig().shouldSpawnNaturally)
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GIANT, 3, 1, 1));
    }

}
