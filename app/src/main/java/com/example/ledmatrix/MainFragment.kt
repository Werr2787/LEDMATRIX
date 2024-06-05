package com.example.ledmatrix

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.findNavController
import com.example.ledmatrix.databinding.FragmentMainBinding
import com.example.mylibrary_bt_dev.BluetoothConstants
import com.example.mylibrary_bt_dev.blutooth.BluetoothController

class MainFragment : Fragment(),BluetoothController.Listener {
    private lateinit var btAdapter:BluetoothAdapter
    private lateinit var binding:FragmentMainBinding
    private lateinit var bluetoothController:BluetoothController
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
        val pref =
            activity?.getSharedPreferences(BluetoothConstants.PREFERENCES, Context.MODE_PRIVATE)
        val mac=pref?.getString(BluetoothConstants.MAC,"")
        bluetoothController= BluetoothController(btAdapter)
        binding.bList.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_deviceListFragment)
        }
        binding.bConnect.setOnClickListener{
            bluetoothController.contect(mac ?:"",this)
        }
    }

    private fun initBtAdapter() {
        val bManager = activity?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        btAdapter = bManager.adapter
    }

    override fun onReceive(message: String) {
        activity?.runOnUiThread{
            when(message){
                BluetoothController.BLUETOOTH_CONNECTED->{
                    binding.bConnect.backgroundTintList=AppCompatResources
                        .getColorStateList(requireContext(), com.example.mylibrary_bt_dev.R.color.red)
                    binding.bConnect.text="Disconnect"
                }
                BluetoothController.BLUETOOTH_NO_CONNECTED->{
                    binding.bConnect.backgroundTintList=AppCompatResources
                        .getColorStateList(requireContext(), com.example.mylibrary_bt_dev.R.color.green)
                    binding.bConnect.text="Connect"
                }
                else->{

                }
            }
        }
    }


}