package com.overlord.module.modules.movement;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class HighJump extends Module {
    private double jumpBoost = 0.5;
    
    public HighJump() {
        super("HighJump", "Jump higher than normal", Category.MOVEMENT);
    }
    
    public double getJumpBoost() {
        return isEnabled() ? jumpBoost : 0;
    }
    
    public void setJumpBoost(double boost) {
        this.jumpBoost = boost;
    }
}
