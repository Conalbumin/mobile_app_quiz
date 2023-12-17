package com.example.final_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermHolder> {
    private ArrayList<Term> termArrayList;
    private Context context;
    private TopicAdapter.OnItemClickListener onItemClickListener;
    private TopicAdapter.OnDeleteClickListener onDeleteClickListener;

    public TermAdapter(ArrayList<Term> termArrayList, Context context){
        this.termArrayList=termArrayList;
        this.context=context;
    }

    public void addTerm(Term term) {
        termArrayList.add(term);
        notifyItemInserted(termArrayList.size() - 1);
    }

    public ArrayList<Term> getTermArrayList() {
        return termArrayList;
    }

    @NonNull
    @Override
    public TermAdapter.TermHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TermHolder(LayoutInflater.from(context).inflate(R.layout.custom_term_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermHolder holder, int position) {
        Term term=termArrayList.get(position);
        holder.term.setText(term.getTerm());
        holder.defi.setText(term.getDefinition());
    }

    @Override
    public int getItemCount() {
        return termArrayList.size();
    }

    class TermHolder extends RecyclerView.ViewHolder{
        private final TextView term;
        private final TextView defi;

        public TermHolder(@NonNull View itemView){
            super(itemView);
            term=itemView.findViewById(R.id.termEditText);
            defi=itemView.findViewById(R.id.defEditText);
        }
    }
}
