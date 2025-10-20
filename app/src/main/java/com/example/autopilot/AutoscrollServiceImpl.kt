package com.example.autopilot

import android.os.Handler
import android.os.Looper
import android.util.Log
import kotlin.random.Random

class AutoscrollServiceImpl {

    private val handler = Handler(Looper.getMainLooper())
    private var isAutoscrolling = false
    private var scrollRunnable: Runnable? = null
    private var currentScrollDelay = 2000L

    fun startAutoscroll(delayMs: Long = 2000) {
        if (isAutoscrolling) return
        
        isAutoscrolling = true
        currentScrollDelay = delayMs
        Log.d("AutoscrollServiceImpl", "Starting autoscroll with delay: $delayMs ms")
        
        performSimulatedScroll()
    }

    fun stopAutoscroll() {
        isAutoscrolling = false
        scrollRunnable?.let { handler.removeCallbacks(it) }
        Log.d("AutoscrollServiceImpl", "Autoscroll stopped")
    }

    private fun performSimulatedScroll() {
        if (!isAutoscrolling) return

        Log.d("AutoscrollServiceImpl", "Performing scroll action")
        scheduleNextScroll()
    }

    private fun scheduleNextScroll() {
        if (!isAutoscrolling) return

        // Human-like random delay variation
        // Base delay ± 30% random variation
        val variation = (currentScrollDelay * 0.3).toLong()
        val nextDelay = currentScrollDelay + Random.nextLong(-variation, variation)
        val finalDelay = maxOf(1000L, nextDelay)

        scrollRunnable = Runnable {
            performSimulatedScroll()
        }

        handler.postDelayed(scrollRunnable!!, finalDelay)
    }

    companion object {
        private const val TAG = "AutoscrollServiceImpl"
    }
}
