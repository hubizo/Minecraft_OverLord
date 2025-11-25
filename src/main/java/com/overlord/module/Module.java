package com.overlord.module;

import com.overlord.OverLordClient;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public abstract class Module {
    protected final MinecraftClient mc = MinecraftClient.getInstance();
    
    private final String name;
    private final String description;
    private final Category category;
    private boolean enabled;
    private int keyBind;
    
    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
        this.keyBind = GLFW.GLFW_KEY_UNKNOWN;
    }
    
    public void toggle() {
        setEnabled(!enabled);
    }
    
    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (enabled) {
                onEnable();
            } else {
                onDisable();
            }
        }
    }
    
    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
    public void onRender() {}
    
    // Getters and setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Category getCategory() { return category; }
    public boolean isEnabled() { return enabled; }
    public int getKeyBind() { return keyBind; }
    public void setKeyBind(int keyBind) { this.keyBind = keyBind; }
    
    public String getKeyBindName() {
        if (keyBind == GLFW.GLFW_KEY_UNKNOWN) return "None";
        return GLFW.glfwGetKeyName(keyBind, 0);
    }
}
