package com.overlord.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.overlord.OverLordClient;
import com.overlord.module.Module;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    private final Path configDir;
    private final Path configFile;
    private final Gson gson;
    
    public ConfigManager() {
        configDir = FabricLoader.getInstance().getConfigDir().resolve("overlord");
        configFile = configDir.resolve("config.json");
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    public void load() {
        try {
            if (!Files.exists(configDir)) {
                Files.createDirectories(configDir);
            }
            
            if (!Files.exists(configFile)) {
                save();
                return;
            }
            
            String content = Files.readString(configFile);
            JsonObject json = JsonParser.parseString(content).getAsJsonObject();
            
            if (json.has("modules")) {
                JsonObject modules = json.getAsJsonObject("modules");
                
                for (Module module : OverLordClient.getInstance().getModuleManager().getModules()) {
                    if (modules.has(module.getName())) {
                        JsonObject moduleJson = modules.getAsJsonObject(module.getName());
                        
                        if (moduleJson.has("enabled")) {
                            module.setEnabled(moduleJson.get("enabled").getAsBoolean());
                        }
                        if (moduleJson.has("keybind")) {
                            module.setKeyBind(moduleJson.get("keybind").getAsInt());
                        }
                    }
                }
            }
            
            OverLordClient.LOGGER.info("Config loaded successfully");
        } catch (IOException e) {
            OverLordClient.LOGGER.error("Failed to load config", e);
        }
    }
    
    public void save() {
        try {
            if (!Files.exists(configDir)) {
                Files.createDirectories(configDir);
            }
            
            JsonObject json = new JsonObject();
            JsonObject modules = new JsonObject();
            
            for (Module module : OverLordClient.getInstance().getModuleManager().getModules()) {
                JsonObject moduleJson = new JsonObject();
                moduleJson.addProperty("enabled", module.isEnabled());
                moduleJson.addProperty("keybind", module.getKeyBind());
                modules.add(module.getName(), moduleJson);
            }
            
            json.add("modules", modules);
            
            Files.writeString(configFile, gson.toJson(json));
            OverLordClient.LOGGER.info("Config saved successfully");
        } catch (IOException e) {
            OverLordClient.LOGGER.error("Failed to save config", e);
        }
    }
}
