package by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.login

import android.util.Patterns
import androidx.databinding.ObservableBoolean
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.AuthProtocol
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import by.darya_zdzitavetskaya.bstu_canteen.shared.ITokenCache
import by.darya_zdzitavetskaya.bstu_canteen.shared.IUserCache
import by.darya_zdzitavetskaya.bstu_canteen.utils.toEvent
import com.jakewharton.rxbinding3.InitialValueObservable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

const val PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[!@#\$%^&*])(?=.*[a-zA-Z])[0-9a-zA-Z!@#\$%^&*]{8,}"
private const val AUTHORIZATION = "auth-token"

class LoginViewModel @Inject constructor(
    private val router: Router,
    private val authRepository: AuthProtocol.IAuthRepository,
    private val tokenCache: ITokenCache,
    private val userCache: IUserCache
) : BaseViewModel() {

    val isFieldsValid = ObservableBoolean()

    fun listenFields(
        emailObservable: InitialValueObservable<CharSequence>,
        passwordObservable: InitialValueObservable<CharSequence>
    ) {
        Observable.combineLatest(
            emailObservable.skipInitialValue(),
            passwordObservable.skipInitialValue(),
            BiFunction<CharSequence, CharSequence, Boolean> { email, password ->
                return@BiFunction (Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() && Regex(
                    PASSWORD_PATTERN
                ).matches(password))
            })
            .subscribeOn(Schedulers.io())
            .subscribe {
                isFieldsValid.set(it)
            }
            .addDisposable()
    }

    fun login(email: String, password: String) {
        authRepository.login(email, password)
            .applySchedulers()
            .subscribe({
                if (it.isSuccessful) {
                    it.body()?.run {
                        userCache.newUser(this)
                    }
                    tokenCache.onNewAccessToken(it.headers()[AUTHORIZATION] ?: "")
                    router.replaceScreen(Screens.MainScreen())
                } else {
                    errorMessage.postValue(it.message().toEvent())
                }
            }, {
                it.printStackTrace()
            }).addDisposable()
    }

    fun registration() {
        router.navigateTo(Screens.RegistrationScreen())
    }
}