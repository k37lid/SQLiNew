package com.example.georgevio.sqlinew;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper mydb;

    Button bttnshow1;
    Button bttnshowall;
    Button bttnadd;
    Button bttnAll;

    ListView listView;
    ArrayList<User> userList;

    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText editTextStreet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);


        editTextName = (EditText) findViewById(R.id.editName);
        editTextPhone = (EditText) findViewById(R.id.editPhone);
        editTextEmail = (EditText) findViewById(R.id.editEmail);
        editTextStreet = (EditText) findViewById(R.id.editStreet);

        bttnadd = (Button) findViewById(R.id.bttnAdd);
        bttnshow1 = (Button) findViewById(R.id.bttnShow1);
        bttnshowall = (Button) findViewById(R.id.bttnShowAll);
        bttnAll = (Button) findViewById(R.id.bttnAll);


        bttnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String Name = editTextName.getText().toString();
                    String Phone = editTextPhone.getText().toString();
                    String Email = editTextEmail.getText().toString();
                    String Street = editTextStreet.getText().toString();
                    if (Name.length() != 0 && Phone.length() != 0 && Email.length() != 0 && Street.length() != 0) {
                        AddData(Name, Phone, Email, Street);
                        editTextName.setText("");
                        editTextPhone.setText("");
                        editTextEmail.setText("");
                        editTextStreet.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "You must put something in the text field!", Toast.LENGTH_LONG).show();
                    }
                }
            });

        bttnshow1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("georgeLog", "clicked on fetch");
                Cursor getData = mydb.getData(1); //specific record (id=1)

                if (editTextName.getText().toString().isEmpty() || editTextStreet.getText().toString().isEmpty() ||
                        editTextEmail.getText().toString().isEmpty() || editTextPhone.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            " plz add data ...:-(", Toast.LENGTH_LONG).show();
                } else if (getData.moveToNext()) {// data?
                    Log.v("georgeLog", "data found in DB...");
                    String dName = getData.getString(getData.getColumnIndex("name"));
                    String dPhone = getData.getString(getData.getColumnIndex("phone"));
                    String dEmail = getData.getString(getData.getColumnIndex("email"));
                    String dStreet = getData.getString(getData.getColumnIndex("street"));
                    Toast.makeText(getApplicationContext(),
                            "rec: " + dName + ", " + dPhone + ", " + dEmail + "," + dStreet, Toast.LENGTH_LONG).show();

                }
            }
        });

        bttnshowall.setOnClickListener(new View.OnClickListener() {
                                           public void onClick(View v) {
                    Log.v("georgeLog", "clicked on Result Button");

                    ArrayList<String> fetchAll = new ArrayList<String>();
                    fetchAll = mydb.getAllContacts();
                    for (String a : fetchAll)
                    Log.v("georgeLog:", a.toString());
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    Log.v("georgeLog:", "intent executed");
                    intent.putStringArrayListExtra("fetchAll", fetchAll);
                    Log.v("georgeLog:", "fetchALL executed");
                    startActivity(intent);
                    Log.v("georgeLog:", "startActivity executed");
                                           }
                                       }
        );


        bttnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> fetchAll = new ArrayList<String>();
                fetchAll = mydb.getAllContacts();
                for (String a : fetchAll)
                    Log.v("georgeLog:", a.toString());
                Intent intent = new Intent(getApplicationContext(), AllResult.class);
                Log.v("georgeLog:", "intent executed");
                intent.putStringArrayListExtra("fetchAll", fetchAll);
                Log.v("georgeLog:", "fetchALL executed");
                startActivity(intent);
                Log.v("georgeLog:", "startActivity executed");

            }
        });
    }

    public void AddData(String Name, String Phone, String Email , String Street) {
        boolean insertData = mydb.insertContact(Name, Phone, Email , Street);

        if (insertData == true) {
            Toast.makeText(MainActivity.this, "Successfully Entered Data!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }
}
