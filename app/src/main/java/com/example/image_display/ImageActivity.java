package com.example.image_display;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ImageActivity extends AppCompatActivity {
    String id, desc;
    int rating;
    Boolean isShared;
    EditText titleInput, ratingInput;
    ToggleButton toggle;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_image);
        initialiseUI();
        update();
        titleInput = findViewById(R.id.edit_name);
        titleInput.addTextChangedListener(nameWatcher);
        ratingInput = findViewById(R.id.edit_rating);
        ratingInput.addTextChangedListener(ratingWatcher);
    }

    //extract object from the parcel
    private void initialiseUI() {
        final Image image = getIntent().getExtras().getParcelable("IMAGE");
        id = image.getId();
        desc = image.getDes();
        isShared = image.getShared();

        //get the location to display
        ImageView selectedImage = findViewById(R.id.selected_image);
        TextView selectedDesc = findViewById(R.id.image_desc);
        EditText title = findViewById(R.id.edit_name);
        EditText newUrl = findViewById(R.id.edit_url);
        EditText newKeyword = findViewById(R.id.edit_keyword);
        EditText newRating = findViewById(R.id.edit_rating);
        EditText newEmail = findViewById(R.id.edit_email);
        TextView newDate = findViewById(R.id.edit_date);
        toggle = findViewById(R.id.toggleButton);

        //display data
        int res = getResources().getIdentifier(image.getId(), "drawable", this.getPackageName());
        selectedImage.setImageResource(res);
        selectedDesc.setText(image.getDes());
        title.setText(image.getTitle());
        newUrl.setText(image.getUrl());
        newKeyword.setText(image.getKeyword());
        newEmail.setText(image.getEmail());
        newDate.setText(image.getDate());
        newRating.setText(String.valueOf(image.getRating()));

        //modify the toggle button text display
        if(isShared) {
            toggle.setText("YES");
        } else {
            toggle.setText("NO");
        }
    }

    public void toggleClick(View view) {
        if (isShared) {
            isShared = false;
            toggle.setText("NO");
        } else {
            isShared = true;
            toggle.setText("YES");
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

    private void update() {
        btn = findViewById(R.id.update);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleInput = findViewById(R.id.edit_name);
                EditText url = findViewById(R.id.edit_url);
                EditText keyword = findViewById(R.id.edit_keyword);
                ratingInput = findViewById(R.id.edit_rating);
                EditText email = findViewById(R.id.edit_email);
                TextView date = findViewById(R.id.edit_date);

                String newTitle = titleInput.getText().toString();
                String newUrl = url.getText().toString();
                String newKeyword = keyword.getText().toString();
                String newEmail = email.getText().toString();
                String newDate = date.getText().toString();
                String ratingStr = ratingInput.getText().toString();
                int newRating = Integer.parseInt(ratingStr);

                Image image = new Image(id, newTitle, newUrl, desc, newKeyword, newRating, newEmail, isShared, newDate);
                Intent intent = new Intent();
                intent.putExtra("IMAGE", image);
                setResult(0, intent);
                finish();
                Log.i("sending back: ", newTitle);
            }
        });
    }

    private TextWatcher nameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            String input = titleInput.getText().toString().trim();
            btn.setEnabled(!input.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private TextWatcher ratingWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            int newRate = 0;
            String input = ratingInput.getText().toString().trim();
            try {
                newRate = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if(newRate < 1 || newRate > 5) {
                Toast.makeText(ImageActivity.this, "Please enter the rating between 1-5", Toast.LENGTH_SHORT).show();
                btn.setEnabled(false);
            } else {
                rating = newRate;
                btn.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
