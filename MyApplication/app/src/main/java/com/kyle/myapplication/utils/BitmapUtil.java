package com.kyle.myapplication.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/9/13
 */
public class BitmapUtil {

    /**
     * 调用相册选择图片
     *
     * @param acty
     * @param requestCode
     */
    public static void startPickActivity(Activity acty, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        acty.startActivityForResult(intent, requestCode);
    }

    /**
     * 获取内容的真实路径
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getRealContentUri(Context context, Uri uri) {
        ContentResolver cr = context.getContentResolver();
        String[] projection = new String[]{MediaStore.Images.Media.DATA};  // 查询字段
        Cursor cursor = cr.query(uri, projection, null, null, null);
        String path = null;
        try {
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndexOrThrow = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndexOrThrow);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor != null){
                cursor.close();
            }
        }

        return path;
    }

    public static Bitmap loadRectBitmap(Rect rect, String path) throws IOException {
        BitmapRegionDecoder regionDecoder = BitmapRegionDecoder.newInstance(path, false);
        Bitmap bitmap = regionDecoder.decodeRegion(rect, new BitmapFactory.Options());
        return bitmap;
    }

}
