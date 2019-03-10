package rodolfo.com.br.tarefasmvvm.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rodolfo.com.br.tarefasmvvm.R;
import rodolfo.com.br.tarefasmvvm.model.Tarefa;

public class TarefasAdapter extends RecyclerView.Adapter<TarefasAdapter.TarefasHolder> {

    private List<Tarefa> tarefas;
    private Context context;
    private OnTarefaClickListener listener;

    public interface OnTarefaClickListener{
        void onTarefaClicked(Tarefa tarefa,int position);
    }

    public void setOnTarefaClickListener(OnTarefaClickListener listener){
        this.listener = listener;
    }

    public TarefasAdapter(List<Tarefa> tarefas, Context context) {
        this.tarefas = tarefas;
        this.context = context;
    }

    @NonNull
    @Override
    public TarefasHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tarefa_item_row,viewGroup,false);
        return new TarefasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefasHolder holder, int position) {
        Tarefa tarefa = tarefas.get(position);
        holder.popularCampos(tarefa);
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }



    public class TarefasHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_titulo,tv_descricao;
        View view_cor;
        public TarefasHolder(@NonNull View itemView) {
            super(itemView);
            tv_titulo = itemView.findViewById(R.id.tarefa_tv_titulo);
            tv_descricao = itemView.findViewById(R.id.tarefa_tv_descricao);
            view_cor = itemView.findViewById(R.id.view_color);
            itemView.setOnClickListener(this);
        }

        public void popularCampos(Tarefa tarefa) {
            tv_titulo.setText(tarefa.getTitle());
            tv_descricao.setText(tarefa.getDescricao());
            if(tarefa.getDificuldade()==1) view_cor.setBackgroundColor(context.getResources().getColor(android.R.color.holo_orange_light));
            if(tarefa.getDificuldade()==2) view_cor.setBackgroundColor(context.getResources().getColor(android.R.color.holo_orange_dark));
            if(tarefa.getDificuldade()==3) view_cor.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));
        }

        @Override
        public void onClick(View view) {
            listener.onTarefaClicked(tarefas.get(getAdapterPosition()),getAdapterPosition());
        }
    }


    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas=tarefas;
        notifyDataSetChanged();
    }
}
