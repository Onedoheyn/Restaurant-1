package com.hansung.android.restaurant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private RestaurantDBHelper mRestaurantDBHelper = new RestaurantDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mRestaurantDBHelper.deleteRestaurantSQL();
        Cursor Restaurant = mRestaurantDBHelper.getAllRestaurantMethod();


        if(Restaurant.moveToNext() == false ){
        Intent intent = new Intent(getApplicationContext(), RestaurantInsert.class);
        startActivity(intent);}
        else{
            Setting();
        }


    }

    private void Setting(){

//        Cursor c= mRestaurantDBHelper.getAllRestaurantMethod();
//
//        TextView MainName = (TextView)findViewById(R.id.MainName);
//        TextView MainAddress = (TextView)findViewById(R.id.MainAddress);
//        TextView MainPhone = (TextView)findViewById(R.id.MainPhone);
//        TextView MainOpen = (TextView)findViewById(R.id.MainOpen);
//
//
//        while(c.moveToPosition(0)){
//        MainName.setText(c.getString(2));
//        MainAddress.setText(c.getString(3));
//        MainPhone.setText(c.getString(4));
//        MainOpen.setText(c.getString(5));
//        }
        /*위 코드는 제가 만든 코드이고 밑에는 제가 만든 코드가 안돼서
         우재민 학생한테 물아봐서 배열로 정해주면 된다고 해서 바꾼 코드입니다.*/

        TextView [] Position = new TextView[4];
        String [] data = new String[4];

        Cursor c= mRestaurantDBHelper.getAllRestaurantMethod();

        c.moveToPosition(0);

        data[0] = c.getString(2);
        data[1] = c.getString(3);
        data[2] = c.getString(4);
        data[3] = c.getString(5);

        TextView MainName = (TextView)findViewById(R.id.MainName);
        TextView MainAddress = (TextView)findViewById(R.id.MainAddress);
        TextView MainPhone = (TextView)findViewById(R.id.MainPhone);
        TextView MainOpen = (TextView)findViewById(R.id.MainOpen);

        Position[0] = MainName;
        Position[1] = MainAddress;
        Position[2] = MainPhone;
        Position[3] = MainOpen;

        for(int i=0; i<4; i++){
            Position[i].setText(data[i]);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Menuinsert:
                startActivity(new Intent(this, MenuInsert.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
