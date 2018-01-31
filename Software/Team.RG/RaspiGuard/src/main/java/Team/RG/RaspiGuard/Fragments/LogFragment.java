package Team.RG.RaspiGuard.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

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
public class LogFragment extends Fragment {

    View rootView;
    ListView listView;
    String[] values = new String[15];

    //Declaring an Spinner
    private Spinner spinner;

    //An ArrayList for Spinner Items
    private ArrayList<String> students;

    //JSON Array
    private JSONArray result;


    public LogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_log, container, false);
        // Inflate the layout for this fragment

        getData();


        return rootView;
    }

    private void getData() {
        //Creating a string request

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String username = preferences.getString("username", "");

        StringRequest stringRequest = new StringRequest(Config.DATA_ACTIVITYLOG_URL + "?user=" + username,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(Config.JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getEvents(result);
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
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);


                values[i] = json.getString(Config.TAG_DATETIME) + " - " + json.getString(Config.TAG_SENSORNAME) + " - " + json.getString(Config.TAG_ACTIVITY);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (j.length() < 15) {

            for (int k = j.length(); k < 15; k++) {

                values[k] = "";


            }


        }


        makeList(values);
    }


    private void makeList(String[] values) {
        // Get ListView object from xml
        listView = (ListView) rootView.findViewById(R.id.listview);


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

