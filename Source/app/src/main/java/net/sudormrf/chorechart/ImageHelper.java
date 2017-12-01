package net.sudormrf.chorechart;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 * Created by Josh on 2017-11-27.
 */

//Rounded images adapted from: https://stackoverflow.com/questions/24878740/how-to-use-roundedbitmapdrawable

public class ImageHelper {
    public static RoundedBitmapDrawable roundedImage(Resources res, int iconResource) {
        Bitmap src = BitmapFactory.decodeResource(res, iconResource);
        RoundedBitmapDrawable iconBitmap =
                RoundedBitmapDrawableFactory.create(res, src);
        iconBitmap.setCircular(true);

        return iconBitmap;
    }

    public static RoundedBitmapDrawable roundedImageFromBase64(Resources res, String encodedData)
    {
        Bitmap img = bitmapFromBase64(encodedData);
        RoundedBitmapDrawable result = RoundedBitmapDrawableFactory.create(res, img);
        result.setCircular(true);

        return result;
    }

    public static Bitmap bitmapFromBase64(String encodedData)
    {
        byte[] data = Base64.decode(encodedData, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    //Getting byte array from Bitmap
    //https://stackoverflow.com/questions/4989182/converting-java-bitmap-to-byte-array
    public static String bitmapToBase64(Bitmap btm)
    {
        int size = btm.getRowBytes() * btm.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        btm.copyPixelsToBuffer(byteBuffer);
        return Base64.encodeToString(byteBuffer.array(), Base64.DEFAULT);
    }

    public static String bitmapToBase64(Bitmap btm, Bitmap.CompressFormat compression, int quality)
    {
        ByteArrayOutputStream compressPicOut = new ByteArrayOutputStream();
        btm.compress(compression, quality, compressPicOut);
        return Base64.encodeToString(compressPicOut.toByteArray(), Base64.DEFAULT);
    }

}
