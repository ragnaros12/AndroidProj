package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;

import software.rsquared.textdecorator.Text;

public class Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }
    public void ClickToAdd(View w){
        EditText editText = findViewById(R.id.text_field);
        String[] texts = editText.getText().toString().split("\n");
        All.getNotes().add(new Note(texts[0], texts[1], texts[2], LocalDateTime.now().toString(), R.drawable.first_photo));
        finish();
    }
}