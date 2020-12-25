package com.example.app.presentation.mvp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app.presentation.error.ErrorCode;

public abstract class BaseFragment<V, P extends IBasePresenter<V>> extends Fragment implements IBaseView<P> {

    @LayoutRes
    protected abstract int getLayout();

    protected abstract String logTag();

    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(logTag(), "onViewCreated");
        presenter.subscribe((V) this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(logTag(), "onResume");
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(logTag(), "onPause");
        presenter.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.d(logTag(), "onDestroyView");
        presenter.unsubscribe();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(logTag(), "onDestroy");
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public final void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError(ErrorCode errorCode) {
        switch (errorCode) {
            case REACH_MAX_MEALS:
                //todo get from Strings.
                Toast.makeText(getContext(), "Max meals count is 7", Toast.LENGTH_LONG).show();
                break;
            default:
                Log.d(logTag(), "showError errorCode=" + errorCode + ", unknown error");
        }
    }
}
