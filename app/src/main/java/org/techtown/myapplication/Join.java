package org.techtown.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
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

public class Join extends AppCompatActivity {
    Button[] btnsex = new Button[2];
    Button[] btnhealth = new Button[7];
    Button btnsignup;
    Integer[] Rid_sex = {R.id.btnsex1, R.id.btnsex2};
    boolean[] sexpressed = new boolean[2];
    boolean[] healthpressed = new boolean[7];
    boolean signuppressed = false;

    private RecyclerView mRv_user;
    private FloatingActionButton mBtn_signup;
    private ArrayList<UserItem> mUserItems;
    private DBHelper mDBHelper;

    //boolean sex1pressed = false, sex2pressed = false, health1pressed = false, health2pressed = false, health3pressed = false, health4pressed = false, health5pressed = false, health6pressed = false, health7pressed = false;
    Integer[] Rid_health = {R.id.btnhealth1, R.id.btnhealth2, R.id.btnhealth3, R.id.btnhealth4, R.id.btnhealth5, R.id.btnhealth6, R.id.btnhealth7};
    EditText cid, cpw, cname, cage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionbar = getSupportActionBar();
        actionbar.hide(); //타이틀바 가리기

        cid = (EditText)findViewById(R.id.id);
        cpw = (EditText)findViewById(R.id.pw);
        cname = (EditText)findViewById(R.id.name);
        cage = (EditText)findViewById(R.id.age);
        String id = cid.getText().toString();
        String pw = cpw.getText().toString();
        String name = cname.getText().toString();
        String age = cage.getText().toString(); //얘는 숫자로 받는게 낫지 않을까요 능력이 딸려서 String으로 받앗지만 ㅎ...
        //DB가 구축이 안되어있어서 이 부분은 일단 여기까지만 진행했습니다, id/pw/name/age 알람 DB 만들면 그쪽으로 저장해주세요

        for(int i=0; i<2; i++){
            btnsex[i] = (Button)findViewById(Rid_sex[i]);
        } //성별 버튼 등록
        Arrays.fill(sexpressed, false);

        for(int i=0; i<7; i++){
            btnhealth[i] = (Button)findViewById(Rid_health[i]);
        } //건강정보 버튼 등록
        Arrays.fill(healthpressed, false);

        btnsignup = (Button)findViewById(R.id.btnsignup);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                switch (v.getId()) {
                    case R.id.btnsex1: //남자
                        if (sexpressed[0] == false) {sexpressed[0] = true; sexpressed[1] = false; Toast.makeText(Join.this, "maleselected", Toast.LENGTH_SHORT).show();}
                        else {sexpressed[0] = false; Toast.makeText(Join.this, "femaleselected", Toast.LENGTH_SHORT).show();}
                        //이쪽도 DB로 들어갈 내용이고 에뮬 돌릴때 확인차 Toast 넣어뒀습니다.
                        //성별 선택은 1 선택한 뒤 다시 누르면 자동으로 2 선택되고 마찬가지로 1 선택 후 2 누르면 2만 선택되도록 = 단일 선택 되도록 했습니다 성별 선택에만 한정된 내용이에요
                        //DB 구축하면 Toast 삭제하시고 버튼 해당사항에 맞게 정보 저장하도록 해주세요!
                        break;
                    case R.id.btnsex2: //여자
                        if (sexpressed[1] == false) {sexpressed[1] = true;  sexpressed[0] = true; Toast.makeText(Join.this, "femaleselected", Toast.LENGTH_SHORT).show();}
                        else {sexpressed[1] = false; Toast.makeText(Join.this, "maleselected", Toast.LENGTH_SHORT).show();}
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
                    case R.id.btnsignup: //회원가입버튼, 누르면 메인화면으로 넘어가도록 알고리즘 짰을 거예요
                        Toast.makeText(Join.this, "signup", Toast.LENGTH_SHORT).show();
                        if (signuppressed) signuppressed = false;
                        else signuppressed = true;
                        break;
                    default:
                        Toast.makeText(Join.this, "default", Toast.LENGTH_SHORT).show();
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

    /*
    @SuppressLint("WrongViewCast")
    private void setInit(){
        mDBHelper = new DBHelpter(this);
        mRv_user = findViewById(R.id.rv);
        mBtn_signup = findViewById(R.id.btn_signup);
        mUserItems = new ArrayList<>();

        mBtn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 팝업창
                Dialog dialog;

            }
        });

    }
    */

}

