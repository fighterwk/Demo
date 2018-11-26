package com.kyle.permission;

/**
 * @Description描述: 申请权限结果回调
 * @Author作者: Kyle
 * @Date日期: 2018/1/3
 */
public interface OnPermissionCallback {

    /**
     * 响应申请权限最后结果
     *
     * @param permissions                      申请权限集合
     * @param results                          权限授权结果
     * @param shouldRequestPermissionRationale 是否应该向UI显示请求权限的基本原理。
     *                                         只有在没有权限和请求权限的情况下，您才应该这样做，因为请求的权限并没有清楚地告诉用户授予该权限的好处是什么。
     *                                         <p>例如，如果你编写了一个相机应用程序，请求相机许可将会被用户所期望，而没有理由要求它被要求。
     *                                         然而，如果应用程序需要定位照片的位置，那么一个非技术的用户可能会想知道位置是如何与拍照相关联的。
     *                                         在这种情况下，您可以选择向UI显示请求此权限的基本原理。
     *                                         </p>
     */
    void onRequestPermissionsResult(String[] permissions, boolean[] results,
                                    boolean[] shouldRequestPermissionRationale);
}
