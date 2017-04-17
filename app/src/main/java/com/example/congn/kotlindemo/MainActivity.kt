package com.example.congn.kotlindemo

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.congn.kotlindemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MvpView {
    //The statement inside lazy will be call and save at the first time is call
    // (e.g binding.setVariable(BR.item, user))
    val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    var user = User("cong", "Nguyen")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        var binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.setVariable(BR.item, user)
        binding.setVariable(BR.presenter, MvpPresenter(this))
        user.user = "CCCCCCCCCCCC"
        binding.executePendingBindings()
    }
    override fun onShowProgress() {
        Log.d("AAAAA", "AAAAAAAAAAAAAAAAAA")
        user.user = "Hello world"
        binding.setVariable(BR.item, user)
    }
}
