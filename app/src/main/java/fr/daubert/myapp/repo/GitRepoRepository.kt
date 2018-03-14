package fr.daubert.myapp.repo

import android.content.Context
import fr.daubert.myapp.NetworkManager
import fr.daubert.myapp.model.Repository

/**
 * Created by daubert on 02/03/2018.
 */

class GitRepoRepository(context: Context) {

    val networkManager = NetworkManager(context)
    val localDataSource = GitRepoLocalDataSource()
    val remoteDataSource = GitRepoRemoteDataSource()

    fun getGitRepositories(onRepositoryReadyCallback: OnRepositoryReadyCallback) {
        networkManager.isNetworkConnected.let {
            if (it) {
                remoteDataSource.getRepositories(object : GitRepoRemoteDataSource.OnRepoRemoteReadyCallback {
                    override fun onRemoteDataReady(data: ArrayList<Repository>) {
                        localDataSource.saveRepositories(data)
                        onRepositoryReadyCallback.onDataReady(data)
                    }
                })
            } else {
                localDataSource.getRepositories(object : GitRepoLocalDataSource.OnRepoLocalReadyCallback {
                    override fun onLocalDataReady(data: ArrayList<Repository>) {
                        onRepositoryReadyCallback.onDataReady(data)
                    }
                })
            }
        }
    }

    interface OnRepositoryReadyCallback {
        fun onDataReady(data : ArrayList<Repository>)
    }
}

