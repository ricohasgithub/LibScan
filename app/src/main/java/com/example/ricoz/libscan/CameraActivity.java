package com.example.ricoz.libscan;

import java.util.*;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

public class CameraActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void scanBarcodes(FirebaseVisionImage image) {
        FirebaseVisionBarcodeDetectorOptions options =
                new FirebaseVisionBarcodeDetectorOptions.Builder()
                        .setBarcodeFormats(
                                FirebaseVisionBarcode.FORMAT_EAN_13)
                        .build();

        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
                .getVisionBarcodeDetector();

        Task<List<FirebaseVisionBarcode>> result = detector.detectInImage(image)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionBarcode> barcodes) {

                        for (FirebaseVisionBarcode barcode: barcodes) {
                            Rect bounds = barcode.getBoundingBox();
                            Point[] corners = barcode.getCornerPoints();

                            String rawValue = barcode.getRawValue();

                            int valueType = barcode.getValueType();

                            switch (valueType) {
                                case FirebaseVisionBarcode.TYPE_ISBN:
                                    String isbn = barcode.getRawValue();
                                    System.out.println(isbn);
                                    break;
                            }
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Error");
                    }
                });
    }

}
