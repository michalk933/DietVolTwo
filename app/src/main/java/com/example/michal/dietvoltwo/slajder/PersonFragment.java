package com.example.michal.dietvoltwo.slajder;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.dto.UserPersonalDto;

public class PersonFragment extends Fragment {
    public static final String TAG = "Dane identyfikacyjne";

    private EditText nameEditText, passwordEditText, rePasswordEditText, mailEditText;
    public static UserPersonalDto userPersonalDto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_fragment, container, false);

        nameEditText = (EditText) view.findViewById(R.id.nameEditText);
        passwordEditText = (EditText) view.findViewById(R.id.passEditText);
        rePasswordEditText = (EditText) view.findViewById(R.id.rePassEditText);
        mailEditText = (EditText) view.findViewById(R.id.mailEditText);

        userPersonalDto = UserPersonalDto.createUserPersonalDto();
        userPersonalDto.setLogin(nameEditText.getEditableText().toString());
        userPersonalDto.setPassword(passwordEditText.getText().toString());
        userPersonalDto.setRePassword(rePasswordEditText.getText().toString());
        userPersonalDto.setEMail(mailEditText.getText().toString());

        return view;
    }



}
