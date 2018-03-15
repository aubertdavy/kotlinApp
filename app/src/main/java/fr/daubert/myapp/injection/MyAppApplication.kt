package fr.daubert.myapp.injection

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by daubert on 15/03/2018.
 */

class MyAppApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}