package com.example.saisandeep.asynctaskparallel;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button button;
    ProgressBar pb1,pb2,pb3,pb4,pb5;
    MyAsyncTaskEx asyncTask1, asyncTask2, asyncTask3, asyncTask4, asyncTask5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1= (ProgressBar) findViewById(R.id.progressbar1);
        pb2= (ProgressBar) findViewById(R.id.progressbar2);
        pb3= (ProgressBar) findViewById(R.id.progressbar3);
        pb4= (ProgressBar) findViewById(R.id.progressbar4);
        pb5= (ProgressBar) findViewById(R.id.progressbar5);

        button= (Button) findViewById(R.id.start);

        button.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        asyncTask1 = new MyAsyncTaskEx(pb1);
        asyncTask1.execute();
        asyncTask2 = new MyAsyncTaskEx(pb2);
        asyncTask2.execute();
        asyncTask3 = new MyAsyncTaskEx(pb3);
        asyncTask3.execute();

        asyncTask4 = new MyAsyncTaskEx(pb4);
        StartAsyncTaskInParallel(asyncTask4);

        asyncTask5 = new MyAsyncTaskEx(pb5);
        StartAsyncTaskInParallel(asyncTask5);

    }

    private void StartAsyncTaskInParallel(MyAsyncTaskEx task) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            task.execute();
    }


    public class MyAsyncTaskEx extends AsyncTask<Void,Integer,Void>
    {

        ProgressBar myProgressBar;

        public MyAsyncTaskEx(ProgressBar target) {
            myProgressBar = target;
        }

        @Override
        protected Void doInBackground(Void... params) {

            for(int i=0; i<100; i++){
                publishProgress(i);
                SystemClock.sleep(100);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            myProgressBar.setProgress(values[0]);
        }
    }
}
