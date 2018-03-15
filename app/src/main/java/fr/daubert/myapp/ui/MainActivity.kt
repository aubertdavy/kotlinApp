package fr.daubert.myapp.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import fr.daubert.myapp.R
import fr.daubert.myapp.databinding.ActivityMainBinding
import fr.daubert.myapp.model.Repository
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), RepoAdapter.OnItemClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private var repoAdapter = RepoAdapter(arrayListOf(), this)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
        binding.repositoryRv.adapter = repoAdapter

        viewModel.repositories.observe(this, Observer<ArrayList<Repository>> {it?.let { repoAdapter.replaceData(it) }})
        viewModel.loadRepositories()
    }

    override fun onItemClick(position: Int) {

    }
}
