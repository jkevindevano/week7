package com.vp.week7.model

data class PokemonCard(
    val idKartu: String,
    val namaKartu: String,
    val namaSet: String,
    val nomorKartu: String,
    val hpKartu: String,
    val urlGambar: String,
    val hargaMarket: Double,
    val hargaTerendah: Double,
    val jumlahListingAktif: Int,
    val jumlahPenjualTerverifikasi: Int,
    val tipePrinting: String,
    val rarityKartu: String,
    val tipeKartu: String,
    val illustratorKartu: String
)
