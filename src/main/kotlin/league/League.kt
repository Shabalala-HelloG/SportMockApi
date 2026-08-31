package org.example.Model
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
/**
 * {
 *   "data": {
 *     "name": "2nd Division - Play-offs",
 *     "country": {
 *       "name": "Albania",
 *       "continent": "Europe",
 *       "country_id": 9,
 *       "country_code": "ALB"
 *     },
 *     "league_id": 1
 *   }
 * }
 */
data class League(
    var name: String,
    var country: Country,
    @JsonProperty("league_id")
    @SerializedName("league_id")
    var leagueID: Int
)
