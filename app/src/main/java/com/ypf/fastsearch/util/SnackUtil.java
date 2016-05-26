package com.ypf.fastsearch.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Administrator on 2016/4/21.
 */
public class SnackUtil {
    // android-support-design兼容包中新添加的一个类似Toast的控件。
    // make()中的第一个参数，可以写当前界面中的任意一个view对象。
    private static Snackbar mSnackbar;

    public static void show(View view, String msg, int flag) {
        if (flag == 0) { // 短时显示
            mSnackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        } else { // 长时显示
            mSnackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        }
        mSnackbar.show();
    }
}
