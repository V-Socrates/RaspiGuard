package Team.RG.RaspiGuard.SupportClasses;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import Team.RG.RaspiGuard.Activities.RegisterAccount;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by user on 13/01/2018.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {


    public String targetUser;


    @Override
    public void onTokenRefresh() {


        String token = FirebaseInstanceId.getInstance().getToken();

        registerToken(token, getTargetUser());


    }

    public String getTargetUser() {


        targetUser = RegisterAccount.getTargetUser();

        return targetUser;
    }

    private void registerToken(String token, String username) {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token", token)
                .build();

        Request request = new Request.Builder()
                .url("http://165.227.44.224/register2.php?User=" + username)
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
