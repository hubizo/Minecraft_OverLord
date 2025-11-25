package com.overlord.mixin;

import com.overlord.OverLordClient;
import com.overlord.module.modules.render.FullBright;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {
    
    @Inject(method = "getBrightness", at = @At("RETURN"), cancellable = true)
    private static void onGetBrightness(CallbackInfoReturnable<Float> cir) {
        if (OverLordClient.getInstance() == null) return;
        
        FullBright fullBright = (FullBright) OverLordClient.getInstance()
            .getModuleManager().getModuleByName("FullBright");
        
        if (fullBright != null && fullBright.isEnabled()) {
            cir.setReturnValue(1.0f);
        }
    }
}
