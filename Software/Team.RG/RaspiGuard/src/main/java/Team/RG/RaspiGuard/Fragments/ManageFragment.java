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

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageFragment extends Fragment {


    public ManageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout button_appSettings = (LinearLayout) view.findViewById(R.id.button_appSettings);
        button_appSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainLayout, new AppSettingFragment());
                ft.commit();
            }
        });

        LinearLayout button_manageSensor = (LinearLayout) view.findViewById(R.id.button_manageSensors);
        button_manageSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainLayout, new ManageSensorFragment());
                ft.commit();
            }
        });

        LinearLayout button_manageAccount = (LinearLayout) view.findViewById(R.id.button_manageAccount);
        button_manageAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainLayout, new ManageAccountFragment());
                ft.commit();
            }
        });

    }

}
