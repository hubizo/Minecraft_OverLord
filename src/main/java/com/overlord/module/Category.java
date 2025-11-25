package com.overlord.module;

public enum Category {
    COMBAT("Combat", "⚔", 0xFFE74C3C),      // Red
    MOVEMENT("Movement", "🏃", 0xFF3498DB),  // Blue
    RENDER("Render", "👁", 0xFF9B59B6),      // Purple
    PLAYER("Player", "👤", 0xFF2ECC71),      // Green
    WORLD("World", "🌍", 0xFFF39C12),        // Orange
    EXPLOIT("Exploit", "💀", 0xFF1ABC9C),    // Teal
    MISC("Misc", "⚙", 0xFF95A5A6);          // Gray
    
    private final String displayName;
    private final String icon;
    private final int color;
    
    Category(String displayName, String icon, int color) {
        this.displayName = displayName;
        this.icon = icon;
        this.color = color;
    }
    
    public String getDisplayName() { return displayName; }
    public String getIcon() { return icon; }
    public int getColor() { return color; }
}
