package by.darya_zdzitavetskaya.bstu_canteen.di.modules

import by.darya_zdzitavetskaya.bstu_canteen.shared.ITokenCache
import by.darya_zdzitavetskaya.bstu_canteen.shared.IUserCache
import by.darya_zdzitavetskaya.bstu_canteen.shared.TokenCache
import by.darya_zdzitavetskaya.bstu_canteen.shared.UserCache
import by.darya_zdzitavetskaya.bstu_canteen.utils.ISharedPreferencesUtil
import by.darya_zdzitavetskaya.bstu_canteen.utils.SharedPreferencesUtil
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class SharedModule {

    @Binds
    @Singleton
    abstract fun bindTokenCache(tokenCache: TokenCache): ITokenCache

    @Binds
    @Singleton
    abstract fun bindSharePreferences(shared: SharedPreferencesUtil): ISharedPreferencesUtil

    @Binds
    @Singleton
    abstract fun bindUserCache(userCache: UserCache): IUserCache
}