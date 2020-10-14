package com.ahmadhamwi.keepclone.domain.interactors.usecase.base;

import com.ahmadhamwi.keepclone.domain.interactors.UseCaseDisposableObserver;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class UseCase<RETURN, PARAMS> {

    private CompositeDisposable compositeDisposable;

    public UseCase() {
        this.compositeDisposable = new CompositeDisposable();
    }

    protected abstract Observable<RETURN> buildObservable();

    protected abstract Observable<RETURN> buildParamsObservable(PARAMS params);

    public void execute(PARAMS params, UseCaseDisposableObserver<RETURN> observer) {
        if(params instanceof Void) {
            addDisposable(
                    buildObservable()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribeWith(observer)
            );
        } else {
            addDisposable(
                    buildParamsObservable(params)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribeWith(observer)
            );
        }
    }

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void dispose() {
        compositeDisposable.dispose();
    }
}
