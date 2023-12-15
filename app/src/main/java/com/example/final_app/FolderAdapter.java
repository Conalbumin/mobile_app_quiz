package com.example.final_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderHolder> {
    private ArrayList<Folder> folderArrayList;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public FolderAdapter(ArrayList<Folder> folderArrayList, Context context){
        this.folderArrayList=folderArrayList;
        this.context=context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Folder folder);
    }

    @NonNull
    @Override
    public FolderAdapter.FolderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FolderHolder(LayoutInflater.from(context).inflate(R.layout.folder_card_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FolderAdapter.FolderHolder holder, int position) {
        Folder folder=folderArrayList.get(position);
        holder.folderName.setText(folder.getFolderName());
        holder.folderDis.setText(folder.getFolderDescription());
        holder.userName.setText(folder.getUserName());
    }

    @Override
    public int getItemCount() {
        return folderArrayList.size();
    }

    class FolderHolder extends RecyclerView.ViewHolder{
        private final TextView folderName;
        private final TextView folderDis;
        private final TextView userName;
        public FolderHolder(@NonNull View itemView) {
            super(itemView);
            folderName = itemView.findViewById(R.id.folderName);
            folderDis = itemView.findViewById(R.id.folderDes);
            userName=itemView.findViewById(R.id.userName);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                    onItemClickListener.onItemClick(folderArrayList.get(position));
                }
            });
        }
    }
}
