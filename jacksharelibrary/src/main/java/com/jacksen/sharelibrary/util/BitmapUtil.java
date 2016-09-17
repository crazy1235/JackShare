package com.jacksen.sharelibrary.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Admin on 2016/8/25.
 */

public class BitmapUtil {


    /**
     * bitmap to byte array
     *
     * @param bitmap
     * @return
     */
    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//            bitmap.recycle();
            byte[] result = outputStream.toByteArray();
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        } else {
            Log.e("BitmapUtil", "bitmap == null or bitmap.isRecycled()");
            return null;
        }

    }

    public static Bitmap decodeImageFile(String path, float width, float height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = getInSampleSize(path, width, height);
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(path, options);
    }

    private static int getInSampleSize(String path, float width, float height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        return (int) getScale(width, height, outWidth, outHeight);
    }

    private static double getScale(float targetWidth, float targetHeight, float oriWidth, float oriHeight) {
        double scale;
        if (oriWidth >= oriHeight) {
            float widthScale = oriWidth / targetWidth;
            float heightScale = oriHeight / targetHeight;
            if (widthScale >= heightScale) {
                scale = Math.rint(widthScale);
            } else {
                scale = Math.rint(heightScale);
            }
        } else {
            float widthScale = oriWidth / targetWidth;
            float heightScale = oriHeight / targetHeight;
            if (widthScale >= heightScale) {
                scale = widthScale;
            } else {
                scale = heightScale;
            }
        }
        if (scale <= 0) {
            return 1.0d;
        }
        return scale;
    }

}
