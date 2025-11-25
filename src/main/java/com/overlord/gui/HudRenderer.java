package com.overlord.gui;

import com.overlord.OverLordClient;
import com.overlord.module.Module;
import com.overlord.module.modules.misc.HudModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HudRenderer {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    
    public void render(DrawContext context) {
        if (mc.player == null || mc.options.hudHidden) return;
        
        HudModule hud = (HudModule) OverLordClient.getInstance()
            .getModuleManager().getModuleByName("HUD");
        
        if (hud == null || !hud.isEnabled()) return;
        
        TextRenderer textRenderer = mc.textRenderer;
        int screenWidth = mc.getWindow().getScaledWidth();
        int screenHeight = mc.getWindow().getScaledHeight();
        
        // Watermark
        if (hud.showWatermark()) {
            renderWatermark(context, textRenderer);
        }
        
        // ArrayList (enabled modules)
        if (hud.showArrayList()) {
            renderArrayList(context, textRenderer, screenWidth);
        }
        
        // Coordinates
        if (hud.showCoords()) {
            renderCoords(context, textRenderer, screenHeight);
        }
        
        // FPS
        if (hud.showFPS()) {
            renderFPS(context, textRenderer, screenWidth, screenHeight);
        }
    }
    
    private void renderWatermark(DrawContext context, TextRenderer textRenderer) {
        String watermark = "OverLord v1.0.0";
        
        // Background
        int textWidth = textRenderer.getWidth(watermark);
        context.fill(4, 4, 10 + textWidth, 18, 0xA0000000);
        
        // Rainbow effect
        long time = System.currentTimeMillis();
        int color = getRainbowColor(time, 0);
        
        context.drawTextWithShadow(textRenderer, watermark, 7, 7, color);
    }
    
    private void renderArrayList(DrawContext context, TextRenderer textRenderer, int screenWidth) {
        List<Module> enabledModules = OverLordClient.getInstance()
            .getModuleManager().getEnabledModules()
            .stream()
            .filter(m -> !m.getName().equals("HUD"))
            .sorted(Comparator.comparingInt(m -> -textRenderer.getWidth(m.getName())))
            .collect(Collectors.toList());
        
        int y = 4;
        int index = 0;
        
        for (Module module : enabledModules) {
            String name = module.getName();
            int textWidth = textRenderer.getWidth(name);
            int x = screenWidth - textWidth - 6;
            
            // Background
            context.fill(x - 2, y, screenWidth, y + 12, 0x90000000);
            
            // Side bar with category color
            context.fill(screenWidth - 2, y, screenWidth, y + 12, module.getCategory().getColor());
            
            // Text with rainbow
            int color = getRainbowColor(System.currentTimeMillis(), index * 100);
            context.drawTextWithShadow(textRenderer, name, x, y + 2, color);
            
            y += 12;
            index++;
        }
    }
    
    private void renderCoords(DrawContext context, TextRenderer textRenderer, int screenHeight) {
        if (mc.player == null) return;
        
        String coords = String.format("XYZ: %.1f / %.1f / %.1f",
            mc.player.getX(), mc.player.getY(), mc.player.getZ());
        
        String facing = "Facing: " + getCardinalDirection();
        
        int y = screenHeight - 24;
        
        context.fill(4, y - 2, 10 + Math.max(textRenderer.getWidth(coords), textRenderer.getWidth(facing)), y + 22, 0x90000000);
        context.drawTextWithShadow(textRenderer, coords, 7, y, 0xFFFFFFFF);
        context.drawTextWithShadow(textRenderer, facing, 7, y + 10, 0xFFAAAAAA);
    }
    
    private void renderFPS(DrawContext context, TextRenderer textRenderer, int screenWidth, int screenHeight) {
        String fps = mc.getCurrentFps() + " FPS";
        int textWidth = textRenderer.getWidth(fps);
        int x = screenWidth - textWidth - 6;
        int y = screenHeight - 14;
        
        context.fill(x - 2, y - 2, screenWidth, y + 12, 0x90000000);
        
        int fpsValue = mc.getCurrentFps();
        int color;
        if (fpsValue >= 60) {
            color = 0xFF55FF55; // Green
        } else if (fpsValue >= 30) {
            color = 0xFFFFFF55; // Yellow
        } else {
            color = 0xFFFF5555; // Red
        }
        
        context.drawTextWithShadow(textRenderer, fps, x, y, color);
    }
    
    private String getCardinalDirection() {
        if (mc.player == null) return "";
        
        float yaw = mc.player.getYaw();
        yaw = yaw % 360;
        if (yaw < 0) yaw += 360;
        
        if (yaw >= 315 || yaw < 45) return "South (+Z)";
        if (yaw >= 45 && yaw < 135) return "West (-X)";
        if (yaw >= 135 && yaw < 225) return "North (-Z)";
        return "East (+X)";
    }
    
    private int getRainbowColor(long time, int offset) {
        float hue = ((time + offset) % 2000) / 2000f;
        return java.awt.Color.HSBtoRGB(hue, 0.8f, 1.0f);
    }
}
