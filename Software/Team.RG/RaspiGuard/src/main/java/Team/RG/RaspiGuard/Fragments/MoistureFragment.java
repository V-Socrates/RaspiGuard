package Team.RG.RaspiGuard.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import Team.RG.RaspiGuard.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import Team.RG.RaspiGuard.SupportClasses.SpinnerAdapter;

public class MoistureFragment extends Fragment {

    //XML ELEMENTS
    Spinner MSSensorList;
    TextView MSSensorName;
    TextView MSLocated;
    TextView MSStatus;
    TextView MSAlarm;
    ToggleButton MSAlarmSwitch;
    ListView MSActivityLog;

    //SENSOR FIELDS
    String USER;

    //OBJECT VARIABLES
    JSONArray msAllArray;
    ArrayList<String> mSensorNames;
    ArrayList<String> mSensorLogEntries;

    //SPINNER
    SpinnerAdapter spinAdapter;
    int savedPosition;

    //LISTVIEW
    ArrayAdapter listAdapter;

    //String Request
    String url;
    StringRequest request;
    RequestQueue queue;

    //Preferences
    SharedPreferences sp;
    SharedPreferences.Editor spe;


    public MoistureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_moisture, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //GETTING NESSIARY VARIABLES
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        USER = sp.getString(getString(R.string.currentUserKey), getString(R.string.Empty));
        savedPosition = sp.getInt(getString(R.string.msSpinPositionSave), -1);

        //Setting XML Elements
        MSSensorList = getView().findViewById(R.id.msSpinner);
        MSAlarm = getView().findViewById(R.id.msAlarm);
        MSAlarmSwitch = getView().findViewById(R.id.msAlarmSwitch);

        MSAlarmSwitch.setEnabled(false);

        //SETTING SPINNER ITEMS
        getMoistureList(USER, savedPosition);

        //SETTING SPINNER CLICK FUNCTION
        MSSensorList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position < MSSensorList.getCount()) {

                    MSAlarmSwitch.setEnabled(true);

                    getMSensorInfo(MSSensorList.getSelectedItem().toString());
                    getMSensorLog(MSSensorList.getSelectedItem().toString());

                    //SAVING SPINNER POSITION
                    sp = PreferenceManager.getDefaultSharedPreferences(getContext());
                    spe = sp.edit().putInt(getString(R.string.msSpinPositionSave), position);
                    spe.commit();
                }
            }@Override public void onNothingSelected(AdapterView<?> parent) {}});

        //SETTING ALARM TOGGLE BUTTON FUNCTION
        MSAlarmSwitch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(MSAlarmSwitch.isChecked()) {

                    MSAlarm.setText(R.string.AlarmStateOn);
                    setMoistureAlarm(USER, MSSensorList.getSelectedItem().toString(), getString(R.string.AlarmStateOn));
                }

                else if (!MSAlarmSwitch.isChecked()) {

                    MSAlarm.setText(R.string.AlarmStateOff);
                    setMoistureAlarm(USER, MSSensorList.getSelectedItem().toString(), getString(R.string.AlarmStateOff));
                }
            }
        });
    }

    public void getMoistureList(final String USER, final int savedPosition) {

        url = new String(getString(R.string.mGetAllURL) + getString(R.string.UsernamePHP) + USER);

        request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if (response.length() > 13) {

                    try {
                        mSensorNames = new ArrayList<String>();
                        msAllArray = new JSONObject(response).getJSONArray(getString(R.string.JSONKey));

                        for (int i = 0; i < msAllArray.length(); i++) {

                            mSensorNames.add(msAllArray.getJSONObject(i).getString(getString(R.string.jKeySensorName)));
                        }

                        //Setting Spinner Items
                        MSSensorList = getView().findViewById(R.id.msSpinner);

                        spinAdapter = new SpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item);
                        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinAdapter.addAll(mSensorNames);
                        spinAdapter.add(getString(R.string.spinnerHint));
                        MSSensorList.setAdapter(spinAdapter);

                        if (savedPosition != -1) {

                            MSSensorList.setSelection(savedPosition);
                        }
                        else {

                            MSSensorList.setSelection(spinAdapter.getCount());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {

                    Toast.makeText(getActivity(), getString(R.string.mNoSensorsFound) + USER, Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, new HomeFragment()).commitNowAllowingStateLoss();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), getString(R.string.mErrorFindingSensors) + USER, Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, new HomeFragment()).commitNowAllowingStateLoss();
            }
        });

        queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }

    public void getMSensorInfo(final String sensorName) {

        url = new String(getString(R.string.mGetAllURL) + getString(R.string.UsernamePHP) + USER + getString(R.string.AND) + getString(R.string.SensorNamePHP) + sensorName);
        request = new StringRequest(Request.Method.GET, getString(R.string.mGetAllURL) + getString(R.string.UsernamePHP) + USER + getString(R.string.AND) + getString(R.string.SensorNamePHP) + sensorName, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if (response.length() > 13) {

                    try {

                        MSSensorName = getView().findViewById(R.id.msSensorName);
                        MSLocated = getView().findViewById(R.id.msLocation);
                        MSStatus = getView().findViewById(R.id.msStatus);
                        MSAlarm = getView().findViewById(R.id.msAlarm);
                        MSAlarmSwitch = getView().findViewById(R.id.msAlarmSwitch);

                        msAllArray = new JSONObject(response).getJSONArray(getString(R.string.JSONKey));

                        MSSensorName.setText(msAllArray.getJSONObject(0).getString(getString(R.string.jKeySensorName)));
                        MSLocated.setText(msAllArray.getJSONObject(0).getString(getString(R.string.jKeyLocation)));
                        MSStatus.setText(msAllArray.getJSONObject(0).getString(getString(R.string.jKeyStatus)));
                        MSAlarm.setText(msAllArray.getJSONObject(0).getString(getString(R.string.jKeyAlarmState)));

                        if (MSAlarm.getText().toString().toLowerCase().equals(getString(R.string.AlarmStateOn).toLowerCase())) {

                            MSAlarmSwitch.setChecked(true);
                        }
                        else if (MSAlarm.getText().toString().toLowerCase().equals(getString(R.string.AlarmStateOff).toLowerCase())) {

                            MSAlarmSwitch.setChecked(false);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {

                    Toast.makeText(getActivity(), getString(R.string.mNoDataFound) + sensorName, Toast.LENGTH_SHORT).show();
                }
            }}, new Response.ErrorListener(){@Override public void onErrorResponse(VolleyError error) {Toast.makeText(getActivity(), getString(R.string.mNoDataFound), Toast.LENGTH_SHORT).show();}});

        queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }

    public void getMSensorLog(final String sensorName) {

        url = new String(getString(R.string.mGetLogURL) + getString(R.string.UsernamePHP) + USER + getString(R.string.AND) + getString(R.string.SensorNamePHP) + sensorName);
        request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if (response.length() > 13) {

                    try {

                        MSActivityLog = getView().findViewById(R.id.msActivityLog);
                        MSActivityLog.setAdapter(null);
                        mSensorLogEntries = new ArrayList<String>();
                        msAllArray = new JSONObject(response).getJSONArray(getString(R.string.JSONKey));

                        for (int i = 0; i < msAllArray.length(); i++) {

                            mSensorLogEntries.add(msAllArray.getJSONObject(i).getString(getString(R.string.jKeyDateTime)) + getString(R.string.LogEntryDivider) + msAllArray.getJSONObject(i).getString(getString(R.string.jKeySensorName)) + getString(R.string.LogEntryDivider) + msAllArray.getJSONObject(i).getString(getString(R.string.jKeyActivity)));
                        }

                        if (mSensorLogEntries.size() > 0) {

                            listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mSensorLogEntries);
                            MSActivityLog.setAdapter(listAdapter);
                            MSActivityLog.setClickable(false);
                        }
                        else {

                            MSActivityLog.setAdapter(null);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {

                    MSActivityLog = getView().findViewById(R.id.msActivityLog);
                    MSActivityLog.setAdapter(null);
                    Toast.makeText(getActivity(), getString(R.string.mNoLogFound) + sensorName, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {@Override public void onErrorResponse(VolleyError error) {Toast.makeText(getActivity(), getString(R.string.mNoLogFound) + sensorName, Toast.LENGTH_SHORT).show();}});

        queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }

    private void setMoistureAlarm(String USER, String SensorName, String AlarmState) {

        url = new String(getString(R.string.mSetAlarmURL) + getString(R.string.UsernamePHP) + USER + getString(R.string.AND)+ getString(R.string.AlarmStatePHP) + AlarmState + getString(R.string.AND)+ getString(R.string.SensorNamePHP) + SensorName);
        request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {@Override public void onResponse(String response) {}}, new Response.ErrorListener() {@Override public void onErrorResponse(VolleyError error) {}});
        queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }
}
