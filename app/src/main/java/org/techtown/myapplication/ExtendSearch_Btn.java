package org.techtown.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 확장된 검색
 * -정밀하게 검색하는 기능을 합니다.
 * @author 유세빈, 김은석, 이하나, 김동권
 */
public class ExtendSearch_Btn extends Fragment {

    private View view;

    /**
     * 기능 수행시 호출되는 메소드
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.extend_search_btn, container, false);
        return view;
    }
}
