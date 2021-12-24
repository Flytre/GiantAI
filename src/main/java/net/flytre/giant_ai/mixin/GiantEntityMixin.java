package net.flytre.giant_ai.mixin;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GiantEntity.class)
public class GiantEntityMixin {

    @Inject(method = "createGiantAttributes", at = @At("HEAD"), cancellable = true)
    private static void setAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.setReturnValue(HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 20.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE,40.0D));
    }

    @Inject(method = "getPathfindingFavor", at = @At("HEAD"), cancellable = true)
    public void fixSpawn(BlockPos pos, WorldView world, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(0.5F - world.getBrightness(pos));
    }
}
