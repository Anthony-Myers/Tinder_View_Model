package com.example.tinderviewmodel.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.tinderviewmodel.R;
import com.example.tinderviewmodel.interfaces.EventHandlerInterface;

public class GenderFragment extends Fragment {

    private EventHandlerInterface eventInterface;
    private MainViewModel mMainViewModel;
    private Button manB, womanB, backToBirthdayB;
    private FragmentManager fm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gender_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        fm = getActivity().getSupportFragmentManager();

        manB = view.findViewById(R.id.man_button);
        womanB = view.findViewById(R.id.woman_button);
        backToBirthdayB = view.findViewById(R.id.back_to_birthday_button);

        if(mMainViewModel.getGender().equals(manB.getText().toString())){
            manB.setBackgroundColor(getResources().getColor(R.color.button_orange));
            manB.setTextColor(getResources().getColor(R.color.black));
        }

        if(mMainViewModel.getGender().equals(womanB.getText().toString())){
            womanB.setBackgroundColor(getResources().getColor(R.color.button_orange));
            womanB.setTextColor(getResources().getColor(R.color.black));
        }

        manB.setOnClickListener(v -> {
            mMainViewModel.setGender(manB.getText().toString());
            manB.setBackgroundColor(getResources().getColor(R.color.button_orange));
            manB.setTextColor(getResources().getColor(R.color.black));
            womanB.setBackgroundColor(getResources().getColor(R.color.white));
            womanB.setTextColor(getResources().getColor(R.color.subtext_grey));
            eventInterface.onClick();
        });

        womanB.setOnClickListener(v -> {
            mMainViewModel.setGender(womanB.getText().toString());
            womanB.setBackgroundColor(getResources().getColor(R.color.button_orange));
            womanB.setTextColor(getResources().getColor(R.color.black));
            manB.setBackgroundColor(getResources().getColor(R.color.white));
            manB.setTextColor(getResources().getColor(R.color.subtext_grey));
            eventInterface.onClick();
        });

        backToBirthdayB.setOnClickListener(v -> {
            eventInterface.onBackClick();
            fm.popBackStackImmediate();
        });
    }

    public static GenderFragment newInstance(){ return new GenderFragment(); }

    public void setInterface(EventHandlerInterface newEventInterface){ eventInterface = newEventInterface; }

}
