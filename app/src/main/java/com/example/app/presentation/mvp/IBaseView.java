package com.example.app.presentation.mvp;

import com.example.app.presentation.error.ErrorCode;

public interface IBaseView<P> {

    void setPresenter(P presenter);

    void showError(ErrorCode errorCode);
}
