package by.darya_zdzitavetskaya.bstu_canteen.api

import by.darya_zdzitavetskaya.bstu_canteen.api.request.LoginRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.request.RegistrationRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.request.SetPasswordRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.request.VerifyRequest
import by.darya_zdzitavetskaya.bstu_canteen.api.response.*
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface Api {

    companion object {

        private const val CHECK_ENDPOINT = "api/auth/check"

        private const val LOGIN_ENDPOINT = "api/auth/login"

        private const val REGISTRATION_ENDPOINT = "api/auth/registration"

        private const val VERIFY_ENDPOINT = "api/auth/verify"

        private const val RESEND_ENDPOINT = "api/auth/resend/{userId}"

        private const val SET_PASSWORD_ENDPOINT = "api/auth/setPassword"

        private const val CATEGORIES_ENDPOINT = "api/categories"

        private const val GET_CATEGORY_ENDPOINT = "api/categories/{categoryId}"

        private const val CREATE_PRODUCT_ENDPOINT = "api/products/create"

        private const val GET_PRODUCT_ENDPOINT = "api/products/getById/{productId}"

        private const val UPDATE_PRODUCT_ENDPOINT = "api/products/update"

        private const val DELETE_PRODUCT_ENDPOINT = "api/products/delete/{productId}"

        private const val UPLOAD_PRODUCT_IMAGE_ENDPOINT = "api/upload/productImage"

    }

    @GET(CHECK_ENDPOINT)
    fun check(): Single<UserResponse>

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
    fun getCategories(): Single<CategoriesResponse>

    @GET(GET_CATEGORY_ENDPOINT)
    fun getCategory(@Path("categoryId") categoryId: String): Single<CategoryResponse>

    @POST(CREATE_PRODUCT_ENDPOINT)
    fun createProduct(@Body body: Product): Single<Response<Product>>

    @GET(GET_PRODUCT_ENDPOINT)
    fun getProduct(@Path("productId") productId: String): Single<ProductResponse>

    @PUT(UPDATE_PRODUCT_ENDPOINT)
    fun updateProduct(@Body body: Product): Single<Response<Product>>

    @DELETE(DELETE_PRODUCT_ENDPOINT)
    fun deleteProduct(@Path("productId") productId: String): Completable

    @Multipart
    @POST(UPLOAD_PRODUCT_IMAGE_ENDPOINT)
    fun uploadImage(@Part file: MultipartBody.Part): Single<UploadImageResponse>
}