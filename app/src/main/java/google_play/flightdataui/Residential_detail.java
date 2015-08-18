package google_play.flightdataui;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 8/16/2015.
 */
public class Residential_detail extends Fragment implements View.OnClickListener {

    Spinner spinner1;
    EditText et_house_name, et_house_number, et_street, et_town_city, et_country, et_postal_code, et_time_at_address;
    String house_number, house_name, street, town_city, country, postal_code, time_at_address;
    Button b_next_residential_detail;
//String Log_Tag = getActivity().getClass().getSimpleName();
    String Log_Tag = "RESIDENTIALDETAILS";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_residential_detail, container, false);

//button
        b_next_residential_detail = (Button) rootView.findViewById(R.id.b_next_residential_detail);
        Bundle mybundle = getArguments();
        String first_name = mybundle.getString("NAME");
        String last_name = mybundle.getString("LASTNAME");
        Log.d(Log_Tag , ""+first_name);
        Log.d(Log_Tag,""+last_name);
//first text box 1
        et_house_name = (EditText) rootView.findViewById(R.id.et_house_name);
        house_name = et_house_name.getText().toString();

        et_house_number = (EditText) rootView.findViewById(R.id.et_house_number);
        house_number = et_house_number.getText().toString();

        et_street = (EditText) rootView.findViewById(R.id.et_street);
        street=et_street.getText().toString();

        et_town_city = (EditText) rootView.findViewById(R.id.et_town_city);
        town_city=et_town_city.getText().toString();

        et_country= (EditText) rootView.findViewById(R.id.et_country);
        country=et_country.getText().toString();

        et_postal_code= (EditText) rootView.findViewById(R.id.et_postal_code);
        postal_code=et_postal_code.getText().toString();

        et_time_at_address= (EditText) rootView.findViewById(R.id.et_time_at_address);
        time_at_address=et_time_at_address.getText().toString();

        b_next_residential_detail.setOnClickListener(this);
        // Making Spinner
                spinner1 = (Spinner) rootView.findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Home Owner");
        list.add("On Rent");
        list.add("Parent's Home");



        ArrayAdapter dataAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);


        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.b_next_residential_detail:
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Employement_details fragment_employement_detail = new Employement_details();
                ft.replace(R.id.mainContainer,fragment_employement_detail);
                ft.addToBackStack(null);
                ft.commit();
        }
    }
}
