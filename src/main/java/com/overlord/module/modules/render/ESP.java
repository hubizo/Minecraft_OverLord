package com.overlord.module.modules.render;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class ESP extends Module {
    private boolean players = true;
    private boolean mobs = true;
    private boolean animals = false;
    private boolean items = true;
    
    public ESP() {
        super("ESP", "See entities through walls", Category.RENDER);
    }
    
    public boolean showPlayers() { return players && isEnabled(); }
    public boolean showMobs() { return mobs && isEnabled(); }
    public boolean showAnimals() { return animals && isEnabled(); }
    public boolean showItems() { return items && isEnabled(); }
    
    public void setPlayers(boolean v) { this.players = v; }
    public void setMobs(boolean v) { this.mobs = v; }
    public void setAnimals(boolean v) { this.animals = v; }
    public void setItems(boolean v) { this.items = v; }
}
