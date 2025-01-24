package com.faradilla.gratuit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fabAddNote);
        fab.setOnClickListener(v -> startActivityForResult(new Intent(this, AddNoteActivity.class), 1));

        loadNotes();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            loadNotes();
        }
    }

    private void loadNotes() {
        List<Note> notes = databaseHelper.getAllNotes();
        adapter = new NoteAdapter(this, notes);
        recyclerView.setAdapter(adapter);
    }

    // Method untuk menampilkan dialog konfirmasi hapus
    public void showDeleteDialog(final int position) {
        new AlertDialog.Builder(this)
                .setMessage("Apakah Anda ingin menghapus catatan ini?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Hapus item dari database dan update RecyclerView
                        boolean isDeleted = databaseHelper.deleteNote(adapter.getNoteAt(position));
                        if (isDeleted) {
                            adapter.removeItem(position); // Menghapus item dari RecyclerView
                            Toast.makeText(MainActivity.this, "Catatan berhasil dihapus", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Gagal menghapus catatan", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }


}
