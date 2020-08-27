package com.interview.interviewproject.list

import android.content.DialogInterface
import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.interview.interviewproject.MainActivity
import com.interview.interviewproject.R
import com.interview.interviewproject.databinding.ActivityListBinding
import com.interview.interviewproject.detail.DetailActivity

class ListActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityListBinding

    private lateinit var listRepository: ListRepository

    private val viewModel: ListViewModel by viewModels {
        ListViewModel.Factory(listRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        listRepository = ListRepositoryImpl()
        dataBinding.vm = viewModel
        dataBinding.lifecycleOwner = this

        viewModel.onActivityCreate()

        dataBinding.listRecyclerView.layoutManager = GridLayoutManager(this, 4)


        viewModel.recyclerViewData.observeForever {

            Log.i("Michael","設置Data")
            val adapter = ListAdapter(it, this)
            dataBinding.listRecyclerView.adapter = adapter
            dataBinding.listRecyclerView.setItemViewCacheSize(4)

            adapter.setOnListItemClickListener(object :ListAdapter.OnListItemClickListener{
                override fun onClick(data: ListData) {
                    val detailIntent = Intent(this@ListActivity,DetailActivity::class.java)
                    detailIntent.putExtra("data",data)
                    startActivity(detailIntent)
                }
            })
        }

        viewModel.isClosePage.observeForever {
            if (!it){
                return@observeForever
            }
            finish()
        }
        viewModel.errorCodeLiveData.observeForever {

            AlertDialog.Builder(this)
                .setTitle(getString(R.string.error_title))
                .setMessage(it)
                .setPositiveButton(getString(R.string.confirm),object :DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }
                })
                .create().show()


        }


    }
}