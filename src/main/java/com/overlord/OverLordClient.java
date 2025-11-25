package com.overlord;

import com.overlord.gui.ClickGui;
import com.overlord.gui.HudRenderer;
import com.overlord.module.ModuleManager;
import com.overlord.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OverLordClient implements ClientModInitializer {
    public static final String MOD_ID = "overlord";
    public static final String MOD_NAME = "OverLord";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    
    private static OverLordClient instance;
    private ModuleManager moduleManager;
    private ConfigManager configManager;
    private ClickGui clickGui;
    private HudRenderer hudRenderer;
    
    private KeyBinding openGuiKey;
    
    @Override
    public void onInitializeClient() {
        instance = this;
        LOGGER.info("Initializing {} client...", MOD_NAME);
        
        // Initialize managers
        moduleManager = new ModuleManager();
        configManager = new ConfigManager();
        clickGui = new ClickGui();
        hudRenderer = new HudRenderer();
        
        // Register keybinding for GUI
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.overlord.open_gui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "category.overlord.main"
        ));
        
        // Register tick event
        ClientTickEvents.END_CLIENT_TICK.register(this::onTick);
        
        // Register HUD render callback
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            hudRenderer.render(drawContext);
        });
        
        // Load config
        configManager.load();
        
        LOGGER.info("{} initialized successfully!", MOD_NAME);
    }
    
    private void onTick(MinecraftClient client) {
        if (client.player == null) return;
        
        // Check for GUI key
        while (openGuiKey.wasPressed()) {
            client.setScreen(clickGui);
        }
        
        // Tick all enabled modules
        moduleManager.onTick();
    }
    
    public static OverLordClient getInstance() {
        return instance;
    }
    
    public ModuleManager getModuleManager() {
        return moduleManager;
    }
    
    public ConfigManager getConfigManager() {
        return configManager;
    }
    
    public ClickGui getClickGui() {
        return clickGui;
    }
}
