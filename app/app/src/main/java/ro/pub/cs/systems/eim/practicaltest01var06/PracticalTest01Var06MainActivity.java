package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {
    Button play;
    CheckBox leftC, midC, rightC;
    TextInputEditText leftT, midT, rightT;
    int score = 0;

    public String getRndm() {
        Random rand = new Random();
        List<String> givenList = Arrays.asList("1", "2", "3", "*");
        int randomIndex = rand.nextInt(givenList.size());
        return givenList.get(randomIndex);

    }
    public boolean itemClicked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        return (checkBox.isChecked());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.play);
        leftC =  findViewById(R.id.leftCheck);
        midC =  findViewById(R.id.midCheck);
        rightC =  findViewById(R.id.rightCheck);
        leftT = findViewById(R.id.left);
        midT = findViewById(R.id.midl);
        rightT = findViewById(R.id.right);

        play.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String x1 = "-", x2 = "-", x3= "-";
                int cnt = 0;
                if(!itemClicked(leftC)){
                    x1 = getRndm();
                    Log.i("rndmGen", x1);
                    leftT.setText(x1);
                    cnt += 1;
                }
                if(!itemClicked(midC)){
                    x2 = getRndm();
                    Log.i("rndmGen", x2);
                    midT.setText(x2);
                    cnt += 1;
                }
                if(!itemClicked(rightC)){
                    x3 = getRndm();
                    Log.i("rndmGen", x3);
                    rightT.setText(x3);
                    cnt += 1;
                }
                Intent intent = new Intent("ro.pub.cs.systems.eim.practicaltest01var06.PracticalTest01Var06SecondaryActivity");
                intent.putExtra("num1", x1);
                intent.putExtra("num2", x2);
                intent.putExtra("num3", x3);
                intent.putExtra("check", 3-cnt);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int scr = data.getIntExtra("score", 0);
        score += scr;
        Toast.makeText(getApplication(), "Score:" + String.valueOf(score), Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("save", "onSaveInstanceState() method was invoked");

        savedInstanceState.putInt("score", score);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("restore", "onRestoreInstanceState() method was invoked");

        if (savedInstanceState.get("score") != null) {
            score = savedInstanceState.getInt("score");
        }
        Toast.makeText(getApplication(), "Score:" + String.valueOf(score), Toast.LENGTH_LONG).show();
    }


}