package com.example.image_display;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView titleView;
    Image macaroon, croissant, tiramisu, muffin;
    ArrayList<Image> img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseImage();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == 0) {
            if (intent == null) {
                Log.i("Empty intent: ", "Empty intent");
            } else {
                Image image = intent.getParcelableExtra("IMAGE");
                String id = image.getId();
                String title = image.getTitle();
                switch(id) {
                    case "macaroon":
                        titleView = findViewById(R.id.macaroon_title);
                        titleView.setText(title);
                        break;
                    case "croissant":
                        titleView = findViewById(R.id.croissant_title);
                        titleView.setText(title);
                        break;
                    case "tiramisu":
                        titleView = findViewById(R.id.tiramisu_title);
                        titleView.setText(title);
                        break;
                    case "muffin":
                        titleView = findViewById(R.id.muffin_title);
                        titleView.setText(title);
                        break;
                    default:
                        break;
                }
                updateImage(image);
            }
        }
    }

    public void initialiseImage() {
        img = new ArrayList<Image>(3);
        macaroon = new Image("macaroon", "Macaroon", "www.macaroon.com", "macaroon desc.", "macaroon", 5, "default@gmail.com", false, "00/00/2019");
        tiramisu = new Image("tiramisu", "Tiramisu", "www.tiramisu.com", "tiramisu desc.", "tiramisu", 1,"default@gmail.com", false,"00/00/2019");
        muffin = new Image("muffin", "Muffin", "www.muffin.com", "muffin desc.", "muffin", 4, "default@gmail.com", false, "00/00/2019");
        croissant = new Image("croissant", "Croissant", "www.croissant.com", "croissant desc", "croissant", 2, "default@gmail.com", false, "00/00/2019");
        img.add(macaroon);
        img.add(tiramisu);
        img.add(muffin);
        img.add(croissant);
    }

    //update image object after data being modified
    public void updateImage(Image image) {
        String id = image.getId();
        switch(id) {
            case "macaroon":
                macaroon.setTitle(image.getTitle());
                macaroon.setUrl(image.getUrl());
                macaroon.setDes(image.getDes());
                macaroon.setKeyword(image.getKeyword());
                macaroon.setRating(image.getRating());
                macaroon.setEmail(image.getEmail());
                macaroon.setShared(image.getShared());
                macaroon.setDate(image.getDate());
                break;
            case "croissant":
                croissant.setTitle(image.getTitle());
                croissant.setUrl(image.getUrl());
                croissant.setDes(image.getDes());
                croissant.setKeyword(image.getKeyword());
                croissant.setRating(image.getRating());
                croissant.setEmail(image.getEmail());
                croissant.setShared(image.getShared());
                croissant.setDate(image.getDate());
                break;
            case "tiramisu":
                tiramisu.setTitle(image.getTitle());
                tiramisu.setUrl(image.getUrl());
                tiramisu.setDes(image.getDes());
                tiramisu.setKeyword(image.getKeyword());
                tiramisu.setRating(image.getRating());
                tiramisu.setEmail(image.getEmail());
                tiramisu.setShared(image.getShared());
                tiramisu.setDate(image.getDate());
                break;
            case "muffin":
                muffin.setTitle(image.getTitle());
                muffin.setUrl(image.getUrl());
                muffin.setDes(image.getDes());
                muffin.setKeyword(image.getKeyword());
                muffin.setRating(image.getRating());
                muffin.setEmail(image.getEmail());
                muffin.setShared(image.getShared());
                muffin.setDate(image.getDate());
                break;
            default:
                break;
        }
    }

    public void macaroonClickHandler(View v) {
        sendDetail(macaroon);
    }

    public void tiramisuClickHandler(View v) {
        sendDetail(tiramisu);
    }

    public void muffinClickHandler(View v) {
        sendDetail(muffin);
    }

    public void croissantClickHandler(View v) {
        sendDetail(croissant);
    }

    private void sendDetail(Image image) {
        Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
        intent.putExtra("IMAGE", image);
        Log.i("sending: ", image.getDate() + "/" + image.getShared().toString() + "/" +
                image.getId() + "/" + image.getTitle() + "/" + image.getUrl() + "/" +
                image.getDes() + image.getKeyword() + "/" + image.getRating() + "/" + image.getEmail());
        startActivityForResult(intent, 0);
    }
}
