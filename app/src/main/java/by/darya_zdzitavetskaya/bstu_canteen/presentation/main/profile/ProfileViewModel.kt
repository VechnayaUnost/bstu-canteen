package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.profile

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import by.darya_zdzitavetskaya.bstu_canteen.shared.IUserCache
import by.darya_zdzitavetskaya.bstu_canteen.shared.TokenCache
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val router: Router,
    private val userCache: IUserCache,
    private val tokenCache: TokenCache,
    private val profileRepository: ProfileRepository
) : BaseViewModel() {

    val userEmail = ObservableField<String>()
    val isAdminObservable = ObservableBoolean()

    init {
        userCache.user.subscribe {
            userEmail.set(it.email)
            isAdminObservable.set(it.isAdmin)
        }.addDisposable()
    }

    fun logout() {
        profileRepository.logout()
            .applySchedulers()
            .subscribe({
                tokenCache.onClearAccessToken()
                router.newRootScreen(Screens.LoginScreen())
            }, {
                it.printStackTrace()
            }).addDisposable()
    }

    fun scanQrCode() {
        router.navigateTo(Screens.ScannerScreen())
    }

    fun goToPass() {
        router.navigateTo(Screens.PasswordScreen())
    }

    fun payment() {
        router.navigateTo(Screens.PayScreen())
    }
}