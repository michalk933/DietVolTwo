package com.example.michal.dietvoltwo.slajder;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

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
            String message = editable.toString();
            switch (view.getId()) {
                case R.id.nameEditText:
                    userPersonalDto.setLogin(editable.toString());
                    break;

                case R.id.passEditText:
                    userPersonalDto.setPassword(editable.toString());
                    break;

                case R.id.rePassEditText:
                    if (isPasswordEqualRePassword(userPersonalDto.getPassword(), editable.toString()))
                        userPersonalDto.setRePassword(editable.toString());
                    else {
                        Toast.makeText(getContext(), getResources().getString(R.string.person_fragment_message_password), Toast.LENGTH_SHORT).show();
//                        view.setBackgroundColor(Color.RED);
                    }
                    break;

                case R.id.mailEditText:
                    if (isValidEmailAddress(editable.toString()))
                        userPersonalDto.seteMail(editable.toString());
                    else {
                        Toast.makeText(getContext(), getResources().getString(R.string.person_fragment_message_email), Toast.LENGTH_SHORT).show();
//                        view.setBackgroundColor(0xFFFFFFFF);
                    }
                    break;
            }

        }

        private boolean isValidEmailAddress(String email) {
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(email);
            return m.matches();
        }

        private boolean isPasswordEqualRePassword(String password, String rePassword) {
            return password.equals(rePassword) ? true : false;
        }


    }


}
