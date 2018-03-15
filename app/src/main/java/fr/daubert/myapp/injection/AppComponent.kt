package fr.daubert.myapp.injection

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by daubert on 15/03/2018.
 */

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, MainActivityModule::class, ViewModelBuilder::class])
interface AppComponent : AndroidInjector<MyAppApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyAppApplication>()
}