package com.overlord.module.modules.render;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class FullBright extends Module {
    public FullBright() {
        super("FullBright", "See in the dark", Category.RENDER);
    }
    
    @Override
    public void onEnable() {
        if (mc.options != null) {
            mc.options.getGamma().setValue(10.0);
        }
    }
    
    @Override
    public void onDisable() {
        if (mc.options != null) {
            mc.options.getGamma().setValue(1.0);
        }
    }
}
