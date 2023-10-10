package pls.xngotax.bank69;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class DashboardActivity extends AppCompatActivity {
    private DashboardViewModel dashboardViewModel;
    private TextView welcomeMessageTextView, saldoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        welcomeMessageTextView = findViewById(R.id.welcomeMessageTextView);
        saldoTextView = findViewById(R.id.saldoTextView);

        dashboardViewModel.getNamaLengkap().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String nama) {
                welcomeMessageTextView.setText("Selamat datang, " + nama);
            }
        });

        dashboardViewModel.getSaldo().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer saldo) {
                saldoTextView.setText("Saldo: Rp " + saldo);
            }
        });

        dashboardViewModel.loadUserDataFromDatabase();
    }
}
