package com.krishna.mpermissions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.krishna.mpermissions.R
import com.krishna.mpermissions.databinding.ItemRvLocalsBinding
import com.krishna.mpermissions.dialog.LocalModel

class LocaleAdapter(@NonNull private var localList: ArrayList<LocalModel>, @NonNull private var localAdapterItemClick: LocalAdapterItemClick) :
    RecyclerView.Adapter<LocaleAdapter.LocalHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalHolder {
        return LocalHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_rv_locals,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return localList.size
    }

    override fun onBindViewHolder(holder: LocalHolder, position: Int) {
        holder.bindView(localList[position], localAdapterItemClick)
    }

    class LocalHolder(
        private var localsBinding: ItemRvLocalsBinding
    ) :
        RecyclerView.ViewHolder(localsBinding.root) {
        fun bindView(localModel: LocalModel, localAdapterItemClick: LocalAdapterItemClick) {
            localsBinding.localModel = localModel
            localsBinding.cvLocal.setOnClickListener {
                localAdapterItemClick.onItemClick(localModel)
            }
        }
    }

    interface LocalAdapterItemClick {
        fun onItemClick(localModel: LocalModel)
    }
}