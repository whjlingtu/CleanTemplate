package com.project.whj.clearntemplate.presentation.ui.component.util;

import android.graphics.Color;
import android.view.View;

import com.project.whj.clearntemplate.R;
import com.trycatch.mysnackbar.Prompt;
import com.trycatch.mysnackbar.TSnackbar;


/**
 * 顶部SnackBae工具类
 */
public class TopSnackBar {

    private static TSnackbar snackBar;

    private static View viewGroup;

    /**
     * 警告snackbar
     */
    public static void showWaringBar(String msg) {
        snackBar = TSnackbar.make(viewGroup, msg, TSnackbar.LENGTH_SHORT, TSnackbar.APPEAR_FROM_TOP_TO_DOWN);
        snackBar.addIcon(R.mipmap.ic_launcher, 100, 100);
        snackBar.setPromptThemBackground(Prompt.WARNING);
        snackBar.show();
    }

    /**
     * 等待snackbar
     */
    public static void showWaitBar(String msg) {
        snackBar = TSnackbar.make(viewGroup, msg, TSnackbar.LENGTH_INDEFINITE, TSnackbar.APPEAR_FROM_TOP_TO_DOWN);
        snackBar.setPromptThemBackground(Prompt.SUCCESS);
        snackBar.addIconProgressLoading(0, true, false);
        snackBar.setBackgroundColor(Color.parseColor("#e7a31a"));
        snackBar.show();
    }

    /**
     * 成功snackbar
     */
    public static void showSuccessBar(String msg) {
        snackBar = TSnackbar.make(viewGroup, msg, TSnackbar.LENGTH_SHORT, TSnackbar.APPEAR_FROM_TOP_TO_DOWN);
        snackBar.setPromptThemBackground(Prompt.SUCCESS).show();
    }

    /**
     * 错误snackbar
     */
    public static void showErrorBar(String msg) {
        snackBar = TSnackbar.make(viewGroup, msg, TSnackbar.LENGTH_SHORT, TSnackbar.APPEAR_FROM_TOP_TO_DOWN);
        snackBar.setPromptThemBackground(Prompt.ERROR).show();
    }

    public static void setViewGroup(View vg){
        viewGroup = vg;
    }

}
