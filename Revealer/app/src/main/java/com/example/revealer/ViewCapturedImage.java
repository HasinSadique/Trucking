package com.example.revealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static com.example.revealer.MainActivity.REQUEST_IMAGE_CAPTURE;

public class ViewCapturedImage extends AppCompatActivity {

    ImageView imageView1,imageView2;
    Button compareButton;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_captured_image);

        imageView1=findViewById(R.id.ImageView_1);
        imageView2=findViewById(R.id.ImageView_2);
        compareButton=findViewById(R.id.Button_Compare);

        Bitmap image1 = (Bitmap) getIntent().getExtras().get("data");
        imageView1.setImageBitmap(image1);
//        imageView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //bring up camera
//                Intent captureImage=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if(captureImage.resolveActivity(getPackageManager())!=null){
//                    startActivityForResult(captureImage, REQUEST_IMAGE_CAPTURE);
//                }
//                //click image
//                //save in imageview
//            }
//        });
        imageView2.setImageBitmap(null);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bring up camera
                Intent captureImage=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(captureImage.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(captureImage, REQUEST_IMAGE_CAPTURE);
                }
                //click image
                //save in imageview
            }
        });

        compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareImages();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras= data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView2.setImageBitmap(imageBitmap);
        }
    }

    private void compareImages() {

    }
}
