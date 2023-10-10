package pls.xngotax.bank69;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditProfileViewModel extends ViewModel {
    private MutableLiveData<String> namaLengkapLiveData = new MutableLiveData<>();
    private MutableLiveData<String> emailLiveData = new MutableLiveData<>();
    private MutableLiveData<String> usernameLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isProfileSavedLiveData = new MutableLiveData<>();

    public LiveData<String> getNamaLengkapLiveData() {
        return namaLengkapLiveData;
    }

    public LiveData<String> getEmailLiveData() {
        return emailLiveData;
    }

    public LiveData<String> getUsernameLiveData() {
        return usernameLiveData;
    }

    public LiveData<Boolean> getIsProfileSavedLiveData() {
        return isProfileSavedLiveData;
    }

    public void loadUserData() {
        namaLengkapLiveData.setValue("Nama Lengkap Pengguna");
        emailLiveData.setValue("email@example.com");
        usernameLiveData.setValue("username_pengguna");
    }

    public void saveProfile(String newNamaLengkap, String newEmail, String newUsername) {
        boolean isSuccess = updateProfile(newNamaLengkap, newEmail, newUsername);

        if (isSuccess) {
            isProfileSavedLiveData.setValue(true);
        } else {
            isProfileSavedLiveData.setValue(false);
        }
    }

    private boolean updateProfile(String newNamaLengkap, String newEmail, String newUsername) {
        try {
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
