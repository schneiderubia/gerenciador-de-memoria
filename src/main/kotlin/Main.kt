import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension

// Light
val PrimaryLight = Color(0xFF0061a4)
val OnPrimaryLight = Color(0xFFFFFFFF)
val PrimaryContainerLight = Color(0xFFd1e4ff)
val OnPrimaryContainerLight = Color(0xFF001d36)

val SecondaryLight = Color(0xFF535f70)
val OnSecondaryLight = Color(0xFFffffff)
val SecondaryContainerLight = Color(0xFFd7e3f7)
val OnSecondaryContainerLight = Color(0xFF101c2b)

val TertiaryLight = Color(0xFF6b5778)
val OnTertiaryLight = Color(0xFFffffff)
val TertiaryContainerLight = Color(0xFFf2daff)
val OnTertiaryContainerLight = Color(0xFF251431)

val ErrorLight = Color(0xFFba1a1a)
val OnErrorLight = Color(0xFFffffff)
val ErrorContainerLight = Color(0xFFffdad6)
val OnErrorContainerLight = Color(0xFF410002)

val BackgroundLight = Color(0xFFfdfcff)
val OnBackgroundLight = Color(0xFF1a1c1e)
val SurfaceLight = Color(0xFFfdfcff)

val OutlineLight = Color(0xFF73777f)
val SurfaceVariantLight = Color(0xFFdfe2eb)
val OnSurfaceVariantLight = Color(0xFF43474e)

// Dark
val PrimaryDark = Color(0xFF2c9fff)
val OnPrimaryDark = Color(0xFF003258)
val PrimaryContainerDark = Color(0xFF00497d)
val OnPrimaryContainerDark = Color(0xFFd1e4ff)

val SecondaryDark = Color(0xFFbbc7db)
val OnSecondaryDark = Color(0xFF253140)
val SecondaryContainerDark = Color(0xFF3b4858)
val OnSecondaryContainerDark = Color(0xFFd7e3f7)

val TertiaryDark = Color(0xFFd7bee4)
val OnTertiaryDark = Color(0xFF3b2948)
val TertiaryContainerDark = Color(0xFF523f5f)
val OnTertiaryContainerDark = Color(0xFFf2daff)

val ErrorDark = Color(0xFFffb4ab)
val OnErrorDark = Color(0xFF690005)
val ErrorContainerDark = Color(0xFF93000a)
val OnErrorContainerDark = Color(0xFFffdad6)

val BackgroundDark = Color(0xFF1a1c1e)
val OnBackgroundDark = Color(0xFFe2e2e6)
val SurfaceDark = Color(0xFF1a1c1e)

val OutlineDark = Color(0xFF8d9199)
val SurfaceVariantDark = Color(0xFF43474e)
val OnSurfaceVariantDark = Color(0xFFc3c7cf)

private val DarkColorPalette = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    secondary = SecondaryDark,
    onSecondary = OnSecondaryDark,
    secondaryContainer = SecondaryContainerDark,
    onSecondaryContainer = OnSecondaryContainerDark,
    tertiary = TertiaryDark,
    onTertiary = OnTertiaryDark,
    tertiaryContainer = TertiaryContainerDark,
    onTertiaryContainer = OnTertiaryContainerDark,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    outline = OutlineDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
)

private val LightColorPalette = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,
    secondary = SecondaryLight,
    onSecondary = OnSecondaryLight,
    secondaryContainer = SecondaryContainerLight,
    onSecondaryContainer = OnSecondaryContainerLight,
    tertiary = TertiaryLight,
    onTertiary = OnTertiaryLight,
    tertiaryContainer = TertiaryContainerLight,
    onTertiaryContainer = OnTertiaryContainerLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    outline = OutlineLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
)

val memoria = GerenciadorMemoria()
var blocosLivres by mutableStateOf(memoria.blocosLivres)
var todosBlocos by mutableStateOf(memoria.todosBlocos)

enum class Page(
    val icon: ImageVector,
    val label: String,
) {
    SIMULADOR(Icons.Default.Memory, "Simulador"),
    CONFIG(Icons.Default.Settings, "Config")
}

enum class Algoritmo(val label: String) {
    FIRST_FIT("Primeira Escolha"),
    NEXT_FIT("Próxima Escolha"),
    BEST_FIT("Melhor Escolha"),
    WORST_FIT("Pior Escolha"),
    QUICK_FIT("Rápida Escolha"),
}

@Composable
@Preview
fun GerenciadorMemoriaApp() {
    MaterialTheme(
//        colorScheme = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette
//        colorScheme = LightColorPalette
//        colorScheme = DarkColorPalette
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Row {
                var selectedItem by remember { mutableStateOf(Page.SIMULADOR) }
                NavigationRail(modifier = Modifier.fillMaxHeight()) {
                    Spacer(Modifier.weight(1f))
                    Page.entries.toTypedArray().forEach { page: Page ->
                        NavigationRailItem(
                            icon = { Icon(page.icon, contentDescription = page.label) },
                            label = { Text(page.label) },
                            onClick = { selectedItem = page },
                            selected = selectedItem == page
                        )
                    }
                    Spacer(Modifier.weight(1f))
                }
                Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    when (selectedItem) {
                        Page.SIMULADOR -> PagSimulador()
                        Page.CONFIG -> PagConfig()
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(
        title = "Simulador de Alocação de Memória",
        state = rememberWindowState(width = 900.dp, height = 700.dp),
        onCloseRequest = ::exitApplication
    ) {
        window.minimumSize = Dimension(800, 600)
        GerenciadorMemoriaApp()
    }
}
