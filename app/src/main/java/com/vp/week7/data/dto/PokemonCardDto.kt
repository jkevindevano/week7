package com.vp.week7.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSearchResponseDto(
    @SerialName("data")
    val daftarKartu: List<PokemonCardDto>? = null
)

@Serializable
data class PokemonCardDto(
    @SerialName("id")
    val idKartu: String? = null,
    @SerialName("name")
    val namaKartu: String? = null,
    @SerialName("hp")
    val hpKartu: String? = null,
    @SerialName("cardNumber")
    val nomorKartu: String? = null,
    @SerialName("rarity")
    val rarityKartu: String? = null,
    @SerialName("pokemonType")
    val tipeKartu: String? = null,
    @SerialName("artist")
    val illustratorKartu: String? = null,
    @SerialName("setName")
    val namaSet: String? = null,
    @SerialName("imageCdnUrl")
    val gambarBesar: String? = null,
    @SerialName("imageCdnUrl400")
    val gambarSedang: String? = null,
    @SerialName("imageUrl")
    val gambarUrl: String? = null,
    @SerialName("prices")
    val hargaKartu: CardPricesDto? = null
)

@Serializable
data class CardPricesDto(
    @SerialName("market")
    val hargaMarket: Double? = null,
    @SerialName("low")
    val hargaTerendah: Double? = null,
    @SerialName("listings")
    val jumlahListingAktif: Int? = null,
    @SerialName("sellers")
    val jumlahPenjualTerverifikasi: Int? = null,
    @SerialName("primaryPrinting")
    val tipePrinting: String? = null
)
