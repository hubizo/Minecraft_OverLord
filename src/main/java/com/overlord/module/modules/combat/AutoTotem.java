package com.overlord.module.modules.combat;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class AutoTotem extends Module {
    public AutoTotem() {
        super("AutoTotem", "Automatically puts totem in offhand", Category.COMBAT);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null || mc.interactionManager == null) return;
        if (mc.currentScreen != null) return;
        
        // Check if offhand already has totem
        if (mc.player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING) return;
        
        // Find totem in inventory
        int totemSlot = -1;
        for (int i = 0; i < 36; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == Items.TOTEM_OF_UNDYING) {
                totemSlot = i;
                break;
            }
        }
        
        if (totemSlot != -1) {
            // Convert to screen slot
            int screenSlot = totemSlot < 9 ? totemSlot + 36 : totemSlot;
            
            // Swap to offhand
            mc.interactionManager.clickSlot(
                mc.player.currentScreenHandler.syncId,
                screenSlot,
                40, // Offhand slot
                SlotActionType.SWAP,
                mc.player
            );
        }
    }
}
