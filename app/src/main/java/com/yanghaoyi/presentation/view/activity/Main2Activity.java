package com.yanghaoyi.presentation.view.activity;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yanghaoyi.presentation.R;

import java.util.ArrayList;

import static android.bluetooth.BluetoothDevice.TRANSPORT_LE;

public class Main2Activity extends AppCompatActivity {

   // private static Inner inner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    private BluetoothGatt connectGattCompat(BluetoothGattCallback bluetoothGattCallback, BluetoothDevice device, boolean autoConnect) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return device.connectGatt(this, autoConnect, bluetoothGattCallback, TRANSPORT_LE);
        } else {
            return device.connectGatt(this, autoConnect, bluetoothGattCallback);
        }
    }

}
