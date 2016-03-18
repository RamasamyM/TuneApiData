package com.example.admin.tuneapidata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Admin on 3/17/2016.
 */

public class ImageCrop extends AppCompatActivity implements View.OnClickListener {


    private ImageView cameraImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firsttest);
        cameraImage = (ImageView) findViewById(R.id.my_camera);
        cameraImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_camera:
                Toast.makeText(ImageCrop.this, "camera open", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ImageCrop.this,Image.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}