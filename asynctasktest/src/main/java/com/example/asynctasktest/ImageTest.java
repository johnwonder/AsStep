package com.example.asynctasktest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2015/7/21.
 */
public class ImageTest extends Activity {

    private ImageView imageView;
    private ProgressBar mProgressBar;
    private static  String URL =
            "http://img.my.csdn.net/uploads/201504/12/1428806103_9476.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        imageView = (ImageView)findViewById(R.id.image);
        mProgressBar = (ProgressBar)findViewById(R.id.progressbar);
        new MyAsyncTask().execute(URL);
    }

    class  MyAsyncTask extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mProgressBar.setVisibility(View.GONE);
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            Bitmap bitmap = null;
            URLConnection urlConnection;
            InputStream inputStream;
            try {
                urlConnection = new URL(url).openConnection();
                inputStream = urlConnection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                Thread.sleep(3000);
                bitmap = BitmapFactory.decodeStream(bis);
                inputStream.close();
                bis.close();
            }catch (IOException e){

                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return bitmap;
        }
    }
}
