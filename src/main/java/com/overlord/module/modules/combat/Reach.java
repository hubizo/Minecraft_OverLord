package com.overlord.module.modules.combat;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class Reach extends Module {
    private double extraReach = 1.0;
    
    public Reach() {
        super("Reach", "Extends your attack reach", Category.COMBAT);
    }
    
    public double getExtraReach() {
        return isEnabled() ? extraReach : 0;
    }
    
    public void setExtraReach(double reach) {
        this.extraReach = reach;
    }
}
