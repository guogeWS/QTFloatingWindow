package org.gxz.FloatingWindow;

import android.app.Activity;
import org.qtproject.qt5.android.bindings.QtActivity;
import org.qtproject.qt5.android.bindings.QtApplication;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.*;
import android.widget.Toast;
import android.util.Log;
import android.os.PowerManager;
import android.view.WindowManager;
import android.os.Bundle;
import android.provider.Settings;

import android.net.Uri;
public class FloatingWindow extends QtActivity{
    public static final int CODE_WINDOW = 0 ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("window", "start app ");
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "请打开此应用悬浮窗权限-Shendi", Toast.LENGTH_SHORT).show();
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), CODE_WINDOW);
        }
        Intent intent = new Intent(this, FloatingWindowServer.class);
        startService(intent);
        // 关闭当前activity,这样只显示悬浮窗
        finish();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // 不给权限就退出
            case CODE_WINDOW:
                if (resultCode != Activity.RESULT_OK) System.exit(0);
                break;
            default:
                Toast.makeText(this, "未知权限回调: " + requestCode, Toast.LENGTH_SHORT).show();
        }
    }


};
