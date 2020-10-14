package com.ahmadhamwi.keepclone.domain.interactors;

import com.ahmadhamwi.keepclone.domain.interactors.callbacks.OnComplete;
import com.ahmadhamwi.keepclone.domain.interactors.callbacks.OnError;
import com.ahmadhamwi.keepclone.domain.interactors.callbacks.OnNext;

import io.reactivex.rxjava3.observers.DisposableObserver;

public class UseCaseDisposableObserver<T> extends DisposableObserver<T> {

    private OnNext<T> onNext;
    private OnError onError;
    private OnComplete onComplete;

    public UseCaseDisposableObserver(OnNext<T> onNext, OnError onError, OnComplete onComplete) {
        this.onNext = onNext;
        this.onError = onError;
        this.onComplete = onComplete;
    }

    public UseCaseDisposableObserver(OnNext<T> onNext, OnError onError) {
        this(onNext, onError, null);
    }

    public UseCaseDisposableObserver(OnNext<T> onNext) {
        this(onNext, null);
    }

    public UseCaseDisposableObserver() {
        this(null);
    }

    @Override
    public void onNext(T t) {
        if(onNext != null) {
            onNext.onNext(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        if(onError != null) {
            onError.onError(e);
        }
    }

    @Override
    public void onComplete() {
        if(onComplete != null) {
            onComplete.onComplete();
        }
    }
}
