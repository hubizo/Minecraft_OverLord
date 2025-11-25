package com.overlord.module.modules.world;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class Timer extends Module {
    private double speed = 2.0;
    
    public Timer() {
        super("Timer", "Changes game tick speed", Category.WORLD);
    }
    
    public double getSpeed() {
        return isEnabled() ? speed : 1.0;
    }
    
    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
