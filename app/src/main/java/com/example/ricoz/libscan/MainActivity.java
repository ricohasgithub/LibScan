package com.example.ricoz.libscan;

import java.util.*;
import java.io.*;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import 	android.support.v7.widget.*;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    String mCurrentPhotoPath;

    private BookList history = new BookList();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private String filePath = "isbns.txt";

    private LinkedList<String> list = new LinkedList<String>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_books:
                    mTextMessage.setText(R.string.title_left);
                    return true;
                case R.id.navigation_scan:
                    // Intent myIntent = new Intent(MainActivity.this, CameraActivity.class);
                    // MainActivity.this.startActivity(myIntent);
                    //dispatchTakePictureIntent();
                    //onActivityResult();
                    return true;
                case R.id.navigation_history:
                    mTextMessage.setText(R.string.title_right);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            readFromFile();
            System.out.println("Read successful");
        } catch (Exception e) {
            System.out.println("Error");
        }

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MainAdapter(history);
        mRecyclerView.setAdapter(mAdapter);

        addBooksToList();
        System.out.println("Books added");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
            case R.id.menu_help:
                startActivity(new Intent(MainActivity.this, HelpActivity.class));
                break;
            case R.id.menu_about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;
        }
        return true;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String timeStamp = "time";
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.ricoz.libscan.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void addBookItem (Book b) {
        history.addBook(b);
        mAdapter.notifyDataSetChanged();
    }

    private void readFromFile () throws IOException {
        Scanner file = new Scanner(new File(filePath));
        while (file.hasNext()) {
            list.add(file.nextLine());
        }
    }

    private void addBooksToList () {
        for (String s : list) {
            Book b = new Book(s);
            addBookItem(b);
        }
    }

}
