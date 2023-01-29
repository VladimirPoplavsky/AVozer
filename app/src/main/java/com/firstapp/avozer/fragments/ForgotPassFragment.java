package com.firstapp.avozer.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForgotPassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForgotPassFragment extends Fragment {
    EditText mail;
    private FirebaseAuth mAuth1 = FirebaseAuth.getInstance();




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ForgotPassFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForgotPassFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForgotPassFragment newInstance(String param1, String param2) {
        ForgotPassFragment fragment = new ForgotPassFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Button sendMailButton;
        View view = inflater.inflate(R.layout.fragment_forgot_pass, container, false);
        mail = view.findViewById(R.id.editTextTextEmailAddress);
        sendMailButton = view.findViewById(R.id.resetPassButton);
        sendMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = mail.getText().toString();
                resetPass(userEmail);

            }
        });




        return  view;

    }
    public void resetPass(String userEmail){
        mAuth1.sendPasswordResetEmail(userEmail).addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getActivity(), "mail has been sent", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}