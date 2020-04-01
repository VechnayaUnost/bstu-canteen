package by.darya_zdzitavetskaya.bstu_canteen.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApiModule::class, SharedModule::class, CiceroneModule::class])
class GlobalModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }
}