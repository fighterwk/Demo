//package com.kyle.okhttp;
//
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.text.TextUtils;
//import android.util.Log;
//
//import org.json.JSONObject;
//import org.xutils.common.Callback;
//import org.xutils.common.util.KeyValue;
//import org.xutils.http.BaseParams;
//import org.xutils.http.HttpMethod;
//import org.xutils.http.RequestParams;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.Call;
//import okhttp3.FormBody;
//import okhttp3.HttpUrl;
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import okhttp3.logging.HttpLoggingInterceptor;
//import okio.BufferedSink;
//
//
///**
// * @Description描述:网络请求
// * @Author作者: liuyixin
// * @Date日期: 2016/8/19
// * @修改人:Kyle
// * @修改日期:2017-9-20
// * @修改内存:修改Context,NetWorkCallBack引用方式,由强引用改为弱引用。
// */
//public class HttpUtil {
//
//    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
//
//    private static final String TAG = "HttpUtil";
//    private Context context;
//    private String url;
//    private NetWorkCallBack netWorkCallBack;
//    private int tag;
//    private static final String HEADER_SIGN = "sign";
//    private static final String HEADER_NONESTR = "noncestr";
//    private static final String HEADER_APPTOKEN = "apptoken";
//    private static final String HEADER_USERID = "userid";
//
//    private static OkHttpClient okHttpClient;
//
//    static {
//        okHttpClient = new OkHttpClient()
//                .newBuilder()
//                .readTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .connectTimeout(15, TimeUnit.SECONDS)
//                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                    @Override
//                    public void log(String message) {
//                        Log.d(TAG, message);
//                    }
//                }))
//                .build();
//    }
//
//    /**
//     * xUtils3 构造方法
//     *
//     * @param context  //上下文对象
//     * @param callBack //回调
//     * @param tag      //标记
//     */
//    public HttpUtil(Context context, NetWorkCallBack callBack, int tag) {
//        this.context = context.getApplicationContext();
//        this.netWorkCallBack = callBack;
//        this.tag = tag;
//    }
//
//    /**
//     * xUtils3 构造方法
//     *
//     * @param context  //上下文对象
//     * @param callBack //回调
//     */
//    public HttpUtil(Context context, NetWorkCallBack callBack) {
//        this.context = context.getApplicationContext();
//        this.netWorkCallBack = callBack;
//    }
//
//    /**
//     * 用户行为记录
//     *
//     * @param t_url   //地址
//     * @param context //上下文对象
//     */
//    public HttpUtil(Context context, String t_url) {
//        this.context = context.getApplicationContext();
//        this.url = BaseConstants.USER_ACTION_URL + t_url;
//        NetWorkCallBack netWorkCallBack = new NetWorkCallBack() {
//            @Override
//            public void callBack(String data, int tag) {
//                Log.i("UserAction", "请求地址:" + url);
//                Log.i("UserAction", "callBack" + data);
//            }
//
//            @Override
//            public void callError(Throwable e, int tag) {
//                Log.i("UserAction", "callError" + e.getMessage());
//            }
//        };
//        this.netWorkCallBack = netWorkCallBack;
//    }
//
//    public static HttpUtil get(Context context, NetWorkCallBack callBack, int tag) {
//        return new HttpUtil(context, callBack, tag);
//    }
//
//    public static HttpUtil get(Context context, NetWorkCallBack callBack) {
//        return new HttpUtil(context, callBack);
//    }
//
//
//    /* xUtils3  post 上传图片 */
//    public void postImage(RequestParams params) throws Exception {
//        startReques(HttpMethod.POST, params, cacheCallBack);
//    }
//
//    /* post 上传图片网络医院 */
//    public void postImageToNetHos(RequestParams params, String appToken, String noneStr, String mUserid, String sign) throws Exception {
//        if (isNetworkConnected()) {
//            params.addHeader(HEADER_APPTOKEN, appToken);
//            params.addHeader(HEADER_NONESTR, noneStr);
//            params.addHeader(HEADER_USERID, mUserid);
//            params.addHeader(HEADER_SIGN, sign);
//            params.setUseCookie(false);
//            startReques(HttpMethod.POST, params, cacheCallBack);
//        } else {
//            if (netWorkCallBack != null)
//                netWorkCallBack.callError(new Exception("网络异常"), tag);
//        }
//
//    }
//
//    /* get 请求过滤返回结果 */
//    public Call get(RequestParams params) throws Exception {
//        return startReques(HttpMethod.GET, params, mCallBack);
//    }
//
//    /* get 不过滤返回结果*/
//    public Call getSimpleData(RequestParams params) throws Exception {
//        return startReques(HttpMethod.GET, params, simpleCallBack);
//    }
//
//
//    /* post 请求 过滤返回结果*/
//    public Call post(RequestParams params) throws Exception {
//        params.setAsJsonContent(true);
//        params.setBodyContent(params.toJSONString());
//        return startReques(HttpMethod.POST, params, mCallBack);
//    }
//
//    /* post 这个地方在发布评论会用到*/
//    public Call postNoJson(RequestParams params) throws Exception {
//        return startReques(HttpMethod.POST, params, mCallBack);
//    }
//
//    /* post 不过滤返回结果 */
//    public Call postSimpleData(RequestParams params) throws Exception {
//        params.setAsJsonContent(true);
//        params.setBodyContent(params.toJSONString());
//        return startReques(HttpMethod.POST, params, simpleCallBack);
//
//    }
//
//    /**
//     * 网络医院的请求
//     *
//     * @param httpUtil
//     * @param context
//     * @param params
//     * @throws Exception
//     */
//    public Call requestWlyy(final HttpUtil httpUtil, Context context, final RequestParams params) throws Exception {
//        final Call[] call = new Call[1];
//        final HttpUtil httpUtil1 = new HttpUtil(context, new NetWorkCallBack() {
//            @Override
//            public void callBack(String data, int tag) {
//                try {
//                    JSONObject jsonObject = new JSONObject(data);
//                    String mApptoken = jsonObject.getJSONObject("Data").getString("apptoken");
//                    String mNoncestr = jsonObject.getJSONObject("Data").getString("noncestr");
//                    String mUserid = jsonObject.getJSONObject("Data").getString("userid");
//                    String mSign = jsonObject.getJSONObject("Data").getString("sign");
//                    if (TextUtils.isEmpty(mApptoken) ||
//                            TextUtils.isEmpty(mNoncestr) ||
//                            TextUtils.isEmpty(mUserid) ||
//                            TextUtils.isEmpty(mSign)
//                            ) {
//                        return;
//                    }
//                    params.addHeader("apptoken", mApptoken);
//                    params.addHeader("noncestr", mNoncestr);
//                    params.addHeader("sign", mSign);
//                    params.addHeader("userid", mUserid);
//                    if (params.getMethod() == HttpMethod.POST) {
//                        call[0] = httpUtil.post(params);
//                    } else {
//                        call[0] = httpUtil.get(params);
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void callError(Throwable e, int tag) {
//                netWorkCallBack.callError(e, tag);
//            }
//        });
//        call[0] = httpUtil1.post(new RequestParams(BaseConstants.SERVER_URL + ConstantURLs.GET_KM_CONFIG));
//        return call[0];
//    }
//
//    /**
//     * 取消请求
//     *
//     * @param cancelable
//     */
//    public static void cancel(Callback.Cancelable cancelable) {
//        if (cancelable != null && !cancelable.isCancelled()) {
//            cancelable.cancel();
//        }
//
//        cancelable = null;
//    }
//
//    private okhttp3.Callback mCallBack = new okhttp3.Callback() {
//
//        @Override
//        public void onFailure(Call call, IOException e) {
//            Log.d(TAG, "请求失败:" + e.getMessage());
//            if (netWorkCallBack != null)
//                netWorkCallBack.callError(new Exception("请求失败,请稍后再试!"), tag);
//        }
//
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
//            if (response.isSuccessful()) {
//                String result = response.body().string();
//                try {
//                    JSONObject json = new JSONObject(result);
//                    if (json.has("resultCode") || json.has("IsSuccess")) {
//                        if (getJsonInt(json, "resultCode") == 0 || getJsonBoolean(json, "IsSuccess")) {
//                            if (netWorkCallBack != null)
//                                netWorkCallBack.callBack(result, tag);
//                        } else if (getJsonInt(json, "resultCode") == -2) {
//                            if (netWorkCallBack != null)
//                                netWorkCallBack.callError(new ReLoginException(), tag);
//                        } else {
//                            if (netWorkCallBack != null)
//                                netWorkCallBack.callError(new Exception(getJsonStr(json, "ResultMessage")), tag);
//                        }
//                    } else if (json.has("ResultCode")) {
//                        if (getJsonInt(json, "ResultCode") == 0) {
//                            if (netWorkCallBack != null)
//                                netWorkCallBack.callBack(result, tag);
//                        } else if (getJsonInt(json, "ResultCode") == -2) {
//                            if (netWorkCallBack != null)
//                                netWorkCallBack.callError(new ReLoginException(getJsonStr(json, "ResultMessage")), tag);
//                        } else {
//                            if (netWorkCallBack != null)
//                                netWorkCallBack.callError(new Exception(getJsonStr(json, "ResultMessage")), tag);
//                        }
//                    } else if (json.has("Status")) {
//                        if (getJsonInt(json, "Status") == 0) {
//                            if (netWorkCallBack != null)
//                                netWorkCallBack.callBack(result, tag);
//                        } else {
//                            if (netWorkCallBack != null)
//                                netWorkCallBack.callError(new NetException(getJsonStr(json, "Msg"), getJsonInt(json, "Status")), tag);
//                        }
//                    }
//
//                } catch (Exception e) {
//                    if (netWorkCallBack != null)
//                        netWorkCallBack.callError(new Exception(e.getMessage()), tag);
//                    e.printStackTrace();
//                }
//            } else {
//                if (netWorkCallBack != null)
//                    netWorkCallBack.callError(new Exception("请求失败[code:" + response.code() + "]"), tag);
//            }
//        }
//    };
//
//
//    private okhttp3.Callback simpleCallBack = new okhttp3.Callback() {
//
//        @Override
//        public void onFailure(Call call, IOException e) {
//            Log.d(TAG, "请求失败:" + e.getMessage());
//            if (netWorkCallBack != null)
//                netWorkCallBack.callError(new Exception("请求失败,请稍后再试!"), tag);
//        }
//
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
//            if (response.isSuccessful()) {
//                String result = response.body().string();
//                if (netWorkCallBack != null)
//                    netWorkCallBack.callBack(result, tag);
//            } else {
//                if (netWorkCallBack != null)
//                    netWorkCallBack.callError(new Exception("请求失败[code:" + response.code() + "]"), tag);
//            }
//        }
//    };
//
//    private okhttp3.Callback cacheCallBack = new okhttp3.Callback() {
//
//        @Override
//        public void onFailure(Call call, IOException e) {
//            Log.d(TAG, "请求失败:" + e.getMessage());
//            if (netWorkCallBack != null)
//                netWorkCallBack.callError(new Exception("请求失败,请稍后再试!"), tag);
//        }
//
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
//            if (response.isSuccessful()) {
//                String result = response.body().string();
//                if (netWorkCallBack != null)
//                    netWorkCallBack.callBack(result, tag);
//            } else {
//                if (netWorkCallBack != null)
//                    netWorkCallBack.callError(new Exception("请求失败[code:" + response.code() + "]"), tag);
//            }
//        }
//    };
//
//
//    /**
//     * 判断网络是否连接
//     */
//    private boolean isNetworkConnected() {
//        if (context == null) {
//            return false;
//        }
//        ConnectivityManager mConnectivityManager = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo mNetworkInfo = mConnectivityManager
//                .getActiveNetworkInfo();
//        if (mNetworkInfo != null) {
//            return mNetworkInfo.isAvailable();
//        }
//        //        }
//        return false;
//    }
//
//    private String getJsonStr(JSONObject json, String key) {
//        String str;
//        if (json.has(key)) {
//            try {
//                str = json.getString(key);
//            } catch (Exception e) {
//                str = "网络异常";
//                e.printStackTrace();
//            }
//        } else {
//            str = "网络异常";
//        }
//        return str;
//    }
//
//    private int getJsonInt(JSONObject json, String key) {
//        int code;
//        if (json.has(key)) {
//            try {
//                code = json.getInt(key);
//            } catch (Exception e) {
//                code = -401;
//                e.printStackTrace();
//            }
//        } else {
//            code = -401;
//        }
//        return code;
//    }
//
//    private boolean getJsonBoolean(JSONObject json, String key) {
//        boolean flag = false;
//        if (json.has(key)) {
//            try {
//                flag = json.getBoolean(key);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return flag;
//    }
//
//    /**
//     * 开始请求
//     *
//     * @param method   //请求方法
//     * @param params   //参数
//     * @param callback //回调
//     * @param <T>      //泛型
//     * @return //取消请求的回调对象
//     */
//    private <T> Call startReques(HttpMethod method, RequestParams params,
//                                 okhttp3.Callback callback) {
//
//        if (!isNetworkConnected()) {
//            if (netWorkCallBack != null)
//                netWorkCallBack.callError(new NetException("网络异常", -101), tag);
//            return null;
//        }
//
//        HttpUrl httpUrl = HttpUrl.parse(params.getUri());
//        Request.Builder requestBuilder = new Request.Builder();
//        requestBuilder.url(httpUrl);
//
//        for (BaseParams.Header header : params.getHeaders()) {
//            params.addHeader(header.key, header.getValueStr());
//        }
//
//        params.addHeader(SPUtils.TOKEN, SPUtils.getString(SPUtils.TOKEN, ""));
//
//        // GET请求
//        switch (method) {
//            case GET: {
//                List<KeyValue> stringParams = params.getStringParams();
//                HttpUrl.Builder builder = httpUrl.newBuilder();
//                for (KeyValue stringParam : stringParams) {
//                    builder.addQueryParameter(stringParam.key,
//                            stringParam.getValueStr());
//                }
//
//                requestBuilder.url(builder.build())
//                        .get();
//
//            }
//            break;
//            case POST:
//                // POST 请求
//                if (params.isAsJsonContent()) {
//                    // json 请求
//                    RequestBody body = RequestBody.create(MediaType.parse("application/json"),
//                            params.getBodyContent());
//                    requestBuilder.post(body);
//                } else {
//                    // 表单请求
//                    List<KeyValue> fileParams = params.getFileParams();
//                    if (fileParams.isEmpty()) {
//                        // 普通表单提交
//                        FormBody.Builder builder = new FormBody.Builder();
//                        List<KeyValue> stringParams = params.getStringParams();
//                        for (KeyValue stringParam : stringParams) {
//                            builder.add(stringParam.key, stringParam.getValueStr());
//                        }
//                    } else {
//                        // 多种类型表单提交(文本和文件)
//                        MultipartBody.Builder builder = new MultipartBody.Builder();
//                        List<KeyValue> stringParams = params.getStringParams();
//                        for (KeyValue stringParam : stringParams) {
//                            builder.addFormDataPart(stringParam.key, stringParam.getValueStr());
//                        }
//
//                        for (KeyValue fileParam : fileParams) {
//                            // TODO: 2018/1/4 暂时设置为image
//                            Object value = fileParam.value;
//                            if (value instanceof File) {
//                                File file = (File) value;
//                                builder.addFormDataPart(fileParam.key,
//                                        file.getName(),
//                                        RequestBody.create(MEDIA_TYPE_PNG, file)
//                                );
//                            } else if (value instanceof InputStream) {
//                                final InputStream is = (InputStream) value;
//                                RequestBody requestBody = new RequestBody() {
//                                    @Override
//                                    public MediaType contentType() {
//                                        return MEDIA_TYPE_PNG;
//                                    }
//
//                                    @Override
//                                    public void writeTo(BufferedSink sink) throws IOException {
//                                        byte[] buf = new byte[1024];
//                                        int read = -1;
//                                        while ((read = is.read(buf)) != -1) {
//                                            sink.write(buf, 0, read);
//                                        }
//                                    }
//
//                                };
//
//                                builder.addFormDataPart(fileParam.key,
//                                        "un_know",
//                                        requestBody
//                                );
//                            } else if (value instanceof byte[]) {
//                                builder.addFormDataPart(fileParam.key,
//                                        "un_know",
//                                        RequestBody.create(MEDIA_TYPE_PNG, (byte[]) value)
//                                );
//                            } else {
//                                throw new IllegalArgumentException("参数错误");
//                            }
//                        }
//
//                        requestBuilder.post(builder.build());
//                    }
//                }
//                break;
//
//            default:
//                if (netWorkCallBack != null)
//                    netWorkCallBack.callError(new Exception("不支持类型，当前只支持 GET 和POST"), tag);
//                return null;
//        }
//
//
//        Request request = requestBuilder.build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(callback);
//
//        return call;
//    }
//}
