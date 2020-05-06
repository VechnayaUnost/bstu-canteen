package by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.confirmation_code

import android.content.Context
import android.os.CountDownTimer
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.AuthProtocol
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import by.darya_zdzitavetskaya.bstu_canteen.shared.ITokenCache
import by.darya_zdzitavetskaya.bstu_canteen.shared.IUserCache
import by.darya_zdzitavetskaya.bstu_canteen.utils.toEvent
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val TIMER_MAX_TIME = 300000L
private const val TIMER_TIK = 1000L
private const val AUTHORIZATION = "auth-token"

class ConfirmationCodeViewModel @Inject constructor(
    private val context: Context,
    private val router: Router,
    private val authRepository: AuthProtocol.IAuthRepository,
    private val tokenCache: ITokenCache,
    private val userCache: IUserCache
) : BaseViewModel() {

    var userEmail = ObservableField<String>("")
    var userId: String = ""

    val timerText: ObservableField<String> by lazy {
        startTimer()
        ObservableField("")
    }

    val isTimerVisible: ObservableBoolean = ObservableBoolean(true)

    private var timer: CountDownTimer? = null

    private fun startTimer() {
        isTimerVisible.set(true)
        timer = object : CountDownTimer(TIMER_MAX_TIME, TIMER_TIK) {
            override fun onTick(p0: Long) {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(p0)
                val seconds =
                    TimeUnit.MILLISECONDS.toSeconds(p0 - TimeUnit.MINUTES.toMillis(minutes))
                timerText.set(context.getString(R.string.sms_protection_timer, minutes, seconds))
            }

            override fun onFinish() {
                isTimerVisible.set(false)
            }
        }.start()
    }

    fun verifyEmail(code: Int) {
        authRepository.verify(userId, code)
            .applySchedulers()
            .subscribe({
                if (it.isSuccessful) {
                    it.body()?.run {
                        userCache.newUser(this)
                    }
                    tokenCache.onNewAccessToken(it.headers()[AUTHORIZATION] ?: "")
                    router.replaceScreen(Screens.PasswordScreen())
                } else {
                    errorMessage.postValue(it.message().toEvent())
                }
            }, {
                it.printStackTrace()
            }).addDisposable()
    }

    fun resendCode() {
        authRepository.resend(userId)
            .applySchedulers()
            .subscribe({
                startTimer()
            }, {
                it.printStackTrace()
            }).addDisposable()
    }

    override fun onCleared() {
        timer?.cancel()

        super.onCleared()
    }
}