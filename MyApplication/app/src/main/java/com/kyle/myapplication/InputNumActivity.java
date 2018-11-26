package com.kyle.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/3/19
 */
public class InputNumActivity extends AppCompatActivity {

    EditText etNum;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_input_num);

        etNum = (EditText) findViewById(R.id.et_num);
    }


    public void onInsert(View v) {
        if (v instanceof Button) {
            Button btn = (Button) v;
            insertText(etNum, btn.getText());
        }
    }


    /**
     * 在EditText当前光标后面插入文本
     *
     * @param et   EditText 文本框
     * @param text 插入的文本
     */
    private void insertText(EditText et, CharSequence text) {
        int selection = et.getSelectionStart();
        Editable editable = et.getText();

        editable.insert(selection, text);
    }


    /**
     * 删除EditText当前光标前的一个字符
     *
     * @param et
     */
    private void deleteText(EditText et) {
        int selection = et.getSelectionStart();
        Editable editable = et.getText();

        if (selection == 0 || editable.length() == 0) {
            return;
        }

        editable.delete(selection, selection - 1);
    }
}
