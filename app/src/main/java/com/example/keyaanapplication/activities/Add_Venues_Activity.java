package com.example.keyaanapplication.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.example.keyaanapplication.R;



import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageState;

public class Add_Venues_Activity extends BaseActivity implements View.OnClickListener {

    TextView headerTextView;
    private static final int CAMERA_REQUEST = 1888;

    ImageView add_image_btn,add_image_btn2;

    Button submit_btn;
    String mImageSavedPath = null;
    Bitmap takenPicture = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_venue_main);


        showToolbar();
        setStatusBarTopColor();

        headerTextView = (TextView) findViewById(R.id.header);
        headerTextView.setText("Venues Add");

        add_image_btn=findViewById(R.id.add_image_btn);
        submit_btn=findViewById(R.id.submit_btn);

        submit_btn.setOnClickListener(this);
        add_image_btn.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.submit_btn:

                Intent add_venues_intent = new Intent(getApplicationContext(), MainActivity.class);
                add_venues_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Toast.makeText(getApplicationContext(), "venue added sucessfully", Toast.LENGTH_LONG).show();
                startActivity(add_venues_intent);

                break;
            case R.id.add_image_btn:

                if (getExternalStorageState() != StorageState.NOT_AVAILABLE)
                    Permissions.check(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            "Please Allow Permissions", new Permissions.Options(),
                            new PermissionHandler() {
                                @Override
                                public void onGranted() {
                                    dispatchTakePictureIntent();
                                }

                                @Override
                                public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                                    showToast("Please Allow All Permissions");
                                }
                            });
                else Permissions.check(this, new String[]{Manifest.permission.CAMERA},
                        "Please Allow Permissions", new Permissions.Options(),
                        new PermissionHandler() {
                            @Override
                            public void onGranted() {
                                dispatchTakePictureIntent();
                            }

                            @Override
                            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                                showToast("Please Allow All Permissions");
                            }
                        });


                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST ) {
            if (resultCode == RESULT_OK) {
                File imgFile = new File(mImageSavedPath);
                if (imgFile.exists())
                    takenPicture = BitmapFactory.decodeFile(mImageSavedPath);
                if (takenPicture != null) {
                    try {
                        ExifInterface exif = new ExifInterface(imgFile.getAbsolutePath());
                        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
                        Log.d("EXIF", "Exif: " + orientation);
                        Matrix matrix = new Matrix();
                        if (orientation == 6) {
                            matrix.postRotate(90);
                        } else if (orientation == 3) {
                            matrix.postRotate(180);
                        } else if (orientation == 8) {
                            matrix.postRotate(270);
                        }
                        takenPicture = Bitmap.createBitmap(takenPicture, 0, 0, takenPicture.getWidth(), takenPicture.getHeight(), matrix, true); // rotating bitmap
                        add_image_btn.setImageBitmap(takenPicture);
                    } catch (Exception e) {

                    }
                }

                // ScanFile so it will be appeared on Gallery
                Uri imageUri = Uri.fromFile(new File(imgFile.getAbsolutePath()));
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, imageUri));
            } else if (resultCode == RESULT_CANCELED) {
                showToast("Image Capture Cancelled");
            } else {
                showToast("Failed to Capture Image");
            }
        }
    }
}
