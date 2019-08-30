package com.example.image_display;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ImageActivity extends AppCompatActivity {
    String id, desc, date;
    ToggleButton toggle;
    Boolean isShared;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_image);
        initialiseUI();
        updateDetails();
    }

    private void initialiseUI() {
        final Image image = getIntent().getExtras().getParcelable("IMAGE");
        id = image.getId();
        desc = image.getDes();
        isShared = image.getShared();

//        Log.i("receiving: ", id + "/" + image.getTitle() +
//                "/" + image.getUrl() + "/" + desc + "/" + image.getKeyword() +
//                "/" + image.getRating() + "/" + image.getEmail() + "/" + image.getShared() );

        ImageView selectedImage = findViewById(R.id.selected_image);
        TextView selectedDesc = findViewById(R.id.image_desc);
        EditText title = findViewById(R.id.edit_name);
        EditText newUrl = findViewById(R.id.edit_url);
        EditText newKeyword = findViewById(R.id.edit_keyword);
        EditText newRating = findViewById(R.id.edit_rating);
        EditText newEmail = findViewById(R.id.edit_email);
        TextView newDate = findViewById(R.id.edit_date);
        toggle = findViewById(R.id.toggleButton);

        int res = getResources().getIdentifier(image.getId(), "drawable", this.getPackageName());
        selectedImage.setImageResource(res);
        selectedDesc.setText(image.getDes());
        title.setText(image.getTitle());
        newUrl.setText(image.getUrl());
        newKeyword.setText(image.getKeyword());
        newRating.setText(String.valueOf(image.getRating()));
        newEmail.setText(image.getEmail());
        newDate.setText(image.getDate());

        if(isShared) {
            toggle.setText("YES");
            Log.i("initialiseUI2: ", toggle.getTextOn().toString());
        } else {
            toggle.setText("NO");
            Log.i("initialiseUI1: ",toggle.getTextOff().toString());
        }
    }

    public void toggleClick(View view) {
        if (isShared) {
            isShared = false;
            toggle.setText("NO");
            Log.i("toggleClick: ", "false");
        } else {
            isShared = true;
            toggle.setText("YES");
            Log.i("toggleClick: ", "true");
        }
    }

    public void selectDate(View view) {
        final TextView dateView = findViewById(R.id.edit_date);
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ImageActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dateView.setText(day + "/" + month + "/" + year);
                            }
                        }, day, month, year);
                dialog.show();
            }
        });
    }

    private void updateDetails() {
        Button btn = findViewById(R.id.update);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = findViewById(R.id.edit_name);
                EditText url = findViewById(R.id.edit_url);
                EditText keyword = findViewById(R.id.edit_keyword);
                EditText rating = findViewById(R.id.edit_rating);
                EditText email = findViewById(R.id.edit_email);
                TextView date = findViewById(R.id.edit_date);

                String newTitle = title.getText().toString();
                String newUrl = url.getText().toString();
                String newKeyword = keyword.getText().toString();
                String ratingStr = rating.getText().toString();
                String newEmail = email.getText().toString();
                int newRating = Integer.parseInt(ratingStr);
                String newDate = date.getText().toString();

                Image image = new Image(id, newTitle, newUrl, desc, newKeyword, newRating, newEmail, isShared, newDate);

                Intent intent = new Intent();
                intent.putExtra("IMAGE", image);
                setResult(0, intent);
                finish();
                Log.i("editing: ", newDate);
            }
        });

    }
}
