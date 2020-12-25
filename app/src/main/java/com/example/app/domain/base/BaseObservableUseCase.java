package com.example.app.domain.base;

import androidx.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public abstract class BaseObservableUseCase<T, Params> {

    @Nullable
    private final Scheduler threadExecutor;
    @Nullable
    private final Scheduler postExecutionThread;

    public BaseObservableUseCase() {
        threadExecutor = null;
        postExecutionThread = null;
    }

    public BaseObservableUseCase(@Nullable Scheduler threadExecutor, @Nullable Scheduler postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Observable<T> buildUseCaseObservable(Params params);

    public Observable<T> execute(Params params) {
        Observable<T> observable = buildUseCaseObservable(params);
        if (threadExecutor != null) {
            observable = observable.subscribeOn(threadExecutor);
        }
        if (postExecutionThread != null) {
            observable = observable.observeOn(postExecutionThread);
        }
        return observable;
    }
}