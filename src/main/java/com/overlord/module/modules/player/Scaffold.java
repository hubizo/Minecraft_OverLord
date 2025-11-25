package com.overlord.module.modules.player;

import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class Scaffold extends Module {
    public Scaffold() {
        super("Scaffold", "Automatically places blocks under you", Category.PLAYER);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
        
        BlockPos below = mc.player.getBlockPos().down();
        
        if (!mc.world.getBlockState(below).isAir()) return;
        
        int blockSlot = findBlockSlot();
        if (blockSlot == -1) return;
        
        int prevSlot = mc.player.getInventory().selectedSlot;
        mc.player.getInventory().selectedSlot = blockSlot;
        
        // Find a face to place against
        for (Direction dir : Direction.values()) {
            BlockPos neighbor = below.offset(dir);
            if (!mc.world.getBlockState(neighbor).isAir()) {
                BlockHitResult hit = new BlockHitResult(
                    Vec3d.ofCenter(neighbor),
                    dir.getOpposite(),
                    neighbor,
                    false
                );
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
                mc.player.swingHand(Hand.MAIN_HAND);
                break;
            }
        }
        
        mc.player.getInventory().selectedSlot = prevSlot;
    }
    
    private int findBlockSlot() {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.getItem() instanceof BlockItem) {
                return i;
            }
        }
        return -1;
    }
}
