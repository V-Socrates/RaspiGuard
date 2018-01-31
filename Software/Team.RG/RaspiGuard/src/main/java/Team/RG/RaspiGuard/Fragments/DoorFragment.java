package Team.RG.RaspiGuard.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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
import Team.RG.RaspiGuard.SupportClasses.SpinnerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoorFragment extends Fragment {

    //Declaring an Spinner
    public Spinner spinner;
    View rootView;
    String username;
    String location;
    ListView listView;
    String[] values = new String[15];
    //An ArrayList for Spinner Items
    private ArrayList<String> sensors;

    //JSON Array
    private JSONArray result;
    private JSONArray result2;

    //TextViews to display details
    private TextView textViewName;
    private TextView textViewCourse;
    private TextView textViewSession;
    private TextView textViewAlarm;


    public DoorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_door, container, false);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        username = preferences.getString("username", "");


        //Initializing the ArrayList
        sensors = new ArrayList<String>();

        //Initializing Spinner
        spinner = (Spinner) rootView.findViewById(R.id.spinner);
        addListenerOnSpinner();

        //Initializing TextViews
        textViewName = (TextView) rootView.findViewById(R.id.textViewName);
        textViewCourse = (TextView) rootView.findViewById(R.id.textViewCourse);
        textViewSession = (TextView) rootView.findViewById(R.id.textViewSession);
        textViewAlarm = (TextView) rootView.findViewById(R.id.textViewAlarm);
        //This method will fetch the data from the URL
        getData(username);
        //getEventData(username);

        ToggleButton tb = (ToggleButton) rootView.findViewById(R.id.toggleButton);
        tb.setSelected(false);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked) {
                if (isChecked) {

                    StringRequest stringRequest = new StringRequest("http://165.227.44.224/updateDoorAlarm.php?user=" + username + "&sensorname=" + location + "&alarm=on",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                                    getSensorData(username, location);

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


                } else {

                    StringRequest stringRequest = new StringRequest("http://165.227.44.224/updateDoorAlarm.php?user=" + username + "&sensorname=" + location + "&alarm=off",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                                    getSensorData(username, location);

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
            }
        });


        return rootView;
    }

    // get the selected dropdown list value
    public void addListenerOnSpinner() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
                location = spinner.getSelectedItem().toString();

                textViewName.setText(location);

                getSensorData(username, location);
                getEventData(username, location);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }


    private void getSensorData(String targetuser, String room) {
        //Creating a string request

        StringRequest stringRequest = new StringRequest(Config.DATA_DOORSENSOR_URL + "?user=" + username + "&sensorname=" + room,


                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result2 = j.getJSONArray(Config.JSON_ARRAY);

                            //Calling method getsensors to get the sensors from the JSON Array
                            getSensors(result2);
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

    private void getSensors(JSONArray j) {
        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);


                textViewCourse.setText(json.getString(Config.TAG_LOCATION));
                textViewSession.setText(json.getString(Config.TAG_STATUS));
                textViewAlarm.setText(json.getString(Config.TAG_ALARMSTATE));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ToggleButton tb = (ToggleButton) rootView.findViewById(R.id.toggleButton);
        if (textViewAlarm.getText().equals("on")) {


            tb.setSelected(false);

        } else if (textViewAlarm.getText().equals("off")) {


            tb.setSelected(true);

        }


    }

    private void getEventData(String targetuser, String room) {
        //Creating a string request


        StringRequest stringRequest = new StringRequest(Config.DATA_ACTIVITYLOG_URL + "?user=" + username + "&sensorname=" + room,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);


                            JSONArray myRes = new JSONArray();


                            //Storing the Array of JSON String to our JSON Array
                            myRes = j.getJSONArray(Config.JSON_ARRAY);

                            //Calling method getsensors to get the sensors from the JSON Array
                            getEvents(myRes);
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

    private void getEvents(JSONArray j) {
        //Traversing through all the items in the json array


        int size = j.length();

        String[] myvalues = new String[size];


        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);


                myvalues[i] = json.getString(Config.TAG_DATETIME) + " - " + json.getString(Config.TAG_SENSORNAME) + " - " + json.getString(Config.TAG_ACTIVITY);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        if (j.length() > 0) {

            makeList(myvalues);
        } else {

            listView = (ListView) rootView.findViewById(R.id.listview);

            listView.setAdapter(null); //CLEAR LISTVIEW ITEMS

        }

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

        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                sensors.add(json.getString(Config.TAG_SENSORNAME));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, sensors));
    }


    //this method will execute when we pic an item from the spinner

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Setting the values to textviews for a selected item
        //textViewName.setText(getName(position));
        //textViewCourse.setText(getCourse(position));
        //textViewSession.setText(getSession(position));
        //textViewAlarm.setText(getAlarm(position));

        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
        location = spinner.getSelectedItem().toString();

        textViewName.setText(location);

        getSensorData(username, location);
        getEventData(username, location);
    }

    //When no item is selected this method would execute


    private void makeList(String[] values) {
        // Get ListView object from xml
        listView = (ListView) rootView.findViewById(R.id.listview);

        listView.setAdapter(null); //CLEAR LISTVIEW ITEMS

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);
    }


}








