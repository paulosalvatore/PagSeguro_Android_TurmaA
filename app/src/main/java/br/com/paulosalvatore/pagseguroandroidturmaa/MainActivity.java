package br.com.paulosalvatore.pagseguroandroidturmaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SetError Example
        final EditText editText = findViewById(R.id.editText);
        editText.setError("Digite dados válidos");

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editText.setError("Digite algo...");
            }
        });
        /*Exercício - RecyclerView + Transferência de dados entre telas

        Na 1ª tela: Recycler View + Floating Action Button
        Na 2ª tela: Formulário para cadastro de uma ProgrammingLanguage.
        Validar se todos os dados estão digitados corretamente usando o setError para enviar uma mensagem de
        erro personalizada.
        Retorna programming language criada para a primeira tela e atualiza a RecyclerView*/


        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(
                R.drawable.java,
                "Java",
                1996,
                "Java Description"
        );

        List<ProgrammingLanguage> programmingLanguages = new ArrayList<>();
        programmingLanguages.add(programmingLanguage);
        programmingLanguages.add(programmingLanguage);
        programmingLanguages.add(programmingLanguage);
        programmingLanguages.add(programmingLanguage);
        programmingLanguages.add(programmingLanguage);
        programmingLanguages.add(programmingLanguage);
        programmingLanguages.add(programmingLanguage);
        programmingLanguages.add(programmingLanguage);
        programmingLanguages.add(programmingLanguage);
        programmingLanguages.add(programmingLanguage);
        programmingLanguages.add(programmingLanguage);
        programmingLanguages.add(programmingLanguage);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvTitle = v.findViewById(R.id.tvTitle);
                Toast.makeText(MainActivity.this, tvTitle.getText(), Toast.LENGTH_LONG).show();
            }
        };

        RecyclerView.Adapter adapter = new ProgrammingLanguageAdapter(programmingLanguages, listener);
        recyclerView.setAdapter(adapter);
    }
}
