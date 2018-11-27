package com.example.lenovo.note;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends ListAdapter<Note , MyAdapter.MyHolder> {
    public onItemClickListener listener;

    public MyAdapter() {
        super(DIFF_CALLBACK);
    }
    //This callback is responsiable for knowing which item we will delete or add or  update and then change this item only and not
    //changing all recyclerview like notifyDataChange does !!!!
    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(Note oldItem, Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Note oldItem, Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPeriority() == newItem.getPeriority();
        }
    };
    @NonNull
    @Override

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_item,viewGroup,false);
        return new MyHolder(item);
    }
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Note currentNote = getItem(i);
        myHolder.Title.setText(currentNote.getTitle());
        myHolder.Description.setText(currentNote.getDescription());
        myHolder.Priority.setText(String.valueOf(currentNote.getPeriority()));
    }
    public Note getNoteAt(int position) {
        return getItem(position);
    }
    class MyHolder extends RecyclerView.ViewHolder{
        private TextView Title,Description,Priority;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.text_view_title);
            Description = itemView.findViewById(R.id.text_view_description);
            Priority = itemView.findViewById(R.id.text_view_priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
    public interface onItemClickListener {
        void onItemClick(Note note);
    }
    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
}
