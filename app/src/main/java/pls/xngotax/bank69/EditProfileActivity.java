package pls.xngotax.bank69;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {
    private EditProfileViewModel viewModel;

    private EditText etNamaLengkap;
    private EditText etEmail;
    private EditText etUsername;
    private Button btnSave;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        viewModel = new ViewModelProvider(this).get(EditProfileViewModel.class);

        etNamaLengkap = findViewById(R.id.etNamaLengkap);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        btnSave = findViewById(R.id.btnSave);
        btnLogout = findViewById(R.id.btnLogout);

        viewModel.loadUserData();

        viewModel.getNamaLengkapLiveData().observe(this, namaLengkap -> etNamaLengkap.setText(namaLengkap));
        viewModel.getEmailLiveData().observe(this, email -> etEmail.setText(email));
        viewModel.getUsernameLiveData().observe(this, username -> etUsername.setText(username));
        viewModel.getIsProfileSavedLiveData().observe(this, isProfileSaved -> {
            if (isProfileSaved) {
                Toast.makeText(this, "Profil berhasil disimpan", Toast.LENGTH_SHORT).show();
           }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newNamaLengkap = etNamaLengkap.getText().toString();
                String newEmail = etEmail.getText().toString();
                String newUsername = etUsername.getText().toString();
                viewModel.saveProfile(newNamaLengkap, newEmail, newUsername);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
