package com.example.app.presentation.mvp;

import android.util.Log;

import androidx.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V> implements IBasePresenter<V> {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected V view;

    protected abstract String logTag();

    @Override
    public boolean isViewBound() {
        return view != null;
    }

    @Override
    public void subscribe(V view) {
        Log.d(logTag(), "subscribe");
        this.view = view;
    }

    @Override
    public void onResume() {
        Log.d(logTag(), "onResume");
    }

    @Override
    public void onPause() {
        Log.d(logTag(), "onPause");
    }

    @Override
    public void unsubscribe() {
        Log.d(logTag(), "unsubscribe");
        view = null;
        compositeDisposable.clear();
    }

    @Override
    public void onDestroy() {
        Log.d(logTag(), "onDestroy");
        compositeDisposable.dispose();
    }

    @Override
    public void addDisposable(@Nullable Disposable d) {
        if (d != null) {
            compositeDisposable.add(d);
        }
    }

    @Override
    public void removeDisposable(@Nullable Disposable d) {
        if (d != null) {
            compositeDisposable.remove(d);
        }
    }
}
