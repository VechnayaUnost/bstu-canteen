package by.darya_zdzitavetskaya.bstu_canteen.api

import by.darya_zdzitavetskaya.bstu_canteen.api.request.LoginRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.request.RegistrationRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.request.SetPasswordRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.request.VerifyRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.response.CategoriesResponse
import by.darya_zdzitavetskaya.bstu_canteen.api.response.CategoryResponse
import by.darya_zdzitavetskaya.bstu_canteen.api.response.UserResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    companion object {

        private const val LOGIN_ENDPOINT = "api/auth/login"

        private const val REGISTRATION_ENDPOINT = "api/auth/registration"

        private const val VERIFY_ENDPOINT = "api/auth/verify"

        private const val RESEND_ENDPOINT = "api/auth/resend/{userId}"

        private const val SET_PASSWORD_ENDPOINT = "api/auth/setPassword"

        private const val CATEGORIES_ENDPOINT = "api/categories"

        private const val GET_CATEGORY_ENDPOINT = "api/categories/{categoryId}"

    }

    @POST(LOGIN_ENDPOINT)
    fun login(@Body body: LoginRequest): Single<Response<UserResponse>>

    @POST(REGISTRATION_ENDPOINT)
    fun registration(@Body body: RegistrationRequest): Single<Response<String>>

    @POST(VERIFY_ENDPOINT)
    fun verify(@Body body: VerifyRequest): Single<Response<UserResponse>>

    @POST(RESEND_ENDPOINT)
    fun resend(@Path("userId") userId: String): Completable

    @POST(SET_PASSWORD_ENDPOINT)
    fun setPassword(@Body body: SetPasswordRequest): Completable

    @GET(CATEGORIES_ENDPOINT)
    fun getCategories(): Single<Response<CategoriesResponse>>

    @GET(GET_CATEGORY_ENDPOINT)
    fun getCategory(@Path("categoryId") categoryId: String): Single<Response<CategoryResponse>>
}