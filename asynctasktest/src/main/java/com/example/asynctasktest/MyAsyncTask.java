package com.example.asynctasktest;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Administrator on 2015/7/21.
 */
public class MyAsyncTask extends AsyncTask<Void,Void,Void> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("john", "onPreExecute");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("john", "onPostExecute");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Log.d("john", "onProgressUpdate");
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.d("john","doInBackground");
        publishProgress();
        return null;
    }
}
