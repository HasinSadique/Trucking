package com.example.revealer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;

import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;

import java.util.List;
public class MainActivity extends AppCompatActivity {

    Bitmap imageBitmap;
    private ImageView imageView1;
    private ImageView imageView2;
    TextView textView_WantOthers;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_WantOthers=findViewById(R.id.TextView_WantOthersToFindYou);
        imageView1 = findViewById(R.id.ImageView_Logo);
        imageView2 = findViewById(R.id.ImageView_2nd);

        Button photoButton =findViewById(R.id.Button_TakePicture);
        photoButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent captureImage=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(captureImage.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(captureImage, REQUEST_IMAGE_CAPTURE);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras= data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            Intent intent=new Intent(this,ViewCapturedImage.class);
            intent.putExtras(extras);
            startActivity(intent);

            //for second time
//            imageView1.setImageBitmap(imageBitmap);
//            Detector detector=new Detector(getApplicationContext());
//            detector.runFaceDetector(imageBitmap);
        }
    }


}
//
//    private void runFaceDetector(Bitmap imageBitmap) {
//        //create firebaseVisionFaceDetectorOption object
//        FirebaseVisionFaceDetectorOptions options= new FirebaseVisionFaceDetectorOptions.Builder()
//                .setPerformanceMode(FirebaseVisionFaceDetectorOptions.FAST)
//                .setContourMode(FirebaseVisionFaceDetectorOptions.ALL_CONTOURS)
//                .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
//                .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
//                .setMinFaceSize(0.1f).build();
//        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imageBitmap);
//        //add options
//        FirebaseVisionFaceDetector detector= FirebaseVision.getInstance().getVisionFaceDetector(options);
//        detector.detectInImage(image).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionFace>>() {
//            @Override
//            public void onSuccess(List<FirebaseVisionFace> firebaseVisionFaces) {
//                String result = checkSmiling(firebaseVisionFaces);
//                textView_WantOthers.setText(result);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(MainActivity.this, "Exception; No face detected.-->"+e, Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    private String checkSmiling(List<FirebaseVisionFace> firebaseVisionFaces) {
//        StringBuilder result=new StringBuilder();
//        float smilingProbability=0;
//        for(FirebaseVisionFace face:firebaseVisionFaces){
//            if(face.getSmilingProbability()!=FirebaseVisionFace.UNCOMPUTED_PROBABILITY){
//                smilingProbability=face.getSmilingProbability();
//            }
//            int id=face.getTrackingId();
//            result.append("Smile: ");
//            if(smilingProbability>0.5){
//                result.append("Yes \nProbability: "+smilingProbability+" id: "+id);
//            }else{
//                result.append("No\nProbability: "+smilingProbability+" id: "+id);
//            }
//        }
//        return result.toString();
//    }
//
//    public void secondPic(View view) {
//        Intent captureImage=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if(captureImage.resolveActivity(getPackageManager())!=null){
//            startActivityForResult(captureImage, REQUEST_IMAGE_CAPTURE);
//        }
//    }

