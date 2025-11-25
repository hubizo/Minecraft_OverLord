package com.overlord.module.modules.world;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class Nuker extends Module {
    private double range = 4.5;
    
    public Nuker() {
        super("Nuker", "Destroys blocks around you", Category.WORLD);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
        
        BlockPos playerPos = mc.player.getBlockPos();
        int r = (int) Math.ceil(range);
        
        for (int x = -r; x <= r; x++) {
            for (int y = -r; y <= r; y++) {
                for (int z = -r; z <= r; z++) {
                    BlockPos pos = playerPos.add(x, y, z);
                    
                    if (mc.player.getPos().distanceTo(pos.toCenterPos()) > range) continue;
                    
                    BlockState state = mc.world.getBlockState(pos);
                    if (state.isAir()) continue;
                    if (state.getHardness(mc.world, pos) < 0) continue; // Unbreakable
                    
                    mc.interactionManager.updateBlockBreakingProgress(pos, Direction.UP);
                    return;
                }
            }
        }
    }
}
