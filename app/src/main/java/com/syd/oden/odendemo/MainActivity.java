package com.syd.oden.odendemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;
import com.syd.oden.odenlib.utils.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
        import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    CircleProgressDialog circleProgressDialog;

    @ViewById
    Button btn_show;

    @Click
    void btn_show() {
        circleProgressDialog.showDialog();
    }

    @AfterViews
    void init() {
        circleProgressDialog = new CircleProgressDialog(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
