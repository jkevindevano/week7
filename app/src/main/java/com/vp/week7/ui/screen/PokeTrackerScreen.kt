package com.vp.week7.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vp.week7.ui.widgets.PokemonCardItem
import com.vp.week7.util.Resource
import com.vp.week7.viewmodel.PokeTrackerViewModel

@Composable
fun PokeTrackerScreen(
    viewModel: PokeTrackerViewModel = viewModel(),
    onCardClick: (String) -> Unit = {}
) {
    val kataKunci by viewModel.kataKunciPencarian.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current

    Scaffold(
        containerColor = Color(0xFF0D1117)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFD93829)),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "PokéTracker",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Real-time TCG Card Valuation",
                            color = Color(0xFF8B949E),
                            fontSize = 11.sp
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .background(Color(0xFF122C24), RoundedCornerShape(20.dp))
                        .border(1.dp, Color(0xFF2EA043), RoundedCornerShape(20.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "• LIVE API",
                        color = Color(0xFF2EA043),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = kataKunci,
                onValueChange = { viewModel.ubahKataKunci(it) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Cari kartu Pokémon...",
                        color = Color(0xFF8B949E)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color(0xFFD93829)
                    )
                },
                trailingIcon = {
                    if (kataKunci.isNotEmpty()) {
                        IconButton(onClick = { viewModel.ubahKataKunci("") }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear",
                                tint = Color(0xFF8B949E)
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        focusManager.clearFocus()
                        viewModel.cariKartu()
                    }
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color(0xFF161B22),
                    unfocusedContainerColor = Color(0xFF161B22),
                    focusedBorderColor = Color(0xFF30363D),
                    unfocusedBorderColor = Color(0xFF30363D)
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.daftarChipFilter) { namaChip ->
                    val terpilih = kataKunci.equals(namaChip, ignoreCase = true)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (terpilih) Color(0xFF21262D) else Color(0xFF161B22))
                            .border(
                                width = 1.dp,
                                color = if (terpilih) Color(0xFF8B949E) else Color(0xFF30363D),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clickable {
                                focusManager.clearFocus()
                                viewModel.pilihChip(namaChip)
                            }
                            .padding(horizontal = 14.dp, vertical = 7.dp)
                    ) {
                        Text(
                            text = namaChip,
                            color = if (terpilih) Color.White else Color(0xFF8B949E),
                            fontSize = 12.sp,
                            fontWeight = if (terpilih) FontWeight.SemiBold else FontWeight.Normal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (val state = uiState) {
                is Resource.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color(0xFFD93829))
                    }
                }

                is Resource.Error -> {
                    TampilanError(
                        pesanError = state.message,
                        onCobaLagi = { viewModel.cariKartu() }
                    )
                }

                is Resource.Success -> {
                    val daftarKartu = state.data
                    if (daftarKartu.isEmpty()) {
                        TampilanError(
                            pesanError = "Card tidak ditemukan untuk \"$kataKunci\"",
                            onCobaLagi = { viewModel.cariKartu() }
                        )
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Market Results (${daftarKartu.size})",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "USD • Near Mint",
                                color = Color(0xFF8B949E),
                                fontSize = 11.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(bottom = 20.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(daftarKartu, key = { it.idKartu }) { kartu ->
                                PokemonCardItem(
                                    kartu = kartu,
                                    onClick = { onCardClick(kartu.idKartu) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TampilanError(
    pesanError: String,
    onCobaLagi: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFF2B1719)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.WifiOff,
                contentDescription = "Error",
                tint = Color(0xFFD93829),
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Connection Failed",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = pesanError,
            color = Color(0xFF8B949E),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCobaLagi,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD93829))
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Try Again",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
