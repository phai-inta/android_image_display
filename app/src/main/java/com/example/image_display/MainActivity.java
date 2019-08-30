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
    Image theMoon, theSun, theEarth, thePluto;
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
                Log.i("updateImage: ", image.getDate());
                switch(id) {
                    case "moon":
                        titleView = findViewById(R.id.moon_title);
                        titleView.setText(title);
                        break;
                    case "sun":
                        titleView = findViewById(R.id.sun_title);
                        titleView.setText(title);
                        break;
                    case "earth":
                        titleView = findViewById(R.id.earth_title);
                        titleView.setText(title);
                        break;
                    case "pluto":
                        titleView = findViewById(R.id.pluto_title);
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
        theMoon = new Image("moon", "The moon", "www.moon.com", "The moon is Earths largest natural satellite that we usually see it in the night sky.", "moon", 5, "default@gmail.com", false, "00/00/2019");
        theEarth = new Image("earth", "The earth", "www.earth.com", "The earth, our home is the third planet from the sun the fifth largest of the planets in the solar system.", "earth", 1,"default@gmail.com", false,"00/00/2019");
        thePluto = new Image("pluto", "The pluto", "www.pluto.com", "Pluto was considered the 9th planet of the Solar System.", "pluto", 4, "default@gmail.com", false, "00/00/2019");
        theSun = new Image("sun", "The sun", "www.sun.com", "The sun is the heart of our solar system, a yellow dwarf star and a hot ball of glowing gases", "sun", 2, "default@gmail.com", false, "00/00/2019");
        img.add(theMoon);
        img.add(theEarth);
        img.add(thePluto);
        img.add(theSun);
    }

    //update image object after data being modified
    public void updateImage(Image image) {
        String id = image.getId();
        switch(id) {
            case "moon":
                theMoon.setTitle(image.getTitle());
                theMoon.setUrl(image.getUrl());
                theMoon.setDes(image.getDes());
                theMoon.setKeyword(image.getKeyword());
                theMoon.setRating(image.getRating());
                theMoon.setEmail(image.getEmail());
                theMoon.setShared(image.getShared());
                theMoon.setDate(image.getDate());
                Log.i("updateImage: ", theMoon.getDate());
                break;
            case "sun":
                theSun.setTitle(image.getTitle());
                theSun.setUrl(image.getUrl());
                theSun.setDes(image.getDes());
                theSun.setKeyword(image.getKeyword());
                theSun.setRating(image.getRating());
                theSun.setEmail(image.getEmail());
                theSun.setShared(image.getShared());
                theSun.setDate(image.getDate());
                break;
            case "earth":
                theEarth.setTitle(image.getTitle());
                theEarth.setUrl(image.getUrl());
                theEarth.setDes(image.getDes());
                theEarth.setKeyword(image.getKeyword());
                theEarth.setRating(image.getRating());
                theEarth.setEmail(image.getEmail());
                theEarth.setShared(image.getShared());
                theEarth.setDate(image.getDate());
                break;
            case "pluto":
                thePluto.setTitle(image.getTitle());
                thePluto.setUrl(image.getUrl());
                thePluto.setDes(image.getDes());
                thePluto.setKeyword(image.getKeyword());
                thePluto.setRating(image.getRating());
                thePluto.setEmail(image.getEmail());
                thePluto.setShared(image.getShared());
                thePluto.setDate(image.getDate());
                break;
            default:
                break;
        }
    }

    public void moonClickHandler(View v) {
        sendDetail(theMoon);
    }

    public void earthClickHandler(View v) {
        sendDetail(theEarth);
    }

    public void plutoClickHandler(View v) {
        sendDetail(thePluto);
    }

    public void sunClickHandler(View v) {
        sendDetail(theSun);
    }

    private void sendDetail(Image image) {
        Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
        intent.putExtra("IMAGE", image);
        Log.i("sending: ", image.getDate() + "/" + image.getShared().toString() + "/" + image.getId() + "/" + image.getTitle() + "/" + image.getUrl() + "/" +
                image.getDes() + image.getKeyword() + "/" + image.getRating() + "/" + image.getEmail());
        startActivityForResult(intent, 0);
    }
}
