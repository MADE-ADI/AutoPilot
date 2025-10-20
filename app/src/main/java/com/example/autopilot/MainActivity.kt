package com.example.autopilot

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopilot.ui.theme.AutoPilotTheme

class MainActivity : ComponentActivity() {
    
    private val serviceManager by lazy { AutoscrollServiceManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AutoPilotTheme {
                MainScreen(
                    serviceManager = serviceManager,
                    onOpenSettings = {
                        serviceManager.openAccessibilitySettings()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    serviceManager: AutoscrollServiceManager,
    onOpenSettings: () -> Unit
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val isAutoscrolling = remember { mutableStateOf(false) }
    val serviceEnabled = remember { mutableStateOf(serviceManager.isAutoscrollServiceEnabled()) }
    val scrollDelay = remember { mutableStateOf(2000f) }
    val showDebug = remember { mutableStateOf(false) }
    val allowedApps = remember { mutableStateOf(AllowedApps.getAllAllowedPackages()) }
    val floatingButtonEnabled = remember { mutableStateOf(false) }

    // Refresh service status periodically
    LaunchedEffect(Unit) {
        while (true) {
            serviceEnabled.value = serviceManager.isAutoscrollServiceEnabled()
            allowedApps.value = AllowedApps.getAllAllowedPackages()
            kotlinx.coroutines.delay(1000)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.linearGradient(
                                        listOf(
                                            Color(0xFF6366F1),
                                            Color(0xFF8B5CF6)
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        
                        Column {
                            Text(
                                text = "Auto Pilot",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "Social Media Scroller",
                                fontSize = 11.sp,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6366F1)
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F7FA))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Service Status Card
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.elevatedCardElevation(6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (serviceEnabled.value) 
                            Color(0xFF10B981) else Color(0xFFEF4444)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "STATUS SERVICE",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White.copy(alpha = 0.9f),
                                letterSpacing = 1.sp
                            )
                            
                            Spacer(modifier = Modifier.height(4.dp))
                            
                            Text(
                                text = if (serviceEnabled.value) 
                                    "Aktif & Siap" else "Tidak Aktif",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (serviceEnabled.value) 
                                    Icons.Default.CheckCircle else Icons.Default.Settings,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                    
                    if (!serviceEnabled.value) {
                        Button(
                            onClick = onOpenSettings,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .padding(bottom = 20.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null,
                                tint = Color(0xFFEF4444),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "AKTIFKAN SEKARANG",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFEF4444)
                            )
                        }
                    }
                }

                // Main Control Card
                if (serviceEnabled.value) {
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.elevatedCardElevation(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            // Title with accent
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF6366F1))
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "KONTROL AUTOSCROLL",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1F2937),
                                    letterSpacing = 0.5.sp
                                )
                            }

                            // Slider Card
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFF9FAFB)
                                ),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Kecepatan Scroll",
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color(0xFF374151)
                                        )
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(Color(0xFF6366F1))
                                                .padding(horizontal = 12.dp, vertical = 6.dp)
                                        ) {
                                            Text(
                                                text = "${scrollDelay.value.toInt()} ms",
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.White
                                            )
                                        }
                                    }
                                    
                                    Slider(
                                        value = scrollDelay.value,
                                        onValueChange = { scrollDelay.value = it },
                                        valueRange = 500f..5000f,
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = SliderDefaults.colors(
                                            thumbColor = Color(0xFF6366F1),
                                            activeTrackColor = Color(0xFF6366F1),
                                            inactiveTrackColor = Color(0xFFE5E7EB)
                                        )
                                    )
                                    
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Cepat",
                                            fontSize = 10.sp,
                                            color = Color(0xFF9CA3AF)
                                        )
                                        Text(
                                            text = "Lambat",
                                            fontSize = 10.sp,
                                            color = Color(0xFF9CA3AF)
                                        )
                                    }
                                }
                            }

                            // Status Indicator
                            if (isAutoscrolling.value) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(0xFFDCFCE7))
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(10.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFF10B981))
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Autoscroll Sedang Berjalan...",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color(0xFF065F46)
                                    )
                                }
                            }

                            // Main Action Button
                            Button(
                                onClick = {
                                    val service = AutoscrollService.getInstance()
                                    if (service != null) {
                                        if (isAutoscrolling.value) {
                                            service.stopAutoscroll()
                                            isAutoscrolling.value = false
                                            DebugLogger.i("Stop button clicked")
                                        } else {
                                            service.startAutoscroll(scrollDelay.value.toLong())
                                            isAutoscrolling.value = true
                                            DebugLogger.i("Start button clicked")
                                        }
                                    } else {
                                        DebugLogger.e("Service not available")
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isAutoscrolling.value)
                                        Color(0xFFEF4444) else Color(0xFF10B981)
                                ),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(
                                    text = if (isAutoscrolling.value)
                                        "⏹ STOP AUTOSCROLL"
                                    else
                                        "▶ START AUTOSCROLL",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp
                                )
                            }
                        }
                    }

                    // Feature Info Card
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.elevatedCardElevation(2.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFEF3C7)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "✨ FITUR CANGGIH",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF92400E),
                                letterSpacing = 1.sp
                            )
                            
                            listOf(
                                "🎯 Auto-start saat buka aplikasi",
                                "🤖 Gerakan seperti manusia",
                                "⚡ Kecepatan random & natural",
                                "🎲 Delay bervariasi otomatis"
                            ).forEach { feature ->
                                Text(
                                    text = feature,
                                    fontSize = 12.sp,
                                    color = Color(0xFF78350F),
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }

                    // Floating Button Toggle
                    Button(
                        onClick = {
                            if (floatingButtonEnabled.value) {
                                context.stopService(Intent(context, FloatingButtonService::class.java))
                                floatingButtonEnabled.value = false
                            } else {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    if (!Settings.canDrawOverlays(context)) {
                                        val intent = Intent(
                                            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                            Uri.parse("package:${context.packageName}")
                                        )
                                        context.startActivity(intent)
                                        return@Button
                                    }
                                }
                                context.startService(Intent(context, FloatingButtonService::class.java))
                                floatingButtonEnabled.value = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (floatingButtonEnabled.value) 
                                Color(0xFFF59E0B) else Color(0xFF3B82F6)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = if (floatingButtonEnabled.value) 
                                "🎈 SEMBUNYIKAN FLOATING BUTTON" 
                            else 
                                "🎈 TAMPILKAN FLOATING BUTTON",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Debug Toggle
                    Button(
                        onClick = { showDebug.value = !showDebug.value },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6B7280)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = if (showDebug.value) "🔒 Sembunyikan Debug" else "🔧 Tampilkan Debug",
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Debug Panel
                    if (showDebug.value) {
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.elevatedCardElevation(4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF1F2937)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(
                                    text = "🔍 DEBUG PANEL",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF10B981),
                                    letterSpacing = 1.sp
                                )

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(Color.White.copy(alpha = 0.1f))
                                )

                                Text(
                                    text = "Aplikasi yang Diizinkan:",
                                    fontSize = 11.sp,
                                    color = Color(0xFF9CA3AF),
                                    fontWeight = FontWeight.SemiBold
                                )

                                allowedApps.value.forEach { pkg ->
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier
                                                .size(6.dp)
                                                .clip(CircleShape)
                                                .background(Color(0xFF10B981))
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = AllowedApps.getPackageDisplayName(pkg),
                                            fontSize = 11.sp,
                                            color = Color(0xFFD1D5DB)
                                        )
                                    }
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(Color.White.copy(alpha = 0.1f))
                                )

                                Text(
                                    text = "Status: ${if (isAutoscrolling.value) "RUNNING ✓" else "STOPPED ✗"}",
                                    fontSize = 11.sp,
                                    color = if (isAutoscrolling.value) Color(0xFF10B981) else Color(0xFFEF4444),
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "Delay: ${scrollDelay.value.toInt()}ms",
                                    fontSize = 11.sp,
                                    color = Color(0xFFFBBF24)
                                )

                                Text(
                                    text = "Cek logcat:\nadb logcat | findstr AutoPilot",
                                    fontSize = 10.sp,
                                    color = Color(0xFF9CA3AF),
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
