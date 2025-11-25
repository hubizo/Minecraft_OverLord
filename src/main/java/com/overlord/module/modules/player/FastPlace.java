package com.overlord.module.modules.player;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class FastPlace extends Module {
    public FastPlace() {
        super("FastPlace", "Place blocks faster", Category.PLAYER);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null) return;
        mc.itemUseCooldown = 0;
    }
}
