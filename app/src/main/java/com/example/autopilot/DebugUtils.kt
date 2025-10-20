package com.example.autopilot

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.util.Log

/**
 * Debugging utility untuk membantu troubleshooting autoscroll
 */
object DebugLogger {
    private const val TAG = "AutoPilot"
    private var isDebugEnabled = true

    fun d(message: String) {
        if (isDebugEnabled) {
            Log.d(TAG, message)
        }
    }

    fun e(message: String, throwable: Throwable? = null) {
        if (isDebugEnabled) {
            Log.e(TAG, message, throwable)
        }
    }

    fun w(message: String) {
        if (isDebugEnabled) {
            Log.w(TAG, message)
        }
    }

    fun i(message: String) {
        if (isDebugEnabled) {
            Log.i(TAG, message)
        }
    }
}

/**
 * Daftar aplikasi yang diizinkan untuk autoscroll
 */
object AllowedApps {
    private val ALLOWED_PACKAGES = setOf(
        // Instagram
        "com.instagram.android",
        "com.instagram.canary",
        
        // TikTok
        "com.zhiliaoapp.musically",
        "com.ss.android.ugc.tiktok",
        
        // Facebook
        "com.facebook.katana",
        
        // Twitter / X
        "com.twitter.android",
        "com.twitter.android.lite",
        
        // YouTube
        "com.google.android.youtube",
        "com.google.android.youtube.tv",
        
        // Reddit
        "com.reddit.frontpage",
        
        // Telegram
        "org.telegram.messenger",
        "org.telegram.messenger.web",
        
        // WhatsApp
        "com.whatsapp",
        "com.whatsapp.w4b",
        
        // Snapchat
        "com.snapchat.android",
        
        // LinkedIn
        "com.linkedin.android",
        
        // Threads
        "com.instagram.threads",
        
        // Bluesky
        "xyz.blueskyweb.app"
    )

    fun isPackageAllowed(packageName: String?): Boolean {
        if (packageName == null) {
            DebugLogger.w("Package name is null")
            return false
        }
        
        val isAllowed = ALLOWED_PACKAGES.contains(packageName)
        DebugLogger.d("Package: $packageName | Allowed: $isAllowed")
        
        return isAllowed
    }

    fun getPackageDisplayName(packageName: String): String {
        return when (packageName) {
            "com.instagram.android" -> "Instagram"
            "com.instagram.canary" -> "Instagram (Canary)"
            "com.zhiliaoapp.musically" -> "TikTok"
            "com.ss.android.ugc.tiktok" -> "TikTok (US)"
            "com.facebook.katana" -> "Facebook"
            "com.twitter.android" -> "Twitter"
            "com.twitter.android.lite" -> "Twitter Lite"
            "com.google.android.youtube" -> "YouTube"
            "com.google.android.youtube.tv" -> "YouTube TV"
            "com.snapchat.android" -> "Snapchat"

            "com.instagram.threads" -> "Threads"

            else -> packageName
        }
    }

    fun addPackage(packageName: String) {
        (ALLOWED_PACKAGES as? MutableSet)?.add(packageName)
        DebugLogger.i("Added package: $packageName")
    }

    fun removePackage(packageName: String) {
        (ALLOWED_PACKAGES as? MutableSet)?.remove(packageName)
        DebugLogger.i("Removed package: $packageName")
    }

    fun getAllAllowedPackages(): Set<String> = ALLOWED_PACKAGES.toSet()
}

/**
 * Utility untuk mendapatkan informasi aplikasi saat ini
 */
class AppDetector(private val context: Context) {
    
    fun getCurrentForegroundApp(): String? {
        return try {
            val packageManager = context.packageManager
            val intent = android.content.Intent(android.content.Intent.ACTION_MAIN)
            intent.addCategory(android.content.Intent.CATEGORY_LAUNCHER)
            
            val resolveInfo = packageManager.queryIntentActivities(intent, 0)
            DebugLogger.d("getCurrentForegroundApp: Found ${resolveInfo.size} activities")
            
            // Note: Untuk Android 10+, ini dibatasi. Lihat getUsageStats() di bawah
            null
        } catch (e: Exception) {
            DebugLogger.e("Error getting foreground app", e)
            null
        }
    }

    fun logCurrentPackage(packageName: String?) {
        if (packageName == null) {
            DebugLogger.w("Cannot detect current package (null)")
            return
        }

        val displayName = AllowedApps.getPackageDisplayName(packageName)
        val isAllowed = AllowedApps.isPackageAllowed(packageName)
        
        DebugLogger.d("===== APP INFO =====")
        DebugLogger.d("Package: $packageName")
        DebugLogger.d("Display: $displayName")
        DebugLogger.d("Allowed: $isAllowed")
        DebugLogger.d("====================")
    }
}

/**
 * Validator untuk memastikan autoscroll bekerja dengan benar
 */
class AutoscrollValidator(private val service: AccessibilityService) {
    
    fun validateGestureCapability(): Boolean {
        try {
            // Cek apakah service dapat melakukan gesture
            val canPerformGestures = service.serviceInfo != null
            
            DebugLogger.d("Gesture capability check:")
            DebugLogger.d("  - Service info exists: ${service.serviceInfo != null}")
            DebugLogger.d("  - Result: $canPerformGestures")
            
            return canPerformGestures
        } catch (e: Exception) {
            DebugLogger.e("Error validating gesture capability", e)
            return false
        }
    }

    fun validateWindowContent(): Boolean {
        try {
            val rootNode = service.rootInActiveWindow
            val hasContent = rootNode != null
            
            DebugLogger.d("Window content check:")
            DebugLogger.d("  - Root node exists: $hasContent")
            
            if (hasContent && rootNode != null) {
                DebugLogger.d("  - Package: ${rootNode.packageName}")
                DebugLogger.d("  - Text: ${rootNode.text}")
            }
            
            return hasContent
        } catch (e: Exception) {
            DebugLogger.e("Error validating window content", e)
            return false
        }
    }

    fun performDiagnostics(packageName: String?): DiagnosticsResult {
        return DiagnosticsResult(
            packageName = packageName,
            isPackageAllowed = packageName?.let { AllowedApps.isPackageAllowed(it) } ?: false,
            hasGestureCapability = validateGestureCapability(),
            hasWindowContent = validateWindowContent(),
            timestamp = System.currentTimeMillis()
        )
    }
}

/**
 * Data class untuk hasil diagnostik
 */
data class DiagnosticsResult(
    val packageName: String?,
    val isPackageAllowed: Boolean,
    val hasGestureCapability: Boolean,
    val hasWindowContent: Boolean,
    val timestamp: Long
) {
    fun isReady(): Boolean {
        return packageName != null && isPackageAllowed && hasGestureCapability && hasWindowContent
    }

    fun getDebugInfo(): String {
        return buildString {
            appendLine("===== DIAGNOSTICS REPORT =====")
            appendLine("Timestamp: $timestamp")
            appendLine("Package: $packageName")
            appendLine("Package Allowed: $isPackageAllowed")
            appendLine("Gesture Capability: $hasGestureCapability")
            appendLine("Window Content: $hasWindowContent")
            appendLine("Ready to Scroll: ${isReady()}")
            appendLine("==============================")
        }
    }
}
