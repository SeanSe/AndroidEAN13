package com.project.sean.androidean13;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AndroidEAN13Activity extends AppCompatActivity {

    TextView t;

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
        Button btn2 = (Button)findViewById(R.id.btn_image);
        btn2.setOnClickListener( new Button.OnClickListener() {

            public void onClick(View arg0) {
                imageEAN13Code();
            }
        });

        t = (TextView)findViewById(R.id.barcode);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/EanP72Tt Normal.Ttf");
        t.setTypeface(font);
        t.setText("$!24J5IH-gbdbah!");


    }

    public void createEAN13Code()
    {
        EditText e = (EditText)findViewById(R.id.etBarcode);
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

    /**
     * Converts the TextView into a Bitmap so that it can be stored as a png file.
     */
    public void imageEAN13Code() {
        t.setDrawingCacheEnabled(true); // Enable drawing cache before calling the getDrawingCache() method
        // Get bitmap object from the TextView
        Bitmap tvImage= Bitmap.createBitmap(t.getDrawingCache());

        String filename = "tvimage.png";

        System.out.print(getFilesDir());
        FileOutputStream outputStream = null;
        try {
             outputStream = openFileOutput(filename, MODE_WORLD_READABLE);
            // Save the bitmap object to file
            tvImage.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ioex) {
                    Log.d("IOException", ioex.toString());
                }
            }
        }
    }

}
