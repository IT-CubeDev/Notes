package ru.squarecircle.notes;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText noteNameInput = findViewById(R.id.noteNameInput);
        EditText contentInput = findViewById(R.id.noteContentInput);
        MaterialButton createBtn = findViewById(R.id.createNoteBtn);


        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = noteNameInput.getText().toString();
                String content = contentInput.getText().toString();
                long createdTime = System.currentTimeMillis();

                if(name == null || content == null) {
                    Toast.makeText(getApplicationContext(), "Введите все значения", Toast.LENGTH_SHORT).show();
                    return;
                }

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setTitle(name);
                note.setDescription(content);
                note.setCreatedTime(createdTime);
                realm.commitTransaction();
                finish();
            }
        });
    }
}