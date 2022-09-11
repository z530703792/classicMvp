package com.classic.android.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;

import com.classic.base_library.App.App;
import com.classic.base_library.utils.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sj
 */
public class FileUtil {

    public static final String APP_NAME = "wjinker";
    public static final String IMAGE_FOLDER = "image-path";
    public static final String VOICE_FOLDER = "voice-path";
    public static final String VIDEO_FOLDER = "video-path";

    public static String createImageName() {
        return createFileName("png");
    }

    public static String createVioceName() {
        return createFileName("amr");
    }

    public static String createVioceName(String fileName) {
        return createFileName(fileName, "amr");
    }

    public static String createVideoName() {
        return createFileName("mp4");
    }

    public static String createVideoName(String fileName) {
        return createFileName(fileName, "mp4");
    }

    public static String createFileName(String suffix) {
        return createFileName(null, suffix);
    }

    public static String createFileName(String fileName, String suffix) {
        if (TextUtils.isEmpty(fileName)) {
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSSSSSSS").format(new Date());
            fileName = "thumb_" + timeStamp;
            //LogUtils.i("", "---------------------------->fileName=" + fileName);
        }
        fileName += "." + suffix;// 照片命名;
        return fileName;
    }

    public static String createImagePath() {
        return createFilePath(IMAGE_FOLDER) + "/" + createImageName();
    }

    public static String createViocePath() {
        return createFilePath(VOICE_FOLDER) + "/" + createVioceName();
    }


    public static String createViocePath(String fileName) {
        return createFilePath(VOICE_FOLDER) + "/" + createVioceName(fileName);
    }

    public static String createVideoPath() {
        return createFilePath(VIDEO_FOLDER) + "/" + createVideoName();
    }

    public static String createVideoPath(String fileName) {
        return createFilePath(VIDEO_FOLDER) + "/" + createVideoName(fileName);
    }

    public static String createFilePath(String folder) {
        return getFileDir(folder);
    }


    public static String getFileDir(String folder) {
        return getFileDir(App.getInstance(), folder);
    }

    public static String getFileDir(Context context, String folder) {
        String dir = getDir(context);
        String path = dir + "/" + APP_NAME + "/" + folder;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    public static String getDir() {
        return getDir(App.getInstance().context);
    }

    public static String getDir(Context context) {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            return context.getFilesDir().getAbsolutePath();
        }
    }


    public static String compress(String path) {
        try {
            float w = 500;
            float h = 500;

            String mergePath = createImagePath();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);

            float scale = options.outWidth / w;
            float scale1 = options.outHeight / h;
            if (scale < scale1)
                scale = scale1;
/*
            LogUtils.i("", String.format("------------>options.outWidth=%d, options.outHeight=%d", options.outWidth, options.outHeight));
            LogUtils.i("", String.format("------------>scale=%f", scale));*/
            options.inJustDecodeBounds = false;
            options.inSampleSize = (int) scale;
            Bitmap cs = BitmapFactory.decodeFile(path, options);
            OutputStream os = new FileOutputStream(mergePath);
            if (path.endsWith("jpg") || path.endsWith("JPG") || path.endsWith("JPEG")) {
                cs.compress(Bitmap.CompressFormat.JPEG, 100, os);
            } else {
                cs.compress(Bitmap.CompressFormat.PNG, 100, os);
            }
            return mergePath;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

//    public static void saveMyBitmap(Bitmap mBitmap, String bitName) {
//        File f = new File(createFilePath("Mycode/") + bitName + ".jpg");
//        FileOutputStream fOut = null;
//        if (f.exists()) {
//            f.delete();
//        }
//        try {
//            fOut = new FileOutputStream(f);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
//        try {
//            fOut.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            fOut.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    public static File saveMyBitmap(Bitmap mBitmap, String bitName) {
        File f = new File(createFilePath("Mycode/") + bitName + ".jpg");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            return f;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File saveMyBitmap(Bitmap mBitmap, String folder, String bitName) {
        File f = new File(createFilePath(folder + "/") + bitName + ".jpg");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            return f;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String saveFile(byte[] bytes, String fileName) {

        String path = createFilePath("apps/") + fileName;
        File f = new File(path);
        FileOutputStream bos = null;
        if (f.exists()) {
            f.delete();
        }
        try {
            bos = new FileOutputStream(f);
            bos.write(bytes);
            bos.flush();
            bos.close();

            return path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static boolean saveFileWithPath(byte[] bytes, String path) {

        File f = new File(path);
        FileOutputStream bos = null;
        if (f.exists()) {
            f.delete();
        }
        try {
            bos = new FileOutputStream(f);
            bos.write(bytes);
            bos.flush();
            bos.close();

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static String saveFileWithPath(InputStream inputStream, String path) {

        //在这里用到了文件输出流
        FileOutputStream fileOutputStream = null;
        try {
            //把请求成功的response转为字节流
            File downloadedFile = new File(path);
            if (downloadedFile.exists()) {
                downloadedFile.delete();
            }


            fileOutputStream = new FileOutputStream(downloadedFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, read);
            }
            //LogUtils.d("wuyinlei", "文件下载成功...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    // outputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return path;
    }

    public static String saveFile(InputStream inputStream, String fileName) {

        String path = createFilePath("apps/") + fileName;

        return saveFileWithPath(inputStream, path);
    }


    public static String getAssetFilePath(Context context, String fileName) {
        String filePath = createFilePath(IMAGE_FOLDER) + "/" + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            return filePath;
        }

        AssetManager assetManager = context.getAssets();

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(fileName);
            out = new FileOutputStream(file);
            copyFile(in, out);
        } catch(IOException e) {
            e.printStackTrace();
            filePath = "";
            file.delete();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filePath;
    }

    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    public static String getFileRoot(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File external = context.getCacheDir();
            if (external != null) {
                return external.getAbsolutePath();
            }
        } else {
            ToastUtil.showToast("sd不可用");
        }
        return context.getFilesDir().getAbsolutePath();
    }
}
