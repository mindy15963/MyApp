package org.techtown.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class login extends Fragment {     // Frag

    private View view;
    boolean loginpressed = false;

    EditText cnickname, cpw;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.login, container, false);
        cnickname = rootView.findViewById(R.id.id);
        cpw = rootView.findViewById(R.id.pw);

        Button btnlogin = (Button)rootView.findViewById(R.id.btn_login);
        Button btnsignup = (Button)rootView.findViewById(R.id.btn_signup);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = cnickname.getText().toString();
                String pw = cpw.getText().toString();

                if (nickname.length() == 0 || pw.length() == 0){
                    Toast toast = Toast.makeText(getActivity(), "아이디와 비밀번호를 입력하세요.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }else {
                    //로그인성공
                    Toast toast = Toast.makeText(getActivity(), "로그인성공", Toast.LENGTH_SHORT);
                    toast.show();
                    //인텐트 생성 및 호출
                    Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getActivity(), "회원가입화면으로 이동합니다.", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getActivity().getApplicationContext(),Join.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}