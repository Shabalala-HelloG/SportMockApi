package team
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * {
 *   "data": {
 *     "team_id": 1,
 *     "team_name": "South Africa",
 *     "country_id": 214,
 *     "found_year": 1991
 *   }
 * }
 */


data class Team(
    @JsonProperty("team_id")
    var teamId : Int,
    @JsonProperty("team_name")
    var teamName: String,
    @JsonProperty("country_id")
    var countryId : Int,
    @JsonProperty("found_year")
    var foundYear : Int
)
