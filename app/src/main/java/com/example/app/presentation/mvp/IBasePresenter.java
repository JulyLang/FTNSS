package com.example.app.presentation.mvp;

import androidx.annotation.Nullable;

import io.reactivex.disposables.Disposable;

public interface IBasePresenter<V> {

    boolean isViewBound();

    /**
     * Execute in onViewCreated
     * @param view
     */
    void subscribe(V view);

    void onResume();

    void onPause();

    /**
     * Execute in onDestroyView
     */
    void unsubscribe();

    void onDestroy();

    void addDisposable(@Nullable Disposable d);

    void removeDisposable(@Nullable Disposable d);
}
