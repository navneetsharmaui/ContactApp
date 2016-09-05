package tech.contactapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class Contact_List extends AppCompatActivity {


    android.widget.ListView ListView;
    FloatingActionButton floatingActionButton;
    RelativeLayout relativeLayout;
    static SQLiteDatabase db;
    static Cursor c;
    static String nm,ph,em;
    Toolbar toolbar;
    static int count=0;
    ArrayList<String> al=new ArrayList<String>();
    static String st[]=new String[20];
    static String st1[]=new String[20];
    static String st2[]=new String[20];
    ArrayList<String> name,phone,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__list);

        toolbar=(Toolbar) findViewById(R.id.toolbar_contact_list);
        ListView=(ListView) findViewById(R.id.listView);
        name=new ArrayList<String>();
        phone=new ArrayList<String>();
        email=new ArrayList<String>();
        floatingActionButton=(FloatingActionButton) findViewById(R.id.floating_button_add_contact);

        try {
            db=openOrCreateDatabase("Contacts", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS AllContact(Name VARCHAR,Phone VARCHAR,Email VARCHAR)");

        }catch (Exception e){

            View view = null;
            Snackbar.make(view,"error in database!", Snackbar.LENGTH_SHORT).show();
        }
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

        toolbar.setTitle("Contacts");
        toolbar.setTitleTextColor(getResources().getColor(R.color.actionbartextcolor));
        setSupportActionBar(toolbar);


        /*setting list view items*/
        c=db.rawQuery("SELECT * FROM AllContact ORDER BY Name Collate NOCASE",null);

        if(c.getCount()==0)
        {
            Toast.makeText(getBaseContext(),"record not found",Toast.LENGTH_SHORT).show();
        }
        else {
            while (c.moveToNext()) {
                st[count] = c.getString(0);
                st1[count] = c.getString(1);
                st2[count] = c.getString(2);
                count++;
            }
        }

        int i=0;
        while(i<count){
            name.add(""+st[i].toString());
            phone.add(""+st1[i].toString());
            email.add(""+st2[i].toString());
            i++;
        }
        count=0;
        ExpandableListAdapter dp=new ExpandableListAdapter(getApplicationContext(),name);
        ListView.setAdapter(dp);

/*floating button click listner*/
        try {
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Contact_List.this, AddNewContact.class);
                    startActivity(i);
                }
            });
        }catch (Exception e){
            View view = null;
            Snackbar.make(view,"something goes wrong!", Snackbar.LENGTH_SHORT).show();
        }

        try {
            ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    nm=name.get(i).toString();
                    ph=phone.get(i).toString();
                    em=email.get(i).toString();
                    Intent in = new Intent(Contact_List.this, List_Child_Item.class);
                    in.putExtra("phone",phone.get(i).toString());
                    in.putExtra("email",email.get(i).toString());
                    startActivity(in);
                }
            });
        }catch (Exception e){
            View view = null;
            Snackbar.make(view,"something goes wrong!", Snackbar.LENGTH_SHORT).show();
        }

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
