package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.detail_product

import by.darya_zdzitavetskaya.bstu_canteen.api.response.ProductResponse
import io.reactivex.Single

interface DetailProductProtocol {

    interface IDetailProductRepository {
        fun getProduct(productId: String): Single<ProductResponse>
    }
}