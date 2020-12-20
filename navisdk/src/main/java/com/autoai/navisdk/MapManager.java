package com.autoai.navisdk;

import android.util.Log;
import com.autoai.navisdk.enu.MapEvent;
import com.autoai.navisdk.listener.MapEventListener;

/**
 * @author : YangHaoYi on  2019/4/3010:59.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on  2019/4/3010:59.
 * Version : V 1.0
 */
public class MapManager {

    private static final String TAG = MapManager.class.getSimpleName();
    private static final MapManager instance = new MapManager();

    public static MapManager getInstance() {
        return instance;
    }

    public void zoomIn(MapEventListener mapEventListener){
        Log.i(TAG, "zoomIn");
        mapEventListener.onMapEvent(MapEvent.ZOOM_IN_FINISH,null);
    }

    public void zoomOut(MapEventListener mapEventListener){
        Log.i(TAG, "zoomOut");
        mapEventListener.onMapEvent(MapEvent.ZOOM_OUT_FINSIH,null);
    }
}
