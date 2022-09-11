package com.classic.android.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zcq on 2019/2/7.
 */

public class FileProvideFile {


    public static File getCameraFile(Context context) {
        String filename = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA)
                .format(new Date()) + ".png";
        File file = new File(Environment.getExternalStorageDirectory(), filename);
        if (!file.exists()) {
            file.mkdirs();
        }else {
            file.delete();
        }
        return file;
    }
}
