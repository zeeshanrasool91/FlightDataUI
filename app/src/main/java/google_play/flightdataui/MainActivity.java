package google_play.flightdataui;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;


public class MainActivity extends AppCompatActivity  {
    AccessToken    accessToken;
    CallbackManager   callbackManager;
    String email,name,ID;
    Bundle mybundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        Login_With_FaceBook();
        // Here is FACEBOOK ID
        Log.d("Client ID", "" + ID);
        SharedPreferences sp =getSharedPreferences("FACEBOOK",Activity.MODE_PRIVATE);
        String id_fb = sp.getString("ID",null);
        if( id_fb == null)
            HomeFragment();
        else
        {
            Personal_Detail_Fragment();
        }






    }  // End ONCREATE()




    public void HomeFragment()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MainFragment homefragment = new MainFragment();
        ft.replace(R.id.mainContainer, homefragment);

        ft.commit();
    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public  void Login_With_FaceBook()
    {
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };

        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();
        Log.d("MAIN ACTIVITY ACCESS", "" + accessToken);

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // login successful

                        Toast.makeText(getApplicationContext(), "Login Suucessfull", Toast.LENGTH_LONG).show();

                        final AccessToken accessToken = loginResult.getAccessToken();
                        if (accessToken != null) {
                            final FrameLayout frameLayout = (FrameLayout) findViewById(R.id.mainContainer);
                            frameLayout.setVisibility(View.GONE);
                            Log.d("Access Token", "" + accessToken);
                            ID = accessToken.getUserId();
                            GraphRequest request = GraphRequest.newMeRequest
                                    (loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(JSONObject object, GraphResponse response) {
                                            // Application code
                                            JSONObject obj = null;
                                            Log.v("MAinActivity", response.toString());
                                            try {
                                                obj = new JSONObject(response.toString());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            System.out.println("Check: " + response.toString());
                                            System.out.println("JSONOBJECT" + "" + object.toString());
                                            try {
                                                //  JSONObject     details = obj.getJSONObject("graphObject");
                                                if (object.getString("id") != null) {
                                                    name = object.getString("name");
                                                    email = object.getString("email");
                                                    ID = object.getString("id");
                                                    SharedPreferences sp = getSharedPreferences("FACEBOOK", Activity.MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = sp.edit();
                                                    editor.putString("Name", name);
                                                    editor.putString("ID", ID);
                                                    editor.putString("EMAIL", email);
                                                    editor.commit();
                                                    Log.d("FaceBookMainActivity", "" + name);
                                                    Log.d("FaceBookMainActivity", "" + email);

                                                    Personal_Detail_Fragment();
                                                    Log.d("Facebook ID", ID);
                                                    Log.d("FaceBook Name", "" + name);
                                                    Log.d("FaceBook Email ", "" + email);
                                                    frameLayout.setVisibility(View.VISIBLE);
                                                }


                                               /* mybundle.putString("id",id);
                                                mybundle.putString("name",name);
                                                mybundle.putString("email",email);*/


                       /* name = object.getString("name");
                        if (object.getString("email") != null);
                        email = object.getString("email");
                        String gender = object.getString("gender");
                        String birthday = object.getString("birthday");
                        System.out.println(id + ", " + name + ", " + email + ", " + gender + ", " + birthday);*/
//                        mybundle.putString("name",name);
                                                //mybundle.putString("id",id);
                                                // Log.d("name_fb",name);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }

                                    });
                            Log.d("Client ID ", ID);
                            Bundle parameters = new Bundle();
                            parameters.putString("fields", "id,name,email,gender, birthday");
                            request.setParameters(parameters);
                            request.executeAsync();


                        } else {
                            HomeFragment();
                        }
                    }

                    @Override
                    public void onCancel() {
                        // login cancelled
                        Log.e("OnCancel ", "Oncancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // login error
                        Log.e("OnCancel ", "Error" + exception);
                        if (exception instanceof FacebookAuthorizationException) {
                            if (AccessToken.getCurrentAccessToken() != null) {
                                LoginManager.getInstance().logOut();
                            }
                        }
                    }
                });
    }

    public void Personal_Detail_Fragment()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Personal_Detail personal_detail = new Personal_Detail();

        ft.replace(R.id.mainContainer, personal_detail);
        ft.addToBackStack(null);
        ft.commit();
    }

}
