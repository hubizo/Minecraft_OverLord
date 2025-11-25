# 🎮 Minecraft OverLord

**Potężny overlay client dla Minecraft Java Edition** z nowoczesnym GUI i szerokim zestawem modułów.

![Minecraft](https://img.shields.io/badge/Minecraft-1.20.4-green)
![Fabric](https://img.shields.io/badge/Mod%20Loader-Fabric-blue)
![Java](https://img.shields.io/badge/Java-17+-orange)

---

## 📋 Spis treści

- [Funkcje](#-funkcje)
- [Instalacja](#-instalacja)
- [Sterowanie](#-sterowanie)
- [Lista modułów](#-lista-modułów)
- [Budowanie ze źródeł](#-budowanie-ze-źródeł)

---

## ✨ Funkcje

- 🎨 **Nowoczesne ClickGUI** - Przeciągane panele z kategoriami
- 📊 **HUD Overlay** - Watermark, lista aktywnych modułów, koordynaty, FPS
- 💾 **Auto-zapis konfiguracji** - Ustawienia zapisywane automatycznie
- 🌈 **Efekty Rainbow** - Animowane kolory w interfejsie
- ⚡ **35+ modułów** w 7 kategoriach

---

## 📦 Instalacja

### Wymagania
- Minecraft Java Edition 1.20.4
- [Fabric Loader](https://fabricmc.net/use/installer/) 0.15.0+
- [Fabric API](https://modrinth.com/mod/fabric-api)
- Java 17+

### Kroki instalacji

1. **Zainstaluj Fabric Loader**
   - Pobierz [Fabric Installer](https://fabricmc.net/use/installer/)
   - Uruchom i wybierz wersję 1.20.4

2. **Pobierz Fabric API**
   - Pobierz z [Modrinth](https://modrinth.com/mod/fabric-api)
   - Umieść w folderze `mods`

3. **Zainstaluj OverLord**
   - Pobierz `minecraft-overlord-1.0.0.jar`
   - Umieść w folderze `.minecraft/mods`

4. **Uruchom grę**
   - Wybierz profil Fabric w launcherze
   - Naciśnij **Right Shift** aby otworzyć GUI

---

## 🎮 Sterowanie

| Klawisz | Akcja |
|---------|-------|
| `Right Shift` | Otwórz/zamknij ClickGUI |
| `Lewy przycisk myszy` | Włącz/wyłącz moduł |
| `Prawy przycisk myszy` | Zwiń/rozwiń kategorię |
| `Przeciąganie` | Przesuń panel |

---

## 📚 Lista modułów

### ⚔️ Combat (Walka)
| Moduł | Opis |
|-------|------|
| **KillAura** | Automatycznie atakuje pobliskie entity |
| **TriggerBot** | Atakuje entity na które patrzysz |
| **AimAssist** | Pomaga celować w graczy |
| **AutoCrystal** | Automatycznie niszczy End Crystale |
| **Criticals** | Każdy atak jest krytyczny |
| **Reach** | Zwiększa zasięg ataku |
| **Velocity** | Redukuje knockback |
| **AutoTotem** | Automatycznie trzyma totem w offhand |

### 🏃 Movement (Ruch)
| Moduł | Opis |
|-------|------|
| **Flight** | Pozwala latać |
| **Speed** | Zwiększa prędkość ruchu |
| **Sprint** | Automatyczny sprint |
| **NoFall** | Brak obrażeń od upadku |
| **Step** | Wchodzenie na bloki bez skakania |
| **Jesus** | Chodzenie po wodzie |
| **Spider** | Wspinanie się po ścianach |
| **HighJump** | Wyższe skoki |

### 👁️ Render (Wizualne)
| Moduł | Opis |
|-------|------|
| **ESP** | Podświetlanie entity przez ściany |
| **Tracers** | Linie do entity |
| **FullBright** | Widzenie w ciemności |
| **ChestESP** | Podświetlanie skrzyń |
| **Nametags** | Ulepszone nametagi |
| **Trajectories** | Ścieżki pocisków |
| **XRay** | Widzenie rud przez bloki |
| **Freecam** | Swobodna kamera |

### 👤 Player (Gracz)
| Moduł | Opis |
|-------|------|
| **AutoEat** | Automatyczne jedzenie |
| **AutoArmor** | Automatyczne zakładanie zbroi |
| **FastPlace** | Szybsze stawianie bloków |
| **FastBreak** | Szybsze kopanie |
| **NoSlow** | Brak spowolnienia przy używaniu przedmiotów |
| **AutoFish** | Automatyczne łowienie ryb |
| **ChestStealer** | Automatyczne zabieranie z skrzyń |
| **Scaffold** | Automatyczne stawianie bloków pod sobą |

### 🌍 World (Świat)
| Moduł | Opis |
|-------|------|
| **Nuker** | Niszczy bloki wokół gracza |
| **AutoFarm** | Automatyczne zbieranie upraw |
| **AutoMine** | Automatyczne kopanie cennych rud |
| **Timer** | Zmiana prędkości gry |

### 💀 Exploit
| Moduł | Opis |
|-------|------|
| **Blink** | Kolejkowanie pakietów ruchu |
| **Phase** | Przechodzenie przez bloki |
| **PacketFly** | Latanie za pomocą pakietów |

### ⚙️ Misc (Różne)
| Moduł | Opis |
|-------|------|
| **AntiAFK** | Zapobiega wyrzuceniu za AFK |
| **AutoReconnect** | Automatyczne ponowne łączenie |
| **ClickGUI** | Otwiera menu |
| **HUD** | Wyświetla overlay informacyjny |

---

## 🔧 Budowanie ze źródeł

```bash
# Klonuj repozytorium
git clone https://github.com/hubizo/Minecraft_OverLord.git
cd Minecraft_OverLord

# Zbuduj projekt
./gradlew build

# Plik JAR znajdziesz w:
# build/libs/minecraft-overlord-1.0.0.jar
```

### Wymagania do budowania
- JDK 17+
- Gradle (wrapper dołączony)

---

## 📁 Struktura projektu

```
src/main/java/com/overlord/
├── OverLordClient.java      # Główna klasa moda
├── config/
│   └── ConfigManager.java   # Zarządzanie konfiguracją
├── gui/
│   ├── ClickGui.java        # Interfejs ClickGUI
│   └── HudRenderer.java     # Renderowanie HUD
├── mixin/
│   └── ...                  # Mixiny do modyfikacji gry
└── module/
    ├── Module.java          # Bazowa klasa modułu
    ├── Category.java        # Kategorie modułów
    ├── ModuleManager.java   # Zarządzanie modułami
    └── modules/
        ├── combat/          # Moduły walki
        ├── movement/        # Moduły ruchu
        ├── render/          # Moduły wizualne
        ├── player/          # Moduły gracza
        ├── world/           # Moduły świata
        ├── exploit/         # Moduły exploit
        └── misc/            # Moduły różne
```

---

## ⚠️ Ostrzeżenie

Ten mod jest przeznaczony **wyłącznie do celów edukacyjnych** i użytku na serwerach prywatnych. Używanie na serwerach publicznych może skutkować banem. Używasz na własne ryzyko.

---

## 📄 Licencja

MIT License - zobacz plik [LICENSE](LICENSE)

---

<p align="center">
  Made with ❤️ for Minecraft Java Edition
</p>
