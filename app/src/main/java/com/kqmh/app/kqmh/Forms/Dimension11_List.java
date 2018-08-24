package com.kqmh.app.kqmh.Forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.kqmh.app.kqmh.Models.KeyValue;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;

import java.util.ArrayList;

public class Dimension11_List extends AppCompatActivity {
    private Spinner spinner_dim11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_dimension11_list);

        spinner_dim11 = findViewById(R.id.spinner_dim11);

        spinnerData_dim(spinner_dim11,"0");

        Button dims = findViewById(R.id.btn_dims);
        dims.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dims_submit();
            }
        });
    }

    public void spinnerData_dim(Spinner spinner, final String choice) {
        final ArrayList<KeyValue> keyvalue = new ArrayList<>();

        // adding each child node to HashMap key => value
        keyvalue.add(new KeyValue(0, "Select"));
        keyvalue.add(new KeyValue(1, "Dim11.1: Outpatient Services"));
        keyvalue.add(new KeyValue(2, "Dim11.2: Patient Centered Care"));
        keyvalue.add(new KeyValue(3, "Dim11.3: Infection Prevention and Control"));
        keyvalue.add(new KeyValue(4, "Dim11.4: Inpatient Care"));
        keyvalue.add(new KeyValue(5, "Dim11.5: Accidents and Emergency"));
        keyvalue.add(new KeyValue(6, "Dim11.6: Surgical Emergencies"));
        keyvalue.add(new KeyValue(7, "Dim11.7: Anaesthesia"));
        keyvalue.add(new KeyValue(8, "Dim11.8: Safe Delivery"));
        keyvalue.add(new KeyValue(9, "Dim11.9: Neonatal Care"));
        keyvalue.add(new KeyValue(10, "Dim11.10: Haemodialysis Services"));
        keyvalue.add(new KeyValue(11, "Dim11.11: Laboratory"));
        keyvalue.add(new KeyValue(12, "Dim11.12: Pharmacy"));
        keyvalue.add(new KeyValue(13, "Dim11.13: Radiology"));
        keyvalue.add(new KeyValue(14, "Dim11.14: Mortuary"));


        //fill data in spinner
        ArrayAdapter<KeyValue> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, keyvalue);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        break;
                    case 1:
                        Intent intentDimension1 = new Intent(getBaseContext(), Dimension11_1.class);
                        startActivity(intentDimension1);
                        break;
                    case 2:
                        Intent intentDimension2 = new Intent(getBaseContext(), Dimension11_2.class);
                        startActivity(intentDimension2);
                        break;
                    case 3:
                        Intent intentDimension3 = new Intent(getBaseContext(), Dimension11_3.class);
                        startActivity(intentDimension3);
                        break;
                    case 4:
                        Intent intentDimension4 = new Intent(getBaseContext(), Dimension11_4.class);
                        startActivity(intentDimension4);
                        break;
                    case 5:
                        Intent intentDimension5 = new Intent(getBaseContext(), Dimension11_5.class);
                        startActivity(intentDimension5);
                        break;
                    case 6:
                        Intent intentDimension6 = new Intent(getBaseContext(), Dimension11_6.class);
                        startActivity(intentDimension6);
                        break;
                    case 7:
                        Intent intentDimension7 = new Intent(getBaseContext(), Dimension11_7.class);
                        startActivity(intentDimension7);
                        break;
                    case 8:
                        Intent intentDimension8 = new Intent(getBaseContext(), Dimension11_8.class);
                        startActivity(intentDimension8);
                        break;
                    case 9:
                        Intent intentDimension9 = new Intent(getBaseContext(), Dimension11_9.class);
                        startActivity(intentDimension9);
                        break;
                    case 10:
                        Intent intentDimension10 = new Intent(getBaseContext(), Dimension11_10.class);
                        startActivity(intentDimension10);
                        break;
                    case 11:
                        Intent intentDimension11 = new Intent(getBaseContext(), Dimension11_11.class);
                        startActivity(intentDimension11);
                        break;
                    case 12:
                        Intent intentDimension12 = new Intent(getBaseContext(), Dimension11_12.class);
                        startActivity(intentDimension12);
                        break;
                    case 13:
                        Intent intentDimension13 = new Intent(getBaseContext(), Dimension11_13.class);
                        startActivity(intentDimension13);
                        break;
                    case 14:
                        Intent intentDimension14 = new Intent(getBaseContext(), Dimension11_14.class);
                        startActivity(intentDimension14);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void dims_submit() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Dimensions_List.class);
        startActivity(intent);
    }

}