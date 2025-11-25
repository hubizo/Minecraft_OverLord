package com.overlord.module.modules.render;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class XRay extends Module {
    private final Set<Block> visibleBlocks = new HashSet<>(Arrays.asList(
        Blocks.DIAMOND_ORE,
        Blocks.DEEPSLATE_DIAMOND_ORE,
        Blocks.EMERALD_ORE,
        Blocks.DEEPSLATE_EMERALD_ORE,
        Blocks.GOLD_ORE,
        Blocks.DEEPSLATE_GOLD_ORE,
        Blocks.IRON_ORE,
        Blocks.DEEPSLATE_IRON_ORE,
        Blocks.COAL_ORE,
        Blocks.DEEPSLATE_COAL_ORE,
        Blocks.LAPIS_ORE,
        Blocks.DEEPSLATE_LAPIS_ORE,
        Blocks.REDSTONE_ORE,
        Blocks.DEEPSLATE_REDSTONE_ORE,
        Blocks.COPPER_ORE,
        Blocks.DEEPSLATE_COPPER_ORE,
        Blocks.ANCIENT_DEBRIS,
        Blocks.NETHER_GOLD_ORE,
        Blocks.NETHER_QUARTZ_ORE,
        Blocks.CHEST,
        Blocks.ENDER_CHEST,
        Blocks.SPAWNER,
        Blocks.END_PORTAL_FRAME,
        Blocks.OBSIDIAN,
        Blocks.CRYING_OBSIDIAN,
        Blocks.NETHERITE_BLOCK,
        Blocks.DIAMOND_BLOCK,
        Blocks.EMERALD_BLOCK,
        Blocks.GOLD_BLOCK
    ));
    
    public XRay() {
        super("XRay", "See ores through blocks", Category.RENDER);
    }
    
    @Override
    public void onEnable() {
        if (mc.worldRenderer != null) {
            mc.worldRenderer.reload();
        }
    }
    
    @Override
    public void onDisable() {
        if (mc.worldRenderer != null) {
            mc.worldRenderer.reload();
        }
    }
    
    public boolean isBlockVisible(Block block) {
        return visibleBlocks.contains(block);
    }
    
    public Set<Block> getVisibleBlocks() {
        return visibleBlocks;
    }
}
