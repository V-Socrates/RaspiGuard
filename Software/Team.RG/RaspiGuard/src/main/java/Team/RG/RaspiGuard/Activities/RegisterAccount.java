package Team.RG.RaspiGuard.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class RegisterAccount extends AppCompatActivity {


    public static String targetUser = "null";
    private final String serverUrl = "http://165.227.44.224/index.php"; //THIS IS OUR CLOUD SERVER
    protected EditText username;
    protected String enteredUsername;
    private EditText password;
    private EditText email;
    private EditText pin;

    public static String getTargetUser() {

        return targetUser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        username = (EditText) findViewById(R.id.editText_username);
        password = (EditText) findViewById(R.id.editText_password);
        email = (EditText) findViewById(R.id.editText_email);
        pin = (EditText) findViewById(R.id.editText_pin);


        //BUTTON REGISTER
        Button button_register = (Button) findViewById(R.id.button_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                targetUser = username.getText().toString();
                enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();
                String enteredEmail = email.getText().toString();
                String enteredPin = pin.getText().toString();

                if (enteredUsername.equals("") || enteredPassword.equals("") || enteredEmail.equals("")) {
                    Toast.makeText(RegisterAccount.this, "Username or password or email must be filled", Toast.LENGTH_LONG).show();
                    return;
                }
                if (enteredUsername.length() <= 1 || enteredPassword.length() <= 1) {
                    Toast.makeText(RegisterAccount.this, "Username or password length must be greater than one", Toast.LENGTH_LONG).show();
                    return;
                }
                // request authentication with remote server
                AsyncDataClass asyncRequestObject = new AsyncDataClass();
                asyncRequestObject.execute(serverUrl, enteredUsername, enteredPassword, enteredEmail, enteredPin);
                finish();


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
                nameValuePairs.add(new BasicNameValuePair("email", params[3]));
                nameValuePairs.add(new BasicNameValuePair("pin", params[4]));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpClient.execute(httpPost);
                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
                System.out.println("Returned Json object " + jsonResult.toString());

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
                Toast.makeText(RegisterAccount.this, "Server connection failed", Toast.LENGTH_LONG).show();
                return;
            }
            int jsonResult = returnParsedJsonObject(result);
            if (jsonResult == 0) {
                Toast.makeText(RegisterAccount.this, "Invalid username or password or email", Toast.LENGTH_LONG).show();
                return;
            }
            if (jsonResult == 1) {
                Intent intent = new Intent(RegisterAccount.this, MainActivity.class);
                intent.putExtra("USERNAME", enteredUsername);
                intent.putExtra("MESSAGE", "You have been successfully Registered");
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", enteredUsername);
                editor.apply();


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