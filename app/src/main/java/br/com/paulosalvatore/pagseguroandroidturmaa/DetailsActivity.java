package br.com.paulosalvatore.pagseguroandroidturmaa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    static final String TEXT = "TEXT";

    private TextView textView;
    private EditText editText;
    private Button btApplyChanges;
    private Button btKeepValue;
    private Button btResetValue;

    private String persistText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final Intent intent = getIntent();

        if (intent != null) {
            String info = intent.getStringExtra("INFO");
            Toast.makeText(this, info, Toast.LENGTH_LONG).show();
        }

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        btApplyChanges = findViewById(R.id.btApplyChanges);
        btKeepValue = findViewById(R.id.btKeepValue);
        btResetValue = findViewById(R.id.btResetValue);

        btApplyChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                textView.setText(text);

                // Definindo resultado
                if (intent != null) {
                    intent.putExtra("RESULTADO", text);
                    setResult(RESULT_OK, intent);
                    DetailsActivity.this.finish();
                }
            }
        });

        btKeepValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persistText = editText.getText().toString();
                Toast.makeText(DetailsActivity.this, "Persisted Value: " + persistText, Toast.LENGTH_LONG).show();
            }
        });

        btResetValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persistText = null;
                Toast.makeText(DetailsActivity.this, "Value reseted.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (persistText != null && !persistText.isEmpty()) {
            outState.putString(TEXT, textView.getText().toString());
        } else {
            outState.remove(TEXT);
        }

        // Exemplo com Serializable
        UsuarioSerializable usuarioSerializable = new UsuarioSerializable("Paulo", "Salvatore");
        outState.putSerializable("USUARIO_SERIALIZABLE", usuarioSerializable);

        // Exemplo com Parcelable
        UsuarioParcelable usuarioParcelable = new UsuarioParcelable("Paulo", "Salvatore");
        outState.putParcelable("USUARIO_PARCELABLE", usuarioParcelable);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String text = savedInstanceState.getString(TEXT);
        textView.setText(text);

        UsuarioSerializable usuarioSerializable =
                (UsuarioSerializable) savedInstanceState.getSerializable("USUARIO_SERIALIZABLE");
        if (usuarioSerializable != null) {
            Log.d(getClass().getName(), usuarioSerializable.getNome());
            Log.d(getClass().getName(), usuarioSerializable.getSobrenome());
        }

        UsuarioParcelable usuarioParcelable = savedInstanceState.getParcelable("USUARIO_PARCELABLE");
        if (usuarioParcelable != null) {
            Log.d(getClass().getName(), usuarioParcelable.getNome());
            Log.d(getClass().getName(), usuarioParcelable.getSobrenome());
        }
    }
}
