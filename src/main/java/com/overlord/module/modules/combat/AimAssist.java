package com.overlord.module.modules.combat;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class AimAssist extends Module {
    private double range = 5.0;
    private double speed = 5.0;
    
    public AimAssist() {
        super("AimAssist", "Helps aim at nearby players", Category.COMBAT);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) return;
        
        Entity target = findClosestPlayer();
        if (target == null) return;
        
        Vec3d targetPos = target.getPos().add(0, target.getEyeHeight(target.getPose()) / 2, 0);
        Vec3d playerPos = mc.player.getEyePos();
        
        double diffX = targetPos.x - playerPos.x;
        double diffY = targetPos.y - playerPos.y;
        double diffZ = targetPos.z - playerPos.z;
        
        double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float targetYaw = (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90f;
        float targetPitch = (float) -Math.toDegrees(Math.atan2(diffY, dist));
        
        float currentYaw = mc.player.getYaw();
        float currentPitch = mc.player.getPitch();
        
        float yawDiff = MathHelper.wrapDegrees(targetYaw - currentYaw);
        float pitchDiff = MathHelper.wrapDegrees(targetPitch - currentPitch);
        
        float smoothSpeed = (float) (speed / 10.0);
        mc.player.setYaw(currentYaw + yawDiff * smoothSpeed);
        mc.player.setPitch(currentPitch + pitchDiff * smoothSpeed);
    }
    
    private Entity findClosestPlayer() {
        Entity closest = null;
        double closestDist = range;
        
        for (Entity entity : mc.world.getEntities()) {
            if (entity == mc.player) continue;
            if (!(entity instanceof PlayerEntity player)) continue;
            if (player.isDead()) continue;
            
            double dist = mc.player.distanceTo(entity);
            if (dist < closestDist) {
                closest = entity;
                closestDist = dist;
            }
        }
        
        return closest;
    }
}
