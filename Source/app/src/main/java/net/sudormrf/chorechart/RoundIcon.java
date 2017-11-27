package net.sudormrf.chorechart;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

/**
 * Created by Josh on 2017-11-27.
 */

//Rounded images adapted from: https://stackoverflow.com/questions/24878740/how-to-use-roundedbitmapdrawable

public class RoundIcon {
    public static RoundedBitmapDrawable roundedImage(Resources res, int iconResource) {
        Bitmap src = BitmapFactory.decodeResource(res, iconResource);
        RoundedBitmapDrawable iconBitmap =
                RoundedBitmapDrawableFactory.create(res, src);
        iconBitmap.setCircular(true);

        return iconBitmap;
    }
}
