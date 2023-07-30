/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.androidlearn.mvvm;


import androidx.annotation.MainThread;
import androidx.lifecycle.LifecycleOwner;

public interface ILifecycleOwnerRef {

    @MainThread
    LifecycleOwner getLifecycleOwner();
}
