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
    private List<Note> notes;
    private MainActivity activity;

    public NoteAdapter(MainActivity activity, List<Note> notes) {
        this.notes = notes;
        this.activity = activity;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDescription());

        // Set click listener untuk tombol delete
        holder.deleteButton.setOnClickListener(v -> activity.showDeleteDialog(position));  // Memanggil dialog di MainActivity
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
    }

    // Method untuk menghapus item
    public void removeItem(int position) {
        notes.remove(position);
        notifyItemRemoved(position);
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageButton deleteButton;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.noteTitle);
            description = itemView.findViewById(R.id.noteDescription);
            deleteButton = itemView.findViewById(R.id.deleteButton);  // Tombol delete
        }
    }
}
