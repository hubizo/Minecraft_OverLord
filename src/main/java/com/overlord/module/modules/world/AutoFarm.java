package com.overlord.module.modules.world;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.block.*;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoFarm extends Module {
    private double range = 4.0;
    
    public AutoFarm() {
        super("AutoFarm", "Automatically harvests and replants crops", Category.WORLD);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
        
        BlockPos playerPos = mc.player.getBlockPos();
        int r = (int) Math.ceil(range);
        
        for (int x = -r; x <= r; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -r; z <= r; z++) {
                    BlockPos pos = playerPos.add(x, y, z);
                    BlockState state = mc.world.getBlockState(pos);
                    Block block = state.getBlock();
                    
                    // Check if crop is fully grown
                    if (block instanceof CropBlock crop) {
                        if (crop.isMature(state)) {
                            mc.interactionManager.updateBlockBreakingProgress(pos, Direction.UP);
                            return;
                        }
                    }
                    
                    // Harvest melons/pumpkins
                    if (block == Blocks.MELON || block == Blocks.PUMPKIN) {
                        mc.interactionManager.updateBlockBreakingProgress(pos, Direction.UP);
                        return;
                    }
                }
            }
        }
    }
}
