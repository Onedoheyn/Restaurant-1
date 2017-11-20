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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private RestaurantDBHelper mRestaurantDBHelper =
            new RestaurantDBHelper(this);

    private MenuDBHelper mMenuDBHelper =
            new MenuDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mRestaurantDBHelper.deleteRestaurantSQL();
        //mMenuDBHelper.deleteRestaurantMenuSQL();
        final Cursor Restaurant = mRestaurantDBHelper.getAllRestaurantMethod();


        if(Restaurant.moveToNext() == false ){
        Intent intent = new Intent(getApplicationContext(), RestaurantInsert.class);
        startActivity(intent);}
        else{
            Setting();
        }

        ImageButton PhoneBtn = (ImageButton) findViewById(R.id.call_Button1);
        PhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel = "tel: " + Restaurant.getString(4);
                Intent tel_intent = new Intent(Intent.ACTION_DIAL, Uri.parse(tel));
                startActivity(tel_intent);
            }
        });

        ViewAllMenu();


    }

    private void Setting(){

        Cursor c= mRestaurantDBHelper.getAllRestaurantMethod();

        ImageView MainImage = (ImageView)findViewById(R.id.MainIamge);
        TextView MainName = (TextView)findViewById(R.id.MainName);
        TextView MainAddress = (TextView)findViewById(R.id.MainAddress);
        TextView MainPhone = (TextView)findViewById(R.id.MainPhone);
        TextView MainOpen = (TextView)findViewById(R.id.MainOpen);


        c.moveToPosition(0);

        MainImage.setImageURI(Uri.parse(c.getString(1)));
        MainName.setText(c.getString(2));
        MainAddress.setText(c.getString(3));
        MainPhone.setText(c.getString(4));
        MainOpen.setText(c.getString(5));

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

    private void ViewAllMenu(){
        Cursor Menu = mMenuDBHelper.getAllRestaurantMenuMethod();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
               R.layout.item, Menu, new String[]{
                UserContract.Restaurant.Menu_image,
                UserContract.Restaurant.Menu_name,
                UserContract.Restaurant.Menu_price},
                new int[]{R.id.iconItem, R.id.textItem1, R.id.textItem2 },0 );

        ListView Lv = (ListView)findViewById(R.id.listview);

        Lv.setAdapter(adapter);

    }


}
