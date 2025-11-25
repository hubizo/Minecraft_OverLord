package com.overlord.module.modules.movement;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Automatically sprints", Category.MOVEMENT);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null) return;
        
        if (mc.player.forwardSpeed > 0 && !mc.player.isSneaking() && mc.player.getHungerManager().getFoodLevel() > 6) {
            mc.player.setSprinting(true);
        }
    }
    
    @Override
    public void onDisable() {
        if (mc.player != null) {
            mc.player.setSprinting(false);
        }
    }
}
