package com.overlord.module.modules.misc;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class HudModule extends Module {
    private boolean showWatermark = true;
    private boolean showArrayList = true;
    private boolean showCoords = true;
    private boolean showFPS = true;
    
    public HudModule() {
        super("HUD", "Displays overlay information", Category.MISC);
        setEnabled(true);
    }
    
    public boolean showWatermark() { return showWatermark && isEnabled(); }
    public boolean showArrayList() { return showArrayList && isEnabled(); }
    public boolean showCoords() { return showCoords && isEnabled(); }
    public boolean showFPS() { return showFPS && isEnabled(); }
    
    public void setShowWatermark(boolean v) { this.showWatermark = v; }
    public void setShowArrayList(boolean v) { this.showArrayList = v; }
    public void setShowCoords(boolean v) { this.showCoords = v; }
    public void setShowFPS(boolean v) { this.showFPS = v; }
}
