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

public class SchoolFragment extends Fragment {
    private EventHandlerInterface eventInterface;
    private MainViewModel mMainViewModel;
    private EditText schoolET;
    private Button backToGenderB;
    private FragmentManager fm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.school_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        fm = getActivity().getSupportFragmentManager();

        schoolET = view.findViewById(R.id.school_edit_text);
        backToGenderB = view.findViewById(R.id.back_to_gender_button);
        schoolET.setText(mMainViewModel.getSchool());

        schoolET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                mMainViewModel.setSchool(schoolET.getText().toString());
                eventInterface.onTextChange();
            }
        });

        backToGenderB.setOnClickListener(v -> {
            eventInterface.onBackClick();
            fm.popBackStackImmediate();
        });
    }

    public static SchoolFragment newInstance(){ return new SchoolFragment(); }

    public void setInterface(EventHandlerInterface newEventInterface){
        eventInterface = newEventInterface;
    }

}
