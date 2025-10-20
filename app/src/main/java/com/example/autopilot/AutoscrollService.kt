package com.example.autopilot

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.os.Handler
import android.os.Looper
import android.view.accessibility.AccessibilityEvent
import kotlin.random.Random

class AutoscrollService : AccessibilityService() {

    private val handler = Handler(Looper.getMainLooper())
    private var isAutoscrolling = false
    private var scrollRunnable: Runnable? = null
    private var currentScrollDelay = 2000L // 2 seconds default
    private lateinit var validator: AutoscrollValidator
    private var currentPackageName: String? = null

    override fun onCreate() {
        super.onCreate()
        validator = AutoscrollValidator(this)
        DebugLogger.i("AutoscrollService created")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Deteksi aplikasi yang sedang aktif
        if (event != null) {
            val newPackageName = event.packageName?.toString()
            
            // SKIP system UI dan overlay (volume bar, notification, dll)
            if (newPackageName == "com.android.systemui" || 
                newPackageName == "android" ||
                newPackageName?.contains("inputmethod") == true) {
                // Ignore system overlays, jangan ubah currentPackageName
                return
            }
            
            // Cek jika ada perubahan aplikasi
            if (newPackageName != currentPackageName) {
                val oldPackage = currentPackageName
                currentPackageName = newPackageName
                
                DebugLogger.d("App changed: $oldPackage -> $newPackageName")
                
                // AUTO START jika masuk aplikasi yang diizinkan
                if (currentPackageName != null && AllowedApps.isPackageAllowed(currentPackageName)) {
                    if (!isAutoscrolling) {
                        DebugLogger.i("Auto-starting scroll for ${AllowedApps.getPackageDisplayName(currentPackageName!!)}")
                        startAutoscroll(2000L)
                    }
                } else {
                    // AUTO STOP jika keluar dari aplikasi yang diizinkan
                    if (isAutoscrolling && oldPackage != null && AllowedApps.isPackageAllowed(oldPackage)) {
                        DebugLogger.i("Left allowed app, auto-stopping scroll")
                        stopAutoscroll()
                    }
                }
            }
        }
    }

    override fun onInterrupt() {
        DebugLogger.d("Service interrupted")
        stopAutoscroll()
    }

    fun startAutoscroll(delayMs: Long = 2000) {
        if (isAutoscrolling) {
            DebugLogger.w("Autoscroll already running")
            return
        }

        // Validasi package saat ini (skip validasi jika dipanggil otomatis)
        if (!AllowedApps.isPackageAllowed(currentPackageName)) {
            DebugLogger.e("Current package not allowed: $currentPackageName")
            return
        }

        isAutoscrolling = true
        currentScrollDelay = delayMs
        DebugLogger.i("Autoscroll started - Package: ${AllowedApps.getPackageDisplayName(currentPackageName!!)}, Delay: $delayMs ms")
        
        performScroll()
    }

    fun stopAutoscroll() {
        if (!isAutoscrolling) return
        
        isAutoscrolling = false
        scrollRunnable?.let { handler.removeCallbacks(it) }
        DebugLogger.i("Autoscroll stopped")
    }

    private fun performScroll() {
        if (!isAutoscrolling) return

        // Verifikasi masih di aplikasi yang diizinkan
        if (!AllowedApps.isPackageAllowed(currentPackageName)) {
            DebugLogger.w("App changed to: $currentPackageName, stopping scroll")
            stopAutoscroll()
            return
        }

        try {
            // ALWAYS scroll UP for reels/shorts (swipe from bottom to top)
            val isScrollUp = true

            // Random scroll speed (human-like variation)
            val scrollSpeed = Random.nextInt(100, 400).toFloat() // 100-400 pixels

            // Create scroll gesture
            val gesture = createScrollGesture(isScrollUp, scrollSpeed)

            DebugLogger.d("Performing scroll - Direction: ${if (isScrollUp) "UP" else "DOWN"}, Speed: ${scrollSpeed.toInt()}px")

            dispatchGesture(gesture, object : GestureResultCallback() {
                override fun onCompleted(gestureDescription: GestureDescription?) {
                    super.onCompleted(gestureDescription)
                    DebugLogger.d("Gesture completed successfully")
                    scheduleNextScroll()
                }

                override fun onCancelled(gestureDescription: GestureDescription?) {
                    super.onCancelled(gestureDescription)
                    DebugLogger.w("Gesture cancelled")
                    if (isAutoscrolling) {
                        scheduleNextScroll()
                    }
                }
            }, null)
        } catch (e: Exception) {
            DebugLogger.e("Error performing scroll", e)
            if (isAutoscrolling) {
                scheduleNextScroll()
            }
        }
    }

    private fun createScrollGesture(scrollUp: Boolean, distance: Float): GestureDescription {
        val startY = 1200f // Mulai dari bawah
        val endY = if (scrollUp) startY - distance else startY + distance // UP = swipe dari bawah ke atas
        val centerX = 500f

        val path = Path().apply {
            moveTo(centerX, startY)
            lineTo(centerX, endY)
        }

        // Variable swipe duration for human-like behavior (300-800ms)
        val duration = Random.nextLong(300, 800)

        return GestureDescription.Builder().apply {
            addStroke(GestureDescription.StrokeDescription(path, 0L, duration))
        }.build()
    }

    private fun scheduleNextScroll() {
        if (!isAutoscrolling) return

        // Human-like random delay variation
        // Base delay ± 30% random variation
        val variation = (currentScrollDelay * 0.3).toLong()
        val nextDelay = currentScrollDelay + Random.nextLong(-variation, variation)
        val finalDelay = maxOf(1000L, nextDelay) // Minimum 1 second

        scrollRunnable = Runnable {
            performScroll()
        }

        DebugLogger.d("Next scroll scheduled in ${finalDelay}ms")
        handler.postDelayed(scrollRunnable!!, finalDelay)
    }

    private fun showNotification(message: String) {
        DebugLogger.i("Notification: $message")
    }

    companion object {
        private const val TAG = "AutoscrollService"
        
        // Singleton instance untuk akses dari MainActivity
        private var instance: AutoscrollService? = null
        
        fun getInstance(): AutoscrollService? = instance
    }
    
    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
        DebugLogger.i("Service connected and instance stored")
    }
    
    override fun onDestroy() {
        instance = null
        super.onDestroy()
        DebugLogger.i("Service destroyed")
    }
}
