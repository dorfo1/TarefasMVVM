package rodolfo.com.br.tarefasmvvm.model;

import java.io.Serializable;

public class Tarefa implements Serializable {
    private String title;
    private String descricao;
    private int dificuldade;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }
}
