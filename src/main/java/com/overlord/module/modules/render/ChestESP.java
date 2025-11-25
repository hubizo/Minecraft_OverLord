package com.overlord.module.modules.render;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class ChestESP extends Module {
    private boolean chests = true;
    private boolean enderChests = true;
    private boolean shulkers = true;
    private boolean barrels = true;
    
    public ChestESP() {
        super("ChestESP", "Highlight storage blocks", Category.RENDER);
    }
    
    public boolean showChests() { return chests && isEnabled(); }
    public boolean showEnderChests() { return enderChests && isEnabled(); }
    public boolean showShulkers() { return shulkers && isEnabled(); }
    public boolean showBarrels() { return barrels && isEnabled(); }
}
