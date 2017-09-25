package com.example.michal.dietvoltwo.slajder;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
    private String login, password, rePasword, email = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_fragment, container, false);

        nameEditText = (EditText) view.findViewById(R.id.nameEditText);
        passwordEditText = (EditText) view.findViewById(R.id.passEditText);
        rePasswordEditText = (EditText) view.findViewById(R.id.rePassEditText);
        mailEditText = (EditText) view.findViewById(R.id.mailEditText);

        userPersonalDto = UserPersonalDto.createUserPersonalDto();

        userPersonalDto.setLogin(login);
        userPersonalDto.setPassword(password);
        userPersonalDto.setRePassword(rePasword);
        userPersonalDto.seteMail(email);

        nameEditText.addTextChangedListener(new GenericTextWatcher(nameEditText));
        passwordEditText.addTextChangedListener(new GenericTextWatcher(passwordEditText));
        rePasswordEditText.addTextChangedListener(new GenericTextWatcher(rePasswordEditText));
        mailEditText.addTextChangedListener(new GenericTextWatcher(mailEditText));

        return view;
    }

    private class GenericTextWatcher implements TextWatcher {
        private View view;

        public GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.nameEditText:
                    login = editable.toString();
                    userPersonalDto.setLogin(login);
                    break;

                case R.id.passEditText:
                    password = editable.toString();
                    userPersonalDto.setPassword(password);
                    break;

                case R.id.rePassEditText:
                    rePasword = editable.toString();
                    userPersonalDto.setRePassword(rePasword);
                    break;

                case R.id.mailEditText:
                    email = editable.toString();
                    userPersonalDto.seteMail(email);
                    break;
            }
        }
    }
}
