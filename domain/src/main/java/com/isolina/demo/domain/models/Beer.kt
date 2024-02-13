package com.isolina.demo.domain.models

data class Beer(
    val id: Int?,
    val name: String?,
    val tagline: String?,
    val first_brewed: String?,
    val description: String?,
    val image_url: String?,
    val abv: Double?,
    val ibu: Double?,
    val target_fg: Double?,
    val target_og: Double?,
    val ebc: Double?,
    val srm: Double?,
    val ph: Double?,
    val attenuatuon_level: Int?,
    val contributed_by: String?,
    val volume: Volume?,
    val boil_volume: BoilVolume?,
    val food_pairing: List<String>?,
    val brewers_tips: String?
): java.io.Serializable {
    /*  fun getColorStatus(): Int {
          return when {
              status.equals("Dead", false) -> Color.RED
              status.equals("Alive", false) -> Color.GREEN
              else -> Color.GRAY
          }
      }*/
}