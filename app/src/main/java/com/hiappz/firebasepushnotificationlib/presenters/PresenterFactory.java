package com.hiappz.firebasepushnotificationlib.presenters;

import com.hiappz.firebasepushnotificationlib.utils.UtilityConstant;

/**
 * Created by aj on 18/9/17.
 */

public abstract class PresenterFactory {
    private static PresenterFactory presenterFacory;

    public static PresenterFactory getInstance(int factoryCode){
        switch (factoryCode){
            case UtilityConstant.REGISTER_DEVICE_PRSENTER:
                presenterFacory = new RegisterDevicePresenter();
                return presenterFacory;

        }
        return presenterFacory;
    }
}
