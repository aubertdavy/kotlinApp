package fr.daubert.myapp.repo

import fr.daubert.myapp.NetworkManager
import fr.daubert.myapp.model.Repository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by daubert on 02/03/2018.
 */

class GitRepoRepository @Inject constructor (val networkManager: NetworkManager) {

    private val localDataSource = GitRepoLocalDataSource()
    private val remoteDataSource = GitRepoRemoteDataSource()

    fun getGitRepositories(): Observable<ArrayList<Repository>> {
        networkManager.isNetworkConnected.let {
            return if (it) {
                return remoteDataSource.getRepositories().flatMap {
                    return@flatMap localDataSource.saveRepositories(it)
                            .toSingleDefault(it)
                            .toObservable()
                }
            } else {
                return localDataSource.getRepositories()
            }
        }
    }
}

