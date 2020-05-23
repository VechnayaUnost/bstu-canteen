package by.darya_zdzitavetskaya.bstu_canteen

import by.darya_zdzitavetskaya.bstu_canteen.di.DaggerAppComponent
import by.darya_zdzitavetskaya.bstu_canteen.di.modules.GlobalModule
import com.stripe.android.PaymentConfiguration
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .globalModule(GlobalModule(applicationContext))
            .build()

    override fun onCreate() {
        super.onCreate()

        PaymentConfiguration.init(
            this,
            BuildConfig.STIPE_KEY
        )
    }
}