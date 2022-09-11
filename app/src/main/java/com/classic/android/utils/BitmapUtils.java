package com.classic.android.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: zcq 2019-08-29 10:55
 */
public class BitmapUtils {

    private static Bitmap view2Bitmap(View view, int bitmapWidth, int bitmapHeight) {
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.RGB_565);//Bitmap.Config.ARGB_8888
        view.draw(new Canvas(bitmap));
        return bitmap;
    }

    private static boolean writeBitmapToSDcard(Context context, String fileName, Bitmap bitmap, int compress) {
        String strPath = fileName;
        File file = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String galleryPath = Environment.getExternalStorageDirectory()
                    + File.separator + Environment.DIRECTORY_DCIM
                    + File.separator + "zcq" + File.separator;
            FileOutputStream fos = null;
            try {
                file = new File(galleryPath, strPath);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                fos = new FileOutputStream(file);

                // 当指定压缩格式为 PNG 时保存下来的图片显示正常
                bitmap.compress(Bitmap.CompressFormat.PNG, compress, fos);
                // 当指定压缩格式为 JPEG 时保存下来的图片背景为黑色
                //bitmap.compress(CompressFormat.JPEG, 100, fos);
                fos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bitmap.recycle();// 释放掉 bitmap
                    fos.close();
                    scanFile(context,file);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean view2PNG(Context context, View view, String fileName, int compress) {
        Bitmap bitmap = view2Bitmap(view, view.getMeasuredWidth(), view.getMeasuredHeight());
        if (bitmap == null) {
            return false;
        }
        return writeBitmapToSDcard(context, fileName, bitmap, compress);

    }

    /**
     * 针对系统文夹只需要扫描,不用插入内容提供者,不然会重复
     *
     * @param context 上下文
     * @param file 文件路径
     */
    private static void scanFile(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        context.sendBroadcast(intent);
    }

}
