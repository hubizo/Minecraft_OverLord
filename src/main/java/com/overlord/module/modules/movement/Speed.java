package com.overlord.module.modules.movement;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.util.math.Vec3d;

public class Speed extends Module {
    private double speedMultiplier = 1.5;
    
    public Speed() {
        super("Speed", "Increases movement speed", Category.MOVEMENT);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (!mc.player.isOnGround()) return;
        
        Vec3d velocity = mc.player.getVelocity();
        mc.player.setVelocity(
            velocity.x * speedMultiplier,
            velocity.y,
            velocity.z * speedMultiplier
        );
    }
    
    public double getSpeedMultiplier() { return speedMultiplier; }
    public void setSpeedMultiplier(double mult) { this.speedMultiplier = mult; }
}
