package rodolfo.com.br.tarefasmvvm.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.Serializable;

import rodolfo.com.br.tarefasmvvm.R;
import rodolfo.com.br.tarefasmvvm.model.Tarefa;

public class FormActivity extends AppCompatActivity {

    private static final String TAREFA = "Tarefa";
    private static final String POSICAO = "Posicao";
    private Button btn_salvar;
    private EditText et_titulo,et_descricao;
    private RadioButton radio_facil,radio_medio,radio_dificil;
    private boolean alterar = false;
    private int dificuldade;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        btn_salvar = findViewById(R.id.btn_salvar);
        et_titulo = findViewById(R.id.et_titulo);
        et_descricao = findViewById(R.id.et_descricao);
        radio_facil = findViewById(R.id.radio_facil);
        radio_medio = findViewById(R.id.radio_medio);
        radio_dificil = findViewById(R.id.radio_dificil);

        Tarefa tarefa = (Tarefa) getIntent().getSerializableExtra(TAREFA);

        if(tarefa!=null){
            position = getIntent().getIntExtra(POSICAO,-1);
            alterar = true;
            btn_salvar.setText("Alterar");
            et_titulo.setText(tarefa.getTitle());
            et_descricao.setText(tarefa.getDescricao());
            if(tarefa.getDificuldade()==1) radio_facil.setChecked(true);
            if(tarefa.getDificuldade()==2) radio_medio.setChecked(true);
            if(tarefa.getDificuldade()==3) radio_dificil.setChecked(true);
        }


        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alterar){
                   Tarefa tarefa = pegaDadosDigitados();
                   Intent intent = new Intent();
                   intent.putExtra(TAREFA,tarefa);
                   intent.putExtra(POSICAO,position);
                   setResult(Activity.RESULT_OK,intent);
                   finish();
                }else{
                    Tarefa tarefa = pegaDadosDigitados();
                    Intent intent = new Intent();
                    intent.putExtra(TAREFA,tarefa);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            }
        });
    }

    private Tarefa pegaDadosDigitados(){
        Tarefa tarefa = new Tarefa();
        tarefa.setTitle(et_titulo.getText().toString());
        tarefa.setDescricao(et_descricao.getText().toString());
        if(radio_facil.isChecked()) dificuldade=1;
        if(radio_medio.isChecked()) dificuldade=2;
        if(radio_dificil.isChecked()) dificuldade=3;
        tarefa.setDificuldade(dificuldade);
        return tarefa;
    }
}
