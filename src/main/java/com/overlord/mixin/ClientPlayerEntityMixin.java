package com.overlord.mixin;

import com.overlord.OverLordClient;
import com.overlord.module.modules.combat.Velocity;
import com.overlord.module.modules.movement.HighJump;
import com.overlord.module.modules.player.NoSlow;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {
    
    @Inject(method = "pushAwayFrom", at = @At("HEAD"), cancellable = true)
    private void onPushAway(Entity entity, CallbackInfo ci) {
        Velocity velocity = (Velocity) OverLordClient.getInstance()
            .getModuleManager().getModuleByName("Velocity");
        if (velocity != null && velocity.isEnabled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = "getJumpVelocity", at = @At("RETURN"), cancellable = true)
    private void onGetJumpVelocity(CallbackInfoReturnable<Float> cir) {
        HighJump highJump = (HighJump) OverLordClient.getInstance()
            .getModuleManager().getModuleByName("HighJump");
        if (highJump != null && highJump.isEnabled()) {
            cir.setReturnValue((float) (cir.getReturnValue() + highJump.getJumpBoost()));
        }
    }
}
