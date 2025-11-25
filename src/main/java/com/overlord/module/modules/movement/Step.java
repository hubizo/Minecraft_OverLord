package com.overlord.module.modules.movement;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class Step extends Module {
    private float stepHeight = 1.0f;
    
    public Step() {
        super("Step", "Allows stepping up blocks", Category.MOVEMENT);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null) return;
        mc.player.setStepHeight(stepHeight);
    }
    
    @Override
    public void onDisable() {
        if (mc.player != null) {
            mc.player.setStepHeight(0.6f);
        }
    }
    
    public float getStepHeight() { return stepHeight; }
    public void setStepHeight(float height) { this.stepHeight = height; }
}
