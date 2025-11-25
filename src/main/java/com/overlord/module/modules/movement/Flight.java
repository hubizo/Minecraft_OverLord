package com.overlord.module.modules.movement;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.util.math.Vec3d;

public class Flight extends Module {
    private double speed = 1.0;
    
    public Flight() {
        super("Flight", "Allows you to fly", Category.MOVEMENT);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null) return;
        
        mc.player.getAbilities().flying = true;
        
        Vec3d velocity = mc.player.getVelocity();
        double motionY = 0;
        
        if (mc.options.jumpKey.isPressed()) {
            motionY = speed;
        } else if (mc.options.sneakKey.isPressed()) {
            motionY = -speed;
        }
        
        mc.player.setVelocity(velocity.x, motionY, velocity.z);
    }
    
    @Override
    public void onDisable() {
        if (mc.player != null) {
            mc.player.getAbilities().flying = false;
        }
    }
    
    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
}
