package com.hiappz.firebasepushnotificationlib.firebaseutils;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.hiappz.firebasepushnotificationlib.BuildConfig;
import com.hiappz.firebasepushnotificationlib.LifeCycleListener;
import com.hiappz.firebasepushnotificationlib.UtilityConstant;
import com.hiappz.firebasepushnotificationlib.models.RegisterDeviceResModel;
import com.hiappz.firebasepushnotificationlib.presenters.PresenterFactory;
import com.hiappz.firebasepushnotificationlib.presenters.RegisterDevicePresenter;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by aj on 14/9/17.
 */

public class FirebaseInstnaceIdServiceHelper extends FirebaseInstanceIdService {
    private final String TAG = "FbInstnceIdServiceHlpr";
    private String currentRefreshToken = null;
    private String currentRefreshTokenId = null;
    private long currentRefreshTokenCreationTime = 0;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        currentRefreshToken = FirebaseInstanceId.getInstance().getToken();
        currentRefreshTokenCreationTime = FirebaseInstanceId.getInstance().getCreationTime();
        currentRefreshTokenId = FirebaseInstanceId.getInstance().getId();

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onTokenRefresh -->> executed : currentRefreshToken :: " + currentRefreshToken);
            Log.d(TAG, "onTokenRefresh -->> executed : currentRefreshTokenCreationTime :: " + currentRefreshTokenCreationTime);
            Log.d(TAG, "onTokenRefresh -->> executed : currentRefreshTokenId :: " + currentRefreshTokenId);
        }

        LifeCycleListener lifeCycleListener = new LifeCycleListener() {
            @Override
            public void onViewResumed(Object object) {
                RegisterDeviceResModel deviceResModel = null;

                if (object instanceof RegisterDeviceResModel){
                    deviceResModel = (RegisterDeviceResModel) object;
                }
            }

            @Override
            public void onViewAttached() {

            }

            @Override
            public void onViewDetached() {

            }
        };

        RegisterDevicePresenter presenter = (RegisterDevicePresenter) PresenterFactory.getInstance(UtilityConstant.REGISTER_DEVICE_PRSENTER);
        Observable<Response<RegisterDeviceResModel>> responseObservable = presenter.registerDeviceToFirebase(lifeCycleListener, currentRefreshToken);
    }
}
