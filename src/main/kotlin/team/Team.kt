package team
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

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
    @SerializedName("team_id")
    var teamId : Int,
    @JsonProperty("team_name")
    @SerializedName("team_name")
    var teamName: String,
    @JsonProperty("country_id")
    @SerializedName("country_id")
    var countryId : Int,
    @JsonProperty("found_year")
    @SerializedName("found_year")
    var foundYear : Int
    //I don't know why but null values are assigned 0
)
