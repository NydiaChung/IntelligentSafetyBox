package com.newland.intelligentsafetybox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.newland.intelligentsafetybox.Constant;
import com.newland.intelligentsafetybox.R;
import com.newland.intelligentsafetybox.base.BaseActivity;
import com.newland.intelligentsafetybox.bean.DeviceInfo;
import com.newland.intelligentsafetybox.utils.LogUtil;
import com.newland.intelligentsafetybox.utils.SPHelper;
import com.newland.intelligentsafetybox.view.DividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryDatasActivity extends BaseActivity {

    private static String TAG = "QueryDatasActivity";
    private Context mContext;
    private String mJsonData;

    private SPHelper spHelper;
    private String mDeviceId;

    List<Map<String,Object>> mList = new ArrayList<>();
    private RecyclerView mRecycleView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mJsonData = intent.getStringExtra("jsonData");
        LogUtil.d(TAG, "jsonData:" + mJsonData);
        setContentView(R.layout.activity_query);
        spHelper = SPHelper.getInstant(getApplicationContext());
        mDeviceId = spHelper.getStringFromSP(getApplicationContext(), Constant.DEVICE_ID);
        LogUtil.d(TAG, "mDeviceId 1:" + mDeviceId);
        mContext = this;
        initView();
    }

    private void initView() {
        initHeadView();
        setHeadVisable(true);
        initLeftTitleView("返回");
        setLeftTitleView(true);
        initTitleView("查询传感数据");
        setRithtTitleViewVisable(false);

        initData();
        LogUtil.d(TAG, "mList size:" + mList.size());
        mRecycleView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(this, mList);
        mRecycleView.setAdapter(mRecyclerViewAdapter);
        mRecycleView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
    }

    //绑定数据
    private void initData() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(mJsonData);
            JSONObject resultObj = (JSONObject) jsonObject.get("ResultObj");
            mDeviceId = String.valueOf(Integer.parseInt(new java.text.DecimalFormat("0").format(resultObj.getDouble("DeviceId"))));
            LogUtil.d(TAG, "mDeviceId 2:" + mDeviceId);
            JSONArray dataPoints = (JSONArray) resultObj.getJSONArray("DataPoints");
            LogUtil.d(TAG, "dataPoints size:" + dataPoints.length());
            LogUtil.d(TAG, "dataPoints:" + dataPoints);

            JSONArray pointDTO = (JSONArray) dataPoints.getJSONObject(0).getJSONArray("PointDTO");
            LogUtil.d(TAG, "pointDTO size:" + pointDTO.length());
            LogUtil.d(TAG, "pointDTO:" + pointDTO);
            for (int i = 0; i < pointDTO.length(); i++) {
                JSONObject subObject = pointDTO.getJSONObject(i);
                Map<String, Object> map = new HashMap<>();
                double value = (double) subObject.get("Value");
                String recordTime = (String) subObject.get("RecordTime");
                map.put("DeviceId", mDeviceId);
                map.put("Sensor", getResources().getString(R.string.box));
                map.put("Value", value);
                map.put("RecordTime", recordTime);
                mList.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
