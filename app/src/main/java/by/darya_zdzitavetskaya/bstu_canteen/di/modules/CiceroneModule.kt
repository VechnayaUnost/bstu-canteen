package by.darya_zdzitavetskaya.bstu_canteen.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class CiceroneModule {

    private val cicerone = Cicerone.create()

    @Singleton
    @Provides
    fun provideCicerone(): Cicerone<Router> {
        return cicerone
    }

    @Singleton
    @Provides
    fun provideRouter(): Router {
        return cicerone.router
    }
}