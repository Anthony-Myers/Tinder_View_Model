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

public class NameFragment extends Fragment {

    private EventHandlerInterface eventInterface;
    private MainViewModel mMainViewModel;
    private EditText nameET;
    private Button backToEmailB;
    private FragmentManager fm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.name_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        fm = getActivity().getSupportFragmentManager();

        nameET = view.findViewById(R.id.first_name_edit_text);
        backToEmailB = view.findViewById(R.id.back_to_email_button);

        nameET.setText(mMainViewModel.getName());

        nameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                mMainViewModel.setName(nameET.getText().toString());
                eventInterface.onTextChange();
            }
        });

        backToEmailB.setOnClickListener(v -> {
            eventInterface.onBackClick();
            fm.popBackStackImmediate();
        });
    }

    public static NameFragment newInstance(){ return new NameFragment(); }

    public void setInterface(EventHandlerInterface newEventInterface){
        eventInterface = newEventInterface;
    }
}
