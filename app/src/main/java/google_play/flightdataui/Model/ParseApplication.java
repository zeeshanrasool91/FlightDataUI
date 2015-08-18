package google_play.flightdataui.Model;

/**
 * Created by Hamza khan on 8/15/2015.
 */

import android.app.Application;


import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Customer.class);
        Parse.enableLocalDatastore(getApplicationContext());
        // Add your initialization code here
        Parse.initialize(this, "fuFUYaCkbFVyre1G7BhI8KNO7wQpxQwluu5ujYBe", "AbGNmeuiEBSVUkujKBCWZZRmgon12HZQxGQClFRC");


        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);

    }

}