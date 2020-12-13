package com.goda.npmoa.di.component

import android.app.Application
import com.goda.npmoa.NTMPOAApplication
import com.goda.npmoa.di.builder.ActivityBuilder
import com.goda.npmoa.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent {
    fun inject(app: NTMPOAApplication)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}