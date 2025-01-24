package com.faradilla.gratuit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context context;
    private List<Note> noteList;
    private DatabaseHelper databaseHelper;

    public NoteAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDescription());

        holder.deleteButton.setOnClickListener(v -> {
            databaseHelper.deleteNote(note.getId());
            noteList.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageButton deleteButton;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.noteTitle);
            description = itemView.findViewById(R.id.noteDescription);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}

