package com.hiappz.firebasepushnotificationlib.presenters;

import com.hiappz.firebasepushnotificationlib.utils.listeners.LifeCycleListener;
import com.hiappz.firebasepushnotificationlib.apis.FirebaseApis;
import com.hiappz.firebasepushnotificationlib.helpers.LogHelper;
import com.hiappz.firebasepushnotificationlib.helpers.RetrofitHelper;
import com.hiappz.firebasepushnotificationlib.models.RegisterDeviceReqModel;
import com.hiappz.firebasepushnotificationlib.models.RegisterDeviceResModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by aj on 18/9/17.
 */

public class RegisterDevicePresenter extends PresenterFactory{
    private final String TAG = "RegisterDevicePresenter";
    private RegisterDeviceResModel deviceResModel = null;

    public Observable<Response<RegisterDeviceResModel>> registerDeviceToFirebase(final LifeCycleListener listener, final String firebaseToken) {
        LogHelper.d(TAG, "-->> registerDeviceToFirebase -->> executed");

        Observable<Response<RegisterDeviceResModel>> responseObservable = null;
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        FirebaseApis apis = retrofit.create(FirebaseApis.class);

        RegisterDeviceReqModel deviceReqModel = new RegisterDeviceReqModel();
        deviceReqModel.setSubId(firebaseToken);
        deviceReqModel.setAppId("BnbOptsmQPtHa9+4e+9kY7N6QbqlnxkALL852a4VP+k=");
        deviceReqModel.setDeviceType("android");
        deviceReqModel.setDeviceId("12345");
        deviceReqModel.setCity("Noida");
        deviceReqModel.setState("Uttar Pradesh");
        deviceReqModel.setCountry("India");

        deviceResModel = new RegisterDeviceResModel();

        responseObservable = apis.subscribeDevice(deviceReqModel);
        responseObservable.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<RegisterDeviceResModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogHelper.d(TAG, "-->> onSubscribe -->> executed -->> "+d.toString());
                    }

                    @Override
                    public void onNext(Response<RegisterDeviceResModel> value) {
                        if (value.isSuccessful() && value.code() == 200) {
                            deviceResModel = value.body();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        registerDeviceToFirebase(listener, firebaseToken);
                    }

                    @Override
                    public void onComplete() {
                        listener.onViewResumed(deviceResModel);
                    }
                });

        return responseObservable;
    }
}
