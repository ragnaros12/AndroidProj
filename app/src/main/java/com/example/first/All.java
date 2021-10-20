package com.example.first;

import java.util.ArrayList;

public class All {
    private static ArrayList<Note> notes;

    public static ArrayList<Note> getNotes() {
        if(notes == null){
            notes = new ArrayList<>();
        }
        return notes;
    }

    public static void setNotes(ArrayList<Note> notes) {
        All.notes = notes;
    }
}
