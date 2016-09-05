package tech.contactapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class List_Child_Item extends AppCompatActivity {

    TextView textView_listchild_phone,textView_listchild_email;
    Button Delete,Edit;
    static Boolean update=false;
    Toolbar toolbar;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_child_item);

        imageButton=(ImageButton)findViewById(R.id.imageButton);
        Delete=(Button)findViewById(R.id.button_list_child_delete);
        Edit=(Button)findViewById(R.id.button_list_child_edit);
        toolbar=(Toolbar)findViewById(R.id.toolbar_list_child_item);
        textView_listchild_phone=(TextView)findViewById(R.id.textView_list_child_phone);
        textView_listchild_email=(TextView)findViewById(R.id.textView_list_child_email1);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        toolbar.setTitle(""+Contact_List.nm);
        toolbar.setTitleTextColor(getResources().getColor(R.color.actionbartextcolor));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent in=getIntent();
        String s=in.getStringExtra("phone");
        String s1=in.getStringExtra("email");

        textView_listchild_phone.setText(s);
        textView_listchild_email.setText(s1);

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact_List.db.execSQL("DELETE FROM AllContact WHERE Name='"+Contact_List.nm+"'");

                Intent intent=new Intent(getBaseContext(),Contact_List.class);
                startActivity(intent);
                Snackbar.make(view,"something goes wrong!", Snackbar.LENGTH_LONG).show();
            }
        });

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Contact_List.db.execSQL("DELETE FROM AllContact WHERE Name='"+Contact_List.nm+"'");

                Intent in = new Intent(List_Child_Item.this, AddNewContact.class);
                update=true;
                in.putExtra("name",Contact_List.nm);
                in.putExtra("phone",Contact_List.ph);
                in.putExtra("email",Contact_List.em);
                startActivity(in);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+textView_listchild_phone.getText().toString()));

                    startActivity(i);
                }catch (android.content.ActivityNotFoundException e){
                    Toast.makeText(getBaseContext(),"App Failed",Toast.LENGTH_SHORT).show();
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
