package com.overlord.module.modules.combat;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Criticals extends Module {
    public Criticals() {
        super("Criticals", "Makes all hits critical hits", Category.COMBAT);
    }
    
    public void doCritical() {
        if (mc.player == null || !isEnabled()) return;
        if (!mc.player.isOnGround()) return;
        
        double x = mc.player.getX();
        double y = mc.player.getY();
        double z = mc.player.getZ();
        
        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x, y + 0.0625, z, false));
        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x, y, z, false));
    }
}
