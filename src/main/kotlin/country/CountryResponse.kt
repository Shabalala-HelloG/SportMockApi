package org.example.Country

import org.example.Model.Country

/**This data class is used by both Jackson and Gson
 * as they coexist but not compatible with each other.
 * link: https://stackoverflow.com/questions/32958521/can-gson-and-jackson-annotations-coexist-in-the-same-pojo
 */
data class DataCountry(
    var data: List<Country>
)

data class OneDataCountry(
    var data: Country
)

data class DataCountryByContinent(
    var data:Map<String,Country>
)