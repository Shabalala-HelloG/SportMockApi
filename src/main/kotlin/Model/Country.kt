package org.example.Model
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

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
    @SerializedName("country_id")
    var countryID: Int,
    @JsonProperty("country_code")
    @SerializedName("country_code")
    var countryCode: String
)
/**This data class is used by both Jackson and Gson
 * as they coexist but not compatible with each other.
 * link: https://stackoverflow.com/questions/32958521/can-gson-and-jackson-annotations-coexist-in-the-same-pojo
 */
