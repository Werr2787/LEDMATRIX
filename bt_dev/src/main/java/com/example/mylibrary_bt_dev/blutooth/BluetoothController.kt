package com.example.mylibrary_bt_dev.blutooth

import android.bluetooth.BluetoothAdapter

class BluetoothController(private val adapter: BluetoothAdapter) {
    private var connectThread: ConnectThread?=null
    fun contect(mac: String){
        if(adapter.isEnabled && mac.isNotEmpty()){
            val divice=adapter.getRemoteDevice(mac)
            connectThread= ConnectThread(divice)
            connectThread?.start()
        }
    }

}