package com.overlord.module.modules.player;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.screen.slot.SlotActionType;

public class ChestStealer extends Module {
    private int delay = 2;
    private int timer = 0;
    
    public ChestStealer() {
        super("ChestStealer", "Automatically takes items from chests", Category.PLAYER);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null || mc.interactionManager == null) return;
        if (!(mc.currentScreen instanceof GenericContainerScreen screen)) return;
        
        if (timer > 0) {
            timer--;
            return;
        }
        
        int rows = screen.getScreenHandler().getRows();
        int slots = rows * 9;
        
        for (int i = 0; i < slots; i++) {
            if (!screen.getScreenHandler().getSlot(i).getStack().isEmpty()) {
                mc.interactionManager.clickSlot(
                    screen.getScreenHandler().syncId,
                    i,
                    0,
                    SlotActionType.QUICK_MOVE,
                    mc.player
                );
                timer = delay;
                return;
            }
        }
    }
}
