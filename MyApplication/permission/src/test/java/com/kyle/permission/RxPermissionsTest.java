package com.kyle.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.mockito.Mockito.spy;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.M)
public class RxPermissionsTest {

    private Activity mActivity;

    private PermissionDispose permissionDispose;

    @Before
    public void setup() {
        ActivityController<Activity> activityController = Robolectric.buildActivity(Activity.class);
        mActivity = spy(activityController.setup().get());
        permissionDispose = spy(new PermissionDispose(mActivity));



    }

    @Test
    @TargetApi(Build.VERSION_CODES.M)
    public void subscription_preM() {
        permissionDispose.request(new OnPermissionCallback() {
            @Override
            public void onRequestPermissionsResult(String[] permissions, boolean[] results, boolean[] shouldRequestPermissionRationale) {

            }
        }, Manifest.permission.READ_PHONE_STATE);
    }

}