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
import androidx.lifecycle.ViewModelProvider;

import com.example.tinderviewmodel.R;
import com.example.tinderviewmodel.interfaces.EventHandlerInterface;

public class EmailFragment extends Fragment {

    private MainViewModel mMainViewModel;
    private EditText emailET;
    private EventHandlerInterface eventInterface;
    private Button closeApplicationB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.email_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        emailET = view.findViewById(R.id.email_edit_text);
        closeApplicationB = view.findViewById(R.id.x_button);
        emailET.setText(mMainViewModel.getEmail());

        emailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                mMainViewModel.setEmail(emailET.getText().toString());
                eventInterface.onTextChange();
            }
        });

        closeApplicationB.setOnClickListener(v -> eventInterface.onBackClick());
    }

    public static EmailFragment newInstance(){ return new EmailFragment(); }

    public void setInterface(EventHandlerInterface newEventInterface){
        eventInterface = newEventInterface;
    }
}
