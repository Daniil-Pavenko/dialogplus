package com.setcom.sampletestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        globalDialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.big_dialog))
                .setGravity(Gravity.TOP)
                .create();
    }

    public void onClickOneDialog(View view) {
        DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.small_dialog))
                .setGravity(Gravity.TOP)
                .create()
                .show();
    }

    public void onClickTwoDialogs(View view) {
        DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.small_dialog))
                .setGravity(Gravity.TOP)
                .create()
                .show();
        DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.big_dialog))
                .setGravity(Gravity.TOP)
                .create()
                .show();
    }

    private DialogPlus globalDialog;

    public void onClickGlobal(View view) {
        if (!globalDialog.isShowing()) globalDialog.show(); else globalDialog.dismiss();
    }
}
