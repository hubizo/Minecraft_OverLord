package com.overlord.module.modules.world;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AutoMine extends Module {
    private double range = 4.5;
    private final Set<Block> targetBlocks = new HashSet<>(Arrays.asList(
        Blocks.DIAMOND_ORE,
        Blocks.DEEPSLATE_DIAMOND_ORE,
        Blocks.EMERALD_ORE,
        Blocks.DEEPSLATE_EMERALD_ORE,
        Blocks.ANCIENT_DEBRIS
    ));
    
    public AutoMine() {
        super("AutoMine", "Automatically mines valuable ores", Category.WORLD);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
        
        BlockPos playerPos = mc.player.getBlockPos();
        int r = (int) Math.ceil(range);
        
        BlockPos closest = null;
        double closestDist = range + 1;
        
        for (int x = -r; x <= r; x++) {
            for (int y = -r; y <= r; y++) {
                for (int z = -r; z <= r; z++) {
                    BlockPos pos = playerPos.add(x, y, z);
                    double dist = mc.player.getPos().distanceTo(pos.toCenterPos());
                    
                    if (dist > range) continue;
                    
                    Block block = mc.world.getBlockState(pos).getBlock();
                    if (targetBlocks.contains(block) && dist < closestDist) {
                        closest = pos;
                        closestDist = dist;
                    }
                }
            }
        }
        
        if (closest != null) {
            mc.interactionManager.updateBlockBreakingProgress(closest, Direction.UP);
        }
    }
}
