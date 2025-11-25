package com.overlord.module.modules.render;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class Tracers extends Module {
    private boolean players = true;
    private boolean mobs = false;
    
    public Tracers() {
        super("Tracers", "Draw lines to entities", Category.RENDER);
    }
    
    public boolean showPlayers() { return players && isEnabled(); }
    public boolean showMobs() { return mobs && isEnabled(); }
    
    public void setPlayers(boolean v) { this.players = v; }
    public void setMobs(boolean v) { this.mobs = v; }
}
