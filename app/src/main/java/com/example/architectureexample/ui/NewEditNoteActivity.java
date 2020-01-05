package com.example.architectureexample.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.architectureexample.R;

public class NewEditNoteActivity extends AppCompatActivity {
    private EditText editTextTitle, editTextDescription;
    private NumberPicker mNumberPicker;
    public static final String EXTRA_TITLE = "com.example.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.architectureexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_NUMBER_PICKER = "com.example.architectureexample.EXTRA_NUMBER_PICKER";
    public static final String EXTRA_ID = "com.example.architectureexample.EXTRA_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        mNumberPicker = findViewById(R.id.number_picker_priority);

        mNumberPicker.setMaxValue(10);
        mNumberPicker.setMinValue(1);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        }

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            mNumberPicker.setValue(intent.getIntExtra(EXTRA_NUMBER_PICKER, 1));

        } else {
            setTitle("Add New Note");
        }
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = mNumberPicker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a title or description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_NUMBER_PICKER, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.save_note):
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
