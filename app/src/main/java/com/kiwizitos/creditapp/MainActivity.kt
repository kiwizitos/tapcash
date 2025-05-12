package com.kiwizitos.creditapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kiwizitos.creditapp.ui.theme.CreditappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CreditappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding),
                        items = listOf(
                            Item("Item A1", "2023-10-01"),
                            Item("Item A2", "2023-10-01"),
                            Item("Item B1", "2023-10-02"),
                            Item("Item B2", "2023-10-02"),
                            Item("Item C1", "2023-10-03"),
                            Item("Item C2", "2023-10-03"),
                            Item("Item D1", "2023-10-04"),
                            Item("Item D2", "2023-10-04"),
                        ),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting(modifier: Modifier = Modifier, items: List<Item>) {
    val grouped = items.groupBy { it.date ?: "Sem data" }

    Column(modifier = modifier) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Detalhes")
                    Text(text = "Gasto total: R$${items.sumOf { it.price ?: 0.0 }}")
                }
            }
        }
        LazyColumn(
            modifier = Modifier.padding(4.dp)
        ) {
            grouped.forEach { (date, items) ->
                val formattedDate = date.takeIf { it != "Sem data" }?.let {
                    it.substring(8, 10) + "/" + it.substring(5, 7)
                } ?: date
                stickyHeader {
                    HorizontalDivider()
                    Text(text = formattedDate, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
                items(items.size) { index ->
                    Card(modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth()) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(items[index].name)
                            Text(text = "R$${items[index].price ?: 0.0}")
                        }
                    }
                }
            }
        }
    }
}

class Item(val name: String, val date: String? = null, val price: Double? = null)


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CreditappTheme {
        Greeting(
            items = listOf(
                Item("Item A1", "2023-10-01", 10.0),
                Item("Item A2", "2023-10-01", 20.0),
                Item("Item B1", "2023-10-02", 15.0),
                Item("Item B2", "2023-10-02", 25.0),
                Item("Item C1", "2023-10-03", 30.0),
                Item("Item C2", "2023-10-03", 12.0),
                Item("Item D1", "2023-10-04", 5.0),
                Item("Item D2", "2023-10-04", 8.0),
                Item("Item E1", price = 18.0),
            )
        )
    }
}