package com.overlord.module.modules.movement;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall extends Module {
    public NoFall() {
        super("NoFall", "Prevents fall damage", Category.MOVEMENT);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null) return;
        
        if (mc.player.fallDistance > 2.5f) {
            mc.player.networkHandler.sendPacket(
                new PlayerMoveC2SPacket.OnGroundOnly(true)
            );
        }
    }
}
