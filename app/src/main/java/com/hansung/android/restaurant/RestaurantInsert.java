package com.hansung.android.restaurant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RestaurantInsert extends AppCompatActivity {
    public static String mPhotoFileName;
    public static File mPhotoFile;


    private RestaurantDBHelper mRestaurant;

    static final int REQUEST_IMAGE_CAPTURE = 200;
    final int  REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_insert);

        checkDangerousPermissions();

        mRestaurant = new RestaurantDBHelper(this);

        ImageButton photoBtn = (ImageButton)findViewById(R.id.imageButton);
        photoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        Button insertBtn = (Button) findViewById(R.id.insert);
        insertBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                insertRecord();

            }
        });
    }

    //============================카메라 앱에 들어가서 사진 촬영=======================
    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //1. 카메라 앱으로 찍은 이미지를 저장할 파일 객체 생성
            mPhotoFileName = "IMG"+currentDateFormat()+".jpg";
            mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName);

            if (mPhotoFile !=null) {
                //2. 생성된 파일 객체에 대한 Uri 객체를 얻기
                Uri imageUri = FileProvider.getUriForFile(this, "com.hansung.android.restaurant", mPhotoFile);

                //3. Uri 객체를 Extras를 통해 카메라 앱으로 전달
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else
                Toast.makeText(getApplicationContext(), "file null", Toast.LENGTH_SHORT).show();
        }
    }

    //날짜, 시간 나타내는 함수
    private String currentDateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String  currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }

    //======================촬영한 데이터 보야주기========================
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //촬영한 사진 화면에 나타내기
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (mPhotoFileName != null) {
                mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName);
                Uri uri = Uri.fromFile(mPhotoFile);
                ImageView imageView = (ImageView) findViewById(R.id.imageButton);
                imageView.setImageURI(uri);
            } else {
                Toast.makeText(getApplicationContext(), "mPhotoFile is null", Toast.LENGTH_SHORT).show();
            }
        }
    }

//===================EditText에 입력한 정보를 데이터에 저장하는 메소드==========

    private void insertRecord(){
        //getAbsolutePath()를 이용해서 restaurantImage에 mPhotoFile의 절대 경로 위치를 반환
        String restaurantImage = mPhotoFile.getAbsolutePath();
        EditText restaurantname = (EditText)findViewById(R.id.edit_name);
        EditText restaurantadress = (EditText)findViewById(R.id.edit_address);
        EditText restaurantphone = (EditText)findViewById(R.id.edit_phone);
        EditText restaurantopen = (EditText)findViewById(R.id.edit_open);

        long nOfRows = mRestaurant.insertRestaurantMethod(restaurantImage, restaurantname.getText().toString(),
                restaurantadress.getText().toString(), restaurantphone.getText().toString(), restaurantopen.getText().toString());
        if (nOfRows >0)
            Toast.makeText(this,nOfRows+" Record Inserted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"No Record Inserted", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


    //========================외부 저장소 권한 허용==========================
    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA);
        }
    }

}
