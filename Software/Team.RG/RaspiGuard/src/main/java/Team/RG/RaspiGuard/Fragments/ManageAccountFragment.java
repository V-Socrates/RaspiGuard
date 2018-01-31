package Team.RG.RaspiGuard.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import Team.RG.RaspiGuard.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageAccountFragment extends Fragment {

    View rootView;
    Button button;
    String username;
    String curpw;
    String newpw;
    String newpw2;
    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    EditText et5;
    EditText et6;
    String curpin;
    String newpin;
    String newpin2;

    //Declaring an Spinner
    public Spinner spinner;

    //An ArrayList for Spinner Items
    private ArrayList<String> sensors;

    public ManageAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_manage_account, container, false);
        // Inflate the layout for this fragment

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        username = preferences.getString("username", "");

        et1 = (EditText) rootView.findViewById(R.id.editText);
        et2 = (EditText) rootView.findViewById(R.id.editText2);
        et3 = (EditText) rootView.findViewById(R.id.editText3);
        et4 = (EditText) rootView.findViewById(R.id.editText4);
        et5 = (EditText) rootView.findViewById(R.id.editText5);
        et6 = (EditText) rootView.findViewById(R.id.editText6);

        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //first check if provided passwords match

                curpw = et1.getText().toString();
                newpw = et2.getText().toString();
                newpw2 = et3.getText().toString();

                if (newpw.equals(newpw2)){

                    //next check if current password is correct
                    StringRequest stringRequest = new StringRequest("http://165.227.44.224/changepw.php?user="+username+"&password="+curpw+"&newpass="+newpw,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();


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

                    String clr="";
                    et1.setText(clr);
                    et2.setText(clr);
                    et3.setText(clr);

                    Toast.makeText(getContext(),"Password changed!",Toast.LENGTH_SHORT).show();

                    ;
                } else Toast.makeText(getContext(),"New password doesnt match",Toast.LENGTH_SHORT).show();


            }
        });


        Button button2 = (Button) rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //first check if provided passwords match

                curpin = et4.getText().toString();
                newpin = et5.getText().toString();
                newpin2 = et6.getText().toString();

                if (newpin.equals(newpin2)){

                    //next check if current password is correct
                    StringRequest stringRequest = new StringRequest("http://165.227.44.224/changepin.php?user="+username+"&pin="+curpin+"&newpin="+newpin,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();


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

                    String clr="";
                    et4.setText(clr);
                    et5.setText(clr);
                    et6.setText(clr);

                    Toast.makeText(getContext(),"Pin changed!",Toast.LENGTH_SHORT).show();

                    ;
                } else Toast.makeText(getContext(),"New password doesnt match",Toast.LENGTH_SHORT).show();


            }
        });



        return rootView;
    }

}
