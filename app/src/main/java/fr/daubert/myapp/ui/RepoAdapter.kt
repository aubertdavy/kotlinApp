package fr.daubert.myapp.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import fr.daubert.myapp.databinding.RvRepositoryItemBinding
import fr.daubert.myapp.model.Repository

/**
 * Created by daubert on 02/03/2018.
 */

class RepoAdapter(private var items: ArrayList<Repository>,
                  private var listener: OnItemClickListener) : RecyclerView.Adapter<RepoAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = RvRepositoryItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    fun replaceData(arrayList: ArrayList<Repository>) {
        items = arrayList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(private var binding: RvRepositoryItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repository, listener: OnItemClickListener?) {
            binding.repository = repo
            if (listener != null) {
                binding.root.setOnClickListener({ _ -> listener.onItemClick(layoutPosition) })
            }
            binding.executePendingBindings()
        }
    }
}