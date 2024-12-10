package com.example.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    titleMedium = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = LittleLemonColor.charcoal
    ),
    titleSmall = TextStyle(
        color = LittleLemonColor.charcoal,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyMedium = TextStyle(
        color = LittleLemonColor.green
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Bold,
        color = LittleLemonColor.green
    ),
    labelMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
)
