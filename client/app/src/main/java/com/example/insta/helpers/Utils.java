package com.example.insta.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.insta.view.profile.ProfileActivity;

public class Utils {
    public static String adres = "http://192.168.1.20:3000"; //192.168.119.103  192.168.1.20
    public static String token;
    public static String album;


    public static String getPath(Context context, Uri uri ) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver( ).query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }

    public static boolean checkIfPermissionsGranted(String[] REQUIRED_PERMISSIONS, Context context) {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void checkPermission(String permission, int requestCode, Context context, Activity activity) {
        // jeśli nie jest przyznane to zażądaj
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
        } else {
            Toast.makeText(context, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }
}
