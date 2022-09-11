/**
 * 
 */
package com.classic.base_library.utils;

import android.content.Context;
import android.view.WindowManager;

public class AppUtil {
    

    public static int[] getScreenDispaly(Context context) {
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int width = (int) (windowManager.getDefaultDisplay().getWidth());
		int height = windowManager.getDefaultDisplay().getHeight();
		int result[] = { width, height };
		return result;
	}
}
