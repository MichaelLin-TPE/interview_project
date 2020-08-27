package com.interview.interviewproject.detail

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.interview.interviewproject.R
import com.interview.interviewproject.databinding.ActivityDetailBinding
import com.interview.interviewproject.list.ListData

class DetailActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityDetailBinding

    private lateinit var detailRepository: DetailRepository

    private val viewModel : DetailViewModel by viewModels{
        DetailViewModel.Factory(detailRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_detail)

        dataBinding.lifecycleOwner = this

        //catch Bundle

        val intent = intent
        val bundle = intent.extras ?: return

        val listData : ListData = bundle.getSerializable("data") as ListData

        detailRepository = DetailRepositoryImpl(listData)

        dataBinding.vm = viewModel

        viewModel.onActivityCreate()

        viewModel.imageLIveData.observeForever{
            dataBinding.detailImage.setImageBitmap(it)
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
                .setPositiveButton(getString(R.string.confirm),object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }
                })
                .create().show()
        }

    }
}