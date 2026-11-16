package com.vp.week7.data.repository

import com.vp.week7.data.dto.PokemonCardDto
import com.vp.week7.model.PokemonCard
import java.util.UUID

fun PokemonCardDto.keModelDomain(): PokemonCard {
    val gambar = gambarSedang?.ifBlank { null }
        ?: gambarBesar?.ifBlank { null }
        ?: gambarUrl?.ifBlank { null }
        ?: ""

    return PokemonCard(
        idKartu = idKartu?.ifBlank { null } ?: UUID.randomUUID().toString(),
        namaKartu = namaKartu?.ifBlank { null } ?: "Unknown Card",
        namaSet = namaSet?.ifBlank { null } ?: "Unknown Set",
        nomorKartu = nomorKartu?.ifBlank { null } ?: "-",
        hpKartu = if (!hpKartu.isNullOrBlank()) "$hpKartu HP" else "-",
        urlGambar = gambar,
        hargaMarket = hargaKartu?.hargaMarket ?: 0.0,
        hargaTerendah = hargaKartu?.hargaTerendah ?: 0.0,
        jumlahListingAktif = hargaKartu?.jumlahListingAktif ?: 0,
        jumlahPenjualTerverifikasi = hargaKartu?.jumlahPenjualTerverifikasi ?: 0,
        tipePrinting = hargaKartu?.tipePrinting?.ifBlank { null } ?: "Normal",
        rarityKartu = rarityKartu?.ifBlank { null } ?: "Common",
        tipeKartu = tipeKartu?.ifBlank { null } ?: "Colorless",
        illustratorKartu = illustratorKartu?.ifBlank { null } ?: "Unknown"
    )
}
