package tech.contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddNewContact extends AppCompatActivity {

    EditText editText_name,editText_phone,editText_email;
    Button button_add;

    Toolbar toolbar;
    static int count=0;
    ArrayList<String> al=new ArrayList<String>();
    static String st[]=new String[20];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        toolbar=(Toolbar) findViewById(R.id.toolbar_new_contact);
        editText_name=(EditText) findViewById(R.id.editText_name);
        editText_phone=(EditText) findViewById(R.id.editText_phone);
        editText_email=(EditText) findViewById(R.id.editText_email);
        button_add=(Button) findViewById(R.id.Add_contact_button);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

         /*
            set toolbar
         */

        toolbar.setTitle("NewContact");
        toolbar.setTitleTextColor(getResources().getColor(R.color.actionbartextcolor));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if(List_Child_Item.update==true) {

            Intent in = getIntent();
            String s = in.getStringExtra("name");
            String s1 = in.getStringExtra("phone");
            String s2 = in.getStringExtra("email");

            editText_name.setText(s);
            editText_phone.setText(s1);
            editText_email.setText(s2);

        }


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (List_Child_Item.update == true) {
                    if (editText_name.getText().toString().length() != 0 &&
                            editText_phone.getText().toString().length()==10) {
                        Contact_List.db.execSQL("UPDATE AllContact SET Name='" + editText_name.getText().toString() + "',Phone='" + editText_phone.getText().toString() + "',Email='" + editText_email.getText().toString() + "' WHERE Name='" + Contact_List.nm + "'");

                        Intent i = new Intent(AddNewContact.this, Contact_List.class);
                        startActivity(i);
                        Snackbar.make(view, "successfully updated", Snackbar.LENGTH_LONG).show();
                    } else {
                        if (editText_name.getText().toString().length() == 0) {
                            editText_name.setError("mandatory!");
                        }
                        if (editText_phone.getText().toString().length() == 0) {
                            editText_phone.setError("legal value!");

                        }
                    }
                }

                else {
                    if (editText_name.getText().toString().length() != 0 &&
                            editText_phone.getText().toString().length()==10) {
                        Contact_List.db.execSQL("INSERT INTO AllContact VALUES ('" + editText_name.getText().toString() + "','" + editText_phone.getText().toString() + "','" + editText_email.getText().toString() + "');");


                        Intent i = new Intent(AddNewContact.this, Contact_List.class);
                        startActivity(i);
                        Snackbar.make(view, "contact added", Snackbar.LENGTH_LONG).show();
                    } else {
                        if (editText_name.getText().toString().length() == 0) {
                            editText_name.setError("mandatory!");
                        }
                        if (editText_phone.getText().toString().length() == 0) {
                            editText_phone.setError("legal value!");

                        }
                    }
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}