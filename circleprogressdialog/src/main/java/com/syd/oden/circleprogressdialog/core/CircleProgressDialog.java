package com.syd.oden.circleprogressdialog.core;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syd.oden.circleprogressdialog.R;
import com.syd.oden.circleprogressdialog.view.RotateLoading;

/**
 * Created by Oden on 2016/7/23.
 */
public class CircleProgressDialog {
    private Context mContext;
    private Dialog mDialog;
    public static final String ACTION_DIALOG_CANCEL = "com.oden.ACTION_DIALOG_CANCEL";

    //默认参数
    private int dialogSize = 65;
    private int progressColor = Color.WHITE;
    private int progressWidth = 6;
    private int shadowOffset = 2;
    private int textColor = Color.parseColor("#c0000000");
    private String text = "loading...";

    private TextView progressTextView;
    private boolean isShowing = false;


    public CircleProgressDialog(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        //dialog的大小,转化成dp
        float scale = mContext.getResources().getDisplayMetrics().density;
        dialogSize = (int) (dialogSize * scale + 0.5f);

        mDialog = new AlertDialog.Builder(mContext).create();
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public void showDialog() {
        mDialog.show();
        mDialog.setContentView(R.layout.dialog_circle_progress);

        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Tools.braodUpdate(mContext, ACTION_DIALOG_CANCEL);
                isShowing = false;
            }
        });
        
        progressTextView = (TextView) mDialog.findViewById(R.id.progreeTextView);
        RotateLoading mRotateLoading = (RotateLoading) mDialog.findViewById(R.id.rotateloading);
        LinearLayout layout = (LinearLayout) mDialog.findViewById(R.id.llProgress);

        layout.setLayoutParams(new LinearLayout.LayoutParams(dialogSize, dialogSize));
        mRotateLoading.setWidth(progressWidth);
        mRotateLoading.setColor(progressColor);
        mRotateLoading.setShadowOffset(shadowOffset);
        progressTextView.setTextColor(textColor);
        progressTextView.setText(text);

        mRotateLoading.start();
        isShowing = true;
    }

    public void changeText(String str) {
        if (progressTextView != null)
        {
            progressTextView.setText(str);
        }
    }

    public void changeTextColor(int color) {
        if (progressTextView != null)
        {
            progressTextView.setTextColor(color);
        }
    }

    public void setCancelable(boolean isCancelable) {
        if (mDialog != null)
        {
            mDialog.setCancelable(isCancelable);
        }
    }

    public void dismiss() {
        if (mDialog != null)
        {
            mDialog.dismiss();
            isShowing = false;
        }
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void setDialogSize(int dialogSize) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        this.dialogSize =  (int) (dialogSize * scale + 0.5f);
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public void setProgressWidth(int progressWidth) {
        this.progressWidth = progressWidth;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setShadowPosition(int shadowPosition) {
        this.shadowOffset = shadowPosition;
    }
}
