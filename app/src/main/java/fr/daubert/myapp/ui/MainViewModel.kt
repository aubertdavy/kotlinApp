package fr.daubert.myapp.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import fr.daubert.myapp.model.Repository
import fr.daubert.myapp.repo.GitRepoRepository

/**
 * Created by daubert on 02/03/2018.
 */

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var repoModel: GitRepoRepository = GitRepoRepository(getApplication())

    val text = ObservableField("old data")

    val isLoading = ObservableField(false)

    var repositories = MutableLiveData<ArrayList<Repository>>()

    fun loadRepositories() {
        isLoading.set(true)
        repoModel.getGitRepositories(object : GitRepoRepository.OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.value = data
            }
        })
    }
}