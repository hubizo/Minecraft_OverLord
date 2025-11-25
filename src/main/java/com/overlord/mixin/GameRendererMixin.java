package com.overlord.mixin;

import com.overlord.OverLordClient;
import com.overlord.module.modules.combat.Reach;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    
    @Inject(method = "getBasicProjectionMatrix", at = @At("HEAD"))
    private void onGetProjectionMatrix(double fov, CallbackInfoReturnable<org.joml.Matrix4f> cir) {
        // Projection matrix hook for future use
    }
}
