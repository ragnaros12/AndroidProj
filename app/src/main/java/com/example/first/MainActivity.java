package com.example.first;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.time.LocalDate;
import java.util.ArrayList;

import software.rsquared.textdecorator.Text;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitData();

        Context context = this;


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        NoteAdapter.OnStateClickListener stateClickListener = new NoteAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Note note, int position) {
                Toast.makeText(getApplicationContext(), "Был выбран пункт " + note.getHeader(), Toast.LENGTH_SHORT).show();
            }
        };

        NoteAdapter adapter = new NoteAdapter(this, stateClickListener);

        ItemTouchHelper helper = new ItemTouchHelper(new Call() {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                new AlertDialog.Builder(context).setTitle("удалить?").setMessage("вы действительно хотите удалить").
                        setPositiveButton("да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                All.getNotes().remove(viewHolder.getAdapterPosition());
                                adapter.RemoveClicked(All.getNotes().get(viewHolder.getAdapterPosition()));
                                adapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("нет", (d,i) -> {adapter.notifyDataSetChanged();}).show();
            }
        });

        helper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(adapter);

    }


    private void setInitData() {
        int y, m, d;
        for (int x = 1; x<10; ++x) {
            y = (int) (Math.random() * 30) + 1991;
            m = (int) (Math.random() * 12) + 1;
            d = (int) (Math.random() * 30) + 1;
            All.getNotes().add(new Note("Заголовок " + x, ".первая строчка.", ".вторая строчка.", d +"-" + m + "-" + y, R.drawable.first_photo));
        }
    }

}