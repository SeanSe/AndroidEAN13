package com.project.sean.androidean13;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidEAN13Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_android_ean13);
        Button btn=(Button)findViewById(R.id.btn_create);
        btn.setOnClickListener( new Button.OnClickListener() {

            public void onClick(View arg0) {
                createEAN13Code();
            }
        });

        TextView t = (TextView)findViewById(R.id.barcode);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/EanP72Tt Normal.Ttf");
        t.setTypeface(font);
        t.setText("$!24J5IH-gbdbah!");
    }

    public void createEAN13Code()
    {

        EditText e = (EditText)findViewById(R.id.edittext1);
        String s = e.getText().toString();
        if( s == null || s.length() != 12 ) {
            Toast.makeText(AndroidEAN13Activity.this, "A barcode requires 12 numbers.", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        EAN13CodeBuilder bb = new EAN13CodeBuilder(s);

        TextView t = (TextView)findViewById(R.id.barcode);
        t.setText(bb.getCode());
        Toast.makeText(AndroidEAN13Activity.this, "Barcode generated successfully!", Toast.LENGTH_SHORT)
                .show();
    }
}
