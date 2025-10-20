# 🚀 AUTO PILOT - PANDUAN LENGKAP (BAHASA INDONESIA)

## 📱 Tentang Aplikasi

**Auto Pilot** adalah aplikasi Android yang dapat melakukan autoscroll (scroll otomatis) pada aplikasi media sosial dan aplikasi lainnya dengan perilaku yang mirip manusia.

### Fitur Utama:
- ✅ Autoscroll otomatis dengan berbagai kecepatan
- ✅ Perilaku mirip manusia (human-like behavior)
- ✅ Kontrol mudah dengan slider
- ✅ Berfungsi di background
- ✅ Support semua aplikasi dengan scroll gesture

## 📋 Syarat & Ketentuan

- **Android Version**: Android 13+ (API 33+)
- **Device**: Smartphone atau Emulator
- **Storage**: ~50MB untuk install
- **RAM**: Minimal 2GB

## 🔧 Langkah Instalasi

### 1️⃣ Build APK

Buka Terminal/PowerShell di folder project dan ketik:

```powershell
.\gradlew build
```

Tunggu hingga selesai (biasanya 2-5 menit).

### 2️⃣ Install ke Device

```powershell
.\gradlew installDebug
```

Aplikasi akan langsung terinstall di device/emulator yang terkoneksi.

### 3️⃣ Aktifkan Accessibility Service

**PENTING**: Aplikasi ini memerlukan Accessibility Service agar berfungsi.

#### Langkah:
1. **Buka aplikasi "Auto Pilot"** di device Anda
2. **Perhatikan kartu yang merah** yang bertuliskan "Status Service" → **"✗ Tidak Aktif"**
3. **Klik tombol "Aktifkan Service"** (tombol biru)
4. Android akan membuka **Settings → Accessibility**
5. **Cari "Auto Pilot" atau "AutoscrollService"** di daftar aplikasi
6. **Tap aplikasi tersebut** untuk membuka detailnya
7. **Klik toggle/switch untuk mengaktifkan**
8. **Berikan semua permission** yang diminta (Allow)
9. **Kembali ke aplikasi Auto Pilot** (tekan back atau home)
10. Sekarang kartu merah berubah hijau → **"✓ Aktif"** ✅

## 🎮 Cara Menggunakan

### Langkah 1: Atur Kecepatan
```
Geser slider "Delay" ke posisi yang diinginkan:
- Kecil = Cepat (500ms-1500ms)
- Sedang = Normal (1500ms-2500ms)  ← REKOMENDASI
- Besar = Lambat (2500ms-5000ms)
```

### Langkah 2: Mulai Autoscroll
```
1. Klik tombol hijau "Mulai Autoscroll"
2. Tombol berubah merah "Hentikan Autoscroll"
3. Terlihat text "● Autoscroll Berjalan..."
```

### Langkah 3: Buka Aplikasi Sosial
```
1. Buka Instagram, TikTok, Facebook, dll
2. Aplikasi akan mulai scroll otomatis 🎉
3. Scroll akan terus berlanjut selama service berjalan
```

### Langkah 4: Hentikan
```
Klik tombol merah "Hentikan Autoscroll" kapan saja
```

## ⚙️ Pengaturan Kecepatan

### Rekomendasi Berdasarkan Aplikasi:

| Aplikasi | Delay | Alasan |
|----------|-------|--------|
| **Instagram** | 1500ms | Feed balanced |
| **TikTok** | 2500ms | Video lebih panjang |
| **Twitter/X** | 1800ms | Konten cepat |
| **Facebook** | 2000ms | Mixed content |
| **YouTube** | 3000ms | Watch time |
| **Reddit** | 1600ms | Text heavy |

### Kategori Umum:

| Kategori | Range | Gunakan Untuk |
|----------|-------|---------------|
| **Sangat Cepat** | 500-1000ms | Testing, Demo |
| **Cepat** | 1000-1500ms | Browsing cepat |
| **Normal** ⭐ | 1500-2500ms | Penggunaan umum |
| **Lambat** | 2500-4000ms | Reading mode |
| **Sangat Lambat** | 4000-5000ms | Melihat detail |

## 🎯 Human-Like Behavior (Perilaku Mirip Manusia)

Aplikasi ini dirancang agar scroll terlihat natural, bukan robot:

### 1. 🎲 Kecepatan Random
- Setiap scroll punya kecepatan berbeda
- Tidak selalu sama (100-400 pixel)
- Terasa alami

### 2. ⏱️ Delay Bervariasi
- Waktu antar scroll tidak fixed
- ± 30% dari nilai yang diatur
- Bukan perfect rhythm seperti robot

### 3. ↕️ Arah Random
- Sebagian besar scroll ke bawah (90%)
- Sesekali scroll ke atas (10%)
- Seperti manusia yang scroll santai

### 4. 🎬 Gesture Natural
- Durasi gesture 300-800ms
- Simulasi kecepatan swipe manusia
- Bukan instant jump

## 🐛 Troubleshooting (Jika Ada Masalah)

### ❌ Service tidak aktif / tidak muncul di Accessibility

**Solusi:**
1. Tutup aplikasi sepenuhnya
2. Buka ulang aplikasi Auto Pilot
3. Coba klik "Aktifkan Service" lagi
4. Jika masih tidak muncul, restart device

### ❌ Scroll tidak bekerja padahal service sudah aktif

**Solusi:**
1. Pastikan benar-benar klik "Mulai Autoscroll" (tombol berubah merah)
2. Pastikan aplikasi target support scroll gesture
3. Coba aplikasi lain untuk test
4. Tutup app lain yang berat

### ❌ Aplikasi terasa lambat / lag

**Solusi:**
1. Tingkatkan delay (geser slider ke kanan)
2. Tutup aplikasi background lain
3. Clear cache: Buka Settings → Apps → Auto Pilot → Storage → Clear Cache
4. Restart device

### ❌ Scroll tidak konsisten

**Solusi:**
- Ini normal! Perilaku human-like yang berarti scroll alami
- Jika perlu lebih konsisten, kurangi delay variation

## 📊 Contoh Penggunaan

### Scenario 1: Scroll Instagram
```
1. Buka Auto Pilot
2. Set delay ke 1500ms
3. Klik "Mulai Autoscroll"
4. Buka Instagram
5. Lihat feed scroll otomatis
6. Setelah selesai, klik "Hentikan"
```

### Scenario 2: Testing Konten
```
1. Set delay ke 800ms (sangat cepat)
2. Klik "Mulai"
3. Buka app target
4. Monitor scroll behavior
5. Adjust delay jika perlu
```

### Scenario 3: Background Scrolling
```
1. Set delay ke 2500ms
2. Klik "Mulai"
3. Buka browser
4. Keluar dari app (minimize)
5. Service tetap berjalan di background
6. Kembali ke app, klik "Hentikan"
```

## 🔒 Keamanan & Privasi

### Permission yang Diminta:

1. **BIND_ACCESSIBILITY_SERVICE** 
   - Diperlukan untuk perform gesture
   - Dari Android official framework

2. **SYSTEM_ALERT_WINDOW**
   - Optional (standar Android)
   - Untuk overlay jika diperlukan

3. **INTERNET**
   - Basic permission
   - Untuk mungkin future features

### Catatan Keamanan:
- ✅ Aplikasi tidak mengakses data pribadi
- ✅ Tidak ada koneksi internet saat scroll
- ✅ Semua proses lokal di device
- ✅ Tidak install background app lain

## 📝 Tips & Trik

### 💡 Tip 1: Gunakan Profil untuk Berbagai App
- Instagram: 1500ms
- TikTok: 2500ms
- YouTube: 3000ms
- Catat favorit Anda!

### 💡 Tip 2: Monitor Battery
- Autoscroll tidak berat untuk battery
- Tapi kalau intensive, gunakan delay lebih besar

### 💡 Tip 3: Test di Emulator
- Gunakan Android Emulator untuk testing
- Tidak perlu device fisik

### 💡 Tip 4: Lock Screen
- Service tetap jalan saat lock screen
- Buat apa yang perlu ada berjalan di background

### 💡 Tip 5: Kombinasi dengan Lainnya
- Bisa digunakan dengan app lain
- Service bekerja universal

## 🆘 Pertanyaan Umum (FAQ)

**Q: Apakah bisa di-uninstall dan install ulang?**
A: Ya, bisa. Tapi perlu aktifkan service lagi setelah install ulang.

**Q: Bagaimana jika device disconnect dari power?**
A: Service akan terus berjalan di battery. Tapi perhatikan battery health.

**Q: Bisa lock screen sambil scroll?**
A: Ya, bisa. Service bekerja di background, lock screen tidak masalah.

**Q: Apakah aplikasi ini legal?**
A: Ya. Menggunakan official Android API. Untuk penggunaan pribadi & testing.

**Q: Butuh internet?**
A: Tidak. Semuanya offline dan lokal.

**Q: Bisa remote control?**
A: Saat ini tidak. Hanya local control lewat UI app.

## 🎓 Untuk Developer

Jika ingin custom atau develop lebih lanjut:

- **Bahasa**: Kotlin
- **UI Framework**: Jetpack Compose
- **Accessibility**: Android AccessibilityService API
- **Source Code**: Tersedia di folder `src/main/java`
- **File Penting**: 
  - `MainActivity.kt` - UI
  - `AutoscrollService.kt` - Main service
  - `AutoscrollConfig.kt` - Configuration

Lihat `ARCHITECTURE.md` untuk dokumentasi lengkap.

## 📞 Support & Feedback

Jika ada masalah:
1. Check troubleshooting section
2. Baca logcat (Android Studio)
3. Restart app & device

---

**Dibuat dengan ❤️ menggunakan Kotlin & Jetpack Compose**

**Terakhir diupdate: Oktober 2025**
