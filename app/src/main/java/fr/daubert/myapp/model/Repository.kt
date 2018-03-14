package fr.daubert.myapp.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import fr.daubert.myapp.BR

/**
 * Created by daubert on 20/02/2018.
 */

class Repository(repositoryName: String?, var repositoryOwner: String?,
                 var numberOfStars: Int?, var hasIssues: Boolean = false) : BaseObservable() {

    @get:Bindable
    var repositoryName : String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.repositoryName)
        }
}