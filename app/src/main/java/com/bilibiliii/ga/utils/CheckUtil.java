package com.bilibiliii.ga.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author No.47 create at 2017/11/9.
 */
public class CheckUtil {
    private static final String EMAIL_CHECK_CODE = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

    public static boolean isUserNameValid(String userName) {
        if (!TextUtils.isEmpty(userName)) {
            return true;
        }
        return false;
    }

    public static boolean isEmailValid(String email) {
        if (!TextUtils.isEmpty(email)) {
            Pattern pattern = Pattern.compile(EMAIL_CHECK_CODE);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
        return false;
    }

    public static boolean isPasswordValid(String password) {
        if (!TextUtils.isEmpty(password)) {
            return true;
        }
        return false;
    }

}
