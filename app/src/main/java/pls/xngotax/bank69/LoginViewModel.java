package pls.xngotax.bank69;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private UserRepository userRepository;
    private MutableLiveData<Boolean> authenticationState = new MutableLiveData<>();

    public LoginViewModel(Context context) {
        userRepository = new UserRepository(context);
        authenticationState.setValue(false);
    }

    public void authenticateUser(String username, String password) {
        User user = userRepository.getUserByUsernameAndPassword(username, password);
        if (user != null) {
            authenticationState.setValue(true);
        } else {
            authenticationState.setValue(false);
        }
    }

    public LiveData<Boolean> getAuthenticationState() {
        return authenticationState;
    }
}

