package com.aeriostv.ui.home

/**
 * HomeScreenWrapper.kt
 *
 * ──────────────────────────────────────────────────────────────────────────────
 * HOW TO USE IN YOUR PROJECT
 * ──────────────────────────────────────────────────────────────────────────────
 * 1. Drop HomeScreen.kt and this file into your home UI package.
 * 2. Replace the old HomeScreen call in your NavGraph / Activity with:
 *
 *      HomeScreenWrapper(viewModel = hiltViewModel())
 *
 * 3. Adapt the field names inside this wrapper to match your real ViewModel.
 *    Look for "// TODO:" comments below.
 *
 * The wrapper is intentionally a thin adapter so HomeScreen stays preview-able
 * and testable without a ViewModel dependency.
 * ──────────────────────────────────────────────────────────────────────────────
 */

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

/*
 * Uncomment and adapt these imports once you wire this to your real ViewModel:
 *
 * import androidx.hilt.navigation.compose.hiltViewModel
 * import com.aeriostv.ui.home.HomeViewModel   // ← your actual VM class
 */

/**
 * Adapter composable: pulls live data from your HomeViewModel and passes it
 * into the pure-UI [HomeScreen].
 *
 * Replace every "// TODO:" with the real field / function from your ViewModel.
 */
@Composable
fun HomeScreenWrapper(
    // TODO: inject your HomeViewModel here, e.g.:
    // viewModel: HomeViewModel = hiltViewModel(),
    onMoviesClick: () -> Unit = {},
    onSeriesClick: () -> Unit = {},
    onLiveTvClick: () -> Unit = {}
) {
    // ── Pull state from ViewModel ──────────────────────────────────────────
    // Uncomment and adapt the lines below:
    //
    // val uiState by viewModel.uiState.collectAsState()
    //
    // val username       = uiState.userInfo?.username       ?: ""
    // val expirationDate = uiState.userInfo?.expirationDate ?: ""
    // val accountId      = uiState.userInfo?.accountCode    ?: ""

    // Placeholder values — replace with ViewModel state above
    val username        = ""   // TODO: replace with real username from ViewModel
    val expirationDate  = ""   // TODO: replace with real expiration date
    val accountId       = ""   // TODO: replace with real account identifier

    HomeScreen(
        appName        = "Eagle X",            // Change to your app branding constant
        username       = username,
        expirationDate = expirationDate,
        accountId      = accountId,
        onMoviesClick  = onMoviesClick,
        onSeriesClick  = onSeriesClick,
        onLiveTvClick  = onLiveTvClick,
        onPowerClick   = { /* TODO: viewModel.onExit() or finish activity */ },
        onRefreshClick = { /* TODO: viewModel.refresh() */ },
        onUserClick    = { /* TODO: navigate to profile or show account info */ },
        onSettingsClick= { /* TODO: navigate to settings */ },
        onSearchClick  = { /* TODO: navigate to search */ }
    )
}
