package com.overlord.module.modules.render;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.util.math.Vec3d;

public class Freecam extends Module {
    private double speed = 1.0;
    private Vec3d originalPos;
    private float originalYaw;
    private float originalPitch;
    
    public Freecam() {
        super("Freecam", "Free camera movement", Category.RENDER);
    }
    
    @Override
    public void onEnable() {
        if (mc.player != null) {
            originalPos = mc.player.getPos();
            originalYaw = mc.player.getYaw();
            originalPitch = mc.player.getPitch();
        }
    }
    
    @Override
    public void onDisable() {
        if (mc.player != null && originalPos != null) {
            mc.player.setPos(originalPos.x, originalPos.y, originalPos.z);
            mc.player.setYaw(originalYaw);
            mc.player.setPitch(originalPitch);
        }
    }
    
    @Override
    public void onTick() {
        if (mc.player == null) return;
        
        mc.player.setVelocity(Vec3d.ZERO);
        mc.player.noClip = true;
        
        Vec3d forward = Vec3d.fromPolar(0, mc.player.getYaw());
        Vec3d right = Vec3d.fromPolar(0, mc.player.getYaw() + 90);
        
        double x = 0, y = 0, z = 0;
        
        if (mc.options.forwardKey.isPressed()) {
            x += forward.x * speed;
            z += forward.z * speed;
        }
        if (mc.options.backKey.isPressed()) {
            x -= forward.x * speed;
            z -= forward.z * speed;
        }
        if (mc.options.rightKey.isPressed()) {
            x += right.x * speed;
            z += right.z * speed;
        }
        if (mc.options.leftKey.isPressed()) {
            x -= right.x * speed;
            z -= right.z * speed;
        }
        if (mc.options.jumpKey.isPressed()) {
            y += speed;
        }
        if (mc.options.sneakKey.isPressed()) {
            y -= speed;
        }
        
        mc.player.setPos(mc.player.getX() + x, mc.player.getY() + y, mc.player.getZ() + z);
    }
    
    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
}
