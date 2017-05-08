package com.example.congn.kotlindemo.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import com.congnt.kotlinmvp.mvp.BaseFragment
import com.example.congn.kotlindemo.R
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.first_fragment.*

/**
 * Created by congn on 4/16/2017.
 */
class FirstFragment : BaseFragment(R.layout.first_fragment){
    override fun injector() {

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_submit.setOnClickListener { activity.awesomeNavigation!!.openFragment(R.id.content_frame, SecondFragment(), "SecondFragment") }
        //Nocache and no store to not keep bitmap in memory, Wen you back to this fragment, is will load form scratch
        var config = Bitmap.Config.RGB_565
        Picasso.with(activity).load(R.drawable.example).config(config).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(imageview)
    }
}