package com.example.pk.wordrecovery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private ArrayList<String> recoveryElements;

    private static final String EDIT_TEXT_KEY = "Edit text";
    private static final String RECOVERT_ELEMENTS_KEY = "Reoovery elements";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        if (recoveryElements == null) {
            recoveryElements = new ArrayList<>();
        }

        final Button clearButton = (Button) findViewById(R.id.clearButton);
        final Button recoveryButton = (Button) findViewById(R.id.recoveryButton);

        clearButton.setOnClickListener(this);
        recoveryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clearButton:
                saveText();
                editText.setText("");
                break;
            case R.id.recoveryButton:
                recoveryText();
                break;
        }
    }

    /**
     * This method save the text at edit text view before button delete him.
     */
    private void saveText() {
        String saveText = editText.getText().toString();

        if (recoveryElements.size() == 5 && !saveText.equals("")) {
            recoveryElements.remove(0);

            recoveryElements.add(saveText);
            return;
        }

        if (!saveText.equals("")) {
            recoveryElements.add(saveText);
        }
    }

    /**
     * This method recovery deleted text in edit text.
     */
    private void recoveryText() {
        if (!recoveryElements.isEmpty()) {
            //set last element in list to edit text view
            editText.setText(recoveryElements.get(recoveryElements.size() - 1));
            //remove last element in list
            recoveryElements.remove(recoveryElements.size() - 1);
        } else {
            Toast.makeText(this, "Words no more!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(EDIT_TEXT_KEY, editText.getText().toString());
        outState.putStringArrayList(RECOVERT_ELEMENTS_KEY, recoveryElements);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        editText.setText(savedInstanceState.getString(EDIT_TEXT_KEY));
        recoveryElements = savedInstanceState.getStringArrayList(RECOVERT_ELEMENTS_KEY);
    }
}
