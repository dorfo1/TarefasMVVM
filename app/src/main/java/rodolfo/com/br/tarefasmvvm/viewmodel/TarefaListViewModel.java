package rodolfo.com.br.tarefasmvvm.viewmodel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import rodolfo.com.br.tarefasmvvm.model.Tarefa;

public class TarefaListViewModel extends AndroidViewModel {

    private MutableLiveData<List<Tarefa>> tarefas;

    public TarefaListViewModel(@NonNull Application application) {
        super(application);
        tarefas = new MutableLiveData<>();
        tarefas.setValue(new ArrayList<Tarefa>());
    }


    public LiveData<List<Tarefa>> getTarefas(){
       return tarefas;
    }


    public void setTarefa(Tarefa tarefa){
        tarefas.getValue().add(tarefa);
        tarefas.setValue(tarefas.getValue());
    }

    public void updateTarefa(int position,Tarefa tarefa){
        tarefas.getValue().set(position,tarefa);
        tarefas.setValue(tarefas.getValue());
    }





}
