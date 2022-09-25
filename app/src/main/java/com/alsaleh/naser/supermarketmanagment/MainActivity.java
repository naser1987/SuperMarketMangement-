package com.alsaleh.naser.supermarketmanagment;

import androidx.appcompat.app.AppCompatActivity;
import com.alsaleh.naser.supermarketmanagment.data.DataUtilities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login_btn;
    EditText userName_et, password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_btn = findViewById(R.id.BTN_login);
        userName_et = findViewById(R.id.ET_userName);
        password_et = findViewById(R.id.ET_password);



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uName= userName_et.getText().toString();
                String pass= password_et.getText().toString();

                if( !( (uName.equalsIgnoreCase("") ) && ( pass.equalsIgnoreCase("") ) ) ){
                    // connect to the Db and check if the user exists //

                      DataUtilities dataUtility = DataUtilities.getInstance(MainActivity.this);
                      dataUtility.openConnect();
                   if(dataUtility.getUser(uName,pass)){
                       // run the main Activity  and close login Activity
                       Intent logMain = new Intent(MainActivity.this,HomePage.class);
                       startActivity(logMain);
                        finish();
                        }else {
                       Toast.makeText(MainActivity.this,"Unknown user info",Toast.LENGTH_LONG).show();
                   }

                }else {
                    Toast.makeText(MainActivity.this,R.string.error_empty_fields,Toast.LENGTH_LONG).show();
                }
            }

        });

    }

}