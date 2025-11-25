package com.overlord.module.modules.movement;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class Spider extends Module {
    public Spider() {
        super("Spider", "Climb walls like a spider", Category.MOVEMENT);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null) return;
        
        if (mc.player.horizontalCollision) {
            mc.player.setVelocity(mc.player.getVelocity().x, 0.2, mc.player.getVelocity().z);
            mc.player.fallDistance = 0;
        }
    }
}
