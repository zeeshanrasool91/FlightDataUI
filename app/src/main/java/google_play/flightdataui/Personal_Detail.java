package google_play.flightdataui;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Personal_Detail extends Fragment implements View.OnClickListener {

   Button b_next;
   EditText et_first_name,et_last_name,et_date_of_birth,et_email_address,et_home_phone,et_mobile_phone;
   String first_name,last_name,date_of_birth,email_address,home_phone,mobile_phone;

    Spinner spinner1;
    public Personal_Detail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_personal__detail, container, false);

        // Inflate the layout for this fragment

        b_next = (Button) rootView.findViewById(R.id.b_next);


        Bundle newbundle = this.getArguments();
      //String name =  newbundle.getString("name");

SharedPreferences sp = getActivity().getSharedPreferences("FACEBOOK", Activity.MODE_PRIVATE);
        //edit text box's Start from here
        et_first_name = (EditText) rootView.findViewById(R.id.et_firstname);
        et_first_name.setText(""+sp.getString("Name","Default"));
       first_name = et_first_name.getText().toString();

       // if(name != null)
       // et_first_name.setText(name);

        et_last_name = (EditText) rootView.findViewById(R.id.et_last_name);
        last_name = et_last_name.getText().toString();

        et_date_of_birth = (EditText) rootView.findViewById(R.id.et_date_of_birth);
        date_of_birth = et_date_of_birth.getText().toString();

        et_email_address = (EditText) rootView.findViewById(R.id.et_email_address);
        et_email_address.setText(""+sp.getString("EMAIL","default@com"));
        email_address = et_email_address.getText().toString();

        et_home_phone = (EditText) rootView.findViewById(R.id.et_home_phone);
        home_phone = et_home_phone.getText().toString();

        et_mobile_phone = (EditText) rootView.findViewById(R.id.et_mobile_phone);
        mobile_phone = et_mobile_phone.getText().toString();

        b_next.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.b_next :

                if(first_name.length() > 0 && last_name.length()>0) {

                    Bundle myBundle = new Bundle();
                    myBundle.putString("NAME",first_name);
                    myBundle.putString("LASTNAME",last_name);
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Residential_detail fragment_residential_detail = new Residential_detail();
                    fragment_residential_detail.setArguments(myBundle);
                    ft.replace(R.id.mainContainer, fragment_residential_detail);
                    ft.addToBackStack(null);
                    ft.commit();
                }
        }
    }
}
