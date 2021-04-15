package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity {

    public int gained(String n1, String n2, String n3, int check){
        String gain = "Gained";
        int cnt = 1;

        if (n1.equals("*"))
            cnt = 1;
        if (n2.equals("*"))
            cnt += 1;
        if (n3.equals("*"))
            cnt += 1;
        if(n1.equals(n2))
            cnt += 1;
        if(n2.equals(n3))
            cnt += 1;
        if (cnt >= 2){
            if (check == 0)
                return 100;
            if (check == 1)
                return 50;
            if (check == 2)
                return 10;
        }
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_secondary);
        Intent intent = getIntent();

        String num1 = intent.getStringExtra("num1");
        String num2 = intent.getStringExtra("num2");
        String num3 = intent.getStringExtra("num3");
        Integer checks = intent.getIntExtra("check", -1);
        Button ok = findViewById(R.id.ok);
        int score = gained(num1, num2, num3, checks);
        TextView txt = (TextView)findViewById(R.id.textView);
        if (score == 0){
            txt.setText("you lose");
        }else{
            txt.setText("Gained " + String.valueOf(score));
        }
        ok.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent();
                  intent.putExtra("score", score);
                  setResult(Activity.RESULT_OK, intent);
                  finish();
              }
          }
        );
    }

}