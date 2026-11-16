package com.vp.week7.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.vp.week7.model.PokemonCard
import java.util.Locale

@Composable
fun CardDetailScreen(
    kartu: PokemonCard,
    onKembali: () -> Unit
) {
    Scaffold(
        containerColor = Color(0xFF15191F)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = kartu.urlGambar,
                contentDescription = kartu.namaKartu,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(220.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = kartu.namaKartu,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = kartu.namaSet,
                color = Color(0xFF8B949E),
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                KotakHarga(
                    judul = "Market Price",
                    harga = kartu.hargaMarket,
                    warnaHarga = Color(0xFF2EA043),
                    modifier = Modifier.weight(1f)
                )
                KotakHarga(
                    judul = "Lowest Price",
                    harga = kartu.hargaTerendah,
                    warnaHarga = Color(0xFFD29922),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            JudulSeksi(judul = "Market Activity")
            TabelInfo(
                daftarBaris = listOf(
                    "Active Listings" to kartu.jumlahListingAktif.toString(),
                    "Verified Sellers" to kartu.jumlahPenjualTerverifikasi.toString(),
                    "Primary Printing" to kartu.tipePrinting
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            JudulSeksi(judul = "Card Specifications")
            TabelInfo(
                daftarBaris = listOf(
                    "Card Number" to kartu.nomorKartu,
                    "Rarity" to kartu.rarityKartu,
                    "Type" to kartu.tipeKartu,
                    "Illustrator" to kartu.illustratorKartu
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onKembali,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD93829))
            ) {
                Text(
                    text = "Done",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun KotakHarga(
    judul: String,
    harga: Double,
    warnaHarga: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color(0xFF1C2128), RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFF30363D), RoundedCornerShape(12.dp))
            .padding(14.dp)
    ) {
        Text(
            text = judul,
            color = Color(0xFF8B949E),
            fontSize = 11.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = String.format(Locale.US, "$%.2f", harga),
            color = warnaHarga,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun JudulSeksi(judul: String) {
    Text(
        text = judul,
        color = Color.White,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    )
}

@Composable
private fun TabelInfo(daftarBaris: List<Pair<String, String>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1C2128), RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFF30363D), RoundedCornerShape(12.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        daftarBaris.forEach { (label, nilai) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = label,
                    color = Color(0xFF8B949E),
                    fontSize = 13.sp
                )
                Text(
                    text = nilai,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
