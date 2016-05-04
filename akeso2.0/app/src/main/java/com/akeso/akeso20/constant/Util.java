package com.akeso.akeso20.constant;

import android.util.Base64;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by changfeifan on 16/4/21.
 */
public class Util {

    //请求头部
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    //post方法
    public static JSONObject makePostRequest(String info, String url) {
        return makePostRequest(info, url, null);
    }

    public static JSONObject makePostRequest(String info, String url, String token) {
        JSONObject jo = null;
        try {
            RequestBody body = RequestBody.create(JSON, info);

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Content-type", "application/json")
//                    .addHeader("Authorization", token)
                    .post(body)
                    .build();
            Response response = OkHttpUtil.execute(request);
            if (response.isSuccessful()) {
                jo = new JSONObject(response.body().string());
                jo.put("code", response.code());

            } else {
                jo = new JSONObject();
                jo.put("code", response.code());
                throw new IOException("Unexpected code " + response);
            }

//            SSLSocketFactory socketFactory = SSLTrustAllSocketFactory.getSocketFactory();
//            HttpParams params = new BasicHttpParams();U
//            HttpConnectionParams.setSoTimeout(params, timeout);
//            HttpClient httpClient = new DefaultHttpClient(params);
//            SchemeRegistry registry = httpClient.getConnectionManager().getSchemeRegistry();
//            registry.register(new Scheme("https", socketFactory, Configurations.HTTPSPORT));
////            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), Configurations.HTTPPORT));
//            HttpPost httpPost = new HttpPost(url);
//
//            httpPost.setEntity(new StringEntity(info, "UTF8"));
//            httpPost.setHeader("Content-type", "application/json");
//            if (token != null) {
//                httpPost.setHeader("Authorization", token);
//            }
//            HttpResponse resp = httpClient.execute(httpPost);
//
//            if (resp != null) {
//
//                if (resp.getStatusLine().getStatusCode() == 401) {
//                    //logout
//                    AccessTokenKeeper.clearAndGotoHomePage();
//                    response = null;
//                } else {
//                    String result = EntityUtils.toString(resp.getEntity());
//                    response = new JSONObject(result);
//                }
//            }

//            Log.d("Status line", "" + resp.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jo;
    }

    //post方法with token
    public static JSONObject makePostRequestwithtoken(String info, String url, String token, String email) {
        JSONObject jo = null;
        try {
            RequestBody body = RequestBody.create(JSON, info);

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Content-type", "application/json")
                    .addHeader("Authorization", "")
                    .addHeader("X-Api-Token", token)
                    .addHeader("X-Api-Email", email)
                    .post(body)
                    .build();
            Response response = OkHttpUtil.execute(request);
            if (response.isSuccessful()) {
                jo = new JSONObject(response.body().string());
            } else {
                throw new IOException("Unexpected code " + response);
            }

//            SSLSocketFactory socketFactory = SSLTrustAllSocketFactory.getSocketFactory();
//            HttpParams params = new BasicHttpParams();U
//            HttpConnectionParams.setSoTimeout(params, timeout);
//            HttpClient httpClient = new DefaultHttpClient(params);
//            SchemeRegistry registry = httpClient.getConnectionManager().getSchemeRegistry();
//            registry.register(new Scheme("https", socketFactory, Configurations.HTTPSPORT));
////            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), Configurations.HTTPPORT));
//            HttpPost httpPost = new HttpPost(url);
//
//            httpPost.setEntity(new StringEntity(info, "UTF8"));
//            httpPost.setHeader("Content-type", "application/json");
//            if (token != null) {
//                httpPost.setHeader("Authorization", token);
//            }
//            HttpResponse resp = httpClient.execute(httpPost);
//
//            if (resp != null) {
//
//                if (resp.getStatusLine().getStatusCode() == 401) {
//                    //logout
//                    AccessTokenKeeper.clearAndGotoHomePage();
//                    response = null;
//                } else {
//                    String result = EntityUtils.toString(resp.getEntity());
//                    response = new JSONObject(result);
//                }
//            }

//            Log.d("Status line", "" + resp.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();

        }

        return jo;
    }

    //put请求
    public static JSONObject makePutRequest(String entity, String url, String token) {
        JSONObject jo = null;
        try {
            RequestBody body = RequestBody.create(JSON, entity);

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Content-type", "application/json")
                    .addHeader("Authorization", token)
                    .put(body)
                    .build();
            Response response = OkHttpUtil.execute(request);
            if (response.isSuccessful()) {
                jo = new JSONObject(response.body().string());
            } else {
                throw new IOException("Unexpected code " + response);
            }


//            String response = "";
//            SSLSocketFactory socketFactory = SSLTrustAllSocketFactory.getSocketFactory();
//            Scheme sch = new Scheme("https", socketFactory, 443);
//            HttpParams params = new BasicHttpParams();
//            HttpConnectionParams.setSoTimeout(params, timeout);
//            HttpClient mHttpClient = new DefaultHttpClient(params);
//            mHttpClient.getConnectionManager().getSchemeRegistry()
//                    .register(sch);
//            HttpPut httpPut = new HttpPut(url);
//
//            //Log.e(Log_Tag, "putSystemPlanAsyncTask array_wholePlan.toString(): " + array_wholePlan.toString());
//            //Log.e(Log_Tag,"getBasicInfo: "+ getBasicInfo(1));
//            httpPut.setEntity(new StringEntity(entity, "UTF8"));
//            //HttpResponse resp = httpClient.execute(httpPost);
//            httpPut.setHeader("Content-type", "application/json");
//            httpPut.setHeader("Authorization", token);
//            HttpResponse resp = mHttpClient.execute(httpPut);
//            if (resp != null) {
//                if (resp.getStatusLine().getStatusCode() == 401) {
//                    //logout
//                    AccessTokenKeeper.clearAndGotoHomePage();
//                    jo = null;
//                } else {
//                    String result = EntityUtils.toString(resp.getEntity());
//                    jo = new JSONObject(result);
//                }
//
//
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo;
    }

    //get请求
    public static JSONObject makeGetRequest(String url, String token) {
        JSONObject jo = null;
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Content-type", "application/json")
                    .addHeader("Authorization", token)
                    .build();
            Response response = OkHttpUtil.execute(request);
            if (response.isSuccessful()) {
                jo = new JSONObject(response.body().string());
                jo.put("code", response.code());

            } else {
                jo = new JSONObject();
                jo.put("code", response.code());
                throw new IOException("Unexpected code " + response);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo;
    }

    //get请求withToken
    public static JSONObject makeGetRequestWithToken(String url, String token, String email) {
        JSONObject jo = null;
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Content-type", "application/json")
                    .addHeader("Authorization", "")
                    .addHeader("X-Api-Token", token)
                    .addHeader("X-Api-Email", email)
                    .build();
            Response response = OkHttpUtil.execute(request);
            if (response.isSuccessful()) {
                jo = new JSONObject(response.body().string());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo;
    }

    public static String decode(String text) {
        try {
            byte[] key = "z8M1CQ!u0000000000000000z8M1CQ!u".getBytes("UTF-8");
            byte[] keyiv = "L92gL9YI".getBytes("UTF-8");
            byte[] decodedBase64 = Base64.decode(text, Base64.DEFAULT);
            byte[] desDecode = Util.des3DecodeCBC(key, keyiv, decodedBase64);
            return new String(desDecode, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static byte[] des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }

}
