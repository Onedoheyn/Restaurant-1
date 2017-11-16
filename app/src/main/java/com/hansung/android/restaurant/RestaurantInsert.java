package com.hansung.android.restaurant;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RestaurantInsert extends AppCompatActivity {

    static final int REQUEST_VIDEO_CAPTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_insert);

        ImageButton photoBtn = (ImageButton)findViewById(R.id.imageButton);
        photoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dispatchPictureIntent();
            }
        });
    }


    //============================카메라 앱에 들어가서 사진 촬영=======================
// 외부저장소 말고 데이터 베이스 이용 다시 만들기
    private void dispatchPictureIntent(){
        String mVideoFileName;

        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
        //1. 카메라 앱으로 찍은 동영상을 저장할 파일 객체 생성
        mVideoFileName = "VIDEO" + currentDateFormat() + ".mp4";
        File destination = new File(getExternalFilesDir(Environment.DIRECTORY_MOVIES),
                mVideoFileName);

        if (destination != null) {
            //2. 생성된 파일 객체에 대한 Uri 객체를 얻기
            Uri videoUri = FileProvider.getUriForFile(this,
                    "com.hansung.android.practice_mutimedia", destination);

            //3. Uri 객체를 Extras를 통해 카메라 앱으로 전달
            takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }
}

    //날짜, 시간 나타내는 함수
    private String currentDateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String  currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }



}
