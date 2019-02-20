package br.com.paulosalvatore.pagseguroandroidturmaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String resultado = data.getStringExtra("RESULTADO");
            Toast.makeText(this, resultado, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton) {
            // radioButton selecionado
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia uma Activity, enviando uma informação junto
//                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
//                intent.putExtra("INFO", "MainActivity");
//                startActivity(intent);

                // Inicia uma Activity, esperando uma resposta/resultado
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
//                MainActivity.this.finish();


//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });
    }

}
