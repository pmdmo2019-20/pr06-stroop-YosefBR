package es.iessaladillo.pedrojoya.stroop.ui.playerFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.room.entities.Player

class PlayerFragmentAdapter : RecyclerView.Adapter<PlayerFragmentAdapter.ViewHolder>() {

    private var data: List<Player> = emptyList()
    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.player_item, parent, false)
        return ViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount(): Int = data.size

    fun getItem(position: Int): Player = data[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player: Player = data[position]
        holder.bind(player)
    }

    fun submitList(newList: List<Player>) {
        data = newList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: ((Int) -> Unit)?){
        onItemClickListener = listener
    }

    class ViewHolder(itemView: View, onItemClickListener: ((Int) -> Unit)?) : RecyclerView.ViewHolder(itemView) {

        private val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
        private val lblPlayerName: TextView = itemView.findViewById(R.id.lblPlayerName)

        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition)
            }
        }

        fun bind(player: Player) {
            player.run {
                imgAvatar.setImageResource(avatar)
                lblPlayerName.text = player.name
            }
        }

    }

}