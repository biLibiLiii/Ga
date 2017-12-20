package com.bilibiliii.ga.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.bilibiliii.ga.R;
import com.bilibiliii.ga.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapActivity extends BaseActivity {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    BitmapDescriptor mCurrentMarker;
    private boolean isFirstLocate=true;
    @Override
    protected void setContentView() {
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
    }

    private void navigateTo(BDLocation location){
        if(isFirstLocate){
            LatLng ll =new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(update);
            update=MapStatusUpdateFactory.zoomTo(16f);
            mBaiduMap.animateMapStatus(update);
            isFirstLocate=false;
        }
    }
    @Override
    protected void initView() {
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        checkPermission();

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
    void checkPermission(){
        List<String> permissinList=new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MapActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissinList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(MapActivity.this,
                Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissinList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(MapActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissinList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissinList.isEmpty()){
            String[] permissions=permissinList.toArray(new String[permissinList.size()]);
            ActivityCompat.requestPermissions(MapActivity.this,permissions,1);
        }else {
            requestLocation();
        }
    }
    private void requestLocation(){
        mLocationClient.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    for(int result:grantResults){
                        if(result!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(MapActivity.this,"permission deny",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else{
                    Toast.makeText(MapActivity.this,"Unknow erro",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    public class MyLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
                if(bdLocation.getLocType()==BDLocation.TypeGpsLocation||
                        bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                    Toast.makeText(MapActivity.this,"get location info",Toast.LENGTH_SHORT).show();
                    navigateTo(bdLocation);
                }
        }
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
