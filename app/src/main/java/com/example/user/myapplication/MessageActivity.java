package com.example.user.myapplication;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity implements MainActivity.MyShowInfo {

    private class RunnableLog implements Runnable {

        private Thread tx = null;
        private boolean bStop = false;
        public void run() {

            try {
                for (int ix = 0; ix < 100; ix++) {
                    if (bStop) {
                        Log.d("BBB", "Runnable stop/break");
                        break;
                    }
                    Log.d("BBB", "Runnable running: " + ix);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Log.d("BBB", "Runnable interrupted");
            }
            Log.d("BBB", "Runnable done.");
        }
        public void start() {
            Log.d("BBB", "Runnable Start");
            tx = new Thread(this);
            tx.start();
        }

        public void stop() {
            Log.d("BBB", "Runnable Stop");
            bStop = true;
        }

    }

    RunnableLog rl = null;
    EditText etMsg;
    String[] myArray = {"Apple","Banana","Cat","Dog", "Elephant","Frog","Giraffe","Horse"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intx = getIntent();
        String sx = intx.getStringExtra("BLAH");
        Log.d("BBB", "extra = " + sx);
        setContentView(R.layout.activity_message);

        etMsg = (EditText) findViewById(R.id.editText1);
        Button btnMessage = (Button) findViewById(R.id.button1);
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sMsg = etMsg.getText().toString();
                Log.d("BBB", "Message (1):" + sMsg);
                sendBackInfo(sMsg);
            }
        });

        final ListView lv = (ListView) findViewById(R.id.listview1);
        ArrayAdapter aa = new ArrayAdapter<String>(this, R.layout.my_list_item, myArray);

        lv.setAdapter(aa);

        // ListView Item Click Listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) lv.getItemAtPosition(position);

                // Show Alert
                etMsg.setText(itemValue);
                //Toast.makeText(getApplicationContext(),
                //        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                //        .show();

            }

        });


        //if (rl == null) {
        //    rl = new RunnableLog();
        //    rl.start();
        //    ShowInfo();
        //}


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("BBB", "Back pressed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("BBB", "Destroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("BBB", "Stop");
        if (BuildConfig.FLAVOR.equals("blah")) {

        }
        if (rl != null) {
            rl.stop();
        }

    }


    public void sendBackInfo(String sx) {
        Intent intent=new Intent();
        intent.putExtra("MESSAGE", sx);
        setResult(2, intent);
        finish();
    }

    @Override
    public void ShowInfo() {
        Log.d("BBB", "ShowInfo message" + MainActivity.MyShowInfo.MyVal);
    }
}
