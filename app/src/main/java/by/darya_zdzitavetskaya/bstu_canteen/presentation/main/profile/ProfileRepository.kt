package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.profile

import by.darya_zdzitavetskaya.bstu_canteen.api.Api
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface ProfileRepository {

    fun logout(): Completable
}

class ProfileRepositoryImpl @Inject constructor(
    private val api: Api
) : ProfileRepository {

    override fun logout(): Completable {
        return api.logout().subscribeOn(Schedulers.io())
    }

}