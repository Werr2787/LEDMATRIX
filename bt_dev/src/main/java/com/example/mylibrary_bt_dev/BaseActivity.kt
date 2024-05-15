package com.example.bt_def

import DeviceListFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary_bt_dev.ListItem
import com.example.mylibrary_bt_dev.R

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        //initRcView()
        supportFragmentManager.beginTransaction()
            .replace(R.id.placeHolder, DeviceListFragment()).commit()

    }

//    private fun initRcView() {
//        val rcView = findViewById<RecyclerView>(R.id.rcViewPaired)
//        rcView.layoutManager = LinearLayoutManager(this)
//        val adapter = ItemAdapter()
//        rcView.adapter = adapter
//        adapter.submitList(creatDeviceList())
//    }
//
//    private fun creatDeviceList(): List<ListItem> {
//        val list = ArrayList<ListItem>()
//
//        for (i in 0 until 5) {
//            list.add(
//                ListItem(
//                    "Device $i",
//                    "34:56:89:56"
//                )
//            )
//        }
//        return list
//    }
}