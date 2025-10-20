package com.example.autopilot

/**
 * Konfigurasi aplikasi Autoscroll
 */
object AutoscrollConfig {

    /**
     * Delay default untuk autoscroll (dalam milliseconds)
     */
    const val DEFAULT_DELAY_MS = 2000L

    /**
     * Delay minimum (dalam milliseconds)
     */
    const val MIN_DELAY_MS = 500L

    /**
     * Delay maximum (dalam milliseconds)
     */
    const val MAX_DELAY_MS = 5000L

    /**
     * Minimum scroll distance (dalam pixels)
     */
    const val MIN_SCROLL_DISTANCE = 100f

    /**
     * Maximum scroll distance (dalam pixels)
     */
    const val MAX_SCROLL_DISTANCE = 400f

    /**
     * Minimum gesture duration (dalam milliseconds)
     */
    const val MIN_GESTURE_DURATION = 300L

    /**
     * Maximum gesture duration (dalam milliseconds)
     */
    const val MAX_GESTURE_DURATION = 800L

    /**
     * Probability untuk scroll ke bawah (0.0 - 1.0)
     * 0.9 = 90% scroll ke bawah, 10% ke atas
     */
    const val SCROLL_DOWN_PROBABILITY = 0.9f

    /**
     * Variation dalam delay untuk human-like behavior
     * 0.3 = ± 30% dari base delay
     */
    const val DELAY_VARIATION_PERCENTAGE = 0.3f

    /**
     * Kategori delay untuk berbagai use case
     */
    object DelayProfiles {
        const val VERY_FAST = 800L      // Testing & Demo
        const val FAST = 1200L          // Quick browsing
        const val NORMAL = 2000L        // Standard social media
        const val SLOW = 3000L          // Relaxed scrolling
        const val VERY_SLOW = 4500L     // Reading mode
    }

    /**
     * App-specific recommendations
     */
    object AppProfiles {
        const val INSTAGRAM_DELAY = 1500L
        const val TIKTOK_DELAY = 2500L
        const val TWITTER_DELAY = 1800L
        const val FACEBOOK_DELAY = 2000L
        const val YOUTUBE_DELAY = 3000L
        const val REDDIT_DELAY = 1600L
    }
}
