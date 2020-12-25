package com.example.app.domain;

import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import com.example.app.data.repository.MealRepository;
import com.example.app.domain.base.BaseObservableUseCase;
import com.example.app.domain.model.MealModel;
import com.example.app.domain.repository.IMealRepository;
import com.example.app.domain.util.DateUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetAllMealsUseCase extends BaseObservableUseCase<List<MealModel>, Void> {

    private final IMealRepository mealRepository = new MealRepository();

    public GetAllMealsUseCase(@Nullable Scheduler threadExecutor, @Nullable Scheduler postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable<List<MealModel>> buildUseCaseObservable(Void ignore) {
        final Pair<Long, Long> dayStartAndEndTimestamps = DateUtil.getDayStartAndEndTimestamps();
        final long startDayTimestamp = dayStartAndEndTimestamps.first;
        final long endDayTimestamp = dayStartAndEndTimestamps.second;
        return mealRepository.getAllMeals(startDayTimestamp, endDayTimestamp);
    }

    //todo support different days
//    public static class Params {
//
//        private final long date;
//
//        public Params(long date) {
//            this.date = date;
//        }
//
//        public long getDate() {
//            return date;
//        }
//    }
}
