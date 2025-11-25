package com.overlord.module.modules.misc;

import com.overlord.module.Category;
import com.overlord.module.Module;

public class AutoReconnect extends Module {
    private int delay = 5000; // 5 seconds
    
    public AutoReconnect() {
        super("AutoReconnect", "Automatically reconnects to server", Category.MISC);
    }
    
    public int getDelay() {
        return delay;
    }
    
    public void setDelay(int delay) {
        this.delay = delay;
    }
}
