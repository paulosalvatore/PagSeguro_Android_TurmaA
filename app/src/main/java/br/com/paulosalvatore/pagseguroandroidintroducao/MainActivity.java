package br.com.paulosalvatore.pagseguroandroidintroducao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvPrincipal = findViewById(R.id.tvPrincipal);
        tvPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPrincipal.setText("Novo Texto");
            }
        });
    }
}
