package com.example.asynctasktest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2015/7/21.
 */
public class ProgressBarTest extends Activity {

    private ProgressBar progressBar;

    private MyAsyTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        progressBar = (ProgressBar) findViewById(R.id.pg);
        task = new  MyAsyTask();
        task.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(task != null && task.getStatus() == AsyncTask.Status.RUNNING)
            task.cancel(true);
    }

    class  MyAsyTask extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 100; i++) {
                if(isCancelled())
                    break;
                publishProgress(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return  null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
    }
}
