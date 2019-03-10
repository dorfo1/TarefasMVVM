package rodolfo.com.br.tarefasmvvm.ui;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import rodolfo.com.br.tarefasmvvm.R;
import rodolfo.com.br.tarefasmvvm.model.Tarefa;
import rodolfo.com.br.tarefasmvvm.ui.adapter.TarefasAdapter;
import rodolfo.com.br.tarefasmvvm.viewmodel.TarefaListViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAREFA = "Tarefa";
    private static final String POSICAO = "Posicao";
    private TarefaListViewModel tarefaListViewModel;
    private static final int NOVA_TAREFA_REQUEST_CODE = 10;
    private static final int ALTERAR_TAREFA_REQUEST_CODE = 11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv_tarefas = findViewById(R.id.rv_tarefas);
        FloatingActionButton fab_novo = findViewById(R.id.fab_novo);
        tarefaListViewModel = ViewModelProviders.of(this).get(TarefaListViewModel.class);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv_tarefas.setLayoutManager(manager);
        final TarefasAdapter adapter = new TarefasAdapter(new ArrayList<Tarefa>(),this);
        rv_tarefas.setAdapter(adapter);

        adapter.setOnTarefaClickListener(new TarefasAdapter.OnTarefaClickListener() {
            @Override
            public void onTarefaClicked(Tarefa tarefa, int position) {
                Intent i = new Intent(MainActivity.this,FormActivity.class);
                i.putExtra(TAREFA,tarefa);
                i.putExtra(POSICAO,position);
                startActivityForResult(i,ALTERAR_TAREFA_REQUEST_CODE);
            }
        });

        tarefaListViewModel.getTarefas().observe(this, new Observer<List<Tarefa>>() {
            @Override
            public void onChanged(@Nullable List<Tarefa> tarefas) {
                adapter.setTarefas(tarefas);
            }
        });




        fab_novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivityForResult(intent,NOVA_TAREFA_REQUEST_CODE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==NOVA_TAREFA_REQUEST_CODE && resultCode== Activity.RESULT_OK){
            if(data!=null){
                tarefaListViewModel.setTarefa((Tarefa) data.getSerializableExtra(TAREFA));
            }
        }
        if(requestCode==ALTERAR_TAREFA_REQUEST_CODE && resultCode==Activity.RESULT_OK){
            if(data!=null){
                tarefaListViewModel.updateTarefa((Integer) data.getSerializableExtra(POSICAO),(Tarefa) data.getSerializableExtra(TAREFA));
            }
        }
    }
}
