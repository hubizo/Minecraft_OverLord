package com.overlord.module.modules.combat;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.Hand;

public class AutoCrystal extends Module {
    private double range = 6.0;
    
    public AutoCrystal() {
        super("AutoCrystal", "Automatically breaks end crystals", Category.COMBAT);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) return;
        
        for (Entity entity : mc.world.getEntities()) {
            if (!(entity instanceof EndCrystalEntity)) continue;
            if (mc.player.distanceTo(entity) > range) continue;
            
            mc.interactionManager.attackEntity(mc.player, entity);
            mc.player.swingHand(Hand.MAIN_HAND);
            break;
        }
    }
}
