package com.kyle.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kyle.myapplication.fragment.PermissionFragment;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/1/3
 */
public class PermissionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, new PermissionFragment())
                .commitAllowingStateLoss();
    }


//    @OnClick(R.id.btn)
//    void onBtnClicked(View v) {
//        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.ACCESS_WIFI_STATE,
//                Manifest.permission.CAMERA};
//
//        for (String permission : permissions) {
//            Log.d("TAG", permission + " 授权:" +
//                    permissionDispose.isGrant(permission) + "  被拒绝:"
//                    + permissionDispose.isRevoked(permission));
//        }
//
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////            permissionDispose.request(new OnPermissionCallback() {
////                @Override
////                public void onRequestPermissionsResult(String[] permissions, boolean[] results, boolean[] shouldRequestPermissionRationale) {
////                    for (int i = 0; i < permissions.length; i++) {
////                        Log.d("TAG", permissions[i] + " grant:" + results[i]
////                                + " shouldRequestPermission:" + shouldRequestPermissionRationale[i]);
////                    }
////                }
////            }, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
////                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION
////                    );
////        }
//
//    }

}
