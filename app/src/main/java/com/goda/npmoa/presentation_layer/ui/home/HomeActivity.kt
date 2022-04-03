package com.goda.npmoa.presentation_layer.ui.home

import android.content.pm.PackageInfo
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.goda.npmoa.BR
import com.goda.npmoa.R
import com.goda.npmoa.databinding.HomeActivityBinding
import com.goda.npmoa.presentation_layer.ui.base.di.ViewModelProviderFactory
import com.goda.npmoa.presentation_layer.ui.base.ui.BaseActivity
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class HomeActivity : BaseActivity<HomeActivityBinding, HomeViewModel>(),
    HasAndroidInjector {
    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.home_activity

    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val manager = this.packageManager
        val info = manager.getPackageInfo(this.packageName, 0)
        println("version " + info.versionCode)
        val pInfo: PackageInfo =
            packageManager.getPackageInfo(packageName, 0)
        val versionCode: Int
        versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            pInfo.longVersionCode.toInt() // avoid huge version numbers and you will be ok
        } else {
            pInfo.versionCode
        }
        println("version $versionCode")

        val versionCodde: Long =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageManager.getPackageInfo(packageName, 0).longVersionCode
            } else {
                packageManager.getPackageInfo(packageName, 0).versionCode.toLong()
            }

    }
}