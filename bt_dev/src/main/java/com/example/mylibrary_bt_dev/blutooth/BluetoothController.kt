package com.example.mylibrary_bt_dev.blutooth

import android.app.Notification.MessagingStyle.Message
import android.bluetooth.BluetoothAdapter

class BluetoothController(private val adapter: BluetoothAdapter) {
    private var connectThread: ConnectThread?=null
    fun contect(mac: String, listener: Listener){
        if(adapter.isEnabled && mac.isNotEmpty()){
            val divice=adapter.getRemoteDevice(mac)
            connectThread= ConnectThread(divice,listener)
            connectThread?.start()
        }
    }
    companion object{
        const val BLUETOOTH_CONNECTED="bluetooth_connected"
        const val BLUETOOTH_NO_CONNECTED="bluetooth_no_connected"

    }
    interface Listener{
        fun onReceive(message: String)
    }

}