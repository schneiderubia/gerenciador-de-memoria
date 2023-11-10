import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagConfig() {
    Column {
        Box(modifier = Modifier.weight(1f)) { CardTamanhoMemoria() }
        Spacer(modifier = Modifier.padding(8.dp))
        Box(modifier = Modifier.weight(1f)) { CardConfigDiversas() }
    }
}

@Composable
fun CardTamanhoMemoria() {
    var tamanho by remember { mutableStateOf(TextFieldValue("")) }
    Card {
        Column(Modifier.padding(16.dp)) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Memory,
                        contentDescription = "Tamanho da Memória",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Tamanho da Memória", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Define o tamanho da memória (KBytes)", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(16.dp))
            }
            Column(Modifier.weight(1f)) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.RemoveCircleOutline,
                            contentDescription = "emailIcon"
                        )
                    },
                    trailingIcon = { Text("Byte(s)", modifier = Modifier.padding(10.dp)) },
                    value = tamanho,
                    onValueChange = { tamanho = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = "Tamanho") },
                )
            }
            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (tamanho.text.isNotEmpty()) {
                        memoria.alocar(tamanho.text.toInt())
                        memoria.imprimir();
                        blocosLivres = memoria.blocosLivres
                        todosBlocos = memoria.todosBlocos
                    }
                }
            ) {
                Text("Redefinir")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardConfigDiversas() {
    Card {
        Column(Modifier.padding(16.dp)) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Configurações",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Configurações", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Configurações do gerenciador de memória", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(16.dp))
            }
            Column(Modifier.weight(1f)) {
                val coffeeDrinks = arrayOf("Tema 1", "Tema 2", "Tema 3", "Tema 4", "Tema 5")
                var expanded by remember { mutableStateOf(false) }
                var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }

                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        OutlinedTextField(
                            value = selectedText,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            label = { Text(text = "Escolha o tema") },
                            modifier = Modifier.menuAnchor().fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            coffeeDrinks.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        selectedText = item
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}