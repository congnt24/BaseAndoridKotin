package com.congnt.kotlinmvp.mvp.domain;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by congn on 3/22/2017.
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the result using a {@link DisposableObserver}
 * that will execute its job in a background thread and will post the result in the UI thread.
 * <p>
 * Why we use DisposableObservable:
 * DisposableObservable is a container and control all Observable.
 * All observable in DisposableObservable have ability to execute parallel at the same time
 * If Activity is destroy, we will unsubscribe/dispose this DisposableObservable, so all observable will be canceled
 */

public class UseCaseManager {
    private final CompositeDisposable disposables;

    public UseCaseManager() {
        this.disposables = new CompositeDisposable();
    }

    public void execute(UseCase uc, DisposableObserver observer, String... params) {
        Observable<?> observable = uc.getObservable(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        addDisposable(observable.subscribeWith(observer));
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Add disposable {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }
}
