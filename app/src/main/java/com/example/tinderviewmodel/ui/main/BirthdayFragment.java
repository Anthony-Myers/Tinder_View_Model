package com.example.tinderviewmodel.ui.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.tinderviewmodel.R;
import com.example.tinderviewmodel.interfaces.EventHandlerInterface;

public class BirthdayFragment extends Fragment {

    private EventHandlerInterface eventInterface;
    private MainViewModel mMainViewModel;
    private EditText dayET, monthET, yearET;
    private FragmentManager fm;
    private Button backToNameB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.birthday_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        fm = getActivity().getSupportFragmentManager();

        dayET = view.findViewById(R.id.day_edit_text);
        backToNameB = view.findViewById(R.id.back_to_name_button);
        dayET.setText(mMainViewModel.getBirthDay());

        dayET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                mMainViewModel.setBirthDay(dayET.getText().toString());
                eventInterface.onTextChange();
            }
        });

        monthET = view.findViewById(R.id.month_edit_text);
        monthET.setText(mMainViewModel.getBirthMonth());

        monthET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                mMainViewModel.setBirthMonth(monthET.getText().toString());
                eventInterface.onTextChange();
            }
        });

        yearET = view.findViewById(R.id.year_edit_text);
        yearET.setText(mMainViewModel.getBirthYear());

        yearET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                mMainViewModel.setBirthYear(yearET.getText().toString());
                eventInterface.onTextChange();
            }
        });

        backToNameB.setOnClickListener(v -> {
            eventInterface.onBackClick();
            fm.popBackStackImmediate();
        });
    }

    public static BirthdayFragment newInstance(){ return new BirthdayFragment(); }

    public void setInterface(EventHandlerInterface newEventInterface){
        eventInterface = newEventInterface;
    }

}
