package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] mobileArray = {"Java","C++","C#","CSS",
            "HTML","XML",".Net","VisualBasic", "SQL", "Python", "PHP", "a", "b", "c", "d", "e", "f", "g"};
    TextView tvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //do_list();

        tvMain = (TextView) findViewById(R.id.textViewMain);
        tvMain.setText("No Message");
        Button btnMessage = (Button) findViewById(R.id.buttonMessage);
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageClick();
            }
        });
    }

    private void do_list(){
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.my_list_item, mobileArray);
        final ListView listView = (ListView) findViewById(R.id.my_list);
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String  itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });


    }

    private void onSnack(){
    }

    public class MyUndoListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.d("BBB", "snack click");
        }
    }

    public interface MyShowInfo {
        final static int MyVal = 33;
        public void ShowInfo();
    }

    private void MessageClick() {


        Log.d("BBB", "Message");
        Intent intx = new Intent(this, MessageActivity.class);
        intx.putExtra("BLAH", "Extra stuff");
        startActivityForResult(intx, 33);

        /*
        Toast tst = Toast.makeText(getApplicationContext(), "blah", Toast.LENGTH_SHORT);
        tst.show();
        */
        /*
        Snackbar sn = Snackbar.make(findViewById(android.R.id.content), "blah2", Snackbar.LENGTH_SHORT);
        sn.setAction("action", new MyUndoListener());
        sn.show();
        */
        //MyTest mt = new MyTest(7);
        //MyTest.iVal2 = 512;
        //mt.iVal2 = 55;
        //MyTest.showVals();
        //mt.showVals();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 33)
        {
            String sx;
            if (data == null) {
                sx = "[data null]";
            } else {
                sx = data.getStringExtra("MESSAGE");
            }
            Log.d("BBB", "onActivityResult=" + resultCode + " msg=" + sx);
            tvMain.setText(sx);
        }
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
}
