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
/**This data class is used by both Jackson and Gson
 * as they coexist but not compatible with each other.
 * link: https://stackoverflow.com/questions/32958521/can-gson-and-jackson-annotations-coexist-in-the-same-pojo
 */