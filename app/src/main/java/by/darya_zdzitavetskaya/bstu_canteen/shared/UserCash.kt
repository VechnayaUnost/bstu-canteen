package by.darya_zdzitavetskaya.bstu_canteen.shared

import by.darya_zdzitavetskaya.bstu_canteen.api.response.UserResponse
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class UserCache @Inject constructor() : IUserCache {

    override val user = BehaviorSubject.create<UserResponse>()

    override fun newUser(userResponse: UserResponse) {
        user.onNext(userResponse)
    }
}

interface IUserCache {

    fun newUser(userResponse: UserResponse)

    val user: Observable<UserResponse>
}