package com.newland.intelligentsafetybox.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.newland.intelligentsafetybox.Constant;
import com.newland.intelligentsafetybox.R;
import com.newland.intelligentsafetybox.base.BaseActivity;
import com.newland.intelligentsafetybox.utils.DataCache;
import com.newland.intelligentsafetybox.utils.SPHelper;


public class SettingActivity extends BaseActivity {
    private static String TAG = "SettingActivity";
    private TextView tvGateWayTag;
    private TextView tvPlatformAddress;
    private TextView tvPort;
    private TextView mDeviceIdText;

    private SPHelper spHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        spHelper = SPHelper.getInstant(getApplicationContext());
        initView();
        initViewData();
        registerListener();
    }

    private void initView() {
        initHeadView();
        setHeadVisable(true);
        initLeftTitleView("设置");
        setTitleViewVisable(false);
        setRithtTitleViewVisable(false);

        tvGateWayTag = findViewById(R.id.Tag);
        tvPlatformAddress = findViewById(R.id.platformAddress);
        tvPort = findViewById(R.id.port);
        mDeviceIdText = findViewById(R.id.device_id_text);
    }

    protected void initViewData() {
        tvGateWayTag.setText(spHelper.getStringFromSP(getApplicationContext(), Constant.SETTING_GATEWAY_TAG));
        tvPlatformAddress.setText(spHelper.getStringFromSP(getApplicationContext(), Constant.SETTING_PLATFORM_ADDRESS));
        tvPort.setText(spHelper.getStringFromSP(getApplicationContext(), Constant.SETTING_PORT));
        mDeviceIdText.setText(spHelper.getStringFromSP(getApplicationContext(), Constant.DEVICE_ID));
    }

    protected void registerListener() {
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSetting();
            }
        });

        findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void saveSetting() {
        String platformAddress = tvPlatformAddress.getText().toString().trim();
        String port = tvPort.getText().toString().trim();
        String gateWayTag = tvGateWayTag.getText().toString().trim();
        String devicdId = mDeviceIdText.getText().toString().trim();
        if ("".equals(platformAddress)) {
            Toast.makeText(getApplicationContext(), "请填写IP地址", Toast.LENGTH_LONG).show();
            return;
        }
        if ("".equals(port)) {
            Toast.makeText(getApplicationContext(), "请填写平台端口", Toast.LENGTH_LONG).show();
            return;
        }
        if ("".equals(devicdId)) {
            Toast.makeText(getApplicationContext(), "请填写设备ID", Toast.LENGTH_LONG).show();
            return;
        }
        if (!TextUtils.isEmpty(platformAddress) && !TextUtils.isEmpty(port)) {
            DataCache.updateBaseUrl(getApplicationContext(), "http://" + platformAddress + ":" + port + "/");
        }
        DataCache.updateGateWayTag(getApplicationContext(), gateWayTag);

        spHelper.putData2SP(getApplicationContext(), Constant.SETTING_PLATFORM_ADDRESS, platformAddress);
        spHelper.putData2SP(getApplicationContext(), Constant.SETTING_PORT, port);
        spHelper.putData2SP(getApplicationContext(), Constant.DEVICE_ID, devicdId);
        Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
        this.setResult(2);
        finish();
    }
}
