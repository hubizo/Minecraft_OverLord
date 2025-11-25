package com.overlord.module.modules.player;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class AutoFish extends Module {
    private int catchDelay = 0;
    
    public AutoFish() {
        super("AutoFish", "Automatically catches fish", Category.PLAYER);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null || mc.interactionManager == null) return;
        
        if (catchDelay > 0) {
            catchDelay--;
            return;
        }
        
        FishingBobberEntity bobber = mc.player.fishHook;
        
        if (bobber == null) {
            // Cast if holding fishing rod
            if (mc.player.getMainHandStack().getItem() == Items.FISHING_ROD) {
                mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
                catchDelay = 20;
            }
        } else if (bobber.getVelocity().y < -0.05 && bobber.isInOpenWater()) {
            // Fish caught - reel in
            mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
            catchDelay = 20;
        }
    }
}
