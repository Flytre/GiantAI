package net.flytre.giant_ai.mixin;

import net.flytre.giant_ai.BreakBlockGoal;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class MobEntityMixin {

    @Shadow
    @Final
    protected GoalSelector goalSelector;

    @Shadow
    @Final
    protected GoalSelector targetSelector;

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "initGoals", at = @At("HEAD"))
    public void customAI(CallbackInfo ci) {
        MobEntity me = (MobEntity) (Object) this;
        if (me instanceof GiantEntity) {
            GiantEntity giant = (GiantEntity) me;
            this.goalSelector.add(2, new MeleeAttackGoal(giant, 1.0D, false));
            this.goalSelector.add(3, new BreakBlockGoal(giant));
            this.goalSelector.add(6, new MoveThroughVillageGoal(giant, 1.0D, true, 4, () -> false));
            this.goalSelector.add(7, new WanderAroundFarGoal(giant, 1.0D));
            this.targetSelector.add(1, new RevengeGoal(giant).setGroupRevenge(ZombifiedPiglinEntity.class));
            this.targetSelector.add(2, new ActiveTargetGoal<>(giant, PlayerEntity.class, true));
            this.targetSelector.add(3, new ActiveTargetGoal<>(giant, MerchantEntity.class, false));
            this.targetSelector.add(3, new ActiveTargetGoal<>(giant, IronGolemEntity.class, true));
            this.targetSelector.add(5, new ActiveTargetGoal<>(giant, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
            this.goalSelector.add(8, new LookAtEntityGoal(giant, PlayerEntity.class, 8.0F));
            this.goalSelector.add(8, new LookAroundGoal(giant));
        }
    }
}
