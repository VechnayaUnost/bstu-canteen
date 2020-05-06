package by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.password

import androidx.databinding.ObservableBoolean
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.AuthProtocol
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.login.PASSWORD_PATTERN
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import com.jakewharton.rxbinding3.InitialValueObservable
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class PasswordViewModel @Inject constructor(
    private val router: Router,
    private val authRepository: AuthProtocol.IAuthRepository
) : BaseViewModel() {

    val isPasswordValid = ObservableBoolean()

    fun setPassword(password: String) {
        authRepository.setPassword(password)
            .applySchedulers()
            .subscribe({
                router.replaceScreen(Screens.MainScreen())
            }, {
                it.printStackTrace()
            }).addDisposable()
    }

    fun listenPasswordField(passwordObservable: InitialValueObservable<CharSequence>) {
        passwordObservable
            .skipInitialValue()
            .subscribeOn(Schedulers.io())
            .subscribe {
                isPasswordValid.set(Regex(PASSWORD_PATTERN).matches(it))
            }
            .addDisposable()
    }
}