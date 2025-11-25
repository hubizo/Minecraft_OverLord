package com.overlord.module.modules.combat;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class KillAura extends Module {
    private double range = 4.0;
    private boolean targetPlayers = true;
    private boolean targetMobs = true;
    private boolean targetAnimals = false;
    
    public KillAura() {
        super("KillAura", "Automatically attacks nearby entities", Category.COMBAT);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) return;
        
        Entity target = findTarget();
        if (target != null && mc.player.getAttackCooldownProgress(0.5f) >= 1.0f) {
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }
    
    private Entity findTarget() {
        Entity closest = null;
        double closestDist = range;
        
        for (Entity entity : mc.world.getEntities()) {
            if (entity == mc.player) continue;
            if (!(entity instanceof LivingEntity living)) continue;
            if (living.isDead() || living.getHealth() <= 0) continue;
            
            double dist = mc.player.distanceTo(entity);
            if (dist > range) continue;
            
            boolean valid = false;
            if (targetPlayers && entity instanceof PlayerEntity) valid = true;
            if (targetMobs && entity instanceof Monster) valid = true;
            if (targetAnimals && entity instanceof AnimalEntity) valid = true;
            
            if (valid && dist < closestDist) {
                closest = entity;
                closestDist = dist;
            }
        }
        
        return closest;
    }
    
    // Getters/Setters for settings
    public double getRange() { return range; }
    public void setRange(double range) { this.range = range; }
    public boolean isTargetPlayers() { return targetPlayers; }
    public void setTargetPlayers(boolean targetPlayers) { this.targetPlayers = targetPlayers; }
    public boolean isTargetMobs() { return targetMobs; }
    public void setTargetMobs(boolean targetMobs) { this.targetMobs = targetMobs; }
    public boolean isTargetAnimals() { return targetAnimals; }
    public void setTargetAnimals(boolean targetAnimals) { this.targetAnimals = targetAnimals; }
}
