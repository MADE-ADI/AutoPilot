# 🎨 AUTO PILOT - UI GUIDE & SCREENSHOTS

## 📱 Application Interface Overview

```
┌─────────────────────────────────────┐
│  📱 AUTO PILOT                       │
│                                      │
│  Auto Scroll untuk Media Sosial      │
│                                      │
├──────────────────────────────────────┤
│                                      │
│  ┌──────────────────────────────┐   │
│  │  Status Service              │   │
│  │  ✗ Tidak Aktif               │   │
│  │  ┌─────────────────────────┐ │   │
│  │  │ Aktifkan Service   > │ │   │
│  │  └─────────────────────────┘ │   │
│  └──────────────────────────────┘   │
│                                      │
└──────────────────────────────────────┘
```

## 🔴 Status: Service Tidak Aktif

Tampilan saat service belum diaktifkan:

```
┌─────────────────────────────────────┐
│  Status Service (Kartu Merah)       │
├─────────────────────────────────────┤
│  Status Service                      │
│  ✗ Tidak Aktif                       │
│  ┌─────────────────────────────┐    │
│  │  Aktifkan Service       →   │    │
│  └─────────────────────────────┘    │
│                                      │
│  Keterangan:                        │
│  - Kartu latar merah (#FFEBEE)      │
│  - Teks: "✗ Tidak Aktif" (merah)   │
│  - Tombol biru: "Aktifkan Service"  │
│  - Klik untuk buka Settings         │
└─────────────────────────────────────┘
```

## 🟢 Status: Service Aktif

Tampilan setelah service berhasil diaktifkan:

```
┌─────────────────────────────────────┐
│  Status Service (Kartu Hijau)       │
├─────────────────────────────────────┤
│  Status Service                      │
│  ✓ Aktif                             │
│  (Tidak ada tombol)                  │
│                                      │
│  Keterangan:                        │
│  - Kartu latar hijau (#E8F5E9)      │
│  - Teks: "✓ Aktif" (hijau gelap)   │
│  - Tampilan siap untuk kontrol      │
└─────────────────────────────────────┘
```

## ⚙️ Kontrol Autoscroll (Setelah Service Aktif)

```
┌──────────────────────────────────────┐
│  Kontrol Autoscroll (Kartu Putih)    │
├──────────────────────────────────────┤
│                                       │
│  Delay (ms):           2000 ms        │
│  ├────────●─────────────┤            │
│  │ 500ms        5000ms                │
│  │                                    │
│  Semakin kecil = semakin cepat scroll │
│                                       │
│  ┌─────────────────────────────────┐ │
│  │  Mulai Autoscroll         ▶     │ │  ← HIJAU
│  └─────────────────────────────────┘ │
│                                       │
│  Keterangan:                         │
│  - Slider range 500-5000ms          │
│  - Current value displayed           │
│  - Tombol hijau = Start              │
│  - Info text di bawah               │
└──────────────────────────────────────┘
```

## 🔴 Kontrol saat Autoscroll Berjalan

```
┌──────────────────────────────────────┐
│  Kontrol Autoscroll                  │
├──────────────────────────────────────┤
│                                       │
│  Delay (ms):           2000 ms        │
│  ├────────●─────────────┤            │
│  │ 500ms        5000ms                │
│  │                                    │
│  ● Autoscroll Berjalan...             │  ← Green indicator
│                                       │
│  ┌─────────────────────────────────┐ │
│  │  Hentikan Autoscroll      ◼     │ │  ← MERAH
│  └─────────────────────────────────┘ │
│                                       │
│  Keterangan:                         │
│  - Teks "Autoscroll Berjalan..."    │
│  - Green dot indicator              │
│  - Tombol berubah merah             │
│  - Slider masih bisa diubah         │
└──────────────────────────────────────┘
```

## ℹ️ Info Card (Features)

```
┌──────────────────────────────────────┐
│  Features Info (Kartu Abu-abu)       │
├──────────────────────────────────────┤
│  Fitur Human-Like:                   │
│                                       │
│  • Scroll dengan kecepatan random    │
│  • Delay antar scroll bervariasi    │
│  • Arah scroll mirip manusia        │
│  • Durasi gesture natural           │
│                                       │
│  Keterangan:                         │
│  - Background: #F5F5F5              │
│  - Penjelasan fitur human-like      │
│  - Bullet points jelas              │
│  - Text size kecil (11sp)           │
└──────────────────────────────────────┘
```

## 📐 Layout Structure

```
┌─────────────────────────────────────┐
│           HEADER (Top)              │
│  Auto Pilot (32sp, Bold, Primary)   │
│  Autoscroll untuk Media Sosial      │
│  (14sp, Gray)                       │
├─────────────────────────────────────┤
│         CONTENT (Scrollable)        │
│  1. Status Service Card             │
│     ├─ Green (Aktif) atau          │
│     └─ Red (Tidak Aktif)           │
│                                     │
│  2. Kontrol Autoscroll Card         │
│     ├─ Slider                      │
│     ├─ Status Text                 │
│     └─ Start/Stop Button           │
│                                     │
│  3. Info Card                      │
│     └─ Features list               │
└─────────────────────────────────────┘
```

## 🎨 Color Scheme

```
Primary Colors:
├─ Status Aktif: Green (#2E7D32)
├─ Status Tidak Aktif: Red (#C62828)
├─ Tombol Mulai: Green (#2E7D32)
├─ Tombol Hentikan: Red (#C62828)
├─ Card Aktif: Light Green (#E8F5E9)
└─ Card Tidak Aktif: Light Red (#FFEBEE)

Background Colors:
├─ Primary Background: Surface (Light gray)
├─ Card Background: White
├─ Control Card: Variant Surface
└─ Info Card: #F5F5F5

Text Colors:
├─ Header: Primary (Blue)
├─ Body: OnSurface (Black)
├─ Secondary: OnSurface 70% opacity
└─ Tertiary: OnSurface 50% opacity
```

## 📏 Size & Spacing Guide

```
Typography:
├─ Header: 32sp, Bold
├─ Subtitle: 14sp, Regular
├─ Card Title: 14sp, Semi-Bold
├─ Card Body: 12sp, Regular
└─ Info: 11sp, Regular

Spacing:
├─ Screen Padding: 16dp
├─ Card Padding: 16dp
├─ Between Cards: 16dp
├─ Between Items: 8-16dp
└─ Button Height: 48dp

Button:
├─ Height: 48dp
├─ Width: Full width
├─ Corner Radius: 4dp (default)
└─ Text: 16sp, Bold

Slider:
├─ Height: 4dp
├─ Thumb Radius: 4dp
├─ Range: 500-5000
└─ Step: 1 (continuous)
```

## 🎯 Interactive Flow

```
User Opens App
       ↓
┌──────────────────────────┐
│ Is Service Enabled?      │
└────────┬─────────────────┘
         │
         ├─ YES → Show Kontrol Card
         │         ├─ Show Slider
         │         ├─ Show Start Button
         │         └─ Show Info Card
         │
         └─ NO → Show Status Card
                  └─ Button "Aktifkan Service"
                     └─ Opens Settings
                        └─ User enables service
                           └─ Returns to App
                              └─ Shows Kontrol Card
```

## 📱 Responsive Design

```
Phone (default - 412dp wide):
├─ All elements stretch to fill
├─ Text readable
├─ Buttons easy to tap
└─ All content scrollable

Landscape:
├─ Vertical scroll still available
├─ Content adapts to width
└─ UI remains usable

Tablet (600dp+):
├─ Wider cards
├─ Better spacing
├─ Same layout structure
└─ Scales proportionally
```

## 🎬 Animation & Interactions

```
Slider:
├─ Smooth drag feedback
├─ Real-time value updates
└─ Haptic feedback (system)

Buttons:
├─ Ripple effect on tap
├─ Color change immediate
├─ State change instant
└─ Visual feedback on press

Status Updates:
├─ Text changes smooth
├─ Color transitions animate
├─ Live updates every 1 second
└─ No harsh changes

Card Layout:
├─ Vertical scroll smooth
├─ Content loads instantly
├─ No flicker
└─ Stable layout
```

## 🎨 Material Design 3 Elements

```
Components Used:
├─ Card (Material 3)
│  ├─ Elevation: 1dp
│  ├─ Corner Radius: 12dp
│  └─ Color: Dynamic
│
├─ Button (Material 3)
│  ├─ Style: Filled
│  ├─ Shape: RoundedCornerShape(4dp)
│  └─ Color: Dynamic
│
├─ Slider (Material 3)
│  ├─ Color: Primary
│  └─ Track color: Primary 30%
│
└─ Text (Material 3)
   ├─ Font: System default
   └─ Color: Dynamic
```

## 💡 Accessibility Features

```
Text Contrast:
├─ All text meets WCAG AA standards
├─ Primary on white: >7:1
└─ Secondary on white: >4.5:1

Touch Targets:
├─ Minimum 48dp x 48dp
├─ Buttons: 48dp
├─ Slider thumb: 4dp radius
└─ Good spacing between targets

Visual Indicators:
├─ Color ✓ (green/red)
├─ Text ✓ (explicit labels)
├─ Icon ✓ (✓ and ✗)
└─ Position ✓ (clear hierarchy)

Screen Reader Support:
├─ Content descriptions
├─ Semantic structure
├─ Announcement of changes
└─ Clear button labels
```

## 🔄 State Management Visual

```
Initial State:
├─ Service: OFF
├─ Card Color: RED
├─ Button: "Aktifkan Service"
└─ Controls: HIDDEN

After Enabling Service:
├─ Service: ON
├─ Card Color: GREEN
├─ Controls: VISIBLE
├─ Button: "Mulai Autoscroll" (GREEN)
└─ Slider: Active & adjustable

During Autoscroll:
├─ Button: "Hentikan Autoscroll" (RED)
├─ Status: "● Autoscroll Berjalan..."
├─ Slider: Still adjustable (live update)
└─ Visual: Button color changes

After Stopping:
├─ Button: "Mulai Autoscroll" (GREEN)
├─ Status: Hidden
├─ Slider: Ready for new value
└─ Back to "During Autoscroll" state ready
```

---

**UI Design follows Material Design 3 guidelines**
**Optimized for clarity and ease of use**
**Responsive to all screen sizes**
