package org.example.Team

import org.example.Model.Team

/**This data class is used by both Jackson and Gson
 * as they coexist but not compatible with each other.
 * link: https://stackoverflow.com/questions/32958521/can-gson-and-jackson-annotations-coexist-in-the-same-pojo
 */
data class DataTeam(
    var data: List<Team>
)
data class OneDataTeam(
    var data: Team
)