package ru.geekbrains.dictionary.view.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.dictionary.R
import ru.geekbrains.dictionary.databinding.ActivityMainBinding
import ru.geekbrains.dictionary.model.entities.AppState
import ru.geekbrains.dictionary.model.entities.DataModel
import ru.geekbrains.dictionary.model.repository.Repository
import ru.geekbrains.dictionary.view.base.BaseActivity
import ru.geekbrains.dictionary.view.base.View
import ru.geekbrains.dictionary.view.main.adapter.MainAdapter
import javax.inject.Inject

class MainActivity : BaseActivity<AppState, Repository>() {

    override fun injectViewModel() {
        viewModel = getViewModel()
    }
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener, emptyList()) }

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        recyclerView = binding.mainActivityRecyclerview

        binding.search.setEndIconOnClickListener {
            println("here")
            viewModel.getData(binding.tvSearch.text.toString(), true)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        recyclerView.layoutManager =
                            LinearLayoutManager(baseContext)
                        recyclerView.adapter =
                            MainAdapter(onListItemClickListener, dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            viewModel.getData("hi", true)
        }
    }

    private fun showViewSuccess() {
        binding.mainActivityRecyclerview.visibility = VISIBLE
        binding.progressBarHorizontal.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.mainActivityRecyclerview.visibility = GONE
        binding.progressBarHorizontal.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.mainActivityRecyclerview.visibility = GONE
        binding.progressBarHorizontal.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }

}
