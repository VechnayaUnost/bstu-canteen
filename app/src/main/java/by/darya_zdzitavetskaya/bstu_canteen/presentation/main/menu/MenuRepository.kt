package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu

import by.darya_zdzitavetskaya.bstu_canteen.api.Api
import by.darya_zdzitavetskaya.bstu_canteen.api.response.CategoriesResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MenuRepository @Inject constructor(private val api: Api) : MenuProtocol.IMenuRepository {

    override fun getCategories(): Single<CategoriesResponse> {
        return api.getCategories().subscribeOn(Schedulers.io())
    }
}