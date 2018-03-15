package fr.daubert.myapp.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import fr.daubert.myapp.model.Repository
import fr.daubert.myapp.repo.GitRepoRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by daubert on 02/03/2018.
 */

class MainViewModel @Inject constructor(var gitRepoRepository: GitRepoRepository) : ViewModel() {

    var repositories = MutableLiveData<ArrayList<Repository>>()
    val isLoading = ObservableField(false)

    private var compositeDisposable = CompositeDisposable()

    fun loadRepositories() {
        isLoading.set(true)

        gitRepoRepository.getGitRepositories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ArrayList<Repository>> {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onNext(t: ArrayList<Repository>) {
                        repositories.value = t
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                    }
                })
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}