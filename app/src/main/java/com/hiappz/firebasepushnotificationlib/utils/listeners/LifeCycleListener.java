package com.hiappz.firebasepushnotificationlib.utils.listeners;

/**
 * Created by aj on 18/9/17.
 */

public interface LifeCycleListener {
    void onViewResumed(Object object);
    void onViewAttached();
    void onViewDetached();
}
