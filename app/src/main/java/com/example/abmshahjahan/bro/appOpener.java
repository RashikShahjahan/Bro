package com.example.abmshahjahan.bro;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.List;


public class appOpener {

    public String getPackName(Context context, String name) {
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> l = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        String packName = "";
        for (ApplicationInfo ai : l) {
            String n = (String) pm.getApplicationLabel(ai);
            if (n.contains(name) || name.contains(n)) {
                packName = ai.packageName;
            }
        }

        return packName;
    }

    /**
     * Open another app.
     *
     * @param context     current Context, like Activity, App, or Service
     * @param packageName the full package name of the app to open
     * @return true if likely successful, false if unsuccessful
     */
    public static boolean openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();

        //throw new PackageManager.NameNotFoundException();
        Intent i = manager.getLaunchIntentForPackage(packageName);
        if (i == null) {
            return false;

        }
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        context.startActivity(i);
        return true;
    }
}
