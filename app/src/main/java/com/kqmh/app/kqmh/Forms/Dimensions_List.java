package com.kqmh.app.kqmh.Forms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.kqmh.app.kqmh.Activities.ProgressActivity;
import com.kqmh.app.kqmh.Activities.Welcome;
import com.kqmh.app.kqmh.Models.KeyValue;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;

import java.util.ArrayList;


public class Dimensions_List extends AppCompatActivity {
    private Spinner spinner_dim;
    private ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_dimensions_list);

        logout = findViewById(R.id.img_logout);
        spinner_dim = findViewById(R.id.spinner_dim);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prev_submit();
            }
        });

        spinnerData_dim(spinner_dim, "0");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(logout);
            }
        });
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.home_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                logout();
                return true;
            }
        });
        popup.show();
    }

    public void spinnerData_dim(Spinner spinner, final String choice) {
        final ArrayList<KeyValue> keyvalue = new ArrayList<>();

        // adding each child node to HashMap key => value
        keyvalue.add(new KeyValue(0, "Select"));
        keyvalue.add(new KeyValue(1, "Dim1: Leadership"));
        keyvalue.add(new KeyValue(2, "Dim2: HRM"));
        keyvalue.add(new KeyValue(3, "Dim3: Guidelines"));
        keyvalue.add(new KeyValue(4, "Dim4: Infrastructure"));
        keyvalue.add(new KeyValue(5, "Dim5: Supplies Management"));
        keyvalue.add(new KeyValue(6, "Dim6: Equipment Management"));
        keyvalue.add(new KeyValue(7, "Dim7: Transport Management"));
        keyvalue.add(new KeyValue(8, "Dim8: Referral System"));
        keyvalue.add(new KeyValue(9, "Dim9: HMIS"));
        keyvalue.add(new KeyValue(10, "Dim10: Financial Management"));
        keyvalue.add(new KeyValue(11, "Dim11: Services"));
        keyvalue.add(new KeyValue(12, "Dim12: Results"));
        keyvalue.add(new KeyValue(13, "Summary Progress"));


        //fill data in spinner
        ArrayAdapter<KeyValue> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, keyvalue);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        break;
                    case 1:
                        Intent intentDimension1 = new Intent(getBaseContext(), Dimension1.class);
                        startActivity(intentDimension1);
                        break;
                    case 2:
                        Intent intentDimension2 = new Intent(getBaseContext(), Dimension2.class);
                        startActivity(intentDimension2);
                        break;
                    case 3:
                        Intent intentDimension3 = new Intent(getBaseContext(), Dimension3.class);
                        startActivity(intentDimension3);
                        break;
                    case 4:
                        Intent intentDimension4 = new Intent(getBaseContext(), Dimension4.class);
                        startActivity(intentDimension4);
                        break;
                    case 5:
                        Intent intentDimension5 = new Intent(getBaseContext(), Dimension5.class);
                        startActivity(intentDimension5);
                        break;
                    case 6:
                        Intent intentDimension6 = new Intent(getBaseContext(), Dimension6.class);
                        startActivity(intentDimension6);
                        break;
                    case 7:
                        Intent intentDimension7 = new Intent(getBaseContext(), Dimension7.class);
                        startActivity(intentDimension7);
                        break;
                    case 8:
                        Intent intentDimension8 = new Intent(getBaseContext(), Dimension8.class);
                        startActivity(intentDimension8);
                        break;
                    case 9:
                        Intent intentDimension9 = new Intent(getBaseContext(), Dimension9.class);
                        startActivity(intentDimension9);
                        break;
                    case 10:
                        Intent intentDimension10 = new Intent(getBaseContext(), Dimension10.class);
                        startActivity(intentDimension10);
                        break;
                    case 11:
                        Intent intentDimension11_List = new Intent(getBaseContext(), Dimension11_List.class);
                        startActivity(intentDimension11_List);
                        break;
                    case 12:
                        Intent intentDimension12 = new Intent(getBaseContext(), Dimension12.class);
                        startActivity(intentDimension12);
                        break;
                    case 13:
                        Intent intentProgress = new Intent(getBaseContext(), ProgressActivity.class);
                        startActivity(intentProgress);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void prev_submit() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Assessment_InfoEx.class);
        startActivity(intent);
    }


    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Log out?");
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new SessionManager(getBaseContext()).setLoggedIn(false);
                Intent intent = new Intent(getBaseContext(), Welcome.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}