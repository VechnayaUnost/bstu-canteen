package by.darya_zdzitavetskaya.bstu_canteen.presentation.auth

import by.darya_zdzitavetskaya.bstu_canteen.api.Api
import by.darya_zdzitavetskaya.bstu_canteen.api.request.LoginRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.request.RegistrationRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.request.SetPasswordRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.request.VerifyRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.response.UserResponse
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api: Api) : AuthProtocol.IAuthRepository {

    override fun login(email: String, password: String): Single<Response<UserResponse>> {
        return api.login(LoginRequest(email, password)).subscribeOn(Schedulers.io())
    }

    override fun registration(email: String): Single<Response<String>> {
        return api.registration(RegistrationRequest(email)).subscribeOn(Schedulers.io())
    }

    override fun verify(userId: String, code: Int): Single<Response<UserResponse>> {
        return api.verify(VerifyRequest(userId, code)).subscribeOn(Schedulers.io())
    }

    override fun resend(userId: String): Completable {
        return api.resend(userId).subscribeOn(Schedulers.io())
    }

    override fun setPassword(password: String): Completable {
        return api.setPassword(SetPasswordRequest(password)).subscribeOn(Schedulers.io())
    }
}