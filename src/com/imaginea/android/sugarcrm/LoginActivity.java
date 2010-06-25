package com.imaginea.android.sugarcrm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.imaginea.android.sugarcrm.util.RestUtil;
import com.imaginea.android.sugarcrm.util.Util;

import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends Activity {

    public final static String LOG_TAG = "LoginActivity";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = "http://192.168.1.83/sugarcrm/service/v2/rest.php";
        String username = "will";
        String password = "will";
        String md5Password;
        try {
            md5Password = Util.MD5(password);
            String sessionId = RestUtil.loginToSugarCRM(url, username, md5Password);
            Log.i(LOG_TAG, "sessionId : " + sessionId);

            SugarCrmApp app = ((SugarCrmApp) getApplicationContext());
            app.setSessionId(sessionId);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.main);
    }

}
