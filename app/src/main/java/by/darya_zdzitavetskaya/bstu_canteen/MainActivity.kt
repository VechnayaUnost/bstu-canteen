package by.darya_zdzitavetskaya.bstu_canteen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Navigator
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable, HasAndroidInjector {

    override fun androidInjector(): DispatchingAndroidInjector<Any> = dispatchingAndroidInjector

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var ciceroneFactory: Cicerone<Router>

    override fun onCreate(savedInstanceState: Bundle?) {
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
