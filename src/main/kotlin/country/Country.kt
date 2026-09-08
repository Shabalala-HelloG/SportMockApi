package country
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * {
 *   "data": {
 *     "name": "United States",
 *     "continent": "North America",
 *     "country_id": 1,
 *     "country_code": "USA"
 *   }
 * }
 */

data class Country(
    var name: String,
    var continent: String,
    @JsonProperty("country_id")
    var countryID: Int,
    @JsonProperty("country_code")
    var countryCode: String
)

