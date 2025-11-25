package com.overlord.module.modules.misc;

import com.overlord.module.Category;
import com.overlord.module.Module;

import java.util.Random;

public class AntiAFK extends Module {
    private int timer = 0;
    private int interval = 100;
    private final Random random = new Random();
    
    public AntiAFK() {
        super("AntiAFK", "Prevents AFK kick", Category.MISC);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null) return;
        
        timer++;
        if (timer >= interval) {
            timer = 0;
            
            // Random action
            int action = random.nextInt(3);
            switch (action) {
                case 0 -> mc.player.setYaw(mc.player.getYaw() + (random.nextFloat() - 0.5f) * 10);
                case 1 -> mc.player.jump();
                case 2 -> mc.player.swingHand(net.minecraft.util.Hand.MAIN_HAND);
            }
        }
    }
}
