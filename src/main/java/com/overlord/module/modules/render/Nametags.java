package com.overlord.module.modules.render;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class Nametags extends Module {
    private boolean showHealth = true;
    private boolean showArmor = true;
    private boolean showDistance = true;
    private double scale = 1.5;
    
    public Nametags() {
        super("Nametags", "Enhanced player nametags", Category.RENDER);
    }
    
    public boolean showHealth() { return showHealth && isEnabled(); }
    public boolean showArmor() { return showArmor && isEnabled(); }
    public boolean showDistance() { return showDistance && isEnabled(); }
    public double getScale() { return scale; }
}
