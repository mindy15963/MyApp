package org.techtown.myapplication;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
/**
 * 초기화면
 * -앱 실행시 나오는 첫화면으로 로그인 화면이 나옵니다.
 * @author 유세빈, 김은석, 이하나, 김동권
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv_user;
    private FloatingActionButton mBtn_signup;
    private ArrayList<UserItem> mUserItems;
    private UserDBHelper DBHelper;

    /**
     * 기능 수행시 호출되는 메소드
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        EditText id = (EditText)findViewById(R.id.id);
        EditText pw = (EditText)findViewById(R.id.pwd);
        Button btnSignup = (Button)findViewById(R.id.btn_signup);
        Button btnLogin = (Button)findViewById(R.id.btn_login);

        DBHelper = new UserDBHelper(this);
        DBHelper.open();
        DBHelper.create();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            /**
             * 회원가입 버튼
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Join.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            /**
             * 로그인 버튼
             * @param view
             */
            @Override
            public void onClick(View view) {
                String cid = id.getText().toString();
                String cpw = pw.getText().toString();

                if(cid.equals("") || cpw.equals(""))
                    Toast.makeText(MainActivity.this, "please enter all the fields", Toast.LENGTH_SHORT).show();

                else{
                    Boolean check = DBHelper.memberCheck(cid, cpw);
                    if(check == true){
                        Toast.makeText(MainActivity.this, cid+"님 환영합니다.", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = new Intent(MainActivity.this,BottomNavibar.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "아이디 및 패스워드를 확인해주세요.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}