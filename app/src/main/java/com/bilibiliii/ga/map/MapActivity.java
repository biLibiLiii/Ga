package com.bilibiliii.ga.map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.bilibiliii.ga.R;
import com.bilibiliii.ga.base.BaseActivity;

public class MapActivity extends BaseActivity {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    BitmapDescriptor mCurrentMarker;
    @Override
    protected void setContentView() {
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);


    }

    @Override
    protected void initView() {
        mCurrentMarker=BitmapDescriptorFactory .fromResource(R.mipmap.icon_openmap_mark);
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap=mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING,true,mCurrentMarker));
//        MyLocationData locData = new MyLocationData.Builder()
//                .accuracy(location.getRadius())
//                // 此处设置开发者获取到的方向信息，顺时针0-360
//                .direction(100).latitude(location.getLatitude())
//                .longitude(location.getLongitude()).build();

// 设置定位数据
//        mBaiduMap.setMyLocationData(locData);

// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）

// 当不需要定位图层时关闭定位图层,
        mBaiduMap.setMyLocationEnabled(false);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
}
