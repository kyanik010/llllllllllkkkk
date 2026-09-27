# Eagle X – Home Screen Redesign Integration Guide

## الملفات الجديدة

| الملف | الوصف |
|---|---|
| `HomeScreen.kt` | الواجهة الرئيسية – Composable نقي بدون ViewModel |
| `HomeScreenWrapper.kt` | محول رفيع يربط HomeScreen بـ ViewModel الحالي |
| `HomeScreenPreview.kt` | معاينة Android Studio (Phone + TV) |

---

## خطوات التطبيق

### 1. انسخ الملفات إلى مشروعك

انسخ الملفات الثلاثة إلى:

```
app/src/main/java/com/[yourpackage]/ui/home/
```

غيّر `package` في أعلى كل ملف ليطابق package مشروعك الفعلي.

---

### 2. احذف أو أعد تسمية الـ HomeScreen القديمة

إذا كان لديك:
```
HomeScreen.kt  (القديمة بتصميم Lumen)
```
أعد تسميتها إلى `HomeScreenLegacy.kt` أو احذفها بعد التأكد من نجاح البناء.

---

### 3. ربط ViewModel

افتح `HomeScreenWrapper.kt` وتتبع كل تعليق `// TODO:`.

**مثال كامل إذا كان ViewModel لديك يبدو هكذا:**

```kotlin
// HomeViewModel.kt (موجود بالفعل)
data class UserInfo(
    val username: String,
    val expirationDate: String,
    val accountCode: String
)

data class HomeUiState(
    val userInfo: UserInfo? = null
)

class HomeViewModel : ViewModel() {
    val uiState: StateFlow<HomeUiState> = ...

    fun refresh() { ... }
    fun onExit()  { ... }
}
```

**الـ Wrapper بعد التعديل:**

```kotlin
@Composable
fun HomeScreenWrapper(
    viewModel: HomeViewModel = hiltViewModel(),
    onMoviesClick: () -> Unit,
    onSeriesClick: () -> Unit,
    onLiveTvClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        appName        = "Eagle X",
        username       = uiState.userInfo?.username       ?: "",
        expirationDate = uiState.userInfo?.expirationDate ?: "",
        accountId      = uiState.userInfo?.accountCode    ?: "",
        onMoviesClick  = onMoviesClick,
        onSeriesClick  = onSeriesClick,
        onLiveTvClick  = onLiveTvClick,
        onPowerClick   = { viewModel.onExit() },
        onRefreshClick = { viewModel.refresh() },
        onUserClick    = { /* navigate */ },
        onSettingsClick= { /* navigate */ },
        onSearchClick  = { /* navigate */ }
    )
}
```

---

### 4. استبدل الاستدعاء القديم في NavGraph

```kotlin
// قبل
composable(Screen.Home.route) {
    OldHomeScreen(viewModel = hiltViewModel(), ...)
}

// بعد
composable(Screen.Home.route) {
    HomeScreenWrapper(
        onMoviesClick  = { navController.navigate(Screen.Movies.route) },
        onSeriesClick  = { navController.navigate(Screen.Series.route) },
        onLiveTvClick  = { navController.navigate(Screen.LiveTv.route) }
    )
}
```

---

### 5. تحقق من Gradle build

```bash
./gradlew assembleDebug 2>&1 | grep -E "error:|BUILD"
```

---

### 6. أخطاء شائعة وحلولها

| الخطأ | الحل |
|---|---|
| `Unresolved reference: Divider` | استخدم `HorizontalDivider()` في Material3 ≥ 1.2 |
| `@Composable invocations can only happen from the context of a @Composable function` | تأكد أن `HomeScreenPreview.kt` ليس في `main` source set إذا كان compilation fails |
| `Duplicate @Composable annotation` | احذف أي composable بنفس الاسم في الملفات القديمة |
| `Focus not working on TV` | أضف `modifier = Modifier.focusable()` على الـ Box الخارجي إذا لزم |

---

### 7. Git Commit

```bash
git add app/src/main/java/com/[pkg]/ui/home/HomeScreen.kt \
        app/src/main/java/com/[pkg]/ui/home/HomeScreenWrapper.kt \
        app/src/main/java/com/[pkg]/ui/home/HomeScreenPreview.kt

git commit -m "feat: redesign home UI to match Eagle X reference

- Premium dark IPTV interface with abstract ambient waves
- Digital Arabic clock in header
- Glass toolbar: Power, Refresh, User, Settings, Search
- Main glass panel with Movies / Series / Live TV nav items
- 3D-style icons with focus glow states for Android TV
- Subscription card with real user data from ViewModel
- Full RTL Arabic layout
- Responsive: phone portrait + Android TV landscape
- TV D-pad navigation via focusRequester + focusProperties
- Zero fake data – all state from existing ViewModel"
```

---

### 8. ملاحظات التصميم

- **الألوان الرئيسية:** `#060C18` background · `#4A9EFF` blue accent · `#00C8A0` teal · `#B87333` copper
- **Corner radius:** 24dp للبطاقة الرئيسية · 16dp للعناصر الداخلية · 12dp لأزرار الـ toolbar
- **Focus state:** scale + border glow + radial background عند التركيز — لا يشوه التصميم
- **TV navigation order:** Toolbar (←→) → Main Panel items (↑↓) — منطقي ومتوقع
- **RTL:** كل الواجهة RTL، الأيقونات لا تُعكس خطأً
