package com.overlord.module.modules.combat;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class Velocity extends Module {
    private double horizontal = 0.0;
    private double vertical = 0.0;
    
    public Velocity() {
        super("Velocity", "Reduces knockback from attacks", Category.COMBAT);
    }
    
    public double getHorizontal() { return horizontal; }
    public double getVertical() { return vertical; }
    public void setHorizontal(double h) { this.horizontal = h; }
    public void setVertical(double v) { this.vertical = v; }
}
