package com.example.app.domain;

import androidx.annotation.Nullable;

import com.example.app.data.repository.MealRepository;
import com.example.app.domain.base.BaseCompletableUseCase;
import com.example.app.domain.repository.IMealRepository;

import io.reactivex.Completable;
import io.reactivex.Scheduler;

public class CreateMealUseCase extends BaseCompletableUseCase<CreateMealUseCase.Params> {

    private final IMealRepository mealRepository = new MealRepository();

    public CreateMealUseCase(@Nullable Scheduler threadExecutor, @Nullable Scheduler postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Completable buildUseCaseObservable(Params params) {
        return mealRepository.createMeal(params.getDate());
    }

    public static class Params {

        private final long date;

        public Params(long date) {
            this.date = date;
        }

        public long getDate() {
            return date;
        }
    }
}
