package com.overlord.module.modules.player;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class FastBreak extends Module {
    private double multiplier = 1.5;
    
    public FastBreak() {
        super("FastBreak", "Break blocks faster", Category.PLAYER);
    }
    
    public double getMultiplier() {
        return isEnabled() ? multiplier : 1.0;
    }
    
    public void setMultiplier(double mult) {
        this.multiplier = mult;
    }
}
