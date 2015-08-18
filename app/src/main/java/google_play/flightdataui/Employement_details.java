package google_play.flightdataui;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Employement_details extends Fragment implements View.OnClickListener {

  Button b_employment;
    public Employement_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_employement_details, container, false);
        b_employment = (Button) rootView.findViewById(R.id.b_next_employement_detail);
        b_employment.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.b_next_employement_detail :
                Intent mintent = new Intent(getActivity(),Dashboard_activity.class);
                startActivity(mintent);
        }

    }
}
