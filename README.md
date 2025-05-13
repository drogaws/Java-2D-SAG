# 🥗 2DSAG - 2D Salad and Go Simulator

A 2D simulation game inspired by real-life **Salad and Go** operations.  
Play as a single store manager handling the drive-thru.  
Take customer orders, build salads, and serve them — fast and correctly.

---

## 🎮 Gameplay Features (Current MVP)

- 🚗 Customers queue in the drive-thru  
- 🧾 Random orders are generated  
- 🥗 Build and serve salads  
- 🕹️ Player movement and interaction  
- 📋 Order accuracy checking

---

## 🔜 Planned Features

- 🧍 Employee management and training  
- 📈 Store performance system  
- 🧼 Kitchen prep and cleaning tasks  
- 🔊 Sound effects and music  
- 🧠 Rush hour and random events  

---

## 📸 Screenshots

> Coming soon...

---

## ⚙️ Built With

- **Language**: Java  
- **Game Engine**: Custom Java 2D rendering  
- **IDE**: VS Code  

---

## 📁 Folder Structure

```bash
src/
├── main/
│   ├── Main.java              # Game launcher
│   ├── GamePanel.java         # Core update & render loop
│   └── KeyHandler.java        # Keyboard input logic
│
├── entity/
│   ├── Entity.java            # Base class for all moving characters
│   ├── Player.java            # Player logic
│   └── Customer.java          # Customer and order requester
│
├── object/
│   └── OBJ_Salad.java         # Salad construction logic
│
├── tile/
│   ├── Tile.java              # Tile data
│   └── TileManager.java       # Map layout and drawing
│
├── order/
│   ├── Order.java             # Order data
│   └── OrderManager.java      # Order tracking and validation
│
├── ui/
│   └── UI.java                # Order display and UI text
│
└── utils/
    └── CollisionChecker.java  # Prevent walking through walls
