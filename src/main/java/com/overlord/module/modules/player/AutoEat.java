package com.overlord.module.modules.player;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class AutoEat extends Module {
    private int hungerThreshold = 14;
    private boolean wasEating = false;
    
    public AutoEat() {
        super("AutoEat", "Automatically eats food", Category.PLAYER);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null || mc.interactionManager == null) return;
        
        int hunger = mc.player.getHungerManager().getFoodLevel();
        
        if (hunger <= hungerThreshold) {
            int foodSlot = findFood();
            if (foodSlot != -1) {
                int prevSlot = mc.player.getInventory().selectedSlot;
                mc.player.getInventory().selectedSlot = foodSlot;
                mc.options.useKey.setPressed(true);
                wasEating = true;
            }
        } else if (wasEating) {
            mc.options.useKey.setPressed(false);
            wasEating = false;
        }
    }
    
    private int findFood() {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.getItem().getFoodComponent() != null) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public void onDisable() {
        if (wasEating) {
            mc.options.useKey.setPressed(false);
            wasEating = false;
        }
    }
}
