package Team.RG.RaspiGuard.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Team.RG.RaspiGuard.SupportClasses.Config;
import Team.RG.RaspiGuard.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageSensorFragment extends Fragment {

    //Declaring an Spinner
    public Spinner spinner;
    View rootView;
    String username;
    String sensorname;
    String location;
    String sensorType;
    EditText et1;
    EditText et2;
    RadioButton rb1;
    RadioButton rb2;
    String sensorDelName;
    //JSON Array
    private JSONArray result;
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    StringRequest stringRequest = new StringRequest("http://165.227.44.224/deleteSensor.php?user=" + username + "&sensorName=" + sensorDelName,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();


                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });

                    //Creating a request queue
                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());

                    //Adding request to the queue
                    requestQueue.add(stringRequest);
                    getData(username);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };


    public ManageSensorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_manage_sensor, container, false);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        username = preferences.getString("username", "");


        et1 = (EditText) rootView.findViewById(R.id.editText1);
        et2 = (EditText) rootView.findViewById(R.id.editText2);

        rb1 = (RadioButton) rootView.findViewById(R.id.rb1);
        rb2 = (RadioButton) rootView.findViewById(R.id.rb2);

        //MAKING SURE ONLY ONE RADIO BUTTON IS CLICKED
        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (rb1.isChecked()) {
                    rb2.setChecked(false);
                    sensorType = "doorsensors";

                }
            }
        });
        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (rb2.isChecked()) {
                    rb1.setChecked(false);
                    sensorType = "moisturesensors";
                }
            }
        });


        Button button_add = (Button) rootView.findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sensorname = et1.getText().toString();
                location = et2.getText().toString();

                if (rb1.isChecked() || rb2.isChecked()) {

                    if (rb1.isChecked()) {
                        sensorType = "doorsensors";

                    } else if (rb2.isChecked()) {
                        sensorType = "moisturesensors";
                    }

                    StringRequest stringRequest = new StringRequest("http://165.227.44.224/addSensor.php?user=" + username + "&table=" + sensorType + "&sensorName=" + sensorname + "&location=" + location,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();

                                    et1.setText("");
                                    et2.setText("");
                                    rb1.setChecked(false);
                                    rb2.setChecked(false);


                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });

                    //Creating a request queue
                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());

                    //Adding request to the queue
                    requestQueue.add(stringRequest);

                    getData(username);

                } else Toast.makeText(getContext(), "Select Sensor Type", Toast.LENGTH_SHORT).show();
            }
        });

        getData(username);


        //Initializing Spinner
        spinner = (Spinner) rootView.findViewById(R.id.spinner);
        addListenerOnSpinner();

        Button button_del = (Button) rootView.findViewById(R.id.btn_del);
        button_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sensorDelName = spinner.getSelectedItem().toString();


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure? Once the sensor is deleted it cannot be restored!").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();


            }
        });


        return rootView;
    }

    private void getData(String targetuser) {
        //Creating a string request

        StringRequest stringRequest = new StringRequest(Config.DATA_DOORSENSOR_URL + "?user=" + username,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);


                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(Config.JSON_ARRAY);

                            //Calling method getsensors to get the sensors from the JSON Array
                            getSensorList(result);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getSensorList(JSONArray j) {

        //An ArrayList for Spinner Items
        ArrayList<String> sensors = new ArrayList<String>();


        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                sensors.add(json.getString(Config.TAG_SENSORNAME));
                //Toast.makeText(getContext(),json.getString(Config.TAG_SENSORNAME),Toast.LENGTH_SHORT).show();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, sensors));

    }

    // get the selected dropdown list value
    public void addListenerOnSpinner() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
