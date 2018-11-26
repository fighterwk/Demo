package com.kyle.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;

/**
 * Created by feng on 2016/11/24.
 * 6.0以上权限申请
 */
public class PermissionDispose {

    static final String TAG = PermissionFragment.class.getSimpleName();

    private PermissionFragment permissionFragment;

    public PermissionDispose(Activity activity) {
        permissionFragment = getPermissionFragment(activity);
    }

    private PermissionFragment getPermissionFragment(Activity activity) {
        PermissionFragment fragment = (PermissionFragment) activity.getFragmentManager().findFragmentByTag(TAG);
        if (fragment == null) {
            fragment = new PermissionFragment();
            FragmentManager fm = activity.getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(fragment, TAG);
            ft.commitAllowingStateLoss();

            //它将在进程的主线程异步执行。如果您想立即执行任何这样的挂起操作，您可以调用此函数(仅从主线程)来执行此操作。
            // 请注意，所有回调和其他相关的行为都将在这个调用中完成，所以要注意这是从哪里调用的。
            fm.executePendingTransactions();
        }

        return fragment;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void request(OnPermissionCallback permissionCallback, String... permissions) {
        permissionFragment.setOnPermissionCallback(permissionCallback);
        permissionFragment.requestPermission(permissions);
    }


    /**
     * 是否已经授权权限
     *
     * @param permission
     * @return
     */
    public boolean isGrant(String permission) {
        return isMarshmallow() && permissionFragment.isGranted(permission);
    }

    /**
     * 是否需要
     *
     * @param permission
     * @return
     */
    public boolean isRevoked(String permission) {
        return isMarshmallow() && permissionFragment.isRevoked(permission);
    }

    private boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

//    /**
//     * 显示提示对话框
//     */
//    private void showTipsDialog() {
//        new AlertDialog.Builder(activity)
//                .setTitle("提示信息")
//                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                })
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        startAppSettings();
//                    }
//                }).show();
//    }

//    /**
//     * 启动当前应用设置页面
//     */
//    private void startAppSettings() {
//        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        intent.setData(Uri.parse("package:" + activity.getPackageName()));
//        activity.startActivity(intent);
//    }

}
