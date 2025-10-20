package com.example.autopilot

import android.accessibilityservice.AccessibilityServiceInfo
import android.content.ComponentName
import android.content.Context
import android.provider.Settings
import android.view.accessibility.AccessibilityManager
import android.util.Log

class AutoscrollServiceManager(private val context: Context) {

    private val accessibilityManager =
        context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager

    fun isAutoscrollServiceEnabled(): Boolean {
        val componentName = ComponentName(context, AutoscrollService::class.java)
        val enabledServices = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: return false

        return enabledServices.contains(componentName.flattenToString())
    }

    fun getAutoscrollService(): AutoscrollService? {
        return try {
            val services = accessibilityManager.getEnabledAccessibilityServiceList(
                AccessibilityServiceInfo.FEEDBACK_GENERIC
            )
            
            for (service in services) {
                if (service.id.contains("AutoscrollService")) {
                    Log.d("AutoscrollServiceManager", "Found AutoscrollService")
                    return null // Return null since we can't directly access the service
                }
            }
            null
        } catch (e: Exception) {
            Log.e("AutoscrollServiceManager", "Error getting service", e)
            null
        }
    }

    fun openAccessibilitySettings() {
        val intent = android.content.Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        context.startActivity(intent)
    }

    companion object {
        private const val TAG = "AutoscrollServiceManager"
    }
}
