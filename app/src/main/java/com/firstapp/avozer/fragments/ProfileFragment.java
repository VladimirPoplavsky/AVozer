package com.firstapp.avozer.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.firstapp.avozer.Deal;
import com.firstapp.avozer.Person;
import com.firstapp.avozer.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        NavigationView navigationView = view.findViewById(R.id.navigation_view);
        MaterialToolbar toolbar = view.findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = view.findViewById(R.id.drawer_layout);


        // --------------------------------------------------------------------

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {

                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {

                    case R.id.nav_home:
                        Toast.makeText(getActivity(), "Home is Clicked", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_homeyFragmentage);
                        break;
                    case R.id.nav_message:
                        Toast.makeText(getActivity(), "Message is Clicked",Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_messageFragment);
                        break;


                    case R.id.settings:
                        Toast.makeText(getActivity(), "loadinG",Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_settingssFragmento);
                        break;
                    case R.id.nav_login:
                        Toast.makeText(getActivity(), "loadin2g",Toast.LENGTH_SHORT).show();break;


                    default:
                        return true;

                }
                return true;
            }
        });


        // User press button "Want to help" or "Need help"
        Button needHelp = view.findViewById(R.id.need_help_button);
        Button wantToHelp = view.findViewById(R.id.want_help_button);

        needHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_placeRequestFormFragment);
//
//                // test
//                FirebaseDatabase database;
//
//                DatabaseReference myRef;
//
//                 Deal d = new Deal("000000001", "SomeCity", false, false, "some comments");
//
//
//                database = FirebaseDatabase.getInstance();
//                myRef = database.getReference("deals").child(d.dealID);
//
//                myRef.setValue(d);

            }
        });

        wantToHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_findRequestFormFragment);
            }
        });




        return view;

    }

    private void replaceFragment(Fragment fragment){

    }

}