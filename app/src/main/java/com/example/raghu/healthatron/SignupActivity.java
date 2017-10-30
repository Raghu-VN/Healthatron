package com.example.raghu.healthatron;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private short selectedButton = 0;
    DatabaseHelperClass myDB;
    String patName;
    String actName;
    String actPhone;
    String docId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_reset) {
            myDB = new DatabaseHelperClass(this);
            myDB.insertData("uid001", "uid001", "Kiran Kumar", "Pediatrics");
            myDB.insertData("uid002", "uid002", "Shiv", "Ophthalmology");
            myDB.insertData("uid003", "uid003", "Dhanu", "Gynaecology");
            myDB.insertData("uid004", "uid004", "Manu", "Cardiology");
            myDB.insertData("uid005", "uid005", "Kannada Raja", "Dental Sciences");
            myDB.insertData("uid006", "uid006", "Arya", "ENT");
            myDB.insertData("uid007", "uid007", "Dhruv", "General Physician");
            myDB.insertData("uid008", "uid008", "Nandini", "Sports Medicine");
            myDB.insertData("uid009", "uid009", "Roopa", "Yoga Therapy");
            myDB.insertData("uid100", "uid100", "Stella", "Nurse");
            myDB.insertData("uid101", "uid101", "Mary", "Nurse");
            setContentView(R.layout.activity_signup);
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    public void signInButtonClick(View v) {
        setContentView(R.layout.activity_signin);
        //Spinner s = (Spinner)findViewById(R.id.spinner);
        //s.setOnItemSelectedListener(this);
    }

    public void actualButtonClick(View v) {
        EditText t1 = (EditText)findViewById(R.id.ph);
        String phone = t1.getText().toString();
        if(phone.length() != 10)
        {
            Toast.makeText(getApplicationContext(), "Invalid Phone!", Toast.LENGTH_SHORT).show();
            return;
        }
        EditText t2 = (EditText)findViewById(R.id.pw);
        String pw = t2.getText().toString();
        if(pw.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        Cursor res = myDB.getPhone(phone);
        res.moveToFirst();
        if(res.getCount() == 0)
        {
            Toast.makeText(getApplicationContext(), "Phone not registered!", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);
        Spinner s = (Spinner)findViewById(R.id.spinner);
        s.setOnItemSelectedListener(this);
        TextView pname = (TextView)findViewById(R.id.textView8);
        pname.setText("Welcome " + res.getString(2) + "!");
        patName = "Welcome " + res.getString(2) + "!";
        actName = res.getString(2);
        actPhone = res.getString(0);
    }

    public void loginButtonClick(View v) {
        setContentView(R.layout.doctor_signin);
    }

    public void onClickServicesButton(View v)
    {
        setContentView(R.layout.activity_main);
        Spinner s = (Spinner)findViewById(R.id.spinner);
        s.setOnItemSelectedListener(this);
        TextView txtView = (TextView)findViewById(R.id.s);
        txtView.setBackgroundColor(0xffffffff);
        txtView.setTextColor(0xffff2415);
        txtView = getCurrentSelection();
        txtView.setBackgroundColor(0xffff2415);
        txtView.setTextColor(0xffffffff);
        selectedButton = 0;
        TextView pname = (TextView)findViewById(R.id.textView8);
        pname.setText(patName);
    }

    public void onClickBillingButton(View v)
    {
        setContentView(R.layout.activity_billing);
        TextView txtView = (TextView)findViewById(R.id.b);
        txtView.setBackgroundColor(0xffffffff);
        txtView.setTextColor(0xffff2415);
        txtView = getCurrentSelection();
        txtView.setBackgroundColor(0xffff2415);
        txtView.setTextColor(0xffffffff);
        selectedButton = 1;
        TextView pname = (TextView)findViewById(R.id.textView8);
        pname.setText(patName);
    }

    public void onClickInsuranceButton(View v)
    {
        setContentView(R.layout.activity_insurance);
        TextView txtView = (TextView)findViewById(R.id.i);
        txtView.setBackgroundColor(0xffffffff);
        txtView.setTextColor(0xffff2415);
        txtView = getCurrentSelection();
        txtView.setBackgroundColor(0xffff2415);
        txtView.setTextColor(0xffffffff);
        selectedButton = 2;
        TextView pname = (TextView)findViewById(R.id.textView8);
        pname.setText(patName);
    }

    public void onClickReportsButton(View v)
    {
        setContentView(R.layout.activity_reports);
        TextView txtView = (TextView)findViewById(R.id.r);
        txtView.setBackgroundColor(0xffffffff);
        txtView.setTextColor(0xffff2415);
        txtView = getCurrentSelection();
        txtView.setBackgroundColor(0xffff2415);
        txtView.setTextColor(0xffffffff);
        selectedButton = 3;
        TextView pname = (TextView)findViewById(R.id.textView8);
        pname.setText(patName);
    }

    public TextView getCurrentSelection()
    {
        if(selectedButton == 0)
        {
            return (TextView)findViewById(R.id.s);
        }
        else if(selectedButton == 1)
        {
            return (TextView)findViewById(R.id.b);
        }
        else if(selectedButton == 2)
        {
            return (TextView)findViewById(R.id.i);
        }
        else if(selectedButton == 3)
        {
            return (TextView)findViewById(R.id.r);
        }
        return null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        Spinner spin = (Spinner)parent;
        Spinner spin2 = (Spinner)parent;
        if(spin.getId() == R.id.spinner)
        {
            String item = parent.getItemAtPosition(position).toString();
            TextView v = (TextView)findViewById(R.id.Name);
            // Showing selected spinner item
            if(item != null) {
                Cursor res = myDB.getName(item);
                res.moveToFirst();
                v.setText(res.getString(2));
                docId = res.getString(0);
            }
        }
        if(spin2.getId() == R.id.sp)
        {
            String item = parent.getItemAtPosition(position).toString();
            Cursor res = myDB.getAptByName(item);
            res.moveToFirst();
            if(res.getCount() > 0) {
                TextView v1 = (TextView) findViewById(R.id.name);
                TextView v2 = (TextView) findViewById(R.id.phone);
                TextView v3 = (TextView) findViewById(R.id.date);
                v1.setText(res.getString(0));
                v2.setText(res.getString(1));
                v3.setText(res.getString(3));
            }
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void signUpButtonClick(View v)
    {
        EditText t1 = (EditText)findViewById(R.id.editText);
        String name = t1.getText().toString();
        if((name.equals("Ex: Raghunand V N")) || (name.equals("")))
        {
            Toast.makeText(getApplicationContext(), "Invalid Name!", Toast.LENGTH_SHORT).show();
            return;
        }
        EditText t2 = (EditText)findViewById(R.id.editText2);
        String phone = t2.getText().toString();
        if(phone.length() != 10)
        {
            Toast.makeText(getApplicationContext(), "Invalid Phone!", Toast.LENGTH_SHORT).show();
            return;
        }
        EditText t3 = (EditText)findViewById(R.id.editText3);
        EditText t4 = (EditText)findViewById(R.id.editText4);
        String pw = t3.getText().toString();
        String rpw = t4.getText().toString();
        if(pw.equals("") || !(rpw.equals(pw)))
        {
            Toast.makeText(getApplicationContext(), "Password Mismatch!", Toast.LENGTH_SHORT).show();
            return;
        }
        Cursor res = myDB.getPhone(phone);
        res.moveToFirst();
        if(res.getCount() != 0)
        {
            Toast.makeText(getApplicationContext(), "Already registered, please sign in!", Toast.LENGTH_SHORT).show();
            return;
        }
        myDB.insertPat(phone, pw, name);
        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);
        Spinner s = (Spinner)findViewById(R.id.spinner);
        s.setOnItemSelectedListener(this);
        TextView pname = (TextView)findViewById(R.id.textView8);
        pname.setText("Welcome " + name + "!");
        patName = "Welcome " + name + "!";
        actName = name;
        actPhone = phone;
    }

    public void onHospitalClick(View v)
    {
        onClickServicesButton(v);
    }

    public void onReminderClick(View v)
    {
        setContentView(R.layout.activity_reminder);
    }

    public void onAlertClick(View v)
    {
        setContentView(R.layout.activity_alert);
    }

    public void onRedButtonClick(View v)
    {
        Toast.makeText(getApplicationContext(), "Alerted, help on the way!", Toast.LENGTH_SHORT).show();
    }

    public void onBookClick(View v)
    {
        EditText Dt = (EditText)findViewById(R.id.DateEntry);
        EditText Tm = (EditText)findViewById(R.id.TimeEntry);
        String D = Dt.getText().toString();
        String T = Tm.getText().toString();
        myDB.insertApt(actName, actPhone, docId, D, T);
        Toast.makeText(getApplicationContext(), "Congrats! Appointment booked", Toast.LENGTH_SHORT).show();
    }

    public void doctorButtonClick(View v)
    {
        EditText hid = (EditText)findViewById(R.id.hid);
        String id = hid.getText().toString();
        if(id.equals("uid100") || id.equals("uid101"))
        {
            setContentView(R.layout.nurse);
        }
        else
        {
            setContentView(R.layout.doctor);
        }
        List<String> spinnerArray =  new ArrayList<String>();
        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,spinnerArray);
        Spinner s = (Spinner) findViewById(R.id.sp);
        s.setOnItemSelectedListener(this);
        Cursor res = myDB.getApt(id);
        if(res.getCount() > 0) {
            res.moveToFirst();
            TextView v1 = (TextView)findViewById(R.id.name);
            TextView v2 = (TextView)findViewById(R.id.phone);
            TextView v3 = (TextView)findViewById(R.id.date);
            v1.setText(res.getString(0));
            v2.setText(res.getString(1));
            v3.setText(res.getString(3));
            for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
                spinnerArray.add(res.getString(0));
            }
        }
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(a);
    }

    public void onClickAptButton(View v)
    {

    }

    public void onClickRBButton(View v)
    {

    }

    public void onClickMedButton(View v)
    {

    }

    public void onClickNRBButton(View v)
    {

    }
}
