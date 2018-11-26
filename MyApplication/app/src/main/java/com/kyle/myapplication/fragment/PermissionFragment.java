package com.kyle.myapplication.fragment;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyle.myapplication.R;
import com.kyle.permission.OnPermissionCallback;
import com.kyle.permission.PermissionDispose;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/1/3
 */
public class PermissionFragment extends Fragment {

    PermissionDispose permissionDispose;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_permission, container, false);

        ButterKnife.bind(this, inflate);

        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        permissionDispose = new PermissionDispose(getActivity());
    }

    @OnClick(R.id.btn)
    void onBtnClicked(View v) {
        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CAMERA};

        for (String permission : permissions) {
            Log.d("TAG", permission + " 授权:" +
                    permissionDispose.isGrant(permission) + "  被拒绝:"
                    + permissionDispose.isRevoked(permission));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionDispose.request(new OnPermissionCallback() {
                @Override
                public void onRequestPermissionsResult(String[] permissions, boolean[] results, boolean[] shouldRequestPermissionRationale) {
                    for (int i = 0; i < permissions.length; i++) {
                        Log.d("TAG", permissions[i] + " grant:" + results[i]
                                + " shouldRequestPermission:" + shouldRequestPermissionRationale[i]);
                    }
                }
            }, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION
                    );
        }

    }
}
