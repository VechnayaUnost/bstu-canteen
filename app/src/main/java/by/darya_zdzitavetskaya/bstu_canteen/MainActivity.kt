package by.darya_zdzitavetskaya.bstu_canteen

import android.os.Bundle
import by.darya_zdzitavetskaya.bstu_canteen.api.Api
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Navigator
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import by.darya_zdzitavetskaya.bstu_canteen.shared.ITokenCache
import by.darya_zdzitavetskaya.bstu_canteen.shared.IUserCache
import by.darya_zdzitavetskaya.bstu_canteen.utils.custom_views.ProgressDialog
import com.sembozdemir.permissionskt.handlePermissionsResult
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

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(this)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            if (!tokenCache.accessToken.isNullOrBlank()) {
                val disp = api.check().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { progressDialog?.show() }
                    .doFinally { progressDialog?.dismiss() }
                    .subscribe({
                        it?.let { userCache.newUser(it) }
                        ciceroneFactory.router.newRootScreen(Screens.PayScreen())
                    }, {
                        ciceroneFactory.router.newRootScreen(Screens.PayScreen())
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        handlePermissionsResult(requestCode, permissions, grantResults)
    }
}