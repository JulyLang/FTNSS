package com.example.app.domain.base;

import androidx.annotation.Nullable;

import io.reactivex.Completable;
import io.reactivex.Scheduler;

public abstract class BaseCompletableUseCase<Params> {

    @Nullable
    private final Scheduler threadExecutor;
    @Nullable
    private final Scheduler postExecutionThread;

    public BaseCompletableUseCase() {
        threadExecutor = null;
        postExecutionThread = null;
    }

    public BaseCompletableUseCase(@Nullable Scheduler threadExecutor, @Nullable Scheduler postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Completable buildUseCaseObservable(Params params);

    public Completable execute(Params params) {
        Completable completable = buildUseCaseObservable(params);
        if (threadExecutor != null) {
            completable = completable.subscribeOn(threadExecutor);
        }
        if (postExecutionThread != null) {
            completable = completable.observeOn(postExecutionThread);
        }
        return completable;
    }
}