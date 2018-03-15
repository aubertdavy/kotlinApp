package fr.daubert.myapp.extensions

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by daubert on 15/03/2018.
 */

class RxExtension {

    operator fun CompositeDisposable.plusAssign(disposable: CompositeDisposable) {
        add(disposable)
    }
}