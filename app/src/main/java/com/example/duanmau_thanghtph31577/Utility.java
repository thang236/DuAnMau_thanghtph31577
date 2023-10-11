package com.example.duanmau_thanghtph31577;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utility {

    public static void showCustomToastError(Context context, String message) {
        // Inflate the custom view
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast_error, (ViewGroup) ((Activity) context).findViewById(R.id.view_container_error));

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textView = view.findViewById(R.id.text_toast_error);
        textView.setText(message);

        // Set layout parameters to match the screen width
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(((Activity) context).getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setView(view);  // Set the custom view
        toast.setGravity(Gravity.FILL_HORIZONTAL| Gravity.TOP, 0, 0);
        toast.show();

        toast.show();
    }

    public static void showCustomToastSuccess(Context context, String message) {
        // Inflate the custom view
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast_success, (ViewGroup) ((Activity) context).findViewById(R.id.view_container_succeeded));

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textView = view.findViewById(R.id.toast_text);
        textView.setText(message);

        // Set layout parameters to match the screen width
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(((Activity) context).getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setView(view);  // Set the custom view
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
        toast.show();

        toast.show();
    }

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
