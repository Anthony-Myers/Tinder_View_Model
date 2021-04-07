package com.example.tinderviewmodel.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tinderviewmodel.R;

public class ProfileFragment extends Fragment {
    private MainViewModel mMainViewModel;
    private TextView emailT, nameT, birthdayT, genderT, schoolT;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        emailT = view.findViewById(R.id.email);
        nameT = view.findViewById(R.id.first_name);
        birthdayT = view.findViewById(R.id.birthday);
        genderT = view.findViewById(R.id.gender);
        schoolT = view.findViewById(R.id.school);

        emailT.setText("Email: "+mMainViewModel.getEmail());
        nameT.setText("Name: "+mMainViewModel.getName());
        birthdayT.setText("Birthday: "+mMainViewModel.getBirthDay()+"/"+mMainViewModel.getBirthMonth()+"/"+mMainViewModel.getBirthYear());
        genderT.setText("Gender: "+mMainViewModel.getGender());
        schoolT.setText("School: "+mMainViewModel.getSchool());
    }

    public static ProfileFragment newInstance(){ return new ProfileFragment(); }

}
