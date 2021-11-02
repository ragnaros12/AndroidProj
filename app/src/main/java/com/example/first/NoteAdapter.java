package com.example.first;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private final LayoutInflater inflater;

    // clicked - массив нажатых записей
    private final ArrayList<Note> clicked;
    // changed - обьект для реализации колбека по конкретной itemview
    private final ClickedChanged changed;

    // интерфейс для описывающий колбек
    interface ClickedChanged{
        void Changed();
    }

    NoteAdapter (Context context, ClickedChanged changed) {
        this.inflater = LayoutInflater.from(context);
        clicked = new ArrayList<>();
        this.changed = changed;
    }

    public ArrayList<Note> getClicked() {
        return clicked;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycle_row, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = All.getNotes().get(position);

        holder.note = note;
        holder.headerView.setText(note.getHeader());
        holder.FirstLineView.setText(note.getFirstLine());
        holder.SecondLineView.setText(note.getSecondLine());
        holder.dateView.setText(note.getDate());
        holder.ResourceImageView.setImageResource(note.getResourceImage());
        holder.clicked = clicked.contains(note);
        if(holder.clicked){
            holder.itemView.setBackgroundColor(R.color.my_color3);
        }
        else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public int getItemCount() {
        return All.getNotes().size();
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView headerView, FirstLineView, SecondLineView, dateView;
        ImageView ResourceImageView;
        Note note;
        boolean clicked;

        ViewHolder(@NonNull View view) {
            super(view);
            //setIsRecyclable(false);
            headerView =  view.findViewById(R.id.header);
            FirstLineView =  view.findViewById(R.id.firstline);
            SecondLineView =  view.findViewById(R.id.secondline);
            dateView =  view.findViewById(R.id.date);
            ResourceImageView =  view.findViewById(R.id.imagePhoto);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @SuppressLint({"ResourceAsColor", "RestrictedApi"})
                @Override
                public boolean onLongClick(View view) {
                    clicked = !clicked;
                    if(clicked){
                        itemView.setBackgroundColor(R.color.my_color3);
                        NoteAdapter.this.clicked.add(note);
                        NoteAdapter.this.changed.Changed();
                    }
                    else {
                        itemView.setBackgroundColor(Color.WHITE);
                        NoteAdapter.this.clicked.remove(note);
                        NoteAdapter.this.changed.Changed();
                    }
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View view) {
                    if(NoteAdapter.this.clicked.size() == 0){
                        Toast.makeText(itemView.getContext(), "имя элемента" + note.getHeader(), Toast.LENGTH_LONG).show();
                    }
                    else{
                        clicked = !clicked;
                        if(clicked){
                            itemView.setBackgroundColor(R.color.my_color3);
                            NoteAdapter.this.clicked.add(note);
                            NoteAdapter.this.changed.Changed();
                        }
                        else {
                            itemView.setBackgroundColor(Color.WHITE);
                            NoteAdapter.this.clicked.remove(note);
                            NoteAdapter.this.changed.Changed();
                        }
                    }
                }
            });
            itemView.setLongClickable(true);

        }
    }

}
