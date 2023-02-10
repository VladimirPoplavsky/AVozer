package com.firstapp.avozer.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firstapp.avozer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link changing_Password_In_User_Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class changing_Password_In_User_Profile extends Fragment {
    private FirebaseAuth mmAuth;
    EditText password;
    EditText newPass;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public changing_Password_In_User_Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment changing_Password_In_User_Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static changing_Password_In_User_Profile newInstance(String param1, String param2) {
        changing_Password_In_User_Profile fragment = new changing_Password_In_User_Profile();
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
        mmAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_changing__password__in__user__profile, container, false);
        password= view.findViewById(R.id.enterNewPass);
        newPass = view.findViewById(R.id.reEnterNewPass);
        Button button=view.findViewById(R.id.changePasswordButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userPass = password.getText().toString();
                String reUserPass= newPass.getText().toString();
                if (userPass.equals(reUserPass) == false){
                    Toast.makeText(getActivity(), "Password does not match", Toast.LENGTH_SHORT).show();

                }

                changePassword(userPass);


            }
        });






        return view;
    }


    public void changePassword(String newPassword) {
        FirebaseUser user = mmAuth.getCurrentUser();

        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("Change Password", "Password updated");
                            Toast.makeText(getActivity(), "Password updated", Toast.LENGTH_SHORT).show();



                        } else {
                            Log.d("Change Password", "Error password not updated");
                            Toast.makeText(getActivity(), "Error password did not changed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}