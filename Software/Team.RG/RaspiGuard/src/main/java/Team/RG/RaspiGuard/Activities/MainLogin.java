package Team.RG.RaspiGuard.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Team.RG.RaspiGuard.R;

public class MainLogin extends AppCompatActivity {

    private final String serverUrl = "http://165.227.44.224/index.php"; //THIS IS OUR CLOUD SERVER
    public String enteredUsername;
    public String pincode;
    protected EditText username;
    Button pinButton;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);


        username = (EditText) findViewById(R.id.editText_username);
        password = (EditText) findViewById(R.id.editText_password);
        Button loginButton = (Button) findViewById(R.id.button_login);
        Button registerButton = (Button) findViewById(R.id.button_register);
        pinButton = (Button) findViewById(R.id.button_pin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();

                if (enteredUsername.equals("") || enteredPassword.equals("")) {
                    Toast.makeText(MainLogin.this, "Username or password must be filled", Toast.LENGTH_LONG).show();
                    return;
                }
                if (enteredUsername.length() <= 1 || enteredPassword.length() <= 1) {
                    Toast.makeText(MainLogin.this, "Username or password length must be greater than one", Toast.LENGTH_LONG).show();
                    return;
                }


                // request authentication with remote server4
                AsyncDataClass asyncRequestObject = new AsyncDataClass();
                asyncRequestObject.execute(serverUrl, enteredUsername, enteredPassword);


            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLogin.this, RegisterAccount.class);
                startActivity(intent);
            }
        });

        pinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();


                //next check if current password is correct
                StringRequest stringRequest = new StringRequest("http://165.227.44.224/pinAuth.php?user=" + enteredUsername + "&pin=" + enteredPassword,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                if (response.equals("Success")) {

                                    Intent intent = new Intent(MainLogin.this, MainActivity.class);
                                    intent.putExtra("USERNAME", enteredUsername);

                                    startActivity(intent);


                                } else
                                    Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

                //Creating a request queue
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                //Adding request to the queue
                requestQueue.add(stringRequest);


            }
        });


    }

    private int returnParsedJsonObject(String result) {

        JSONObject resultObject = null;
        int returnedResult = 0;
        try {
            resultObject = new JSONObject(result);
            returnedResult = resultObject.getInt("success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnedResult;
    }

    private class AsyncDataClass extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
            HttpConnectionParams.setSoTimeout(httpParameters, 5000);

            HttpClient httpClient = new DefaultHttpClient(httpParameters);
            HttpPost httpPost = new HttpPost(params[0]);

            String jsonResult = "";
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("username", params[1]));
                nameValuePairs.add(new BasicNameValuePair("password", params[2]));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpClient.execute(httpPost);
                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResult;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("Resulted Value: " + result);
            if (result.equals("") || result == null) {
                Toast.makeText(MainLogin.this, "Server connection failed", Toast.LENGTH_LONG).show();
                return;
            }
            int jsonResult = returnParsedJsonObject(result);
            if (jsonResult == 0) {
                Toast.makeText(MainLogin.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                return;
            }
            if (jsonResult == 1) {
                Intent intent = new Intent(MainLogin.this, MainActivity.class);
                intent.putExtra("USERNAME", enteredUsername);

                startActivity(intent);
            }
        }

        private StringBuilder inputStreamToString(InputStream is) {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            try {
                while ((rLine = br.readLine()) != null) {
                    answer.append(rLine);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return answer;
        }
    }
}