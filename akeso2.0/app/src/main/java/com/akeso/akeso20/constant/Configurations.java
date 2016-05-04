package com.akeso.akeso20.constant;

import android.os.Environment;

/**
 * Created by changfeifan on 16/4/21.
 */
public class Configurations {
    //    public static final String APIDOMAIN = "http://staging.akeso.cn";
    public static final String APIDOMAIN = "http://www.akeso.cn";
    public static final String GET_TOKEN = APIDOMAIN + "";

    //请求1.0
    public static final String PostDemo = APIDOMAIN + "";
    public static final String POSTLOGIN = APIDOMAIN + "/api/v1/auth/login";
    //    public static final String POSTREGISTER = APIDOMAIN + "/api/v1/auth/register";
    //    public static final String POSTCHECKPHONE = APIDOMAIN + "/api/v1/auth/checkphone";
    //    public static final String POSTCHECKCODE = APIDOMAIN + "/api/v1/auth/checkcode";
    public static final String POSTFORGOT = APIDOMAIN + "/api/v1/auth/forgot";
    public static final String POSTUPDATE = APIDOMAIN + "/api/v1/auth/update";
    public static final String POSTME = APIDOMAIN + "/api/v1/me";
//    public static final String GETME = APIDOMAIN + "/api/v1/me";

    //请求2.0
    public static final String POSTTOKEN = APIDOMAIN + "/oauth/token";
    public static final String POSTCHECKPHONE = APIDOMAIN + "/api/v2/check_phone";
    public static final String POSTREGISTER = APIDOMAIN + "/api/v2/register";
    public static final String POSTSENDCODE = APIDOMAIN + "/api/v2/send_code";
    public static final String POSTRESETPASSWORD = APIDOMAIN + "/api/v2/reset_password";
    public static final String POSTREVOKE = APIDOMAIN + "/api/oauth/revoke";
    public static final String GETME = APIDOMAIN + "/api/v2/me";

    // 路径
    public static final String PATH_APPLICATION = Environment.getDataDirectory().getPath() + "/data/com.akeso.akeso";
    public static final String PATH_DOWNLOAD = PATH_APPLICATION + "/download/";
    public static final String PATH_UNZIP = PATH_APPLICATION + "/unzipped/";
    public static final String PATH_MUSIC = PATH_APPLICATION + "/music/";
    public static final String PATH_SHARED_PREFS = PATH_APPLICATION + "/shared_prefs/";
    public static final String PATH_CUSTOMPLAN = PATH_APPLICATION + "/customplan/";
    public static final String PATH_DATABASE = PATH_APPLICATION + "/database/";
    public static final String PATH_FILE = PATH_APPLICATION + "/files/";
    public static final String PATH_MESSAGE = PATH_FILE + "message/";
    public static final String PATH_NOTICE = PATH_FILE + "notice/";

    // 文件
    public static final String FILE_DOWNLOAD_ZIP = PATH_DOWNLOAD + "myZip.zip";
    public static final String FILE_DOWNLOAD_MUSIC = PATH_DOWNLOAD + "musicZip.zip";
    // 名称
    public static final String FILENAME_DEMO = "DEMO.txt";
    public static final String FILENAME_USER = "User.txt";
    public static final String FILENAME_USERDATA = "UserData.txt";
    public static final String FILENAME_GLASSDATA = "GlassData.txt";
}
