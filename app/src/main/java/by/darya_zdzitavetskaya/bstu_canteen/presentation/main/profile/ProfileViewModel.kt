package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.profile

import androidx.databinding.ObservableField
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import by.darya_zdzitavetskaya.bstu_canteen.shared.IUserCache
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val router: Router,
    private val userCache: IUserCache
) : BaseViewModel() {

    val userEmail = ObservableField<String>()

    init {
        userCache.user.subscribe {
            userEmail.set(it.email)
        }.addDisposable()
    }

    fun logout() {
        router.newRootScreen(Screens.LoginScreen())
    }
}