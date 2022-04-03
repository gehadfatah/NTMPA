package com.goda.npmoa

import android.app.Application
import com.goda.npmoa.data_layer.common.ApplicationIntegration
import com.goda.npmoa.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class NTMPOAApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        ApplicationIntegration.with(this)

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return activityDispatchingAndroidInjector
    }
}