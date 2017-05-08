package com.bwei.xutilsdemo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.apache.http.params.CoreConnectionPNames;
import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


// 显示布局文件
@ContentView(R.layout.activity_main)
public class MainActivity extends Activity {

    // 实例化button
    @ViewInject(R.id.btn)
    Button buttonSendNet ;

    @ViewInject(R.id.image)
    ImageView imageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        x.view().inject(this);

//        buttonSendNet = (Button) findViewById(R.id.btn);


        System.out.println("buttonSendNet = " + buttonSendNet);


    }


    //点击事件 , type = View.OnClickListener.class type 默认是 OnClickListener
    @Event(value = {R.id.btn,R.id.btn_post,R.id.btn_image} )
    private void btnClick(View view){


        switch (view.getId()) {

            case R.id.btn:
                // get

                get();


                break;

            case R.id.btn_post:
                //

                post();
                break;
            case R.id.btn_image:

//                showImage();

                download();



                break;
        }

    }
    public static String photoCacheDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Bwei";

    public void toPic(){

        if(!new File(photoCacheDir).exists()){
            new File(photoCacheDir).mkdirs();
        }

        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        getAlbum.setType("image/*");
        startActivityForResult(getAlbum, 1);

    }

    ImageOptions  imageOptions ;

    private void imageOption(){
        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
// 很多时候设置了合适的scaleType也不需要它.
// 加载中或错误图片的ScaleType
//.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
        imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                .setRadius(DensityUtil.dip2px(5))
                .setCrop(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .build();
    }


    private void showImage() {




        x.image().bind(imageView,"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2389140428,1315216178&fm=23&gp=0.jpg");


//
//        x.image().bind(imageView, "ffff", new Callback.CommonCallback<Drawable>() {
//            @Override
//            public void onSuccess(Drawable result) {
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });


    }


    // get 请求
    private void get(){


        RequestParams requestParams = new RequestParams("http://qhb.2dyt.com/Bwei/login");
        requestParams.addQueryStringParameter("postkey","bwei");
        requestParams.addQueryStringParameter("username","18511098888");
        requestParams.addQueryStringParameter("password","11111");

        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                System.out.println("result = " + result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });




    }


    // post
    private void post(){

        RequestParams requestParams = new RequestParams("http://qhb.2dyt.com/Bwei/login");
        requestParams.addBodyParameter("postkey","bwei");
        requestParams.addBodyParameter("username","18511098888");
        requestParams.addBodyParameter("password","11111");



        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                System.out.println("result = " + Thread.currentThread().getName());

                System.out.println("result = " + result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }


    private void upload(String path){
        String url = "http://qhb.2dyt.com/Bwei/upload" ;
        String [] arr = path.split("/");

        RequestParams requestParams = new RequestParams("http://qhb.2dyt.com/Bwei/login");
        requestParams.addBodyParameter("postkey","bwei");
        requestParams.addBodyParameter("file",new File(path));
        requestParams.addBodyParameter("imageFileName",arr[arr.length-1]);
        requestParams.addBodyParameter("username", "111");
        requestParams.addBodyParameter("pwd", "123456");
        requestParams.addBodyParameter("age", "23");

        // 上传的参数


        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                System.out.println("result = " + Thread.currentThread().getName());

                System.out.println("result = " + result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }



    private void download(){
        String path = "http://gdown.baidu.com/data/wisegame/25b7c41e8863fd92/QQ_676.apk" ;

        RequestParams requestParams = new RequestParams(path);

        //设置保存路径
//        requestParams.setSaveFilePath();

        x.http().get(requestParams, new Callback.ProgressCallback<File>() {

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {

                System.out.println("total = " + total);
                System.out.println("current = " + current);
                System.out.println("current isDownloading=  " + isDownloading);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onSuccess(File result) {

            }
        });


    }







}
