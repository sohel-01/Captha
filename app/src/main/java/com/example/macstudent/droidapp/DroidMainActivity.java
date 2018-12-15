package com.example.macstudent.droidapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class DroidMainActivity extends AppCompatActivity {

    GridView gridView;
    Button verify;
    CheckBox mCheckBox;
    ImageView mImageView;
    boolean isRobot;
    int count = 0;


    private Integer[] mThumbIds = {
            R.drawable.img1, R.drawable.img2,
            R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6,

            R.drawable.img7, R.drawable.img8,
            R.drawable.img9

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_droid_main);
        gridView = (GridView) findViewById(R.id.grid_products);
        verify = (Button) findViewById(R.id.verify);
        mCheckBox = (CheckBox) findViewById(R.id.check);
        mImageView = (ImageView) findViewById(R.id.refresh);

        gridView.setAdapter(new GridviewAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

              /*  int imageId = mThumbIds[position];

                GridviewAdapter imageAdapter = new GridviewAdapter(MainActivity.this);

                ImageView imageView = (ImageView) findViewById(R.id.photoView);
                v.setImageResource(mThumbIds[position]);*/
                ImageView imageView = (ImageView)v;
                Animation myFadeInAnimation = AnimationUtils.loadAnimation(DroidMainActivity.this, R.anim.fade);
                imageView.startAnimation(myFadeInAnimation);

                if (position == 0 || position == 1 || position == 2
                        || position == 3) {
                    count++;
                }
                imageView.setImageResource(R.drawable.checked);//Set animation to your ImageView
            }
        });


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isRobot && count == 4) {

                    alertView("Login verified");
                } else {

                    alertView("Login Failed");
                }
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = 0; i < mThumbIds.length; i++) {
                    list.add(mThumbIds[i]);
                }

                Collections.shuffle(list);
                gridView.setAdapter(new GridviewAdapter(DroidMainActivity.this));


            }
        });

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    isRobot = true;
                }else{
                    isRobot = false;
                }
            }
        });

    }

    public class GridviewAdapter extends BaseAdapter {
        private Context mContext;

        public GridviewAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(280, 280));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setTag(position);
//			imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // references to your images
    }


    private void alertView(String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(DroidMainActivity.this);
        dialog
                .setMessage(message)
//     .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//      public void onClick(DialogInterface dialoginterface, int i) {
//          dialoginterface.cancel();
//          }})
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                }).show();
    }

}
