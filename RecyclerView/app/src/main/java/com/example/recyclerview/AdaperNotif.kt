package com.example.recyclerview

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemNotifBinding
import kotlinx.coroutines.currentCoroutineContext

class AdaperNotif (private val data : MutableList<ItemNotifModel> = mutableListOf()): RecyclerView.Adapter<AdaperNotif.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNotifBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding:ItemNotifBinding ): RecyclerView.ViewHolder(binding.root){
        fun bindItem(item: ItemNotifModel){
            binding.tvTitle.text = item.title
            binding.tvAccountInfor.text = item.accountDetail

            binding.tvAmount.text = item.transactionAmount


            val iconResource =  when(item.type) {
                "TIPE TOPUP" -> R.drawable.img_1
                "Transfer Masuk" -> R.drawable.img_2
                "Pembelian" -> R.drawable.img_3
                else -> R.drawable.ic_launcher_foreground
            }
            binding.ivTransaction.setImageResource(iconResource)
            val iconBackround =  when(item.type) {
                "TIPE TOPUP" -> R.color.purple_200
                "Transfer Masuk" -> R.color.orange
                "Pembelian" -> R.color.pink
                else -> R.color.white
            }
            binding.ivTransaction.setBackgroundResource(iconBackround)


            when(item.type) {
                "Transfer Masuk" -> binding.tvAmount.setTextColor(Color.GREEN)
                else -> binding.tvAmount.setTextColor(Color.RED) }



        }
    }
}