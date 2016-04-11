package edu.wwu.corotaa.tipcalculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText initBill;
    private Button percent10;
    private Button percent12;
    private Button percent15;
    private Button percent17;
    private Button percent20;
    private Button percent23;
    private SeekBar splits;
    private TextView splitCount;
    private TextView tipTot;
    private TextView billTot;
    private Button reset;

    private double initDollar = 0.0;
    private double dollar = 0.0;
    private double tip = 0.0;
    private int splitNum = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBill = (EditText) findViewById(R.id.initBill);
        percent10 = (Button) findViewById(R.id.p10);
        percent12 = (Button) findViewById(R.id.p12);
        percent15 = (Button) findViewById(R.id.p15);
        percent17 = (Button) findViewById(R.id.p17);
        percent20 = (Button) findViewById(R.id.p20);
        percent23 = (Button) findViewById(R.id.p23);
        splits = (SeekBar) findViewById(R.id.seekSplit);
        splitCount = (TextView) findViewById(R.id.splitCount);
        tipTot = (TextView) findViewById(R.id.tipReport);
        billTot = (TextView) findViewById(R.id.billReport);
        reset = (Button) findViewById(R.id.resetButton);

        percent10.setOnClickListener(mClickListener);
        percent12.setOnClickListener(mClickListener);
        percent15.setOnClickListener(mClickListener);
        percent17.setOnClickListener(mClickListener);
        percent20.setOnClickListener(mClickListener);
        percent23.setOnClickListener(mClickListener);
        reset.setOnClickListener(mClickListener);
        splits.setOnSeekBarChangeListener(sChangeListener);

    }

    private SeekBar.OnSeekBarChangeListener sChangeListener =
            new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        splitNum = progress + 1;
                        splitCount.setText(String.format("%d",splitNum));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        splitCount.setText(String.format("%d", splitNum));
                    }

    };

    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == reset) {
                reset();
            } else {
                calculate(v);
            }
        }
    };

    private void calculate(View v){
        if (initBill.getText().toString().equals("") || initBill.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Total Bill Amount field must be filled", Toast.LENGTH_LONG).show();
            return;
        }

        initDollar = Double.parseDouble(initBill.getText().toString());
        dollar = Double.parseDouble(initBill.getText().toString());

        if(v == percent10) {
            dollar *= 1.10;
        }
        else if(v == percent12) {
            dollar *= 1.12;
        }
        else if(v == percent15) {
            dollar *= 1.15;
        }
        else if(v == percent17) {
            dollar *= 1.17;
        }
        else if(v == percent20) {
            dollar *= 1.20;
        }
        else{
            dollar *= 1.23;
        }

        tip = (dollar - initDollar)/(splitNum);
        dollar = dollar/(splitNum);

        tipTot.setText(String.format("%.2f", tip));
        billTot.setText(String.format("%.2f", dollar));

        /*percent10.setEnabled(false);
        percent12.setEnabled(false);
        percent15.setEnabled(false);
        percent17.setEnabled(false);
        percent20.setEnabled(false);
        percent23.setEnabled(false);*/
    }

    private void reset() {
        initBill.setText("");
        tipTot.setText("0.00");
        billTot.setText("0.00");
        splitCount.setText("0");
        splits.setProgress(0);
        percent10.setEnabled(true);
        percent12.setEnabled(true);
        percent15.setEnabled(true);
        percent17.setEnabled(true);
        percent20.setEnabled(true);
        percent23.setEnabled(true);
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
