package com.yanghaoyi.presentation.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import android.widget.Toast;
import com.yanghaoyi.presentation.R;
import com.yanghaoyi.presentation.presenter.PresentationPresenter;
import com.yanghaoyi.presentation.view.compoent.FloatButton;
import com.yanghaoyi.presentation.view.page.MapFrameLayout;
import com.yanghaoyi.presentation.view.page.SearchFrameLayout;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

/**
 * @author : YangHaoYi on 2019/1/2.
 *         Email  :  yang.haoyi@qq.com
 *         Description :多屏显示示例
 *         Change : YangHaoYi on 2019/1/2.
 *         Version : V 1.0
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener,
        MapFrameLayout.MapCallBackListener, SearchFrameLayout.SearchCallBack {

    /** TAG **/
    private static final String TAG = "MainActivity";
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1000;
    /** 展示第二块屏幕按钮 **/
    private TextView tvShowPresentation;
    /** Presentation逻辑控制中心 **/
    private PresentationPresenter presentationPresenter;
    /** 页面根布局 **/
    private FrameLayout fmActivityContent;
    /** 地图页 **/
    private MapFrameLayout mapFrameLayout;
    /** 搜索页 **/
    private SearchFrameLayout searchFrameLayout;

    Application

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // requestPermission();
       /* if  (Build.VERSION.SDK_INT >=  23 ) {
            if  (! Settings.canDrawOverlays(MainActivity. this )) {
                Intent intent =  new  Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse( "package:"  + getPackageName()));
                startActivityForResult(intent, 10 );
            }
        }*/
        init();
        // startActivity(new Intent(this, Main2Activity.class));
    }

    /** 初始化 **/
    private void init(){
        initView();
        initEvent();
    }

    /** 初始化视图 **/
    private void initView(){
        tvShowPresentation = findViewById(R.id.tvShowPresentation);
        fmActivityContent = findViewById(R.id.fmActivityContent);
        initPage();
    }

    /** 初始化页面 **/
    private void initPage(){
        mapFrameLayout = new MapFrameLayout(this);
        mapFrameLayout.setMapCallBackListener(this);
        fmActivityContent.addView(mapFrameLayout);
        FloatButton floatButton = new FloatButton(this);
        fmActivityContent.addView(floatButton);
    }

    /** 初始化事件 **/
    private void initEvent(){
        presentationPresenter = new PresentationPresenter();
        tvShowPresentation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvShowPresentation:
                presentationPresenter.openSearchPresentation(MainActivity.this);
                break;
            default:
                break;
        }
    }

    /** 地图页面回调 **/
    @Override
    public void mapCallBackContent(MapFrameLayout.MapEvent event, Object data) {
        switch (event){
            case Search:
                if(null == searchFrameLayout){
                    searchFrameLayout = new SearchFrameLayout(this);
                    searchFrameLayout.setSearchBackListener(this);
                }
                fmActivityContent.addView(searchFrameLayout);
                break;
            case Other:
                break;
            default:
                break;
        }
    }

    /** 搜索页面回调 **/
    @Override
    public void searchCallBackContent(SearchFrameLayout.SearchEvent event, Object data) {
        switch (event){
            case Back:
                fmActivityContent.removeView(searchFrameLayout);
                searchFrameLayout = null;
                break;
            case Other:
                break;
            default:
                break;
        }
    }


    private void requestPermission() {

        Log.i(TAG,"requestPermission");
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SYSTEM_ALERT_WINDOW)
                != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG,"checkSelfPermission");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SYSTEM_ALERT_WINDOW)) {
                Log.i(TAG,"shouldShowRequestPermissionRationale");
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            } else {
                Log.i(TAG,"requestPermissions");
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG,"onRequestPermissionsResult granted");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Log.i(TAG,"onRequestPermissionsResult denied");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    showWaringDialog();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void showWaringDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("警告！")
                .setMessage("请前往设置->应用->PermissionDemo->权限中打开相关权限，否则功能无法正常运行！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 一般情况下如果用户不授权的话，功能是无法运行的，做退出处理
                        finish();
                    }
                }).show();
    }


    @Override
    protected  void  onActivityResult( int  requestCode,  int  resultCode, Intent data) {
        if  (requestCode ==  10 ) {
            if  (Build.VERSION.SDK_INT >=  23 ) {
                if  (!Settings.canDrawOverlays( this )) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                    Toast.makeText(MainActivity. this , "not granted" ,Toast.LENGTH_SHORT);
                }
            }
        }
    }
}
