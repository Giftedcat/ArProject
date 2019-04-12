//package com.git.easylib.http;
//
///**
// * Created by m on 16-3-29.
// */
//public class HttpManager2 {
//    private String appid;
//    private String appkey;
//    private String appVersion;
//    private static HttpManager2 sInstance = null;
////    private OkHttpClient client;
//    private static String TOKEN_PARAM = "access-token=";
////    Headers header;
////    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//    private final static int REQUEST_NUMBERS = 1;
//    private int j =0,i=0;
//
//
//
//    public void init(String appid,String appkey,String appVersion){
//        this.appid=appid;
//        this.appkey=appkey;
//        this.appVersion = appVersion;
//        if(appid==null||appkey==null ||appVersion ==null){
//            throw new RuntimeException();
//        }
////        header= new Headers.Builder()
////                .add("appid", appid)
////                .add("appkey", appkey)
////                .add("appVersion",appVersion).build();
//    }
//    private HttpManager2() {
////        client=new OkHttpClient();
//
//    }
//
//
//    //单例模式
//    public static synchronized HttpManager2 getInstance() {
//        if(sInstance == null) {
//            sInstance = new HttpManager2();
//        }
//
//        return sInstance;
//
//    }
//
////    public void get(String url, Map<String,String> data, final CallCloudCallback callback){
////
////        if(SunCloud.user_token!=null && !"".equals(SunCloud.user_token)){
////            url=url+"?"+TOKEN_PARAM+ SunCloud.user_token;
////        }
////        if(data!=null){
////            for (String key : data.keySet()) {
////                url=url+"&"+key+"="+data.get(key);
////            }
////        }
////
////
////        Request request=new Request.Builder().url(url).headers(header).build();
////        client.newCall(request).enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////
////            }
////
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////
////                if(response.isSuccessful()){
////                    callback.onData(response.body().string());
////                }else{
////                    callback.onData(null);
////                }
////            }
////        });
////    }
//
//    /**
//     *
//     * @param url
//     * @param data
//     * @param callback
//     */
//    public void get(String url, final CallCloudCallback3 callback){
//        Request request=new Request.Builder().url(url).get().build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                callback.onFailed("net occur error");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if(response.isSuccessful()){
//                    callback.onData(response.body().string());
//                }else{
//                    callback.onData(null);
//                }
//            }
//        });
//    }
//
////    public void post(String url,Map<String,String> data, final CallCloudCallback callback){
////        MultipartBody.Builder builder= new MultipartBody.Builder();
////        if(data!=null){
////            for (String key : data.keySet()) {
////                //System.out.println("key= "+ key + " and value= " + data.get(key));
////                if (key != null && data.get(key) != null)
////                    builder.addFormDataPart(key,data.get(key));
////            }
////        }
////
////        if(SunCloud.user_token!=null){
////            url=url+"?"+TOKEN_PARAM+ SunCloud.user_token;
////        }
////
////        RequestBody body=builder.build();
////        Request request=new Request.Builder().url(url).headers(header).post(body).build();
////        client.newCall(request).enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////
////            }
////
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////
////                if(response.isSuccessful()){
////                    callback.onData(response.body().string());
////                }
////            }
////        });
////    }
////
////
////    public void post(String url,Map<String,String> data, final CallCloudCallback3 callback){
////        MultipartBody.Builder builder= new MultipartBody.Builder();
////        if(data!=null){
////            for (String key : data.keySet()) {
////                //System.out.println("key= "+ key + " and value= " + data.get(key));
////                if (key != null && data.get(key) != null)
////                    builder.addFormDataPart(key,data.get(key));
////            }
////        }
////
////        if(SunCloud.user_token!=null){
////            url=url+"?"+TOKEN_PARAM+ SunCloud.user_token;
////        }
////
////        RequestBody body=builder.build();
////        Request request=new Request.Builder().url(url).headers(header).post(body).build();
////        client.newCall(request).enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////                callback.onFailed("net error");
////            }
////
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                if(response.isSuccessful()){
////                    callback.onData(response.body().string());
////                }else{
////                    callback.onData(null);
////                }
////            }
////        });
////    }
////
//////    public <T> void post2(String url,Map<String,String> data, final Type datatype ,final CallCloudCallback2<T> callback){
//////        //MultipartBody.Builder builder= new MultipartBody.Builder();
//////        L.d("request data--==--"+data.toString());
//////
//////        FormBody.Builder builder=new FormBody.Builder();
//////
//////        if(data!=null){
//////            for (String key : data.keySet()) {
//////                //System.out.println("key= "+ key + " and value= " + data.get(key));
//////                //builder.addFormDataPart(key,data.get(key));
//////                if(data.get(key)!=null){
//////                    builder.add(key,data.get(key));
//////                }
//////            }
//////        }
//////
//////        if(SunCloud.user_token!=null){
//////            url=url+"?"+TOKEN_PARAM+SunCloud.user_token;
//////
//////        }
//////
//////        RequestBody body=builder.build();
//////        Request request=new Request.Builder().url(url).headers(header).post(body).build();
//////
//////
//////        client.newCall(request).enqueue(new Callback() {
//////            @Override
//////            public void onFailure(Call call, IOException e) {
//////
//////            }
//////
//////            @Override
//////            public void onResponse(Call call, Response response) throws IOException {
//////
//////                if(response.isSuccessful()){
//////                    String responseStr = response.body().string();
//////                    L.d("response data--==--"+responseStr);
//////                    BaseModel<T> basemodel=new Gson().fromJson(responseStr,datatype);
//////                    if(basemodel.isStatus()){
//////                        callback.onSuccess(basemodel.getData());
//////                    }else{
//////                        callback.onFail(basemodel);
//////
//////                    }
//////
//////                }
//////            }
//////        });
//////    }
////
////    /**
////     *
////     * @param url
////     * @param data
////     * @param datatype
////     * @param callback
////     * @param <T>
////     */
////    public <T> void get2(String url, final Map<String,String> data, final Type datatype ,final CallCloudCallback2<T> callback){
////
////        L.d("request data--==--"+data.toString());
//////        if(SunCloud.user_token!=null && !"".equals(SunCloud.user_token)){
//////            url=url+"?"+TOKEN_PARAM+ SunCloud.user_token;
//////        }
////        url=url+"?"+TOKEN_PARAM+ SunCloud.user_token;
////        if(data!=null){
////            for (String key : data.keySet()) {
////                url=url+"&"+key+"="+data.get(key);
////            }
////        }
////
////        final String get2_again_url =url;
////        L.d(get2_again_url);
////        Request request=new Request.Builder().url(url).headers(header).build();
////
////        client.newCall(request).enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////                L.d("get2 request onFailure ********e:" + e);
////                if (j< REQUEST_NUMBERS){
////                    get2(get2_again_url,data,datatype,callback);
////                    j++;
////                }else {
////                    BaseModel<T> basemodel = new BaseModel<T>();
////                    basemodel.setNetworkOk(false);
////                    callback.onFail(basemodel);
////                    j=0;
////                }
////            }
////
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                String responseStr = response.body().string();
////                L.d("response data--==--"+responseStr);
////                final BaseModel<T> basemodel=new Gson().fromJson(responseStr,datatype);
////                if(response.isSuccessful()){
////                    if(basemodel.isStatus()){
////                        callback.onSuccess(basemodel.getData());
////                    }else{
////                        if (basemodel.getCode()==1004122) {  //返回登录失败消息，用户退出本地登录
////                            UserManger.getInstance().userLoginout(new HttpCallback<Object>() {
////                                @Override
////                                public void onSuccess(Object data) {
////                                    callback.onFail(basemodel);
////
////                                }
////
////                                @Override
////                                public void onFail(int code, String msg) {
////                                    callback.onFail(basemodel);
////
////                                }
////                            });
////                        }else {
////                            callback.onFail(basemodel);
////                        }
////                    }
////                }else {
////                    callback.onFail(basemodel);
////
////                }
////                j=0;
////
////            }
////        });
////    }
////
////    /**
////     *
////     * @param url
////     * @param map
////     * @param datatype
////     * @param callback
////     * @param <T>
////     */
////    public <T> void post2(String url, final Map map, final Type datatype ,final CallCloudCallback2<T> callback){
////        //MultipartBody.Builder builder= new MultipartBody.Builder();
////
////        FormBody.Builder builder=new FormBody.Builder();
////        L.d("request message--==--"+new Gson().toJson(map));
////
////        RequestBody body = RequestBody.create(JSON,new Gson().toJson(map));
////
////        if(SunCloud.user_token!=null){
////            url=url+"?"+TOKEN_PARAM+ SunCloud.user_token;
////
////        }
////        final String post2_again_url =url;
////        Request request=new Request.Builder().url(url).headers(header).post(body).build();
////
////
////        client.newCall(request).enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////                L.d("post2 request onFailure###########e:"+e);
////                if (i<REQUEST_NUMBERS){
////                    post2(post2_again_url, map, datatype, callback);
////                    i++;
////                }else {
////                    BaseModel<T> basemodel = new BaseModel<T>();
////                    basemodel.setNetworkOk(false);
////                    callback.onFail(basemodel);
////                    i =0;
////                }
////
////            }
////
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                String responseStr = response.body().string();
////                L.d("response data--==--" + responseStr);
////                final BaseModel<T> basemodel=new Gson().fromJson(responseStr,datatype);
////                if(response.isSuccessful()){
////
////                    if(basemodel.isStatus()){
////                        callback.onSuccess(basemodel.getData());
////                    }else{
////                        if (basemodel.getCode() == 1004122) {  //返回登录失败消息，用户退出本地登录
////                            UserManger.getInstance().userLoginout(new HttpCallback<Object>() {
////                                @Override
////                                public void onSuccess(Object data) {
////                                    callback.onFail(basemodel);
////
////                                }
////
////                                @Override
////                                public void onFail(int code, String msg) {
////                                    callback.onFail(basemodel);
////
////                                }
////                            });
////                        }else {
////                            callback.onFail(basemodel);
////                        }
////                    }
////                }else {
////                    callback.onFail(basemodel);
////
////                }
////                i =0;
////            }
////        });
////    }
////
////    /**
////     * 获取 minitype
////     * @param filePath
////     * @return
////     */
////    public static String getMimeType(String filePath) {
////        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
////        String mime = "image/png";
////        if (!TextUtils.isEmpty(filePath)) {
////            try {
////                mmr.setDataSource(filePath);
////                mime = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
////            } catch (IllegalStateException e) {
////                return mime;
////            } catch (IllegalArgumentException e) {
////                return mime;
////            } catch (RuntimeException e) {
////                return mime;
////            }
////        }
////        return mime;
////    }
////
////
////    /**
////     *post 上传 file
////     * @param url
////     * @param data
////     * @param datatype
////     * @param callback
////     * @param <T>
////     */
////    public <T> void postFile(String url,File data, final Type datatype ,final CallCloudCallback2<T> callback){
////        if (url == null || data == null || TextUtils.isEmpty(data.getPath()))
////            return;
////
////        try {
////            MultipartBody.Builder builder= new MultipartBody.Builder().setType(MultipartBody.FORM);
////            //        FormBody.Builder builder=new FormBody.Builder();
////
////            final MediaType mimeType = MediaType.parse(getMimeType(data.getPath()));
////            builder.addFormDataPart("file",data.getPath(),RequestBody.create(mimeType, data));
////
////            if(SunCloud.user_token!=null){
////                url=url+"?"+TOKEN_PARAM+ SunCloud.user_token;
////            }
////
////            RequestBody body=builder.build();
////            Request request=new Request.Builder().url(url).headers(header).post(body).build();
////            client.newCall(request).enqueue(new Callback() {
////                @Override
////                public void onFailure(Call call, IOException e) {
////                    BaseModel<T> basemodel = new BaseModel<T>();
////                    basemodel.setNetworkOk(false);
////                    callback.onFail(basemodel);
////                }
////
////                @Override
////                public void onResponse(Call call, Response response) throws IOException {
////
////                    if(response.isSuccessful()){
////                        String responseStr = response.body().string();
////                        L.d("response data--==--"+responseStr);
////                        BaseModel<T> basemodel=new Gson().fromJson(responseStr,datatype);
////                        if(basemodel.isStatus()){
////                            callback.onSuccess(basemodel.getData());
////                        }else{
////                            callback.onFail(basemodel);
////
////                        }
////
////                    }
////                }
////            });
////        } catch (Exception e) {
//////            Context context = MyApplication.getAppContext();
//////            Toast.makeText(context, context.getString(R.string.upload_failed), Toast.LENGTH_SHORT).show();
////        }
////    }
////
////
////    /**
////     *
////     * @param url
////     * @param filePath
////     * @param fileName
////     * @param uiProgressListener
////     * @param callback
////     */
////    public void downloadFile(String url, final String filePath, final String fileName, UIProgressListener uiProgressListener,final CallCloudCallback2<String> callback){
////
////        Request request=new Request.Builder().url(url).headers(header).build();
////
////        ProgressHelper.addProgressResponseListener(client, uiProgressListener).newCall(request).enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////                L.e("error " + e);
////                callback.onSuccess("network_error");
////            }
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                String response_string =response.body().toString();
////                L.e("TAG"+ response_string);
////                InputStream is = response.body().byteStream();
////                int len = 0;
////                byte[] buf = new byte[2048];
////                FileOutputStream fos = null;
////                if (is != null) {
////                    try {
////                        File file = new File(filePath, fileName);
////                        fos = new FileOutputStream(file);
////                        while ((len = is.read(buf)) != -1) {
////                            fos.write(buf, 0, len);
////                        }
////                        fos.flush();
////                        //如果下载文件成功，第一个参数为文件的绝对路径
////                        callback.onSuccess(file.getPath());
////
//////                    sendSuccessResultCallback(file.getAbsolutePath(), callback);
////                    } catch (IOException e) {
//////                    sendFailedStringCallback(response.request(), e, callback);
////                        e.printStackTrace();
////                        callback.onSuccess("stream_error");
////
////                    } finally {
////                        try {
////                            if (is != null) is.close();
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
////                        try {
////                            if (fos != null) fos.close();
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
////                    }
////                }else {
////                    callback.onSuccess("response_error");
////                }
////            }
////        });
////
////    }
//
//
//}
