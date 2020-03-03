package es.iessaladillo.pedrojoya.stroop.ui.rankingFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.room.entities.Game
import es.iessaladillo.pedrojoya.stroop.room.entities.Player

class RankingFragmentAdapter : RecyclerView.Adapter<RankingFragmentAdapter.ViewHolder>() {


    private var data: List<Game> = emptyList()
    private var dataPlayer: List<Player> = emptyList()
    private var onItemClickListener: ((Int) -> Unit)? = null


    fun setOnItemClickListener(listener: ((Int) -> Unit)?) {
        onItemClickListener = listener
    }

    fun getItem(position: Int): Game {
        return data[position]
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.rank_item, parent, false)
        return ViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun submitList(games: List<Game>) {
        data = games
        notifyDataSetChanged()
    }

    fun submitPlayerList(players: List<Player>) {
        dataPlayer = players
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View, onItemClickListener: ((Int) -> Unit)?) : RecyclerView.ViewHolder(itemView) {

        private val lblRankWords: TextView = itemView.findViewById(R.id.lblRankWords)
        private val lblRankCorrect: TextView = itemView.findViewById(R.id.lblRankCorrect)
        private val lblRankMinutes: TextView = itemView.findViewById(R.id.lblRankMinutes)
        private val lblRankGameMode: TextView = itemView.findViewById(R.id.lblRankWords)
        private val imgPlayerAvatar: ImageView = itemView.findViewById(R.id.imgPlayerAvatar)
        private val lblRankPoints: TextView = itemView.findViewById(R.id.lblRankPoints)
        private val lblRankPlayerName: TextView = itemView.findViewById(R.id.lblRankPlayerName)

        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition)
            }
        }

        fun bind(game: Game) {
            game.run {
                val player = dataPlayer.find { pl -> pl.id == game.playerID }

                imgPlayerAvatar.setImageResource(player!!.avatar)
                lblRankPlayerName.text = player!!.name
                lblRankCorrect.text = game.correct.toString()
                lblRankMinutes.text = game.minutes.toString()
                lblRankGameMode.text = game.gameMode
                lblRankPoints.text = game.points.toString()
                lblRankWords.text = game.words.toString()
            }
        }
    }
}
