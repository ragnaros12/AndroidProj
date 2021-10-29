package com.example.first;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StableIdKeyProvider;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import software.rsquared.textdecorator.Text;

public class MainActivity extends AppCompatActivity {

    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new NoteAdapter(this, new NoteAdapter.ClickedChanged() {
            @Override
            public void Changed() {
                invalidateOptionsMenu();
            }
        });
        adapter.setHasStableIds(true);
        ItemTouchHelper helper = new ItemTouchHelper(new Call(this, adapter));
        helper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(adapter.getClicked().size() != 0) {
            menu.clear();
            getMenuInflater().inflate(R.menu.menu, menu);
            menu.findItem(R.id.Count).setTitle(adapter.getClicked().size() + "");
            menu.findItem(R.id.Clear).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    onBackPressed();
                    return true;
                }
            });
            menu.findItem(R.id.Delete).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    new AlertDialog.Builder(MainActivity.this).setTitle("Удалить?").setMessage("вы хотите удалить?")
                            .setPositiveButton("да", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    All.getNotes().removeAll(adapter.getClicked());
                                    adapter.getClicked().clear();
                                    adapter.notifyDataSetChanged();
                                }
                            }).setNegativeButton("нет", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).create().show();

                    return true;
                }
            });
            return true;
        }
        else{
            return super.onCreateOptionsMenu(menu);
        }
    }

    @Override
    public void onBackPressed() {
        if(adapter.getClicked().size() == 0){
            super.onBackPressed();
        }
        else{
            adapter.getClicked().clear();
            adapter.notifyDataSetChanged();
            invalidateOptionsMenu();
        }
    }

    public void ClickToAdd(View w){
        Intent intent = new Intent(this, Add.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void setInitData() {
        int y, m, d;
        for (int x = 1; x<20; ++x) {
            y = (int) (Math.random() * 30) + 1991;
            m = (int) (Math.random() * 12) + 1;
            d = (int) (Math.random() * 30) + 1;
            All.getNotes().add(new Note("Заголовок " + x, ".первая строчка.", ".вторая строчка.", d +"-" + m + "-" + y, R.drawable.first_photo));
        }
    }

}