package Team.RG.RaspiGuard.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import Team.RG.RaspiGuard.R;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //   final String username;

        LinearLayout button_doorSensor = (LinearLayout) view.findViewById(R.id.button_doorSensor);
        button_doorSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainLayout, new DoorFragment());
                ft.commit();
            }
        });

        LinearLayout button_moistureSensor = (LinearLayout) view.findViewById(R.id.button_moistureSensor);
        button_moistureSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainLayout, new MoistureFragment());
                ft.commit();
            }
        });

//        LinearLayout button_manageSensors = (LinearLayout ) view.findViewById(R.id.button_manageSensors);
//        button_manageSensors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Settings.class);
//                startActivity(intent);
//            }
//        });
//
//        LinearLayout button_manageAccount = (LinearLayout ) view.findViewById(R.id.button_manageAccount);
//        button_manageAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Settings.class);
//                startActivity(intent);
//            }
//        });

    }

}
