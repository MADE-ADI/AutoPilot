# AUTO PILOT - ARCHITECTURE & COMPONENTS

## 🏗️ Architecture Overview

```
┌─────────────────────────────────────────────────────┐
│         MainActivity (UI - Jetpack Compose)         │
│  - Status display                                    │
│  - Control buttons                                   │
│  - Settings sliders                                  │
└────────────────┬────────────────────────────────────┘
                 │
                 ├─→ AutoscrollServiceManager
                 │   - Check service status
                 │   - Open settings
                 │
                 └─→ AutoscrollServiceImpl
                     - Manage autoscroll
                     - Schedule gestures
                     - Random delays
                     │
                     └─→ AutoscrollService (Accessibility)
                         - Perform gestures
                         - Dispatch scroll actions
                         - Handle callbacks
```

## 📦 Key Components

### 1. **MainActivity.kt**
```
Tanggung Jawab:
- Render UI dengan Jetpack Compose
- Menampilkan status service
- Slider untuk kontrol delay
- Tombol Start/Stop
- Real-time status updates
```

### 2. **AutoscrollServiceImpl.kt**
```
Tanggung Jawab:
- Logika autoscroll
- Scheduling timing
- Random delay variation
- Handler untuk async execution
- Tidak bergantung pada accessibility service
```

### 3. **AutoscrollService.kt** (Accessibility Service)
```
Tanggung Jawab:
- Implementasi AccessibilityService
- Perform actual gestures
- Random scroll speed (100-400px)
- Random directions (90% down, 10% up)
- Natural gesture duration (300-800ms)
- Dispatch gesture callbacks
```

### 4. **AutoscrollServiceManager.kt**
```
Tanggung Jawab:
- Check apakah service enabled
- Open accessibility settings
- Query enabled services
- Status management
```

### 5. **AutoscrollConfig.kt**
```
Tanggung Jawab:
- Centralized configuration
- Delay profiles (fast, normal, slow)
- App-specific presets
- Constants & defaults
```

## 🔄 Data Flow

### User Mengklik "Mulai Autoscroll"

```
1. MainActivity detects button click
   ↓
2. AutoscrollServiceImpl.startAutoscroll(delay) called
   ↓
3. performSimulatedScroll() scheduled
   ↓
4. scheduleNextScroll() dengan random delay
   ↓
5. Handler postDelayed() dengan calculated delay
   ↓
6. Loop kembali ke step 3 sampai stopAutoscroll() dipanggil
```

### Actual Gesture Execution (Accessibility Service)

```
1. AutoscrollService.performScroll() called
   ↓
2. Generate random parameters:
   - Scroll direction (90% down, 10% up)
   - Distance (100-400px)
   - Duration (300-800ms)
   ↓
3. Create Path untuk gesture
   ↓
4. Build GestureDescription
   ↓
5. dispatchGesture() untuk perform action
   ↓
6. Callback success → schedule next scroll
   ↓
7. Random delay applied sebelum next gesture
```

## 🎲 Human-Like Algorithm

### Random Speed
```kotlin
val scrollSpeed = Random.nextInt(100, 400).toFloat()
// Hasil: 100-400 pixels per gesture
// Berbeda setiap kali
```

### Variable Delays
```kotlin
val variation = (currentScrollDelay * 0.3).toLong()
val nextDelay = currentScrollDelay + Random.nextLong(-variation, variation)
// Hasil: ± 30% dari base delay
// Tidak fixed interval
```

### Natural Directions
```kotlin
val isScrollDown = Random.nextFloat() > 0.1f
// Hasil: 90% down, 10% up
// Kadang-kadang scroll up
```

### Gesture Duration
```kotlin
val duration = Random.nextLong(300, 800)
// Hasil: 300-800ms
// Natural swipe speed
```

## 📋 State Management

```
MainScreen State:
├── isAutoscrolling: Boolean
│   ├── false → Show "Mulai Autoscroll" button
│   └── true → Show "Hentikan Autoscroll" button
│
├── serviceEnabled: Boolean
│   ├── true → Show controls
│   └── false → Show "Aktifkan Service" button
│
└── scrollDelay: Float (500-5000)
    └── Slider value for user control
```

## 🔐 Permission Flow

```
User Opens App
    ↓
Check if service enabled
    ├─ YES → Show controls
    ├─ NO → Show "Aktifkan Service"
        ↓
        User clicks "Aktifkan Service"
        ↓
        Intent to Accessibility Settings
        ↓
        User finds AutoscrollService
        ↓
        User toggles ON
        ↓
        System grants BIND_ACCESSIBILITY_SERVICE
        ↓
        User returns to app
        ↓
        Controls now available
```

## 📊 Performance Considerations

| Metric | Value | Impact |
|--------|-------|--------|
| Gesture Frequency | 2-5 per minute | Low battery impact |
| Handler threads | 1 (Main) | No thread overhead |
| Memory usage | ~20-30MB | Lightweight |
| CPU usage | Minimal | Only during gesture |
| Network | None | No data transfer |

## 🔄 Threading Model

```
Main Thread (Handler):
├── UI updates (Compose)
├── Scheduling delays
└── Gesture dispatch
    ↓
Accessibility Framework:
├── Receives gesture
├── Processes gesture
└── Interacts with apps
```

## 🛡️ Error Handling

```
performScroll()
    ├─ onCompleted() → scheduleNextScroll()
    ├─ onCancelled() → log warning + retry
    └─ Exception → caught by framework
```

## 🧪 Testing Points

- [ ] Service activation flow
- [ ] Delay variation calculation
- [ ] Random number generation
- [ ] Gesture creation
- [ ] UI state updates
- [ ] Permission checking
- [ ] Background execution

## 📈 Scalability

Future enhancements could include:
- Click/like automation
- Specific app profiles
- Recording & playback
- Custom gesture builder
- Advanced analytics
- Machine learning patterns

---

**Architecture designed for simplicity, reliability, and extensibility**
