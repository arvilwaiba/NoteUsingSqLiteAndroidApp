package com.example.notepadwithsqlitedatabase;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDataActivity extends AppCompatActivity {
    Button addBtn, updateBtn, delete_btn;
    EditText editName, editSurename, editMarks, idEditText;
    SqlLiteHelper myDb;
    String id, mark;
    String firstname, lastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        editName = (EditText) findViewById(R.id.nameditText);
        editSurename = (EditText) findViewById(R.id.surnameEditText);
        editMarks = (EditText) findViewById(R.id.marksEditText);
        idEditText = (EditText) findViewById(R.id.idEditText);
        addBtn = (Button) findViewById(R.id.Add_btn);
//        updateBtn = findViewById(R.id.update_btn);
//        delete_btn = findViewById(R.id.delete_btn);
        myDb = new SqlLiteHelper(this);


        //get data from intent

        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        mark = intent.getExtras().getString("mark");
        firstname = intent.getExtras().getString("firstname");
        lastname = intent.getExtras().getString("lastname");

        editName.setText(firstname);
        editSurename.setText(lastname);
        idEditText.setText(id);
        editMarks.setText(mark);
        if(id.length()>0 || mark.length()>0 || firstname.length()>0 || lastname.length()>0){
            addBtn.setText("Update");
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean isUpdate = myDb.updateData(idEditText.getText().toString(), editName.getText().toString(), editSurename.getText().toString(), editMarks.getText().toString());

                    if (isUpdate == true) {
                        Toast.makeText(AddDataActivity.this, "Data is Updated", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddDataActivity.this, MainActivity.class));
                        finish();
                    } else
                        Toast.makeText(AddDataActivity.this, "Data is not Updated", Toast.LENGTH_LONG).show();
                }
            });
        }
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editName.getText().toString(), editSurename.getText().toString(), editMarks.getText().toString());
                if (isInserted == true) {
                    Toast.makeText(AddDataActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddDataActivity.this, MainActivity.class));
                    finish();
                } else
                    Toast.makeText(AddDataActivity.this, "Data is not inserted", Toast.LENGTH_LONG).show();
            }
        });




//        delete_btn.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Integer isDelete = myDb.deleteDta(idEditText.getText());
//                if (isDelete > 0)
//                    Toast.makeText(AddDataActivity.this, "Data is deleted", Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(AddDataActivity.this, "Data is not deleted", Toast.LENGTH_LONG).show();
//                return true;
//            }
//
//        });


    }
}





