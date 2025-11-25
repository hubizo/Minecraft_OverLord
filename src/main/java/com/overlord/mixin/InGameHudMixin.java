package com.overlord.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    // HUD rendering is handled via HudRenderCallback
}
