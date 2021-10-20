package com.example.first;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    interface OnStateClickListener{
        void onStateClick(Note note, int position);
    }

    private final LayoutInflater inflater;
    private final ArrayList<Note> clickedNote;
    private final OnStateClickListener onClickListener;

    NoteAdapter (Context context, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.inflater = LayoutInflater.from(context);
        clickedNote = new ArrayList<>();
    }

    public void RemoveClicked(Note note){
        clickedNote.remove(note);
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycle_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = All.getNotes().get(position);

        holder.note = note;
        holder.headerView.setText(note.getHeader());
        holder.FirstLineView.setText(note.getFirstLine());
        holder.SecondLineView.setText(note.getSecondLine());
        holder.dateView.setText(note.getDate());
        holder.ResourceImageView.setImageResource(note.getResourceImage());

    }

    @Override
    public int getItemCount() {
        return All.getNotes().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView headerView, FirstLineView, SecondLineView, dateView;
        ImageView ResourceImageView;
        boolean IsClicked = false;
        Note note;

        ViewHolder(@NonNull View view) {
            super(view);
            headerView =  view.findViewById(R.id.header);
            FirstLineView =  view.findViewById(R.id.firstline);
            SecondLineView =  view.findViewById(R.id.secondline);
            dateView =  view.findViewById(R.id.date);
            ResourceImageView =  view.findViewById(R.id.imagePhoto);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    NoteAdapter.this.onClickListener.onStateClick(note, pos);
                    IsClicked = !IsClicked;
                    if(IsClicked) {
                        itemView.setBackgroundColor(Color.LTGRAY);
                        NoteAdapter.this.clickedNote.add(note);
                    }
                    else {
                        NoteAdapter.this.clickedNote.remove(note);
                        itemView.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            });
        }
    }

}
