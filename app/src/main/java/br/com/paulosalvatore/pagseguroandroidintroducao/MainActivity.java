package br.com.paulosalvatore.pagseguroandroidintroducao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvPrincipal = findViewById(R.id.tvPrincipal);
        final EditText etPrincipal = findViewById(R.id.etPrincipal);
        final Button btEnviar = findViewById(R.id.btEnviar);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPrincipal.setText(etPrincipal.getText().toString());
            }
        });
    }
}
