package com.example.nillaapp_release

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvSubdistrict: RecyclerView
    private val list = ArrayList<subdistrict>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvSubdistrict = findViewById(R.id.rv_subdistrict)
        rvSubdistrict.setHasFixedSize(true)

        list.addAll(getListSubdistrict())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val profileItem = menu?.findItem(R.id.about_page)
        profileItem?.setOnMenuItemClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
            true
        }
        return super.onCreateOptionsMenu(menu)
    }


    private fun getListSubdistrict(): ArrayList<subdistrict> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listsubdistrict = ArrayList<subdistrict>()
        for (i in dataName.indices) {
            val subdistrict =
                subdistrict(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listsubdistrict.add(subdistrict)
        }
        return listsubdistrict
    }

    private fun showRecyclerList() {
        rvSubdistrict.layoutManager = LinearLayoutManager(this)
        val listSubdistrictAdapter = ListSubdistrictAdapter(list)
        rvSubdistrict.adapter = listSubdistrictAdapter

        listSubdistrictAdapter.setOnItemClickListener { selectedSubdistrict ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("SUBDISTRICT_NAME", selectedSubdistrict.name)
            intent.putExtra("SUBDISTRICT_DESCRIPTION", selectedSubdistrict.description)
            intent.putExtra("SUBDISTRICT_PHOTO", selectedSubdistrict.photo)
            startActivity(intent)
        }
    }
}