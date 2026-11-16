package com.vp.week7.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.vp.week7.model.PokemonCard
import java.util.Locale

@Composable
fun PokemonCardItem(
    kartu: PokemonCard,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFF232830), RoundedCornerShape(14.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF15191F))
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.72f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF0E1116))
            ) {
                AsyncImage(
                    model = kartu.urlGambar,
                    contentDescription = kartu.namaKartu,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                if (kartu.hpKartu != "-") {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(6.dp)
                            .background(Color.Black.copy(alpha = 0.78f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = kartu.hpKartu,
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = kartu.namaKartu,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "${kartu.namaSet} • #${kartu.nomorKartu}",
                color = Color(0xFF8B949E),
                fontSize = 11.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFF122C24), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = String.format(Locale.US, "↗ $%.2f", kartu.hargaMarket),
                        color = Color(0xFF2EA043),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .background(Color(0xFF21262D), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = kartu.rarityKartu,
                        color = Color(0xFF8B949E),
                        fontSize = 10.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
