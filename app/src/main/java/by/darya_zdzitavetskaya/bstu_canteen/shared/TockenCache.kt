package by.darya_zdzitavetskaya.bstu_canteen.shared

import by.darya_zdzitavetskaya.bstu_canteen.utils.ISharedPreferencesUtil
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenCache @Inject constructor(private val sharedPreferences: ISharedPreferencesUtil) :
    ITokenCache {

    override var accessToken: String? = sharedPreferences.getToken()

    override fun onNewAccessToken(accessToken: String) {
        this.accessToken = accessToken
        sharedPreferences.setToken(accessToken)
    }

    override fun onClearAccessToken() {
        accessToken = null
        sharedPreferences.clearToken()
    }
}

interface ITokenCache {

    var accessToken: String?

    fun onNewAccessToken(accessToken: String)

    fun onClearAccessToken()
}