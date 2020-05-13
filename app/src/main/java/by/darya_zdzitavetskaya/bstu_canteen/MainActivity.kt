package by.darya_zdzitavetskaya.bstu_canteen

import android.os.Bundle
import by.darya_zdzitavetskaya.bstu_canteen.api.Api
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Navigator
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import by.darya_zdzitavetskaya.bstu_canteen.shared.ITokenCache
import by.darya_zdzitavetskaya.bstu_canteen.shared.IUserCache
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var ciceroneFactory: Cicerone<Router>

    @Inject
    lateinit var tokenCache: ITokenCache

    @Inject
    lateinit var userCache: IUserCache

    @Inject
    lateinit var api: Api

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            if (!tokenCache.accessToken.isNullOrBlank()) {
                val disp = api.check().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it.isSuccessful) {
                            it.body()?.let { user -> userCache.newUser(user) }
                            ciceroneFactory.router.newRootScreen(Screens.MainScreen())
                        } else {
                            ciceroneFactory.router.newRootScreen(Screens.LoginScreen())
                        }
                    }, {
                        ciceroneFactory.router.newRootScreen(Screens.LoginScreen())
                    })
            } else {
                ciceroneFactory.router.newRootScreen(Screens.LoginScreen())
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        ciceroneFactory.navigatorHolder.setNavigator(
            Navigator(
                this,
                supportFragmentManager,
                R.id.mainContainer
            )
        )
    }

    override fun onPause() {
        super.onPause()
        ciceroneFactory.navigatorHolder.removeNavigator()
    }
}