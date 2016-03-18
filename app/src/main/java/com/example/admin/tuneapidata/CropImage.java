package com.example.admin.tuneapidata;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class CropImage extends AppCompatActivity implements View.OnClickListener {

    final int CAMERA_CAPTURE = 1;
    final int CROP_PIC = 2;
    private Uri picUri;
    ImageView imageView1,getImageView1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cropimage);
        imageView1= (ImageView)findViewById(R.id.picture);
        imageView1.setOnClickListener(this);
        getImageView1=(ImageView)findViewById(R.id.picture1);
        getImageView1.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.picture) {
            try {
                // use standard intent to capture an image
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // we will handle the returned data in onActivityResult
                startActivityForResult(captureIntent, CAMERA_CAPTURE);
            } catch (ActivityNotFoundException anfe) {
                Toast toast = Toast.makeText(this, "This device doesn't support the crop action!",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri = null;
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_CAPTURE) {
                // get the Uri for the captured image
                picUri = data.getData();
                performCrop(uri);
            }
            // user is returning from cropping the image
            else if (requestCode == CROP_PIC) {
                // get the returned data
                Bundle extras = data.getExtras();
                // get the cropped bitmap
                Bitmap thePic = extras.getParcelable("data");
                getImageView1.setImageBitmap(thePic);
            }
        }
    }
    /**
     * this function does the crop operation.
     */


    private void performCrop(Uri picUri) {
        // take care of exceptions
        try {
            // call the standard crop action intent (the user device may not
            // support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 300);
            cropIntent.putExtra("outputY", 300);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, CROP_PIC);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(this, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}