package com.example.filestorageexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {
    private EditText editTextFileName, editTextFolderName, editTextContent;
    private Button buttonDeleteFile, buttonWriteFile, buttonReadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        editTextFileName = findViewById(R.id.editTextFileName);
        editTextFolderName = findViewById(R.id.editTextFolderName);
        editTextContent = findViewById(R.id.editTextContent);
        buttonReadFile = findViewById(R.id.buttonReadFile);
        buttonReadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonReadFile_onClick(view);
            }
        });
        buttonWriteFile = findViewById(R.id.buttonWriteFile);
        buttonWriteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonWriteFile_onClick(view);
            }
        });
        buttonDeleteFile = findViewById(R.id.buttonDeleteFile);
        buttonDeleteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonDeleteFile_onClick(view);
            }
        });
    }

    private void buttonReadFile_onClick(View view) {
        try {
            StringBuilder result = new StringBuilder();
            String line;
            String folder = getApplication().getFilesDir().getAbsolutePath() + File.separator + editTextFolderName.getText().toString();
            File subFolder = new File(folder);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(folder, editTextFileName.getText().toString())));
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            editTextContent.setText(result.toString());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void buttonWriteFile_onClick(View view) {
        try {
            String folder = getApplication().getFilesDir().getAbsolutePath() + File.separator + editTextFolderName.getText().toString();
            File subFolder = new File(folder);
            if (!subFolder.exists()) {
                subFolder.mkdirs();
            }
            FileOutputStream outputStream = new FileOutputStream(new File(subFolder, editTextFileName.getText().toString()));
            outputStream.write(editTextContent.getText().toString().getBytes());
            outputStream.close();
            Toast.makeText(getApplicationContext(), getString(R.string.done), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void buttonDeleteFile_onClick(View view) {
        try {
            String folder = getApplication().getFilesDir().getAbsolutePath() + File.separator + editTextFolderName.getText().toString();
            File subFolder = new File(folder);
            File file = new File(folder, editTextFileName.getText().toString());
            if(file.exists()) {
                file.delete();
            }
            Toast.makeText(getApplicationContext(), getString(R.string.done), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}