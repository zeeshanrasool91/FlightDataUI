package google_play.flightdataui;


import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DashboardFragment extends Fragment implements View.OnClickListener {


    public DashboardFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

View  rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.dashboard_activity_manage_account);
        relativeLayout.setOnClickListener(this);

        return rootView;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.dashboard_activity_manage_account  :
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

             ft.replace(R.id.dashboard_container,new Manage_account());
                ft.addToBackStack(null);
                ft.commit();
                break;
        }

    }
}
