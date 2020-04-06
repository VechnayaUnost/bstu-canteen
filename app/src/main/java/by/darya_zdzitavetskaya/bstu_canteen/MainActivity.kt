package by.darya_zdzitavetskaya.bstu_canteen

import android.os.Bundle
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Navigator
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import dagger.android.support.DaggerAppCompatActivity
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var ciceroneFactory: Cicerone<Router>

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ciceroneFactory.router.newRootScreen(Screens.LoginScreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        ciceroneFactory.navigatorHolder.setNavigator(
            Navigator(
                this,
                supportFragmentManager,
                R.id.fragmentContainer
            )
        )
    }

    override fun onPause() {
        super.onPause()
        ciceroneFactory.navigatorHolder.removeNavigator()
    }
}