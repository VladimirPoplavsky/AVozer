package com.firstapp.avozer.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.firstapp.avozer.Person;
import com.firstapp.avozer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // user data fields
    private EditText teudatZeut;
    private EditText firstName;
    private EditText lastName;
    private EditText city;
    private EditText email;
    private EditText phone;
    private EditText password;
    private MaterialButton register;
    private FirebaseAuth auth;


    //---------------------declaration for upload image------------------------
//    // views for button
//    private Button btnSelect, btnUpload;
//
//    // view for image view
//    private ImageView imageView;
//
//    // Uri indicates, where the image will be picked from
//    private Uri filePath;
//
//    // request code
//    private final int PICK_IMAGE_REQUEST = 22;
//
//    // instance for firebase storage and StorageReference
//    FirebaseStorage storage;
//    StorageReference storageReference;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FirebaseDatabase database;

    DatabaseReference myRef;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

//        // initialise views for image upload
//        btnSelect = view.findViewById(R.id.btnChoose);
//        btnUpload = view.findViewById(R.id.btnUpload);
//        imageView = view.findViewById(R.id.imgView);

        // user data fields
        teudatZeut = view.findViewById(R.id.teudat_zeut);
        firstName = view.findViewById(R.id.first_name_register_input);
        lastName = view.findViewById(R.id.last_name_register_input);
        city = view.findViewById(R.id.city_register_input);
        email = view.findViewById(R.id.emailReg);
        phone = view.findViewById(R.id.phone_register_input);
        password = view.findViewById(R.id.passwordReg);
        register = view.findViewById(R.id.registerBtn);

//        // get the Firebase  storage reference
//        storage = FirebaseStorage.getInstance();
//        storageReference = storage.getReference();


//        // on pressing btnSelect SelectImage() is called
//        btnSelect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                SelectImage();
//            }
//        });
//
//        // on pressing btnUpload uploadImage() is called
//        btnUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                uploadImage();
//            }
//        });

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();

                if(TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)){
                    Toast.makeText(getActivity(), "Empty Credentials", Toast.LENGTH_SHORT).show();
                }else if (txtPassword.length() < 6){
                    Toast.makeText(getActivity(), "Password To Short", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(txtEmail, txtPassword);
                }
            }
        });


        return view;
    }



    private void registerUser(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    //FirebaseUser user = auth.getCurrentUser();
                    String txtId, txtUid, txtFirstName, txtLastName, txtCity, txtPhone;
                    txtId = teudatZeut.getText().toString();

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    txtUid = user.getUid();


                    txtFirstName = firstName.getText().toString();
                    txtLastName = lastName.getText().toString();
                    txtCity = city.getText().toString();
                    txtPhone = phone.getText().toString();
                    
                    writeDB(txtId, txtUid, txtFirstName, txtLastName, email, txtPhone, txtCity);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(getActivity(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//
//    public void readDB() {
//        // Read from the database
//
//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("users").child("0000001");
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                Person value = dataSnapshot.getValue(Person.class);
//
//                Toast.makeText(getActivity(), value.name, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Toast.makeText(getActivity(), "Oops, it is some error", Toast.LENGTH_LONG).show();
//            }
//        });
//    }


    public void writeDB(String id, String uid, String firstName, String lastName, String email, String phone, String city) {
        // Write a message to the database        
        
        Person p = new Person(id, uid, firstName, lastName, email, phone, city);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users").child(p.teudat_zeut);

        myRef.setValue(p);
    }


//    // Select Image method
//    private void SelectImage()  {
//
//        // Defining Implicit Intent to mobile gallery
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(
//                Intent.createChooser(
//                        intent,
//                        "Select Image from here..."),
//                PICK_IMAGE_REQUEST);
//    }

//    // UploadImage method
//    private void uploadImage()  {
//        if (filePath != null) {
//
//            // Code for showing progressDialog while uploading
//            ProgressDialog progressDialog = new ProgressDialog(getContext());
//            progressDialog.setTitle("Uploading...");
//            progressDialog.show();
//
//            // Defining the child of storageReference
//            StorageReference ref
//                    = storageReference
//                    .child(
//                            "images/"
//                                    + UUID.randomUUID().toString());
//
//            // adding listeners on upload
//            // or failure of image
//            ref.putFile(filePath)
//                    .addOnSuccessListener(
//                            new OnSuccessListener<UploadTask.TaskSnapshot>() {
//
//                                @Override
//                                public void onSuccess(
//                                        UploadTask.TaskSnapshot taskSnapshot)
//                                {
//
//                                    // Image uploaded successfully
//                                    // Dismiss dialog
//                                    progressDialog.dismiss();
//                                    Toast.makeText(getActivity(), "Image Uploaded!", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e)
//                        {
//
//                            // Error, Image not uploaded
//                            progressDialog.dismiss();
//                            Toast
//                                    .makeText(getActivity(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnProgressListener(
//                            new OnProgressListener<UploadTask.TaskSnapshot>() {
//
//                                // Progress Listener for loading
//                                // percentage on the dialog box
//                                @Override
//                                public void onProgress(
//                                        UploadTask.TaskSnapshot taskSnapshot)
//                                {
//                                    double progress
//                                            = (100.0
//                                            * taskSnapshot.getBytesTransferred()
//                                            / taskSnapshot.getTotalByteCount());
//                                    progressDialog.setMessage(
//                                            "Uploaded "
//                                                    + (int)progress + "%");
//                                }
//                            });
//        }
//    }

}


