package es.iessaladillo.pedrojoya.stroop.ui.playerFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.avatars

class NewPlayerFragmentAdapter : RecyclerView.Adapter<NewPlayerFragmentAdapter.ViewHolder>() {

    private var data: List<Int> = avatars
    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.avatar_item, parent, false)
        return ViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount(): Int = data.size

    fun getItem(position: Int): Int = data[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val avatar = data[position]
        holder.bind(avatar)
    }

    fun setOnItemClickListener(listener: ((Int) -> Unit)?){
        onItemClickListener = listener
    }

    class ViewHolder(itemView: View, onItemClickListener: ((Int) -> Unit)?) : RecyclerView.ViewHolder(itemView) {

        private val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)

        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition)
            }
        }

        fun bind(avatar: Int) {
            avatar.run {
                imgAvatar.setImageResource(avatar)
            }
        }

    }

}