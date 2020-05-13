package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.category

import by.darya_zdzitavetskaya.bstu_canteen.api.Api
import by.darya_zdzitavetskaya.bstu_canteen.api.response.CategoryResponse
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val api: Api) :
    CategoryProtocol.ICategoryRepository {

    override fun getCategory(categoryId: String): Single<Response<CategoryResponse>> {
        return api.getCategory(categoryId).subscribeOn(Schedulers.io())
    }

    override fun deleteProduct(productId: String): Completable {
        return api.deleteProduct(productId).subscribeOn(Schedulers.io())
    }
}