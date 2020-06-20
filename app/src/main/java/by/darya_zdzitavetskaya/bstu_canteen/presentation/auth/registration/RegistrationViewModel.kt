package by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.registration

import android.util.Patterns
import androidx.databinding.ObservableBoolean
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.AuthProtocol
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import by.darya_zdzitavetskaya.bstu_canteen.utils.toEvent
import com.jakewharton.rxbinding3.InitialValueObservable
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val router: Router,
    private val authRepository: AuthProtocol.IAuthRepository
) : BaseViewModel() {

    val isEmailValid = ObservableBoolean()

    fun listenEmailField(emailObservable: InitialValueObservable<CharSequence>) {
        emailObservable
            .skipInitialValue()
            .subscribeOn(Schedulers.io())
            .subscribe {
                isEmailValid.set(Patterns.EMAIL_ADDRESS.matcher(it.trim()).matches())
            }
            .addDisposable()
    }

    fun confirmEmail(email: String) {
        authRepository.registration(email)
            .applySchedulers()
            .subscribe({
                if (it.isSuccessful) {
                    it.body()?.let { userId ->
                        router.navigateTo(Screens.ConfirmationCodeScreen(userId, email))
                    }
                } else {
                    errorMessage.postValue(it.message().toEvent())
                }
            }, {
                it.printStackTrace()
            }).addDisposable()
    }
}