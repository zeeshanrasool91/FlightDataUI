package google_play.flightdataui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class MainFragment extends Fragment implements View.OnClickListener {


    TextView tv_facebook;
    LinearLayout linearLayout;
    CallbackManager callbackManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        showHashKey(getActivity());
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        tv_facebook = (TextView) rootview.findViewById(R.id.tv_facebook);
        Typeface arialblack_typeface = Typeface.createFromAsset(getActivity().getAssets(),
                "arial_black.ttf");
        tv_facebook.setTypeface(arialblack_typeface);
        tv_facebook.setTextSize(40);
        linearLayout = (LinearLayout) rootview.findViewById(R.id.inear_Layout);
        linearLayout.setOnClickListener(this);
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // login successful
                        Toast.makeText(getActivity(), " Main Activity in LoginResult on success", Toast.LENGTH_LONG).show();

                      //  final AccessToken accessToken = loginResult.getAccessToken();

                        GraphRequest request = GraphRequest.newMeRequest
                                (loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        // Application code
                                        Log.v("MAinFrgment", response.toString());
                                        System.out.println("Check: " + response.toString());
                                        try {
                                            String id = object.getString("id");
                                            String name = object.getString("name");
                                            String email = object.getString("email");
                                            String gender = object.getString("gender");
                                            String birthday = object.getString("birthday");
                                            System.out.println(id + ", " + name + ", " + email + ", " + gender + ", " + birthday);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender, birthday");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        // login cancelled
                        Log.e("OnCancel ","Oncancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // login error
                        Log.e("OnCancel ","Error" +exception);
                    }
                });
        return rootview;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inear_Layout:
                LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("public_profile", "user_friends","user_birthday","user_about_me","email","basic_info"));

               /* FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Personal_Detail personal_detail = new Personal_Detail();

                ft.replace(R.id.mainContainer,personal_detail);
                ft.addToBackStack(null);
                ft.commit();*/

                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public static void showHashKey(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    "google_play.flightdataui", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("KeyHash:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
                // String abc = Base64.encodeToString(md.digest(),
                // Base64.DEFAULT);
                // System.out.println(abc);
            }
        } catch (NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }

}
