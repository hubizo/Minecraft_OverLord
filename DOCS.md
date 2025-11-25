# Minecraft OverLord - Dokumentacja Techniczna

## Informacje o projekcie

| Parametr | Wartość |
|----------|---------|
| **Nazwa** | Minecraft OverLord |
| **Wersja** | 1.0.0 |
| **Minecraft** | 1.20.4 |
| **Mod Loader** | Fabric |
| **Java** | 17+ |
| **Fabric Loader** | 0.15.3+ |
| **Fabric API** | 0.97.0+1.20.4 |

---

## Struktura projektu

```
Minecraft_OverLord/
├── build.gradle                    # Konfiguracja Gradle
├── gradle.properties               # Właściwości projektu
├── settings.gradle                 # Ustawienia Gradle
├── README.md                       # Dokumentacja użytkownika
├── DOCS.md                         # Dokumentacja techniczna
└── src/main/
    ├── java/com/overlord/
    │   ├── OverLordClient.java     # Główny entrypoint
    │   ├── config/
    │   │   └── ConfigManager.java  # Zapis/odczyt konfiguracji
    │   ├── gui/
    │   │   ├── ClickGui.java       # Interfejs GUI
    │   │   └── HudRenderer.java    # Renderowanie HUD
    │   ├── mixin/
    │   │   ├── ClientPlayerEntityMixin.java
    │   │   ├── GameRendererMixin.java
    │   │   ├── InGameHudMixin.java
    │   │   ├── LightmapTextureManagerMixin.java
    │   │   └── WorldRendererMixin.java
    │   └── module/
    │       ├── Module.java         # Klasa bazowa modułu
    │       ├── Category.java       # Enum kategorii
    │       ├── ModuleManager.java  # Manager modułów
    │       └── modules/
    │           ├── combat/         # 8 modułów
    │           ├── movement/       # 8 modułów
    │           ├── render/         # 8 modułów
    │           ├── player/         # 8 modułów
    │           ├── world/          # 4 moduły
    │           ├── exploit/        # 3 moduły
    │           └── misc/           # 4 moduły
    └── resources/
        ├── fabric.mod.json         # Metadane moda
        ├── overlord.mixins.json    # Konfiguracja mixinów
        └── overlord.accesswidener  # Access widener
```

---

## Główne klasy

### OverLordClient.java
Główny entrypoint moda, implementuje `ClientModInitializer`.

```java
public class OverLordClient implements ClientModInitializer {
    public static final String MOD_ID = "overlord";
    public static final String MOD_NAME = "OverLord";
    
    private ModuleManager moduleManager;
    private ConfigManager configManager;
    private ClickGui clickGui;
    private HudRenderer hudRenderer;
    
    @Override
    public void onInitializeClient() {
        // Inicjalizacja wszystkich komponentów
    }
}
```

**Funkcje:**
- Inicjalizacja managerów
- Rejestracja keybindów (Right Shift dla GUI)
- Rejestracja eventów tick i HUD render
- Ładowanie konfiguracji

---

### Module.java
Abstrakcyjna klasa bazowa dla wszystkich modułów.

```java
public abstract class Module {
    protected final MinecraftClient mc;
    
    private final String name;
    private final String description;
    private final Category category;
    private boolean enabled;
    private int keyBind;
    
    // Metody lifecycle
    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
    public void onRender() {}
}
```

---

### Category.java
Enum definiujący kategorie modułów.

| Kategoria | Ikona | Kolor |
|-----------|-------|-------|
| COMBAT | ⚔ | Czerwony (#E74C3C) |
| MOVEMENT | 🏃 | Niebieski (#3498DB) |
| RENDER | 👁 | Fioletowy (#9B59B6) |
| PLAYER | 👤 | Zielony (#2ECC71) |
| WORLD | 🌍 | Pomarańczowy (#F39C12) |
| EXPLOIT | 💀 | Teal (#1ABC9C) |
| MISC | ⚙ | Szary (#95A5A6) |

---

### ModuleManager.java
Zarządza wszystkimi modułami.

**Metody:**
- `getModules()` - zwraca wszystkie moduły
- `getModulesByCategory(Category)` - moduły w kategorii
- `getModuleByName(String)` - moduł po nazwie
- `getEnabledModules()` - aktywne moduły
- `onTick()` - wywołuje tick na aktywnych modułach

---

### ConfigManager.java
Zarządza zapisem/odczytem konfiguracji.

**Lokalizacja:** `.minecraft/config/overlord/config.json`

**Format:**
```json
{
  "modules": {
    "KillAura": {
      "enabled": false,
      "keybind": -1
    },
    "Flight": {
      "enabled": true,
      "keybind": 70
    }
  }
}
```

---

## Mixiny

| Mixin | Cel | Funkcja |
|-------|-----|---------|
| `ClientPlayerEntityMixin` | `ClientPlayerEntity` | Velocity, HighJump |
| `GameRendererMixin` | `GameRenderer` | Reach, Freecam |
| `InGameHudMixin` | `InGameHud` | HUD overlay |
| `LightmapTextureManagerMixin` | `LightmapTextureManager` | FullBright |
| `WorldRendererMixin` | `WorldRenderer` | ESP, Tracers |

---

## Access Widener

```
accessWidener v2 named

accessible field net/minecraft/client/MinecraftClient itemUseCooldown I
mutable field net/minecraft/client/MinecraftClient itemUseCooldown I
```

Umożliwia modyfikację cooldownu używania przedmiotów (FastPlace).

---

## Lista wszystkich modułów (43)

### Combat (8)
1. **KillAura** - Auto-atak na entity w zasięgu
2. **TriggerBot** - Atak na entity w crosshairze
3. **AimAssist** - Wspomaganie celowania
4. **AutoCrystal** - Auto-niszczenie kryształów
5. **Criticals** - Wymuszanie critical hitów
6. **Reach** - Zwiększony zasięg ataku
7. **Velocity** - Redukcja knockbacku
8. **AutoTotem** - Auto-equip totemu

### Movement (8)
1. **Flight** - Latanie
2. **Speed** - Zwiększona prędkość
3. **Sprint** - Auto-sprint
4. **NoFall** - Brak fall damage
5. **Step** - Auto-step na bloki
6. **Jesus** - Chodzenie po wodzie
7. **Spider** - Wspinanie po ścianach
8. **HighJump** - Wyższe skoki

### Render (8)
1. **ESP** - Podświetlanie entity
2. **Tracers** - Linie do entity
3. **FullBright** - Pełna jasność
4. **ChestESP** - Podświetlanie skrzyń
5. **Nametags** - Ulepszone nametagi
6. **Trajectories** - Trajektorie pocisków
7. **XRay** - Widzenie rud
8. **Freecam** - Wolna kamera

### Player (8)
1. **AutoEat** - Auto-jedzenie
2. **AutoArmor** - Auto-zbroja
3. **FastPlace** - Szybkie stawianie
4. **FastBreak** - Szybkie kopanie
5. **NoSlow** - Brak spowolnienia
6. **AutoFish** - Auto-łowienie
7. **ChestStealer** - Auto-kradzież z skrzyń
8. **Scaffold** - Auto-bridge

### World (4)
1. **Nuker** - Niszczenie bloków
2. **AutoFarm** - Auto-farma
3. **AutoMine** - Auto-kopanie rud
4. **Timer** - Modyfikacja tickrate

### Exploit (3)
1. **Blink** - Kolejkowanie pakietów
2. **Phase** - Przechodzenie przez bloki
3. **PacketFly** - Latanie pakietami

### Misc (4)
1. **AntiAFK** - Anty-AFK
2. **AutoReconnect** - Auto-reconnect
3. **ClickGUI** - Menu GUI
4. **HUD** - Overlay HUD

---

## GUI

### ClickGui
- Panele kategorii z drag & drop
- Lewy klik - toggle modułu
- Prawy klik - zwiń/rozwiń kategorię
- Auto-save przy zamknięciu

### HudRenderer
- **Watermark** - "OverLord v1.0.0" z efektem rainbow
- **ArrayList** - Lista aktywnych modułów (sortowana po szerokości)
- **Coords** - Pozycja XYZ + kierunek
- **FPS** - Licznik FPS z kolorami (zielony/żółty/czerwony)

---

## Budowanie

```bash
# Wyczyść i zbuduj
./gradlew clean build

# Uruchom klienta testowego
./gradlew runClient

# Generuj źródła Minecraft
./gradlew genSources
```

**Output:** `build/libs/minecraft-overlord-1.0.0.jar`

---

## Zależności

```groovy
dependencies {
    minecraft "com.mojang:minecraft:1.20.4"
    mappings "net.fabricmc:yarn:1.20.4+build.3:v2"
    modImplementation "net.fabricmc:fabric-loader:0.15.3"
    modImplementation "net.fabricmc.fabric-api:fabric-api:0.97.0+1.20.4"
}
```

---

## Eventy

| Event | Użycie |
|-------|--------|
| `ClientTickEvents.END_CLIENT_TICK` | Tick modułów, sprawdzanie klawiszy |
| `HudRenderCallback.EVENT` | Renderowanie HUD overlay |

---

## Konfiguracja Gradle

| Właściwość | Wartość |
|------------|---------|
| `minecraft_version` | 1.20.4 |
| `yarn_mappings` | 1.20.4+build.3 |
| `loader_version` | 0.15.3 |
| `fabric_version` | 0.97.0+1.20.4 |
| `mod_version` | 1.0.0 |
| `maven_group` | com.overlord |
| `archives_base_name` | minecraft-overlord |

---

## Licencja

MIT License
