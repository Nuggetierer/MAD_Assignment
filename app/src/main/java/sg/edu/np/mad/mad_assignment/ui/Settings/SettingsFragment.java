package sg.edu.np.mad.mad_assignment.ui.Settings;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.security.KeyStore;

import sg.edu.np.mad.mad_assignment.MainActivity;
import sg.edu.np.mad.mad_assignment.R;
import sg.edu.np.mad.mad_assignment.User;
import sg.edu.np.mad.mad_assignment.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private RadioGroup themeButtons;
    private Button loginButton;
    private FirebaseAuth auth;
    private User tempuser = new User();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        themeButtons = binding.radioGroupThemes;

        //Theme buttons setDefaultNightMode
        //3 button names
        // deviceSettingButton / darkModeButton / lightModeButton
        themeButtons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int store = 0;
                switch(i){
                    case R.id.deviceSettingButton:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                    case R.id.darkModeButton:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        store = 1;
                        break;
                    case R.id.lightModeButton:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        store = 2;
                        break;
                }

                SharedPreferences sharedpref = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedpref.edit();
                myEdit.putInt("viewSetting", store);
                myEdit.commit();
            }
        });

        //login button
        //Take login status to change the text on button
        //initial setup phase
        SharedPreferences sharedpref = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean loginStatus = sharedpref.getBoolean("loggedin", false);
        loginButton = binding.loginButton;

        if(loginStatus){
            loginButton.setText("Logout");

            //set up temp user to parse
            tempuser.setNickname(MainActivity.userUsername);
            tempuser.setEmail(MainActivity.userEmail);

            changeLoginText(true, tempuser);
        }
        else{
            loginButton.setText("Login");
            changeLoginText(false, tempuser);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //note that login status has to be checked constantly so as to make sure its up to date
                boolean login_check;
                SharedPreferences prefs = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
                login_check = prefs.getBoolean("loggedin", false);

                if (login_check){
                    auth = FirebaseAuth.getInstance();
                    auth.signOut();

                    //user is signed out
                    SharedPreferences sharedpref = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedpref.edit();
                    myEdit.putBoolean("loggedin", false);
                    myEdit.putString("uid", "");
                    myEdit.commit();

                    //changes to texts
                    loginButton.setText("Login");
                    changeLoginText(false, tempuser);

                    Toast.makeText(getContext(), "Logged out", Toast.LENGTH_SHORT).show();
                }
                else {
                    loginDialog();
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //code for login popup
    //Login should check for user to exist in the database then pull credential for user
    private void loginDialog(){
        Dialog dialog = new Dialog(this.getContext());
        //reference xml
        dialog.setContentView(R.layout.login_dialog);

        TextView emailTXT = dialog.findViewById(R.id.emailEntry);
        TextView passTXT = dialog.findViewById(R.id.passwordEntry);

        Button submitLogin = dialog.findViewById(R.id.submitButton);
        Button cancelLogin = dialog.findViewById(R.id.cancelButton);
        Button createAccount = dialog.findViewById(R.id.newAccButton);

        //Create account button
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrationDialog();
            }
        });

        cancelLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        //Login button
        //Read input -> Check input with database -> assign logged in and local id for future ref (user generated info)
        submitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User loginUser = new User();
                loginUser.setEmail(emailTXT.getText().toString());
                loginUser.setPassword(passTXT.getText().toString());

                if(loginUser.getPassword().isEmpty()){
                    Toast.makeText(getContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginAccount(loginUser, dialog);
            }
        });

        dialog.show();
    }

    //for users who have not made accounts they can register accounts through here
    //users will provide 3 pieces of information
    //Email - requires @ in it (verification)
    //Password - at least 5 digits (further verification or security)
    //Nickname - can be changed later on
    private void registrationDialog(){
        Dialog dialog = new Dialog(this.getContext());

        //reference xml
        dialog.setContentView(R.layout.registration_dialog);

        //assign views in dialog
        TextView emailregis = dialog.findViewById(R.id.registrationEmail);
        TextView passregis = dialog.findViewById(R.id.registrationPass1);
        TextView pass2regis = dialog.findViewById(R.id.registrationPass2);
        TextView nickregis = dialog.findViewById(R.id.regisNick);

        Button submitButton = dialog.findViewById(R.id.regisSubmit);
        Button cancelBUtton = dialog.findViewById(R.id.regisCancel);

        cancelBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Assign and create user class (assorted assignments)
                User newEntry = new User();

                String email = emailregis.getText().toString();
                String password = passregis.getText().toString();
                String password2 = pass2regis.getText().toString();
                String nick = nickregis.getText().toString();

                //Input verification
//
//                //valid email (no regex lol)
//                if(!(email.contains("@"))){
//                    Toast.makeText(getContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                //Ensure email does not exist in database
//                if(daoUser.emailCheck(email)){
//                    Toast.makeText(getContext(),"Email already in use", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                //Ensure password are both the same
                if(!(password.equals(password2)) || password.isEmpty()){
                    //give a toast message
                    Toast.makeText(getContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
                    return;
                }

                //setting information to class
                newEntry.setEmail(email);
                newEntry.setPassword(password);
                newEntry.setNickname(nick);

                createAccount(newEntry, dialog);
            }
        });

        dialog.show();
    }
    public void createAccount(User user, Dialog dialog){
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //add username to firebase user (use .getDisplayName to get the display name from firebase auth instance)
                            UserProfileChangeRequest addNick = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(user.getNickname())
                                    .build();
                            FirebaseAuth.getInstance().getCurrentUser().updateProfile(addNick);

                            //toast message
                            Toast.makeText(getContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                        else{
                            //if account not created created toast as to why
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                if (((FirebaseAuthUserCollisionException) task.getException()).getErrorCode().equals("ERROR_EMAIL_ALREADY_IN_USE")) {
                                    Toast.makeText(getContext(), "Email already already in use", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else if(task.getException() instanceof FirebaseAuthWeakPasswordException){
                                Toast.makeText(getContext(), ((FirebaseAuthWeakPasswordException) task.getException()).getReason(), Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getContext(), "Error creating account!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    public void loginAccount(User user, Dialog dialog){
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "Logged in!", Toast.LENGTH_SHORT).show();

                            //store UID to preferences for reference
                            String UID = auth.getCurrentUser().getUid();
                            String username = auth.getCurrentUser().getDisplayName();
                            String email = auth.getCurrentUser().getEmail();
                            SharedPreferences sharedpref = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = sharedpref.edit();
                            myEdit.putBoolean("loggedin", true);
                            myEdit.putString("uid", UID);
                            myEdit.putString("username", username);
                            myEdit.putString("useremail", email);
                            myEdit.commit();

                            //close dialog
                            dialog.cancel();

                            //change login text
                            loginButton.setText("Logout");

                            user.setNickname(username);
                            changeLoginText(true, user);
                        }
                        else{
                            Toast.makeText(getContext(), "Error logging in try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void changeLoginText(boolean status, User user){
        TextView title = binding.accountInfoHeaderText;
        TextView accountText = binding.accountInfoText;

        if(status){
            title.setText("User Information");
            accountText.setText
                    ("Username: " + user.getNickname() + "\n" +
                            "Email: " + user.getEmail().toString());
        }
        else{
            title.setText("Login to use the following!");
            accountText.setText
                    ("Uploading Posts,\n" +
                    "Show interest for events,\n" +
                    "and many more cool features!");
        }
    }
}