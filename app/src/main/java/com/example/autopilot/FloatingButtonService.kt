package com.example.autopilot

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import androidx.core.content.ContextCompat

class FloatingButtonService : Service() {

    private lateinit var windowManager: WindowManager
    private var floatingView: View? = null
    private var isScrolling = false

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        createFloatingButton()
    }

    private fun createFloatingButton() {
        // Inflate layout
        floatingView = LayoutInflater.from(this).inflate(R.layout.floating_button_layout, null)

        // Setup layout params
        val layoutType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            @Suppress("DEPRECATION")
            WindowManager.LayoutParams.TYPE_PHONE
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            layoutType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        params.gravity = Gravity.TOP or Gravity.START
        params.x = 0
        params.y = 100

        // Add view to window
        windowManager.addView(floatingView, params)

        // Setup button
        val button = floatingView?.findViewById<ImageButton>(R.id.floating_button)
        updateButtonState(button)

        // Button click listener
        button?.setOnClickListener {
            toggleScrolling()
            updateButtonState(button)
        }

        // Make button draggable
        makeButtonDraggable(floatingView!!, params)
    }

    private fun toggleScrolling() {
        val service = AutoscrollService.getInstance()
        
        if (service == null) {
            DebugLogger.e("Cannot toggle scroll - Service not available")
            return
        }

        isScrolling = !isScrolling
        
        if (isScrolling) {
            DebugLogger.i("Floating button: Starting autoscroll")
            service.startAutoscroll(2000L)
        } else {
            DebugLogger.i("Floating button: Stopping autoscroll")
            service.stopAutoscroll()
        }
    }

    private fun updateButtonState(button: ImageButton?) {
        button?.setImageResource(
            if (isScrolling) R.drawable.ic_stop else R.drawable.ic_play
        )
        
        button?.setColorFilter(
            ContextCompat.getColor(
                this,
                if (isScrolling) android.R.color.holo_red_dark else android.R.color.holo_green_dark
            )
        )
    }

    private fun makeButtonDraggable(view: View, params: WindowManager.LayoutParams) {
        var initialX = 0
        var initialY = 0
        var initialTouchX = 0f
        var initialTouchY = 0f

        view.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialX = params.x
                    initialY = params.y
                    initialTouchX = event.rawX
                    initialTouchY = event.rawY
                    false
                }
                MotionEvent.ACTION_MOVE -> {
                    params.x = initialX + (event.rawX - initialTouchX).toInt()
                    params.y = initialY + (event.rawY - initialTouchY).toInt()
                    windowManager.updateViewLayout(view, params)
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        floatingView?.let { windowManager.removeView(it) }
        DebugLogger.i("Floating button service destroyed")
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
