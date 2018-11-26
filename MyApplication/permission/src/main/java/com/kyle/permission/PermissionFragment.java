package com.kyle.permission;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description描述: 申请权限的本体
 * @Author作者: Kyle
 * @Date日期: 2018/1/3
 */
public class PermissionFragment extends Fragment {
    private static final int REQUEST_PERMISSION_CODE = 10;

    private OnPermissionCallback onPermissionCallback;

    void setOnPermissionCallback(OnPermissionCallback onPermissionCallback) {
        this.onPermissionCallback = onPermissionCallback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 已保留的fragment不会随着activity一起被销毁
        setRetainInstance(true);
    }

    @TargetApi(Build.VERSION_CODES.M)
    void requestPermission(String... permissions) {
        requestPermissions(permissions, REQUEST_PERMISSION_CODE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (onPermissionCallback != null) {
                boolean[] grants = new boolean[grantResults.length];
                boolean[] rationale = new boolean[grantResults.length];

                for (int i = 0; i < grantResults.length; i++) {
                    grants[i] = grantResults[i] == PackageManager.PERMISSION_GRANTED;
                    rationale[i] = shouldShowRequestPermissionRationale(permissions[i]);
                }

                onPermissionCallback.onRequestPermissionsResult(permissions, grants, rationale);
            }
        }
    }

    // 权限是否已经通过授权
    @TargetApi(Build.VERSION_CODES.M)
    boolean isGranted(String permission) {
        return getActivity().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    // 权限是否已经在未确认的时候就已经取消
    @TargetApi(Build.VERSION_CODES.M)
    boolean isRevoked(String permission) {
        return getActivity().getPackageManager()
                .isPermissionRevokedByPolicy(permission, getActivity().getPackageName());
    }

    // 获取没有授权的权限列表
    @TargetApi(Build.VERSION_CODES.M)
    List<String> getDeniedPermission(String[] permissions) {
        List<String> permTemps = new ArrayList<>();
        for (String permission : permissions) {
            if (isGranted(permission)){
                continue;
            }
            permTemps.add(permission);
        }
        return permTemps;
    }
}
