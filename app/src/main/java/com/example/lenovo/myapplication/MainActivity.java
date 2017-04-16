package com.example.lenovo.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
   // private Button push;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void search(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        TextView textView = (TextView)findViewById(R.id.textView);
       // textView.setTextSize(20);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        textView.setHorizontallyScrolling(true);
        InputStream inputStream = getResources().openRawResource(R.raw.shisanbai);
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e1) {

        }
        BufferedReader br = new BufferedReader(reader);
        String line,author = "", goal = "";
        int i = 0;
       // SpannableStringBuilder style = new SpannableStringBuilder(line);
        try{
            while((line = br.readLine()) != null){
                if(line.contains(": ")){
                    author = "           " + line;
                }
                if (line.contains(message)&&!(line.contains(": "))) {
                    goal += "\n" + line + "\n" + author;
                    i++;
                }
                if(goal!="") {
                    textView.setText(goal);
                }else{
                    textView.setText("抱歉，没有找到");
                }
            }
        }catch(java.io.IOException e2){

        }
        Toast.makeText(getApplicationContext(),"一共找到"+i+"句",Toast.LENGTH_SHORT).show();



    }



}
