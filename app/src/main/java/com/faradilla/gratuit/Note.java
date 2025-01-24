package com.faradilla.gratuit;

public class Note {
    private int id;
    private String title;
    private String description;

    // Konstruktor untuk inisialisasi objek Note
    public Note(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Getter untuk ID
    public int getId() {
        return id;
    }

    // Getter untuk judul
    public String getTitle() {
        return title;
    }

    // Getter untuk deskripsi
    public String getDescription() {
        return description;
    }
}
