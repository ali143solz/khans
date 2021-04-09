package com.example.semester_project;



import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

public class MainActivity2 extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    TextView detectedText;
    Button btn_detect, phone;
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4;

    //ImageView userpic;
    private static final int GalleryPick = 1;
    private static final int CAMERA_REQUEST = 100;
    private static final int STORAGE_REQUEST = 200;
    private static final int IMAGEPICK_GALLERY_REQUEST = 300;
    private static final int IMAGE_PICKCAMERA_REQUEST = 400;
    String cameraPermission[];
    String storagePermission[];
    Uri imageuri;
    TextView click;




    // private Object CropImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //click = findViewById(R.id.click);
        imageView = (ImageView) findViewById(R.id.img);
        //detectedText = (TextView) findViewById(R.id.text);
        btn_detect = (Button) findViewById(R.id.btn);
        phone = (Button) findViewById(R.id.btn1);
        radioGroup=(RadioGroup)findViewById(R.id.radiogrp);
        radioButton1=(RadioButton)findViewById(R.id.jazz);
        radioButton2=(RadioButton)findViewById(R.id.ufone);
        radioButton3=(RadioButton)findViewById(R.id.Zong);
        radioButton4=(RadioButton)findViewById(R.id.telenor);
        // detectedText.getTextSize()
        showImagePicDialog();
//
        // allowing permissions of gallery and camera
        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });
        //dispatchTakePictureIntent();

        btn_detect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detect();
            }
        });

        //RadioGroup

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(radioButton1.isChecked())
                {
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    String number;
//                    number="*123*"+phone+"#";
//                    number=
                    number="*123*"+phone.getText().toString()+"#".trim();
                    intent.setData(Uri.parse("tel:" + number));
                    startActivity(intent);
                    call();
                }
                if (radioButton2.isChecked())
                {
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    String number1;
                    number1="*123*"+phone.getText().toString()+"#".trim();
                    intent.setData(Uri.parse("tel:" + number1));
                    startActivity(intent);
                    call();
                }
                if (radioButton3.isChecked())
                {
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    String number2;
                    number2="*101*"+phone.getText().toString()+"#".trim();
                    intent.setData(Uri.parse("tel:" + number2));
                    startActivity(intent);
                    call();
                }
                if (radioButton4.isChecked())
                {
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    String number3;
                    number3="*555*"+phone.getText().toString()+"#".trim();
                    intent.setData(Uri.parse("tel:" + number3));
                    startActivity(intent);
                    call();
                }
//                else
//                {
//
//                }


                //String a="";
                // boolean a=true,b=true,c=true,d=true;
//                    Intent intent=new Intent(Intent.ACTION_CALL);
//                String number;
//                String number1;
//                String number2;
//                String number3;
//                number="*123*"+phone.getText().toString()+"#";
//                number1="*101*"+phone.getText().toString()+"#";
//                number2="*555*"+phone.getText().toString()+"#";
//                number3="*123*"+phone.getText().toString()+"#";
                //Toast.makeText(getApplicationContext(),""+number+"\n"+number1+"\n"+number2+"\n"+number3+"\n",Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),""+phone.getText().toString(),Toast.LENGTH_LONG).show();
//                try {
//
//                    if (number == "123" + phone.getText().toString() + "#") {
//                        intent.setData(Uri.parse("tel:" + number));
//                    } else if (number1 == "101" + phone.getText().toString() + "#") {
//                        intent.setData(Uri.parse("tel:" + number1));
//                    } else if (number2 == "555" + phone.getText().toString() + "#") {
//                        intent.setData(Uri.parse("tel:" + number2));
//                    } else if (number3 == "123" + phone.getText().toString() + "#") {
//                        intent.setData(Uri.parse("tel:" + number3));
//                    } else {
//                    }
//                }catch (Exception e)
//                {
//                    Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
//                }
//                intent.setData(Uri.parse("tel:"+number));

//
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(MainActivity2.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                    requestpermission();
                }
                else
                {

                    // startActivity(intent);
                }
            }
        });
    }







    public void detect()  //yes
    {
        //perform text detection here

        //1. define TextRecognizer
        TextRecognizer recognizer=new TextRecognizer.Builder(MainActivity2.this).build();

        //2. Get bitmap from imageview
        Bitmap bitmap =((BitmapDrawable)imageView.getDrawable()).getBitmap();

        //3. get frame from bitmap
        Frame frame=new Frame.Builder().setBitmap(bitmap).build();

        //4. get data from frame
        SparseArray<TextBlock> sparseArray=recognizer.detect(frame);

        //sparseArray=20;
        //5. set data on textview

        StringBuilder stringBuilder = new StringBuilder();

        for (int i=0; i<sparseArray.size(); i++)
        {
            TextBlock tx=sparseArray.get(i);
            String str=tx.getValue();
            // stringBuilder.setLength(1);
            stringBuilder.append(str);
        }

        try {

            //detectedText.setText(stringBuilder);
            phone.setText(stringBuilder);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"OOPs something went wrong",Toast.LENGTH_SHORT).show();
        }

    }
    public  void call()   //no
    {
        //String a="";
        // boolean a=true,b=true,c=true,d=true;
        //  Intent intent=new Intent(Intent.ACTION_CALL);


        //   String number;
//        String number1;
//        String number2;
//        String number3;
//        number="*123*"+phone.getText().toString()+"#";
//        number1="*101*"+phone.getText().toString()+"#";
//        number2="*555*"+phone.getText().toString()+"#";
//        number3="*123*"+phone.getText().toString()+"#";
        //Toast.makeText(getApplicationContext(),""+number+"\n"+number1+"\n"+number2+"\n"+number3+"\n",Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),""+phone.getText().toString(),Toast.LENGTH_LONG).show();
//        try {
//
//            if (number == "123" + phone.getText().toString() + "#") {
//                intent.setData(Uri.parse("tel:" + number));
//            } else if (number1 == "101" + phone.getText().toString() + "#") {
//                intent.setData(Uri.parse("tel:" + number1));
//            } else if (number2 == "555" + phone.getText().toString() + "#") {
//                intent.setData(Uri.parse("tel:" + number2));
//            } else if (number3 == "123" + phone.getText().toString() + "#") {
//                intent.setData(Uri.parse("tel:" + number3));
//            } else {
//            }
//        }catch (Exception e)
//        {
//            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
//        }
        //     intent.setData(Uri.parse("tel:"+number));

//
        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(MainActivity2.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
            requestpermission();
        }
        else
        {

            //   startActivity(intent);
        }
    }
    private void requestpermission()
    {
        ActivityCompat.requestPermissions(MainActivity2.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }

    //crop image

    private void showImagePicDialog() {
        //String options[] = {"Camera"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        pickFromGallery();

    }



    // checking storage permissions
    private Boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    // Requesting  gallery permission
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission() {
        requestPermissions(storagePermission, STORAGE_REQUEST);
    }

    // checking camera permissions
    private Boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    // Requesting camera permission
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        requestPermissions(cameraPermission, CAMERA_REQUEST);
    }

    // Requesting camera and gallery
    // permission if not given
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST: {
                if (grantResults.length > 0) {
                    boolean camera_accepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageaccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (camera_accepted && writeStorageaccepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Please Enable Camera Permissions", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST: {
                if (grantResults.length > 0) {
                    boolean writeStorageaccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (writeStorageaccepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Please Enable Storage Permissions", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
        }
    }

    // Here we will pick image from gallery or camera
    private void pickFromGallery() {
        CropImage.activity().start(MainActivity2.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Picasso.with(this).load(resultUri).into(imageView);
            }
        }
    }
}
//import android.Manifest;
//import android.app.AlertDialog;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.drawable.BitmapDrawable;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.util.SparseArray;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import com.google.android.gms.vision.Frame;
//import com.google.android.gms.vision.text.TextBlock;
//import com.google.android.gms.vision.text.TextRecognizer;
//import com.squareup.picasso.Picasso;
//import com.theartofdev.edmodo.cropper.CropImage;
//
//public class MainActivity2 extends AppCompatActivity {
//    static final int REQUEST_IMAGE_CAPTURE = 1;
//    ImageView imageView;
//    TextView detectedText;
//    Button btn_detect, phone;
//    //Button btn_detect, phone;
//    RadioGroup radioGroup;
//    RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
//
//    //ImageView userpic;
//    private static final int GalleryPick = 1;
//    private static final int CAMERA_REQUEST = 100;
//    private static final int STORAGE_REQUEST = 200;
//    private static final int IMAGEPICK_GALLERY_REQUEST = 300;
//    private static final int IMAGE_PICKCAMERA_REQUEST = 400;
//    String cameraPermission[];
//    String storagePermission[];
//    Uri imageuri;
//    TextView click;
//   // private Object CropImage;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//
//        //click = findViewById(R.id.click);
//        imageView = (ImageView) findViewById(R.id.img);
//       detectedText = (TextView) findViewById(R.id.text);
//        btn_detect = (Button) findViewById(R.id.btn);
//        phone = (Button) findViewById(R.id.btn1);
//
//        radioGroup=(RadioGroup)findViewById(R.id.radiogrp);
//        radioButton1=(RadioButton)findViewById(R.id.jazz);
//        radioButton2=(RadioButton)findViewById(R.id.ufone);
//        radioButton3=(RadioButton)findViewById(R.id.Zong);
//        radioButton4=(RadioButton)findViewById(R.id.telenor);
//        // detectedText.getTextSize()
//        showImagePicDialog();
////        click.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////
////
////            }
////        });
//        // allowing permissions of gallery and camera
//        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        phone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //call();
//            }
//        });
//        //dispatchTakePictureIntent();
//
//        btn_detect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                detect();
//            }
//        });
//
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if(radioButton1.isChecked())
//                {
//                    Intent intent=new Intent(Intent.ACTION_CALL);
//                    String number;
////                    number="*123*"+phone+"#";
////                    number=
//                    number="*123*"+phone.getText().toString()+"#".trim();
//                    intent.setData(Uri.parse("tel:" + number));
//                    startActivity(intent);
//                    call();
//                }
//                if (radioButton2.isChecked())
//                {
//                    Intent intent=new Intent(Intent.ACTION_CALL);
//                    String number1;
//                    number1="*123*"+phone.getText().toString()+"#".trim();
//                    intent.setData(Uri.parse("tel:" + number1));
//                    startActivity(intent);
//                    call();
//                }
//                if (radioButton3.isChecked())
//                {
//                    Intent intent=new Intent(Intent.ACTION_CALL);
//                    String number2;
//                    number2="*101*"+phone.getText().toString()+"#".trim();
//                    intent.setData(Uri.parse("tel:" + number2));
//                    startActivity(intent);
//                    call();
//                }
//                if (radioButton4.isChecked())
//                {
//                    Intent intent=new Intent(Intent.ACTION_CALL);
//                    String number3;
//                    number3="*555*"+phone.getText().toString()+"#".trim();
//                    intent.setData(Uri.parse("tel:" + number3));
//                    startActivity(intent);
//                    call();
//                }
//
//    }
//
//
////    private void dispatchTakePictureIntent () {
////        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////        try {
////            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
////        } catch (ActivityNotFoundException e) {
////            // display error state to the user
////        }
////    }
//
////    @Override
////    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
////        super.onActivityResult(requestCode, resultCode, data);
////        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
////            Bundle extras = data.getExtras();
////            Bitmap imageBitmap = (Bitmap) extras.get("data");
////            //Bitmap selectedBitmap = extras.getParcelable("data");
////            imageView.setImageBitmap(imageBitmap);
////        }
////        if (requestCode == REQUEST_IMAGE_CAPTURE && requestCode==RESULT_OK) {
////            if (data != null) {
////                // get the returned data
////                Bundle extras = data.getExtras();
////                // get the cropped bitmap
////                Bitmap selectedBitmap = extras.getParcelable("data");
////
////                imageView.setImageBitmap(selectedBitmap);
////            }
////        }
//
//
//
//
//    public void detect()  //yes
//    {
//        //perform text detection here
//
//        //1. define TextRecognizer
//        TextRecognizer recognizer=new TextRecognizer.Builder(MainActivity2.this).build();
//
//        //2. Get bitmap from imageview
//        Bitmap bitmap =((BitmapDrawable)imageView.getDrawable()).getBitmap();
//
//        //3. get frame from bitmap
//        Frame frame=new Frame.Builder().setBitmap(bitmap).build();
//
//        //4. get data from frame
//        SparseArray<TextBlock> sparseArray=recognizer.detect(frame);
//
//        //sparseArray=20;
//        //5. set data on textview
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (int i=0; i<sparseArray.size(); i++)
//        {
//            TextBlock tx=sparseArray.get(i);
//            String str=tx.getValue();
//           // stringBuilder.setLength(1);
//            stringBuilder.append(str);
//        }
//
//        try {
//
//            detectedText.setText(stringBuilder);
//            phone.setText(stringBuilder);
//        }catch (Exception e){
//            Toast.makeText(getApplicationContext(),"OOPs something went wrong",Toast.LENGTH_SHORT).show();
//        }
//
//    }
//    public  void call()   //no
//    {
//        Intent intent=new Intent(Intent.ACTION_CALL);
//        String number=phone.getText().toString();
////        int nu[];
////        nu=new int[12] ;
////        if (number.trim().startsWith(""))
////        {
////            intent.setData(Uri.parse("tel:"+number));
////
//
//        intent.setData(Uri.parse("tel:"+number));
//
//        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
//        {
//            Toast.makeText(MainActivity2.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
//            requestpermission();
//        }
//        else
//        {
//            startActivity(intent);
//        }
//    }
//    private void requestpermission()
//    {
//        ActivityCompat.requestPermissions(MainActivity2.this,new String[]{Manifest.permission.CALL_PHONE},1);
//    }
//
////    public void cout()
////    {
////        System.out.println("ali");
////    }
////    public void num(){
////        call();
////    }
//
//    //crop image
//
//    private void showImagePicDialog() {
//        //String options[] = {"Camera"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//       // builder.setTitle("Pick Image From");
//        //builder.setItems(options, new DialogInterface.OnClickListener() {
//            //@RequiresApi(api = Build.VERSION_CODES.M)
//            //@Override
//          //  public void onClick(DialogInterface dialog, int which) {
////                if (which == 0) {
////                    if (!checkCameraPermission()) {
////                        requestCameraPermission();
////                    } else {
//                        pickFromGallery();
//                    //}
////                } else if (which == 1) {
////                    if (!checkStoragePermission()) {
////                        requestStoragePermission();
////                    } else {
////                        pickFromGallery();
////                    }
////                }
//            }
//        //});
//        //builder.create().show();
//
//
//    // checking storage permissions
//    private Boolean checkStoragePermission() {
//        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
//        return result;
//    }
//
//    // Requesting  gallery permission
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void requestStoragePermission() {
//        requestPermissions(storagePermission, STORAGE_REQUEST);
//    }
//
//    // checking camera permissions
//    private Boolean checkCameraPermission() {
//        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
//        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
//        return result && result1;
//    }
//
//    // Requesting camera permission
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void requestCameraPermission() {
//        requestPermissions(cameraPermission, CAMERA_REQUEST);
//    }
//
//    // Requesting camera and gallery
//    // permission if not given
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case CAMERA_REQUEST: {
//                if (grantResults.length > 0) {
//                    boolean camera_accepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    boolean writeStorageaccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//                    if (camera_accepted && writeStorageaccepted) {
//                        pickFromGallery();
//                    } else {
//                        Toast.makeText(this, "Please Enable Camera and Storage Permissions", Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//            break;
//            case STORAGE_REQUEST: {
//                if (grantResults.length > 0) {
//                    boolean writeStorageaccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    if (writeStorageaccepted) {
//                        pickFromGallery();
//                    } else {
//                        Toast.makeText(this, "Please Enable Storage Permissions", Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//            break;
//        }
//    }
//
//    // Here we will pick image from gallery or camera
//    private void pickFromGallery() {
//        CropImage.activity().start(MainActivity2.this);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                Uri resultUri = result.getUri();
//                Picasso.with(this).load(resultUri).into(imageView);
//            }
//        }
//    }
//}