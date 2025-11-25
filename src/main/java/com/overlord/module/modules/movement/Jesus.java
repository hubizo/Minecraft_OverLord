package com.overlord.module.modules.movement;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

public class Jesus extends Module {
    public Jesus() {
        super("Jesus", "Walk on water", Category.MOVEMENT);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) return;
        
        if (mc.player.isTouchingWater() && !mc.player.isSneaking()) {
            mc.player.setVelocity(mc.player.getVelocity().x, 0.1, mc.player.getVelocity().z);
        }
        
        // Check if standing on water
        BlockPos below = mc.player.getBlockPos().down();
        if (mc.world.getBlockState(below).getBlock() == Blocks.WATER && !mc.player.isSneaking()) {
            mc.player.setOnGround(true);
        }
    }
}
