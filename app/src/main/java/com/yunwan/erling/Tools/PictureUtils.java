package com.yunwan.erling.Tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

/**
 * Created by win7 on 2017/3/10.
 */

public class PictureUtils {
    public static void PicassoSmall(Context context, String url, ImageView view, int x, int y) {
        // Picasso.with(context).load(url).resize(300, 300).into(view);
        double ff = 2.5;
        Glide.with(context).load(url).override((int) (x * ff), (int) (y * ff))
                .centerCrop().into(view);
    }

    public static void PicassoSmalll(Context context, String url, ImageView view) {
        Glide.with(context).load(url).fitCenter().into(view);
    }

    public static String NumberFomat(String s) {
        s = s.replaceAll("0+?$", "");//去掉后面无用的零
        s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉
        return s;
    }

    public static Bitmap getBitmap(Context context, String url) {
        Bitmap bm = null;
        try {
            bm = Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .centerCrop()
                    .into(200, 200)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return bm;
    }


}
