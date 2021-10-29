package com.example.first;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class Call extends ItemTouchHelper.Callback {
    Context context;
    NoteAdapter adapter;
    public Call(Context context, NoteAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        new AlertDialog.Builder(context).setTitle("удалить?").setMessage("вы действительно хотите удалить").
                setPositiveButton("да", (dialogInterface, i) -> {
                    All.getNotes().remove(((NoteAdapter.ViewHolder)viewHolder).note);
                    adapter.notifyDataSetChanged();
                }).setNegativeButton("нет", (dialogInterface, i) -> adapter.notifyDataSetChanged()).create().show();
    }
}