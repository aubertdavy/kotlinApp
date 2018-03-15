package fr.daubert.myapp.injection

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by daubert on 15/03/2018.
 */

@Module
class AppModule {

    @Provides
    fun providesContext(application: MyAppApplication): Context {
        return application.applicationContext
    }
}