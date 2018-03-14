package fr.daubert.myapp.repo

import android.os.Handler
import fr.daubert.myapp.model.Repository

/**
 * Created by daubert on 14/03/2018.
 */


class GitRepoRemoteDataSource {

    fun getRepositories(onRepositoryReadyCallback: OnRepoRemoteReadyCallback) {
        var arrayList = ArrayList<Repository>()
        arrayList.add(Repository("First from remote", "Owner 1", 100, false))
        arrayList.add(Repository("Second from remote", "Owner 2", 30, true))
        arrayList.add(Repository("Third from remote", "Owner 3", 430, false))

        Handler().postDelayed({ onRepositoryReadyCallback.onRemoteDataReady(arrayList) }, 2000)
    }

    interface OnRepoRemoteReadyCallback {
        fun onRemoteDataReady(data: ArrayList<Repository>)
    }
}