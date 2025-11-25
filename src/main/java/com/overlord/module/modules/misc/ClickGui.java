package com.overlord.module.modules.misc;

import com.overlord.OverLordClient;
import com.overlord.module.Category;
import com.overlord.module.Module;
import org.lwjgl.glfw.GLFW;

public class ClickGui extends Module {
    public ClickGui() {
        super("ClickGUI", "Opens the ClickGUI", Category.MISC);
        setKeyBind(GLFW.GLFW_KEY_RIGHT_SHIFT);
    }
    
    @Override
    public void onEnable() {
        if (mc.currentScreen == null) {
            mc.setScreen(OverLordClient.getInstance().getClickGui());
        }
        setEnabled(false);
    }
}
