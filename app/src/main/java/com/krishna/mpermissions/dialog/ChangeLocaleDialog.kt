package com.krishna.mpermissions.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.krishna.mpermissions.OrataroApplication
import com.krishna.mpermissions.adapter.LocaleAdapter
import com.krishna.mpermissions.databinding.DialogChangeLocalBinding
import com.krishna.mpermissions.utils.*

typealias changeLocaleDialogDelegate = ChangeLocaleDialog.ChangeLocaleDialogItemClick

object ChangeLocaleDialog {
    private var dialog: Dialog? = null

    fun createDialog(context: Context, changeLocalDialogItemClick: ChangeLocaleDialogItemClick) {
        dialog = Dialog(context)

        val localList: ArrayList<LocalModel> = ArrayList()

        for (localCode in localesCodes) {
            val localModel = LocalModel()
            localModel.localName = locales[localesCodes.indexOf(localCode)]
            localModel.localCode = localCode
            localModel.selected = localCode == AppPreference.loadLocale(context)
            localList.add(localModel)
        }

        val changeLocalBinding: DialogChangeLocalBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context), ProjectLayouts.dialog_change_local, null, false
        )

        dialog?.let {

            it.requestWindowFeature(Window.FEATURE_NO_TITLE)

            it.setContentView(changeLocalBinding.root)

            it.window!!.let { window ->
                window.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
                window.setBackgroundDrawableResource(ProjectColors.transparent)
            }

            changeLocalBinding.rvLocals.adapter =
                    LocaleAdapter(localList, onLocalChange(changeLocalDialogItemClick))

            it.show()
        }
    }

    private fun onLocalChange(changeLocalDialogItemClick: ChangeLocaleDialogItemClick): LocaleAdapter.LocalAdapterItemClick {
        return object : LocaleAdapter.LocalAdapterItemClick {
            override fun onItemClick(localModel: LocalModel) {
                if (AppPreference.loadLocale(OrataroApplication.instance.applicationContext) != localModel.localCode) {
                    changeLocalDialogItemClick.changeLocale(localModel)
                    dismissDialog()
                }
            }
        }
    }

    interface ChangeLocaleDialogItemClick {
        fun changeLocale(localModel: LocalModel)
    }

    fun dismissDialog() {
        dialog!!.dismiss()
    }
}