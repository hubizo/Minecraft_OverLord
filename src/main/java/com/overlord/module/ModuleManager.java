package com.overlord.module;

import com.overlord.module.modules.combat.*;
import com.overlord.module.modules.movement.*;
import com.overlord.module.modules.render.*;
import com.overlord.module.modules.player.*;
import com.overlord.module.modules.world.*;
import com.overlord.module.modules.exploit.*;
import com.overlord.module.modules.misc.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();
    
    public ModuleManager() {
        registerModules();
    }
    
    private void registerModules() {
        // Combat
        modules.add(new KillAura());
        modules.add(new TriggerBot());
        modules.add(new AimAssist());
        modules.add(new AutoCrystal());
        modules.add(new Criticals());
        modules.add(new Reach());
        modules.add(new Velocity());
        modules.add(new AutoTotem());
        
        // Movement
        modules.add(new Flight());
        modules.add(new Speed());
        modules.add(new Sprint());
        modules.add(new NoFall());
        modules.add(new Step());
        modules.add(new Jesus());
        modules.add(new Spider());
        modules.add(new HighJump());
        
        // Render
        modules.add(new ESP());
        modules.add(new Tracers());
        modules.add(new FullBright());
        modules.add(new ChestESP());
        modules.add(new Nametags());
        modules.add(new Trajectories());
        modules.add(new XRay());
        modules.add(new Freecam());
        
        // Player
        modules.add(new AutoEat());
        modules.add(new AutoArmor());
        modules.add(new FastPlace());
        modules.add(new FastBreak());
        modules.add(new NoSlow());
        modules.add(new AutoFish());
        modules.add(new ChestStealer());
        modules.add(new Scaffold());
        
        // World
        modules.add(new Nuker());
        modules.add(new AutoFarm());
        modules.add(new AutoMine());
        modules.add(new Timer());
        
        // Exploit
        modules.add(new Blink());
        modules.add(new Phase());
        modules.add(new PacketFly());
        
        // Misc
        modules.add(new AntiAFK());
        modules.add(new AutoReconnect());
        modules.add(new ClickGui());
        modules.add(new HudModule());
    }
    
    public void onTick() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.onTick();
            }
        }
    }
    
    public List<Module> getModules() {
        return modules;
    }
    
    public List<Module> getModulesByCategory(Category category) {
        return modules.stream()
            .filter(m -> m.getCategory() == category)
            .collect(Collectors.toList());
    }
    
    public Module getModuleByName(String name) {
        return modules.stream()
            .filter(m -> m.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }
    
    public List<Module> getEnabledModules() {
        return modules.stream()
            .filter(Module::isEnabled)
            .collect(Collectors.toList());
    }
}
