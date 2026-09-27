package com.aeriostv.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// ─── Phone Portrait Preview ───────────────────────────────────────────────────
@Preview(
    name = "Home – Phone Portrait",
    showBackground = true,
    widthDp = 390,
    heightDp = 844,
    backgroundColor = 0xFF060C18
)
@Composable
fun HomeScreenPhonePreview() {
    HomeScreen(
        appName        = "Eagle X",
        username       = "اسم المستخدم",
        expirationDate = "٠٧/١٢/٢٠٢٦",
        accountId      = "3866832A3365",
        onMoviesClick  = {},
        onSeriesClick  = {},
        onLiveTvClick  = {},
        onPowerClick   = {},
        onRefreshClick = {},
        onUserClick    = {},
        onSettingsClick= {},
        onSearchClick  = {}
    )
}

// ─── Android TV Preview ───────────────────────────────────────────────────────
@Preview(
    name = "Home – Android TV 1080p",
    showBackground = true,
    widthDp = 1280,
    heightDp = 720,
    backgroundColor = 0xFF060C18
)
@Composable
fun HomeScreenTvPreview() {
    HomeScreen(
        appName        = "Eagle X",
        username       = "اسم المستخدم",
        expirationDate = "٠٧/١٢/٢٠٢٦",
        accountId      = "3866832A3365"
    )
}
