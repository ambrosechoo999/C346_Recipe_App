package sg.edu.rp.c346.id20008189.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pizza extends AppCompatActivity {
Button btnBack;
TextView tvCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
        btnBack=findViewById(R.id.btnBack);
        tvCreate=findViewById(R.id.tvCreate);

        Intent intent = getIntent();

        tvCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pizza.this, Create.class);
                startActivity(intent);

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pizza.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}