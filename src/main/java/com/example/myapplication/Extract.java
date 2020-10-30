    package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class Extract extends AppCompatActivity {

    static final int READ_BLOCK_SIZE = 100;
    private static final int CREATE_REQUEST_CODE = 40;
    private static final int SAVE_REQUEST_CODE = 42;

    private TextView textmsg;
    private static final int CREATE_FILE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extract);
        textmsg = findViewById(R.id.textmsg);
    }

    // write text to file
    public void WriteBtn(View v) throws IOException {
        // write text to file
        // add-write text into file
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/csv");
        intent.putExtra(Intent.EXTRA_TITLE, "newfile.csv");
        startActivityForResult(intent, SAVE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CREATE_REQUEST_CODE) {
                if (data != null) {

                }
            }
            else if (requestCode == SAVE_REQUEST_CODE) {
                if (data != null) {
                    Uri currentUri = data.getData();
                    writeFileContent(currentUri);
                }
            }


        }
    }
    private void writeFileContent(Uri uri)
    {
        try{
            ParcelFileDescriptor pfd =
                    this.getContentResolver().
                            openFileDescriptor(uri, "w");

            FileOutputStream fileOutputStream =
                    new FileOutputStream(
                            pfd.getFileDescriptor());

            String textContent ="Gas,Concentration \n Carbon Monoxide,0.03% \n Hydrogen Sulphide, 0.01%";

            fileOutputStream.write(textContent.getBytes());

            fileOutputStream.close();
            pfd.close();
            Toast.makeText(getBaseContext(), "Stored Succesfully!! ",
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textmsg.setText("There are no toxic Gases.");
    }

}


