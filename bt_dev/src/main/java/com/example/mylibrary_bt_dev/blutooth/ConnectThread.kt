package com.example.mylibrary_bt_dev.blutooth

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.util.UUID

class ConnectThread(
    private val device: BluetoothDevice,
    val listener: BluetoothController.Listener
) : Thread() {
    private val uuid = "00001101-0000-1000-8000-00805F9B34FB"
    private var mSocket: BluetoothSocket? = null

    init {
        try {
            mSocket = device.createRfcommSocketToServiceRecord(UUID.fromString(uuid))
        } catch (e: IOException) {

        } catch (se: SecurityException) {

        }
    }

    override fun run() {
        try {
            mSocket?.connect()
            listener.onReceive(BluetoothController.BLUETOOTH_CONNECTED)
            Log.d("Tg", "Connected")

        } catch (e: IOException) {
            listener.onReceive(BluetoothController.BLUETOOTH_CONNECTED)
        } catch (se: SecurityException) {

        }
    }

    fun closeConnection() {
        try {
            mSocket?.close()
        } catch (e: IOException) {

        }
    }
}