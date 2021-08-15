package ru.geekbrains.core

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.core.databinding.LoadingLayoutBinding
import ru.geekbrains.model.AppState
import ru.geekbrains.model.DataModel
import ru.geekbrains.utils.network.isOnline
import ru.geekbrains.utils.ui.AlertDialogFragment

private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"

abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>
    private var isNetworkAvailable: Boolean = false
    protected lateinit var loadingBinding: LoadingLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline(applicationContext)
        loadingBinding = LoadingLayoutBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun renderData(appState: T) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                appState.data?.let {
                    if (it.isEmpty()) {
                        showAlertDialog(
                            getString(R.string.dialog_tittle_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    loadingBinding.progressBarHorizontal.visibility = View.VISIBLE
                    loadingBinding.progressBarRound.visibility = View.GONE
                    loadingBinding.progressBarHorizontal.progress = appState.progress!!
                } else {
                    loadingBinding.progressBarHorizontal.visibility = View.GONE
                    loadingBinding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }

    private fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    private fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun showViewWorking() {
        loadingBinding.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        loadingBinding.loadingFrameLayout.visibility = View.VISIBLE
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    abstract fun setDataToAdapter(data: List<DataModel>)
}
