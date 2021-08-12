package sg.edu.rp.c346.id20008189.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Create extends AppCompatActivity {

    Button btnAdd, btnEdit, btnRetrieve;
    EditText etContent;
    ArrayList<Recipe> al;
    ListView lv;
    ArrayAdapter<Recipe> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd=findViewById(R.id.btnAdd);
        btnEdit=findViewById(R.id.btnEdit);
        btnRetrieve=findViewById(R.id.btnRetrieve);
        etContent=findViewById(R.id.etContent);
        lv=findViewById(R.id.lv);

        //initialize the variables with UI here

        al = new ArrayList<Recipe>();
        aa = new ArrayAdapter<Recipe>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = etContent.getText().toString();
                DBHelper dbh = new DBHelper(Create.this);
                long inserted_id = dbh.insertRecipe(data);

                if (inserted_id != -1) {
                    al.clear();
                    al.addAll(dbh.getAllRecipe());
                    aa.notifyDataSetChanged();
                    Toast.makeText(Create.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(Create.this);
                al.clear();
                String filterText = etContent.getText().toString().trim();
                if(filterText.length() == 0) {
                    al.addAll(dbh.getAllRecipe());
                }
                else{
                    al.addAll(dbh.getAllRecipe(filterText));
                }


                aa.notifyDataSetChanged();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recipe target = al.get(0);

                Intent i = new Intent(Create.this,
                        EditActivity.class);
                i.putExtra("data", target);
                startActivity(i);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Recipe data = al.get(position);
                Intent i = new Intent(Create.this,
                        EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

    }
    }

