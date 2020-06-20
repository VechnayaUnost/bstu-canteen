package by.darya_zdzitavetskaya.bstu_canteen.api.response

import com.google.gson.annotations.SerializedName

data class PayResponse(
    @SerializedName("publishableKey")
    val publishableKey: String,
    @SerializedName("clientSecret")
    val clientSecret: String
)