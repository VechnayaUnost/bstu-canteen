package by.darya_zdzitavetskaya.bstu_canteen.api.request

data class PayRequest(
    val items: List<PayItem>
)

data class PayItem(
    val _id: String,
    val count: Int
)