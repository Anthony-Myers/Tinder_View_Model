package com.example.tinderviewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.tinderviewmodel.ui.main.BirthdayFragment;
import com.example.tinderviewmodel.interfaces.EventHandlerInterface;
import com.example.tinderviewmodel.ui.main.EmailFragment;
import com.example.tinderviewmodel.ui.main.GenderFragment;
import com.example.tinderviewmodel.ui.main.MainViewModel;
import com.example.tinderviewmodel.ui.main.NameFragment;
import com.example.tinderviewmodel.ui.main.ProfileFragment;
import com.example.tinderviewmodel.ui.main.SchoolFragment;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.regex.Pattern;

public class MainHostActivity extends FragmentActivity implements EventHandlerInterface{

    private MainViewModel mMainViewModel;
    private Button continueButton;
    private FragmentManager fm;
    private LinearProgressIndicator progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_host_activity);
        EmailFragment emailFragment = EmailFragment.newInstance();
        emailFragment.setInterface(this);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, emailFragment).addToBackStack(null)
                    .commit();
        }
        fm = getSupportFragmentManager();
        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        continueButton = findViewById(R.id.continue_button);
        progressBar = findViewById(R.id.profile_progress);

        continueButton.setOnClickListener(v -> {
            switch (fm.getBackStackEntryCount()){
                case 1:
                    progressBar.setProgressCompat(20, true);
                    NameFragment nameFragment = NameFragment.newInstance();
                    nameFragment.setInterface(this);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, nameFragment).addToBackStack(null)
                            .commit();
                    if(mMainViewModel.getName().isEmpty()){
                        disableContinue();
                    }
                    break;
                case 2:
                    progressBar.setProgressCompat(40, true);
                    BirthdayFragment birthdayFragment = BirthdayFragment.newInstance();
                    birthdayFragment.setInterface(this);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, birthdayFragment).addToBackStack(null)
                            .commit();
                    if(mMainViewModel.getBirthDay().isEmpty()||mMainViewModel.getBirthMonth().isEmpty()||mMainViewModel.getBirthYear().isEmpty()){
                        disableContinue();
                    }
                    break;
                case 3:
                    progressBar.setProgressCompat(60, true);
                    GenderFragment genderFragment = GenderFragment.newInstance();
                    genderFragment.setInterface(this);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, genderFragment).addToBackStack(null)
                            .commit();
                    if(mMainViewModel.getGender().isEmpty()){
                        disableContinue();
                    }
                    break;
                case 4:
                    progressBar.setProgressCompat(80, true);
                    SchoolFragment schoolFragment = SchoolFragment.newInstance();
                    schoolFragment.setInterface(this);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, schoolFragment).addToBackStack(null)
                            .commit();
                    if(mMainViewModel.getSchool().isEmpty()){
                        disableContinue();
                    }
                    break;
                case 5:
                    progressBar.setProgressCompat(100, true);
                    ProfileFragment profileFragment = ProfileFragment.newInstance();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, profileFragment).addToBackStack(null)
                            .commit();
                    continueButton.setText("Finish");
                    break;
                default:
                    Intent intent = new Intent(this, MainHostActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
            }
        });
    }

    @Override
    public void onClick() {
        if(!mMainViewModel.getGender().isEmpty()){
            enableContinue();
        }else{
            disableContinue();
        }
    }

    @Override
    public void onTextChange() {
        switch (fm.getBackStackEntryCount()){
            case 1:
                if(isValidEmail(mMainViewModel.getEmail())){
                    enableContinue();
                }else{
                    disableContinue();
                }
                break;
            case 2:
                if(!mMainViewModel.getName().isEmpty()){
                    enableContinue();
                }else{
                    disableContinue();
                }
                break;
            case 3:
                if(!mMainViewModel.getBirthYear().isEmpty() && !mMainViewModel.getBirthDay().isEmpty() && !mMainViewModel.getBirthMonth().isEmpty()){
                    enableContinue();
                }else{
                    disableContinue();
                }
                break;
            case 5:
                if(!mMainViewModel.getSchool().isEmpty()){
                    enableContinue();
                }else{
                    disableContinue();
                }
                break;
        }
    }

    public void disableContinue(){
        continueButton.setBackgroundColor(getResources().getColor(R.color.button_disabled_grey));
        continueButton.setTextColor(getResources().getColor(R.color.button_grey));
        continueButton.setClickable(false);
    }

    public void enableContinue(){
        continueButton.setBackgroundColor(getResources().getColor(R.color.button_orange));
        continueButton.setTextColor(getResources().getColor(R.color.white));
        continueButton.setClickable(true);
    }

    public boolean isValidEmail(String email){
        Pattern emailRegex = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        return emailRegex.matcher(email).matches();
    }

    @Override
    public void onBackClick() {
        switch(fm.getBackStackEntryCount()){
            case 1:
                finish();
                System.exit(0);
                break;
            case 2:
                progressBar.setProgressCompat(10, true);
                if(!mMainViewModel.getEmail().isEmpty()){
                    enableContinue();
                }
                break;
            case 3:
                progressBar.setProgressCompat(20, true);
                if(!mMainViewModel.getName().isEmpty()){
                    enableContinue();
                }
                break;
            case 4:
                progressBar.setProgressCompat(40, true);
                if(!mMainViewModel.getBirthDay().isEmpty()&&!mMainViewModel.getBirthMonth().isEmpty()&&!mMainViewModel.getBirthYear().isEmpty()){
                    enableContinue();
                }
                break;
            case 5:
                progressBar.setProgressCompat(60, true);
                if(!mMainViewModel.getGender().isEmpty()){
                    enableContinue();
                }
        }
    }
}
