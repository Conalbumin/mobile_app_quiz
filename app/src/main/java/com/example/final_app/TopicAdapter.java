package com.example.final_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicHolder> {
    private ArrayList<Topic> topicArrayList;
    private Context context;

    private OnItemClickListener onItemClickListener;
    private OnDeleteClickListener onDeleteClickListener;

    public TopicAdapter(ArrayList<Topic> topicArrayList, Context context){
        this.topicArrayList=topicArrayList;
        this.context=context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.onDeleteClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Topic topic);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Topic topic);
    }

    @NonNull
    @Override
    public TopicAdapter.TopicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TopicHolder(LayoutInflater.from(context).inflate(R.layout.topic_card_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopicHolder holder, int position) {
        Topic topic=topicArrayList.get(position);
        holder.topicTitle.setText(topic.getSetTitle());
        holder.topicDis.setText(topic.getSetDes());
        holder.userName.setText(topic.getUserName());

        holder.deleteBtn.setOnClickListener(v->{
            if (onDeleteClickListener != null) {
                onDeleteClickListener.onDeleteClick(topic);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicArrayList.size();
    }

    class TopicHolder extends RecyclerView.ViewHolder{
        private final TextView topicTitle;
        private final TextView topicDis;
        private final TextView userName;
        private Button deleteBtn;
        public TopicHolder(@NonNull View itemView) {
            super(itemView);
            topicTitle = itemView.findViewById(R.id.topicName);
            topicDis = itemView.findViewById(R.id.topicDes);
            userName=itemView.findViewById(R.id.userName);
            deleteBtn=itemView.findViewById(R.id.deleteBtn);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                    onItemClickListener.onItemClick(topicArrayList.get(position));
                }
            });
        }
    }
}
