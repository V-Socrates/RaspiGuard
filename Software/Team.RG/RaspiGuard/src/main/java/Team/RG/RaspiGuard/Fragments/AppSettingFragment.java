package Team.RG.RaspiGuard.Fragments;


import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;

import Team.RG.RaspiGuard.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppSettingFragment extends Fragment {

    final MediaPlayer mp = new MediaPlayer();
    View rootView;
    String alarmtone;
    String username;

    public AppSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_app_setting, container, false);
        // Inflate the layout for this fragment
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        username = preferences.getString("username", "");

        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.myRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId) {

                    case R.id.radioButton2:
                        // do something
                        //Toast.makeText(getApplicationContext(), "choice: radioButton2", Toast.LENGTH_SHORT).show();
                        playAudio(2);
                        alarmtone = "2.mp3";
                        break;


                    case R.id.radioButton5:
                        // do something
                        //Toast.makeText(getApplicationContext(), "choice: radioButton4", Toast.LENGTH_SHORT).show();
                        playAudio(5);
                        alarmtone = "5.mp3";
                        break;

                    case R.id.radioButton6:
                        // do something
                        //Toast.makeText(getApplicationContext(), "choice: radioButton4", Toast.LENGTH_SHORT).show();
                        playAudio(6);
                        alarmtone = "6.mp3";
                        break;

                    case R.id.radioButton7:
                        // do something
                        //Toast.makeText(getApplicationContext(), "choice: radioButton4", Toast.LENGTH_SHORT).show();
                        playAudio(7);
                        alarmtone = "7.mp3";
                        break;

                    case R.id.radioButton8:
                        // do something
                        //Toast.makeText(getApplicationContext(), "choice: radioButton4", Toast.LENGTH_SHORT).show();
                        playAudio(8);
                        alarmtone = "8.mp3";
                        break;


                    case R.id.radioButton10:
                        // do something
                        //Toast.makeText(getApplicationContext(), "choice: radioButton4", Toast.LENGTH_SHORT).show();
                        if (mp.isPlaying()) {
                            mp.stop();
                        }
                        break;


                }
            }
        });

        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //first check if provided passwords match


                //next check if current password is correct
                StringRequest stringRequest = new StringRequest("http://165.227.44.224/updateAlarmTone.php?user=" + username + "&alarmtone=" + alarmtone,
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

                if (mp.isPlaying()) {
                    mp.stop();
                }
            }
        });


        return rootView;
    }

    public void playAudio(int clipNR) {


        if (mp.isPlaying()) {
            mp.stop();
        }


        try {

            mp.reset();
            AssetFileDescriptor afd;
            afd = getContext().getAssets().openFd(clipNR + ".mp3");
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}





