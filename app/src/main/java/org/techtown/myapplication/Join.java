package org.techtown.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 회원가입
 * -회원가입을 하는 기능을 합니다.
 * @author 유세빈, 김은석, 이하나, 김동권
 */
public class Join extends AppCompatActivity {
    Button[] btnsex = new Button[2];
    Button[] btnhealth = new Button[7];
    Button btnsignup;
    Integer[] Rid_sex = {R.id.btnsex1, R.id.btnsex2};
    boolean[] sexpressed = new boolean[2];
    boolean[] healthpressed = new boolean[7];

    private RecyclerView mRv_user;
    private FloatingActionButton mBtn_signup;
    private ArrayList<UserItem> mUserItems;
    private UserDBHelper DBHelper;

    Integer[] Rid_health = {R.id.btnhealth1, R.id.btnhealth2, R.id.btnhealth3, R.id.btnhealth4, R.id.btnhealth5, R.id.btnhealth6, R.id.btnhealth7};
    EditText cid, cpw, cname, cage;
    String id, pw, name;
    int age, sex = 0;

    /**
     * 기능 수행시 호출되는 메소드
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        ActionBar actionbar = getSupportActionBar();
        actionbar.hide(); //타이틀바 가리기

        cid = (EditText)findViewById(R.id.id);
        cpw = (EditText)findViewById(R.id.pw);
        cname = (EditText)findViewById(R.id.name);
        cage = (EditText)findViewById(R.id.age);

        for(int i=0; i<2; i++){
            btnsex[i] = (Button)findViewById(Rid_sex[i]);
        } //성별 버튼 등록
        Arrays.fill(sexpressed, false);

        for(int i=0; i<7; i++){
            btnhealth[i] = (Button)findViewById(Rid_health[i]);
        } //건강정보 버튼 등록
        Arrays.fill(healthpressed, false);

        btnsignup = (Button)findViewById(R.id.btnsignup);

        DBHelper = new UserDBHelper(this);
        DBHelper.open();
        DBHelper.create();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            /**
             * 설정하기
             * @param v
             */
            @Override
            public void onClick(View v){
                switch (v.getId()) {
                    case R.id.btnsex1: //남자
                        if (sexpressed[0] == false) {sexpressed[0] = true; sexpressed[1] = false;  sex = 1;}//Toast.makeText(Join.this, "maleselected", Toast.LENGTH_SHORT).show();}
                        else {sexpressed[0] = false; sex = 2;}//Toast.makeText(Join.this, "femaleselected", Toast.LENGTH_SHORT).show();}
                        break;
                    case R.id.btnsex2: //여자
                        if (sexpressed[1] == false) {sexpressed[1] = true;  sexpressed[0] = true; sex = 2;}// Toast.makeText(Join.this, "femaleselected", Toast.LENGTH_SHORT).show();}
                        else {sexpressed[1] = false; sex = 1;}// Toast.makeText(Join.this, "maleselected", Toast.LENGTH_SHORT).show();}
                        break;
                    case R.id.btnhealth1: //임신/임신예정
                        Toast.makeText(Join.this, "1", Toast.LENGTH_SHORT).show();
                        if (healthpressed[0]) healthpressed[0] = false;
                        else healthpressed[0] = true;
                        break;
                    case R.id.btnhealth2: //고혈압
                        Toast.makeText(Join.this, "2", Toast.LENGTH_SHORT).show();
                        if (healthpressed[1]) healthpressed[1] = false;
                        else healthpressed[1] = true;
                        break;
                    case R.id.btnhealth3: //호흡기질환
                        Toast.makeText(Join.this, "3", Toast.LENGTH_SHORT).show();
                        if (healthpressed[2]) healthpressed[2] = false;
                        else healthpressed[2] = true;
                        break;
                    case R.id.btnhealth4: //심혈관계질환
                        Toast.makeText(Join.this, "4", Toast.LENGTH_SHORT).show();
                        if (healthpressed[3]) healthpressed[3] = false;
                        else healthpressed[3] = true;
                        break;
                    case R.id.btnhealth5: //당뇨
                        Toast.makeText(Join.this, "5", Toast.LENGTH_SHORT).show();
                        if (healthpressed[4]) healthpressed[4] = false;
                        else healthpressed[4] = true;
                        break;
                    case R.id.btnhealth6: //피라졸론 과민
                        Toast.makeText(Join.this, "6", Toast.LENGTH_SHORT).show();
                        if (healthpressed[5]) healthpressed[5] = false;
                        else healthpressed[5] = true;
                        break;
                    case R.id.btnhealth7: //페니실린 과민
                        Toast.makeText(Join.this, "7", Toast.LENGTH_SHORT).show();
                        if (healthpressed[6]) healthpressed[6] = false;
                        else healthpressed[6] = true;
                        break;
                    case R.id.btnsignup:
                        String id = cid.getText().toString();
                        String pw = cpw.getText().toString();
                        String name = cname.getText().toString();
                        int age = Integer.parseInt(cage.getText().toString());
                        DBHelper.insertColumn(id, pw, name, 11, sex, healthpressed[0], healthpressed[0], healthpressed[0], healthpressed[0], healthpressed[0], healthpressed[0], healthpressed[0]);
                        Toast.makeText(Join.this, name+"님 환영합니다.", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    default:
                        break;
                }
            }
        };

        for(int i=0; i<2; i++){
            btnsex[i].setOnClickListener(onClickListener);
        }

        for(int i=0; i<7; i++){
            btnhealth[i].setOnClickListener(onClickListener);
        }

        btnsignup.setOnClickListener(onClickListener);
    }
}