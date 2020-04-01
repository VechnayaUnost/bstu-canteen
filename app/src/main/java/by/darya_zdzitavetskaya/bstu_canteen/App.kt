package by.darya_zdzitavetskaya.bstu_canteen

import by.darya_zdzitavetskaya.bstu_canteen.di.DaggerAppComponent
import by.darya_zdzitavetskaya.bstu_canteen.di.modules.GlobalModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .globalModule(GlobalModule(applicationContext))
            .build()
}