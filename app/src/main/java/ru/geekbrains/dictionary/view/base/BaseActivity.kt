package ru.geekbrains.dictionary.view.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.dictionary.utils.ui.AlertDialogFragment
import ru.geekbrains.dictionary.R
import ru.geekbrains.dictionary.model.entities.AppState
import ru.geekbrains.dictionary.model.repository.IRepository
import ru.geekbrains.dictionary.utils.isOnline
import ru.geekbrains.dictionary.viewModel.BaseViewModel
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject

abstract class BaseActivity<T : AppState, I : IRepository> : AppCompatActivity() {

    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message).show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    abstract fun renderData(dataModel: T)

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }

    protected open lateinit var viewModel: BaseViewModel<T>

    @Inject
    protected open lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract fun injectViewModel()

    protected inline fun <reified T : ViewModel> getViewModel(): T =
        ViewModelProviders.of(this, viewModelFactory)[T::class.java]

}
