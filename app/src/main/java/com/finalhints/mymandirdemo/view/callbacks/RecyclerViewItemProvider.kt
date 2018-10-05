package com.finalhints.mymandirdemo.view.callbacks


interface RecyclerViewItemProvider<T> {
    fun getItem(adapterPosition: Int): T
}
