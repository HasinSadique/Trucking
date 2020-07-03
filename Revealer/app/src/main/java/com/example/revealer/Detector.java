package com.example.revealer;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;

import java.util.List;

public class Detector {

    Context ApplicationContext;
    String text=null;
    public Detector(Context applicationContext) {
        ApplicationContext=applicationContext;
    }

    private void runFaceDetector(Bitmap imageBitmap) {
        //create firebaseVisionFaceDetectorOption object
        FirebaseVisionFaceDetectorOptions options= new FirebaseVisionFaceDetectorOptions.Builder()
                .setPerformanceMode(FirebaseVisionFaceDetectorOptions.FAST)
                .setContourMode(FirebaseVisionFaceDetectorOptions.ALL_CONTOURS)
                .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                .setMinFaceSize(0.1f).build();
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imageBitmap);
        //add options
        FirebaseVisionFaceDetector detector= FirebaseVision.getInstance().getVisionFaceDetector(options);
        detector.detectInImage(image).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionFace>>() {
            @Override
            public void onSuccess(List<FirebaseVisionFace> firebaseVisionFaces) {
                String result = checkSmiling(firebaseVisionFaces);
                text=result;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ApplicationContext, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String checkSmiling(List<FirebaseVisionFace> firebaseVisionFaces) {
        StringBuilder result=new StringBuilder();
        float smilingProbability=0;
        for(FirebaseVisionFace face:firebaseVisionFaces){
            if(face.getSmilingProbability()!=FirebaseVisionFace.UNCOMPUTED_PROBABILITY){
                smilingProbability=face.getSmilingProbability();
            }
            int id=face.getTrackingId();
            result.append("Smile: ");
            if(smilingProbability>0.5){
                result.append("Yes \nProbability: "+smilingProbability+" id: "+id);
            }else{
                result.append("No\nProbability: "+smilingProbability+" id: "+id);
            }
        }
        return result.toString();
    }
}
