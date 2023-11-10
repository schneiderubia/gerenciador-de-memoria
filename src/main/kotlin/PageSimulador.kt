import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PagSimulador() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(Modifier.weight(1f)) { CardAlocarMemoria() }
            Spacer(Modifier.padding(8.dp))
            Box(Modifier.weight(1f)) { CardLiberarMemoria() }
        }
        Spacer(Modifier.padding(8.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            CardSimuladorMemoria()
        }
    }
}

//@Composable
//fun CardPersonalizado(
//    icon: ImageVector,
//    title: String,
//    subtitle: String
//) {
//    Card {
//        Column(Modifier.padding(16.dp)) {
//            Column {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Icon(imageVector = icon, contentDescription = title, modifier = Modifier.size(24.dp))
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
//                }
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(text = subtitle, fontSize = 14.sp)
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//            var posMemAlocar by remember { mutableStateOf(TextFieldValue("")) }
//            // Content...
//            Button(modifier = Modifier.fillMaxWidth(),
//                onClick = {
//                }
//            ) {
//                Text("Botão")
//            }
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardAlocarMemoria() {
    var tamanho by remember { mutableStateOf(TextFieldValue("")) }
    Card {
        Column(Modifier.padding(16.dp)) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Alocar memória",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Alocar memória", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Aplicações usam esta rotina para alocar memória", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(16.dp))
            }
            Column(Modifier.weight(1f)) {
                val coffeeDrinks =
                    arrayOf("Primeira Escolha", "Próxima Escolha", "Melhor Escolha", "Pior Escolha", "Rápida Escolha")
                var expanded by remember { mutableStateOf(false) }
                var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }
                Box(modifier = Modifier.fillMaxWidth()) {
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
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Swipe,
                                    contentDescription = "Algoritmo"
                                )
                            },
                            label = { Text(text = "Algoritmo de seleção") },
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
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.RemoveCircleOutline,
                            contentDescription = "Alocar memória"
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
                        blocosLivres = emptyList(); // FIXME: Hack para dar certo!
                        blocosLivres = memoria.blocosLivres
                        todosBlocos = memoria.todosBlocos
                        tamanho = TextFieldValue()
                    }
                }
            ) {
                Text("Alocar")
            }
        }
    }
}

@Composable
fun CardLiberarMemoria() {
    var endereco by remember { mutableStateOf(TextFieldValue("")) }
    var tamanho by remember { mutableStateOf(TextFieldValue("")) }
//    var enderecoFim by remember { mutableStateOf(TextFieldValue("")) }
    Card {
        Column(Modifier.padding(16.dp)) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Liberar memória",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Liberar memória", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Aplicações usam esta rotina para liberar memória", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(16.dp))
            }
            Column(Modifier.weight(1f)) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(imageVector = Icons.Default.Numbers, contentDescription = "emailIcon") },
                    trailingIcon = { Text("Byte(s)", modifier = Modifier.padding(10.dp)) },
                    value = endereco,
                    onValueChange = { endereco = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = "Endereço") },
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AddCircleOutline,
                            contentDescription = "addIcon"
                        )
                    },
                    trailingIcon = { Text("Byte(s)", modifier = Modifier.padding(10.dp)) },
                    value = tamanho,
                    onValueChange = { tamanho = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = "Tamanho") },
                )
//                OutlinedTextField(
//                    modifier = Modifier.fillMaxWidth(),
//                    leadingIcon = { Icon(imageVector = Icons.Default.Numbers, contentDescription = "emailIcon") },
//                    trailingIcon = { Text("Byte(s)", modifier = Modifier.padding(10.dp)) },
//                    value = enderecoFim,
//                    onValueChange = { enderecoFim = it },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                    label = { Text(text = "Endereço final") },
//                )
            }
            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (endereco.text.isNotEmpty() and tamanho.text.isNotEmpty()) {
                        memoria.liberar(endereco.text.toInt(), tamanho.text.toInt())
                        memoria.imprimir();
                        blocosLivres = memoria.blocosLivres
                        todosBlocos = memoria.todosBlocos
                        endereco = TextFieldValue();
                        tamanho = TextFieldValue();
                    }
                }
            ) {
                Text("Liberar")
            }
        }
    }
}

@Composable
fun CardSimuladorMemoria() {
    Card {
        Column {
            View1()
            View2()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun View1() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        blocosLivres.forEachIndexed { index, bloco ->
            Chip(
                modifier = Modifier.weight(1f).height(36.dp),
                border = BorderStroke(1.dp, Color.Black),
                shape = RectangleShape,
                onClick = {}
            ) {
                Text(
                    text = bloco.endereco.toString().plus(" | ").plus(bloco.tamanho.toString()),
                    softWrap = false,
                    fontSize = 12.sp
                )
            }
            if (index < blocosLivres.size - 1) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Forward"
                )
            }
        }
    }
}

@Composable
fun View2() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        todosBlocos.forEach {
            Column(modifier = Modifier.weight(it.tamanho.toFloat()).fillMaxWidth()) {
                Row {
                    // TODO: o texto no início e fim deve aparecer dependendo o tamanho deste bloco ou do próximo...
                    // TODO: adicionar ToolTip?
                    Text(
                        if (it.tamanho >= memoria.tamanhoTotal * 0.05f) it.endereco.toString() else "",
                        fontSize = 10.sp,
                        softWrap = false,
                        modifier = Modifier.weight(1f)
                    )
                    if (it.endereco + it.tamanho == memoria.tamanhoTotal) {
                        Text(memoria.tamanhoTotal.toString(), softWrap = false, fontSize = 10.sp)
                    }
                }
                Box(
                    modifier = Modifier.background(
                        if (it.isDisponivel) Color.Green else Color.Gray
                    ).fillMaxWidth().height(20.dp)
                )
            }
        }
    }
}