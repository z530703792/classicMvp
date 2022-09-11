package com.classic.android.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片旋转
 */
public class RotateImageUtils {

    private RotateImageUtils() {
        return;
    }

    public static void compressImage(String srcPath, String outFile) {
        /**
         * 获取图片的旋转角度，有些系统把拍照的图片旋转了，有的没有旋转
         */
        int degree = readPictureDegree(srcPath);

        final int DEF_MIN = 480;
        final int DEF_MAX = 1280;
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;// 只读边,不读内容
        Bitmap bitmap;
        BitmapFactory.decodeFile(srcPath, newOpts);
        int picMin = Math.min(newOpts.outWidth, newOpts.outHeight);
        int picMax = Math.max(newOpts.outWidth, newOpts.outHeight);
        float picShap = 1.0f * picMin / picMax;//原图的比例
        float defShap = 1.0f * DEF_MIN / DEF_MAX;
        float scale = 1f;
        if (picMax < DEF_MAX) {

        } else if (picShap > defShap) {// 比较 方正的，等比压缩至最大值
            scale = 1.0f * DEF_MAX / picMax;

        } else if (picMin > DEF_MIN) {// 较窄边压缩至最小值
            scale = 1.0f * DEF_MIN / picMin;
        }
        int scaledWidth = (int) (scale * newOpts.outWidth);
        int scaledHeight = (int) (scale * newOpts.outHeight);
        newOpts.inSampleSize = (int) ((scale == 1 ? 1 : 2) / scale);
        //LogUtils.i("------inSampleSize---->", newOpts.inSampleSize + "");
        newOpts.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        if (scale != 1) {
            Matrix matrix = new Matrix();
            scale = (float) scaledWidth / bitmap.getWidth();
            matrix.setScale(scale, scale);
            Bitmap bitmapNew = Bitmap.createBitmap(bitmap, 0, 0,
                    bitmap.getWidth(), bitmap.getHeight(), matrix, false);
            bitmap.recycle();
            bitmap = bitmapNew;
        }
        int quality;
        if (scaledHeight * scaledWidth > (800 * 480)) {
            quality = 30;
        } else {
            quality = 80 - (int) ((float) scaledHeight * scaledWidth
                    / (800 * 480) * 50);
        }
        try {
            /**
             * 把图片旋转为正的方向
             */
            bitmap = rotaingImageView(degree, bitmap);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality,
                    new FileOutputStream(new File(outFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.recycle();
        System.gc();
    }

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 通知系统添加了新的图片,更新相册
     *
     * @param filePath
     * @param context
     */
    public static void scanPhotos(String filePath, Context context) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setData(uri);
        context.sendBroadcast(intent);
    }

    /**
     * 根据Uri获取到图片的绝对路径
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getFilePathByContentResolver(Context context, Uri uri) {
        if (null == uri) {
            return null;
        }
        Cursor c = context.getContentResolver().query(uri, null, null, null, null);
        String filePath = null;
        if (null == c) {
            throw new IllegalArgumentException(
                    "Query on " + uri + " returns null result.");
        }
        try {
            if ((c.getCount() != 1) || !c.moveToFirst()) {
            } else {
                filePath = c.getString(
                        c.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA));
            }
        } finally {
            c.close();
        }
        return filePath;
    }
}