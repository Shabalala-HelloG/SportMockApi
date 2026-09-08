package league
import com.fasterxml.jackson.annotation.JsonProperty
import country.Country

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
    var leagueID: Int
)