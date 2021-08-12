package sg.edu.rp.c346.id20008189.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView tvWelcome;
Button btnCook;
ImageView ivWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       ivWelcome=findViewById(R.id.ivWelcome);
       btnCook=findViewById(R.id.btnCook);
       tvWelcome=findViewById(R.id.tvWelcome);


        btnCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Pizza.class);
                startActivity(intent);

            }
        });
    }
}