package google_play.flightdataui;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URI;


public class Dashboard_activity extends ActionBarActivity {
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_activity);
        SharedPreferences sp = getSharedPreferences("FACEBOOK", Activity.MODE_PRIVATE);
         userId = sp.getString("ID","QASIM");
         ImageView imageView = (ImageView) findViewById(R.id.img_person_action_bar);
        ActionBar actionbar = getSupportActionBar();
        try {
            GetActionBarDetails();// Method for inflating ActionBar
        } catch (IOException e) {
            e.printStackTrace();
        }
        Dashboard_Fragment_main();

    }


    private void GetActionBarDetails() throws IOException {

        RelativeLayout custom_RelativeLayout = (RelativeLayout) findViewById(R.id.linear_custom);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar_layout);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setBackgroundDrawable(
                getApplicationContext().getResources().getDrawable(
                        R.drawable.drawable_actionbar_back));
        getSupportActionBar().setCustomView(R.layout.custom_actionbar_layout);
        ImageView imageView = (ImageView) findViewById(R.id.img_person_action_bar);

        Picasso.with(this).load("https://graph.facebook.com/" + userId+ "/picture?type=large").into(imageView);

       /* Picasso.with(this).load("https://graph.facebook.com/" + userId+ "/picture?type=large").into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
*/
     //   Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.nafeeesahmed);

    }

    public void Dashboard_Fragment_main()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.dashboard_container, new DashboardFragment());

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
}
