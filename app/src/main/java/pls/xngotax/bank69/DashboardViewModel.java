package pls.xngotax.bank69;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {
    private MutableLiveData<String> namaLengkap = new MutableLiveData<>();
    private MutableLiveData<Integer> saldo = new MutableLiveData<>();
    private int userId = 1;
    private Context context;
    public DashboardViewModel(Context context) {
        namaLengkap.setValue("Nama Lengkap");
        saldo.setValue(0);
        this.context = context;
    }

    public LiveData<String> getNamaLengkap() {
        return namaLengkap;
    }

    public LiveData<Integer> getSaldo() {
        return saldo;
    }

    public void loadUserDataFromDatabase() {
        UserRepository userRepository = new UserRepository(context);
        User user = userRepository.getUserById(userId);

        if (user != null) {
            namaLengkap.setValue(user.getNamaLengkap());
            saldo.setValue(user.getSaldo());
        }
    }
}
