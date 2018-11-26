package com.kyle.myapplication.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.kyle.myapplication.MenuPopActivity;
import com.kyle.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/11/13
 */
public class MenuPopFragment extends Fragment {

    private static String TAG = MenuPopActivity.class.getSimpleName();
    PopupWindow popupWindow;
    MenuPopActivity popActivity;

    @BindView(R.id.btn_show)
    Button btnShow;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        popActivity = (MenuPopActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_pop, container, false);
        ButterKnife.bind(this, view);
        createPop();
        return view;
    }


    private void createPop() {

        popupWindow = new PopupWindow(getContext());
        popupWindow.setWidth(dp2px(200));
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        btnShow.setTag(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_pop_menu,
                null,
                false);

        popupWindow.setContentView(view);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popActivity.printLog("onDismiss()");
            }
        });
    }

    @OnClick(R.id.btn_show)
    public void onShow(View view) {
//        popupWindow.showAsDropDown(view);
        boolean flag = (boolean) btnShow.getTag();
        if (flag) {
            btnShow.setTag(false);
            popupWindow.setBackgroundDrawable(null);
        } else {
            btnShow.setTag(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable());
        }

    }


    public int dp2px(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float v = displayMetrics.density * dp + 0.5f;
        return (int) v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1: {
                popupWindow.setFocusable(false);
                popupWindow.setOutsideTouchable(false);
            }
            break;
            case R.id.btn2: {
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(false);
            }
            break;
            case R.id.btn3: {
                popupWindow.setFocusable(false);
                popupWindow.setOutsideTouchable(true);
            }
            break;
            case R.id.btn4: {
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
            }
            break;
        }

        popupWindow.showAsDropDown(btnShow);
    }
}
