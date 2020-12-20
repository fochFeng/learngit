package com.yanghaoyi.presentation.view.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.media.MediaRouter;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.view.Display;
import android.view.WindowManager;

import com.yanghaoyi.presentation.view.presentation.SecondScreenPresentation;

/**
 * @author : YangHaoYi on  2019/4/3011:04.
 * Email  :  yang.haoyi@qq.com
 * Description :离屏渲染服务
 * Change : YangHaoYi on  2019/4/3011:04.
 * Version : V 1.0
 */
public class MultiScreenService extends Service {

    /**
     * 屏幕管理器
     **/
    private DisplayManager mDisplayManager;
    /**
     * 屏幕数组
     **/
    private Display[] displays;
    /**
     * 第二块屏
     **/
    private SecondScreenPresentation presentation;


    @Override
    public IBinder onBind(Intent intent) {
        return new MultiScreenBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initPresentation();
    }

    /**
     * 初始化第二块屏幕
     **/
    private void initPresentation() {
        if (null == presentation) {

           /* MediaRouter mMediaRouter = (MediaRouter) this.getSystemService(Context.MEDIA_ROUTER_SERVICE);
            MediaRouter.RouteInfo route = mMediaRouter.getSelectedRoute(MediaRouter.ROUTE_TYPE_LIVE_VIDEO);
            Display presentationDisplay = route != null ? route.getPresentationDisplay() : null;
            if (presentation != null && presentation.getDisplay() != presentationDisplay){
                presentation.dismiss();
                presentation = null;
            }
            if (presentation == null && presentationDisplay != null){
                presentation = new SecondScreenPresentation(this, presentationDisplay);
            }*/
            mDisplayManager = (DisplayManager) this.getSystemService(Context.DISPLAY_SERVICE);
            displays = mDisplayManager.getDisplays();
            if (displays.length > 1) {
                // displays[1]是副屏
                presentation = new SecondScreenPresentation(this, displays[1]);
                //params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0+
                    presentation.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
                } else {
                    presentation.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                }
            }
        }
    }

    /**
     * 显示第二块屏
     **/
    public void showSearchPresentation() {
        presentation.show();
    }


    public class MultiScreenBinder extends Binder {
        public MultiScreenService getService() {
            return MultiScreenService.this;
        }
    }

}
