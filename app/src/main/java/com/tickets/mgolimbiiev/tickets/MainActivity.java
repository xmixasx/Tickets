package com.tickets.mgolimbiiev.tickets;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.tickets.mgolimbiiev.tickets.adapters.StationAutocompliteAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private AutoCompleteTextView FromTextView;
    private AutoCompleteTextView ToTextView;
    private TextView DateView;
    private Calendar calendar;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FromTextView = (AutoCompleteTextView) findViewById(R.id.station_from);
        FromTextView.setAdapter(new StationAutocompliteAdapter(this, R.layout.station_list_item));
        FromTextView.setOnItemClickListener(this);

        ToTextView = (AutoCompleteTextView) findViewById(R.id.station_to);
        ToTextView.setAdapter(new StationAutocompliteAdapter(this, R.layout.station_list_item));
        ToTextView.setOnItemClickListener(this);
        DateView = (TextView) findViewById(R.id.datePicker);

        DateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year,month,day);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        Intent intent = new Intent(this, TicketService.class);
        startService(intent);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String str = (String) parent.getItemAtPosition(position);
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    private void showDate(int year, int month, int day) {
        DateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this,myDateListener,year, month, day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            showDate(year, monthOfYear+1, dayOfMonth);
        }
    };
}
