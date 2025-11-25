package com.overlord.gui;

import com.overlord.OverLordClient;
import com.overlord.module.Category;
import com.overlord.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClickGui extends Screen {
    private final Map<Category, CategoryPanel> panels = new HashMap<>();
    private CategoryPanel draggingPanel = null;
    private int dragOffsetX, dragOffsetY;
    
    public ClickGui() {
        super(Text.literal("OverLord"));
        initPanels();
    }
    
    private void initPanels() {
        int x = 10;
        int y = 10;
        int panelWidth = 120;
        int spacing = 130;
        
        for (Category category : Category.values()) {
            panels.put(category, new CategoryPanel(category, x, y, panelWidth));
            x += spacing;
            if (x > 700) {
                x = 10;
                y += 200;
            }
        }
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Semi-transparent background
        context.fill(0, 0, width, height, 0x80000000);
        
        // Render panels
        for (CategoryPanel panel : panels.values()) {
            panel.render(context, mouseX, mouseY);
        }
        
        super.render(context, mouseX, mouseY, delta);
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (CategoryPanel panel : panels.values()) {
            if (panel.mouseClicked((int) mouseX, (int) mouseY, button)) {
                if (panel.isHeaderHovered((int) mouseX, (int) mouseY)) {
                    draggingPanel = panel;
                    dragOffsetX = (int) mouseX - panel.getX();
                    dragOffsetY = (int) mouseY - panel.getY();
                }
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        draggingPanel = null;
        return super.mouseReleased(mouseX, mouseY, button);
    }
    
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (draggingPanel != null) {
            draggingPanel.setPosition((int) mouseX - dragOffsetX, (int) mouseY - dragOffsetY);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
    
    @Override
    public boolean shouldPause() {
        return false;
    }
    
    @Override
    public void close() {
        OverLordClient.getInstance().getConfigManager().save();
        super.close();
    }
    
    // Inner class for category panels
    public static class CategoryPanel {
        private final Category category;
        private int x, y;
        private final int width;
        private final int headerHeight = 24;
        private final int moduleHeight = 20;
        private boolean expanded = true;
        private final List<Module> modules;
        
        public CategoryPanel(Category category, int x, int y, int width) {
            this.category = category;
            this.x = x;
            this.y = y;
            this.width = width;
            this.modules = OverLordClient.getInstance().getModuleManager().getModulesByCategory(category);
        }
        
        public void render(DrawContext context, int mouseX, int mouseY) {
            // Header background with gradient
            int headerColor = category.getColor();
            context.fill(x, y, x + width, y + headerHeight, headerColor);
            context.fill(x, y + headerHeight - 2, x + width, y + headerHeight, 0xFF000000);
            
            // Category name
            String title = category.getIcon() + " " + category.getDisplayName();
            context.drawTextWithShadow(
                net.minecraft.client.MinecraftClient.getInstance().textRenderer,
                title,
                x + 6,
                y + 7,
                0xFFFFFFFF
            );
            
            // Expand/collapse indicator
            String indicator = expanded ? "▼" : "▶";
            context.drawTextWithShadow(
                net.minecraft.client.MinecraftClient.getInstance().textRenderer,
                indicator,
                x + width - 14,
                y + 7,
                0xFFFFFFFF
            );
            
            if (!expanded) return;
            
            // Module list background
            int listHeight = modules.size() * moduleHeight;
            context.fill(x, y + headerHeight, x + width, y + headerHeight + listHeight, 0xE0101010);
            
            // Render modules
            int moduleY = y + headerHeight;
            for (Module module : modules) {
                boolean hovered = mouseX >= x && mouseX <= x + width && 
                                  mouseY >= moduleY && mouseY <= moduleY + moduleHeight;
                
                // Hover effect
                if (hovered) {
                    context.fill(x, moduleY, x + width, moduleY + moduleHeight, 0x40FFFFFF);
                }
                
                // Module name
                int textColor = module.isEnabled() ? category.getColor() : 0xFFAAAAAA;
                context.drawTextWithShadow(
                    net.minecraft.client.MinecraftClient.getInstance().textRenderer,
                    module.getName(),
                    x + 6,
                    moduleY + 6,
                    textColor
                );
                
                // Enabled indicator
                if (module.isEnabled()) {
                    context.fill(x + width - 8, moduleY + 6, x + width - 4, moduleY + 14, category.getColor());
                }
                
                moduleY += moduleHeight;
            }
            
            // Border
            context.drawBorder(x, y, width, headerHeight + listHeight, 0xFF333333);
        }
        
        public boolean mouseClicked(int mouseX, int mouseY, int button) {
            // Check header click (toggle expand)
            if (isHeaderHovered(mouseX, mouseY)) {
                if (button == 1) { // Right click
                    expanded = !expanded;
                    return true;
                }
                return true;
            }
            
            if (!expanded) return false;
            
            // Check module clicks
            int moduleY = y + headerHeight;
            for (Module module : modules) {
                if (mouseX >= x && mouseX <= x + width && 
                    mouseY >= moduleY && mouseY <= moduleY + moduleHeight) {
                    if (button == 0) { // Left click - toggle
                        module.toggle();
                    }
                    return true;
                }
                moduleY += moduleHeight;
            }
            
            return false;
        }
        
        public boolean isHeaderHovered(int mouseX, int mouseY) {
            return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + headerHeight;
        }
        
        public int getX() { return x; }
        public int getY() { return y; }
        
        public void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
