package com.congnt.kotlinmvp.mvp.presenter

import com.congnt.kotlinmvp.mvp.domain.UseCaseManager
import com.congnt.kotlinmvp.mvp.view.MvpView


/**
 * Created by congn on 03/21/2017.
 */

abstract class BaseCleanPresenter<V : MvpView
        > : BasePresenter<V>() {
    protected var useCaseManager: UseCaseManager = UseCaseManager()

}
