package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.detail_product

import by.darya_zdzitavetskaya.bstu_canteen.api.response.ProductResponse
import io.reactivex.Single
import retrofit2.Response

interface DetailProductProtocol {

    interface IDetailProductRepository {
        fun getProduct(productId: String): Single<Response<ProductResponse>>
    }
}