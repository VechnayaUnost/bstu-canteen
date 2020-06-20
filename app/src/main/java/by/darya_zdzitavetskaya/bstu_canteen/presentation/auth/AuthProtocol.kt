package by.darya_zdzitavetskaya.bstu_canteen.presentation.auth

import by.darya_zdzitavetskaya.bstu_canteen.api.response.UserResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Response

interface AuthProtocol {

    interface IAuthRepository {
        fun login(email: String, password: String): Single<Response<UserResponse>>

        fun registration(email: String): Single<Response<String>>

        fun verify(userId: String, code: Int): Single<Response<UserResponse>>

        fun resend(userId: String): Completable

        fun setPassword(password: String): Completable
    }
}