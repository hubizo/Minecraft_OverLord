package com.overlord.module.modules.player;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;

public class AutoArmor extends Module {
    public AutoArmor() {
        super("AutoArmor", "Automatically equips best armor", Category.PLAYER);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null || mc.interactionManager == null) return;
        if (mc.currentScreen != null) return;
        
        for (EquipmentSlot slot : new EquipmentSlot[]{
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
        }) {
            equipBestArmor(slot);
        }
    }
    
    private void equipBestArmor(EquipmentSlot slot) {
        ItemStack currentArmor = mc.player.getEquippedStack(slot);
        int currentProtection = getProtection(currentArmor, slot);
        
        int bestSlot = -1;
        int bestProtection = currentProtection;
        
        for (int i = 0; i < 36; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.isEmpty()) continue;
            if (!(stack.getItem() instanceof ArmorItem armor)) continue;
            if (armor.getSlotType() != slot) continue;
            
            int protection = getProtection(stack, slot);
            if (protection > bestProtection) {
                bestProtection = protection;
                bestSlot = i;
            }
        }
        
        if (bestSlot != -1) {
            int screenSlot = bestSlot < 9 ? bestSlot + 36 : bestSlot;
            int armorSlot = 8 - slot.getEntitySlotId();
            
            mc.interactionManager.clickSlot(
                mc.player.currentScreenHandler.syncId,
                screenSlot,
                0,
                SlotActionType.PICKUP,
                mc.player
            );
            mc.interactionManager.clickSlot(
                mc.player.currentScreenHandler.syncId,
                armorSlot,
                0,
                SlotActionType.PICKUP,
                mc.player
            );
            mc.interactionManager.clickSlot(
                mc.player.currentScreenHandler.syncId,
                screenSlot,
                0,
                SlotActionType.PICKUP,
                mc.player
            );
        }
    }
    
    private int getProtection(ItemStack stack, EquipmentSlot slot) {
        if (stack.isEmpty()) return -1;
        if (!(stack.getItem() instanceof ArmorItem armor)) return -1;
        return armor.getProtection();
    }
}
