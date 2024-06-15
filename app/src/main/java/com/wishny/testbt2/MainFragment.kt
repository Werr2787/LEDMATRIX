package com.wishny.testbt2

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.mylibrary_bt_dev.BluetoothConstants
import com.example.mylibrary_bt_dev.blutooth.BluetoothController
import com.wishny.testbt2.databinding.FragmentMainBinding


class MainFragment : Fragment(), BluetoothController.Listener {
    private lateinit var bluetoothController: BluetoothController
    private lateinit var btAdapter: BluetoothAdapter
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBtAdapter()
        val pref = activity?.getSharedPreferences(
            BluetoothConstants.PREFERENCES, Context.MODE_PRIVATE)
        val mac = pref?.getString(BluetoothConstants.MAC, "")
        bluetoothController = BluetoothController(btAdapter)

        binding.apply {
            btList.setOnClickListener {
                findNavController().navigate(R.id.deviceListFragment)
            }
            connect.setOnClickListener {
                bluetoothController.connect(mac ?: "", this@MainFragment)

            }
            imageBall.setOnClickListener{
                bluetoothController.sendMessage("Ball")
            }
            imageBalls.setOnClickListener{
                bluetoothController.sendMessage("Balls")
            }

            imageButton.setOnClickListener{
                bluetoothController.sendMessage("Fire")
            }
            imageSnow.setOnClickListener{
                bluetoothController.sendMessage("Snow")
            }
            imageMatrix.setOnClickListener{
                bluetoothController.sendMessage("Matrix")
            }
            imageBlesk.setOnClickListener{
                bluetoothController.sendMessage("Blesk")
            }
            imageStreet.setOnClickListener{
                bluetoothController.sendMessage("Street")
            }
            imageDellet.setOnClickListener{
                bluetoothController.sendMessage("Dellet")
            }

        }
    }

    private fun initBtAdapter(){
        val bManager = activity?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        btAdapter = bManager.adapter
    }

    override fun onReceive(message: String) {
        activity?.runOnUiThread {
            binding.tvMessage.text = message
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bluetoothController.closeConnection()
    }

}