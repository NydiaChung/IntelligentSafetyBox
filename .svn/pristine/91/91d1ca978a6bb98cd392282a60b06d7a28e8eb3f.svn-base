package com.newland.intelligentsafetybox.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.newland.intelligentsafetybox.Constant;
import com.newland.intelligentsafetybox.R;
import com.newland.intelligentsafetybox.base.BaseActivity;
import com.newland.intelligentsafetybox.utils.LogUtil;
import com.newland.intelligentsafetybox.utils.SPHelper;
import com.newland.intelligentsafetybox.view.TableTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QueryDatasActivity extends BaseActivity {

    private static String TAG = "MainActivity";
    private Context mContext;
    private String mJsonData;

    private LinearLayout mainLinerLayout;
    private RelativeLayout relativeLayout;
    private String[] name = {"设备ID", "时间", "状态"};
    private SPHelper spHelper;
    private String mDeviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mJsonData = intent.getStringExtra("jsonData");
        LogUtil.d(TAG, "jsonData:" + mJsonData);
        setContentView(R.layout.activity_querydatas);
        spHelper = SPHelper.getInstant(getApplicationContext());
        mDeviceId = spHelper.getStringFromSP(getApplicationContext(), Constant.DEVICE_ID);
        mContext = this;
        initView();
        initData();
    }

    private void initView() {
        initHeadView();
        setHeadVisable(true);
        initLeftTitleView("返回");
        setLeftTitleView(true);
        initTitleView("查询传感数据");
        setRithtTitleViewVisable(false);

        mainLinerLayout = (LinearLayout) this.findViewById(R.id.MyTable);
    }

    //绑定数据
    private void initData() {
        //初始化标题
        relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.table, null);
        TableTextView title = (TableTextView) relativeLayout.findViewById(R.id.list_1_1);
        title.setText(name[0]);
        title.setTextColor(Color.BLUE);
        title = (TableTextView) relativeLayout.findViewById(R.id.list_1_2);
        title.setText(name[1]);
        title.setTextColor(Color.BLUE);
        title = (TableTextView) relativeLayout.findViewById(R.id.list_1_3);
        title.setText(name[2]);
        mainLinerLayout.addView(relativeLayout);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(mJsonData);
            JSONObject resultObj = (JSONObject) jsonObject.get("ResultObj");
            JSONArray dataPoints = (JSONArray) resultObj.getJSONArray("DataPoints");
            LogUtil.d(TAG, "dataPoints size:" + dataPoints.length());
            LogUtil.d(TAG, "dataPoints:" + dataPoints);

            JSONArray pointDTO = (JSONArray) dataPoints.getJSONObject(0).getJSONArray("PointDTO");
            LogUtil.d(TAG, "pointDTO size:" + pointDTO.length());
            LogUtil.d(TAG, "pointDTO:" + pointDTO);
            for (int i = 0; i < pointDTO.length(); i++) {
                JSONObject subObject = pointDTO.getJSONObject(i);
                double value = (double) subObject.get("Value");
                String recordTime = (String) subObject.get("RecordTime");
                relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.table, null);
                TableTextView txt = (TableTextView) relativeLayout.findViewById(R.id.list_1_1);
                txt.setText(mDeviceId);

                txt = (TableTextView) relativeLayout.findViewById(R.id.list_1_2);
                txt.setText(recordTime);

                txt = (TableTextView) relativeLayout.findViewById(R.id.list_1_3);
                txt.setText(String.valueOf(value));
                mainLinerLayout.addView(relativeLayout);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
