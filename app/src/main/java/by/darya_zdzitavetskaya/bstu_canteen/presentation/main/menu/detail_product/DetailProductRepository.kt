package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.detail_product

import by.darya_zdzitavetskaya.bstu_canteen.api.Api
import by.darya_zdzitavetskaya.bstu_canteen.api.response.ProductResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class DetailProductRepository @Inject constructor(private val api: Api) :
    DetailProductProtocol.IDetailProductRepository {

    override fun getProduct(productId: String): Single<Response<ProductResponse>> {
        return api.getProduct(productId).subscribeOn(Schedulers.io())
    }
}