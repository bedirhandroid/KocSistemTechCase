import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bedirhandroid.kocsistemtechcase.R
import com.bedirhandroid.kocsistemtechcase.databinding.GridListRowBinding
import com.bedirhandroid.kocsistemtechcase.databinding.HorizontalListRowBinding
import com.bedirhandroid.kocsistemtechcase.databinding.ListRowBinding
import com.bedirhandroid.kocsistemtechcase.network.responses.DataModel
import com.bedirhandroid.kocsistemtechcase.util.loadImage

class DynamicLayoutAdapter(
    private val dataList: ArrayList<DataModel>,
    private val layoutType: Int,
    private val clickItem: (DataModel) -> Unit,
    private val deleteItem: ((DataModel) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_LINEAR = 1
        private const val VIEW_TYPE_GRID = 2
        private const val VIEW_TYPE_HORIZONTAL = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_LINEAR -> {
                val binding = ListRowBinding.inflate(inflater, parent, false)
                ListViewHolder(binding)
            }
            VIEW_TYPE_GRID -> {
                val binding = GridListRowBinding.inflate(inflater, parent, false)
                GridViewHolderLayout(binding)
            }
            VIEW_TYPE_HORIZONTAL -> {
                val binding = HorizontalListRowBinding.inflate(inflater, parent, false)
                HorizontalViewHolderLayout(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList[position]
        when (holder) {
            is ListViewHolder -> {
                holder.bind(data)
                holder.binding.btnApply.setOnClickListener { clickItem.invoke(data) }
            }
            is GridViewHolderLayout -> {
                holder.bind(data)
                holder.binding.btnApply.setOnClickListener { clickItem.invoke(data) }
            }
            is HorizontalViewHolderLayout -> {
                holder.apply {
                    bind(data)
                    holder.binding.apply {
                        btnApply.setOnClickListener { clickItem.invoke(data) }
                        btnDelete.setOnClickListener { deleteItem?.invoke(data) }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        val data = dataList[position]
        // Verilere bağlı olarak görünüm türünü belirle
        return when (layoutType) {
            1 -> VIEW_TYPE_LINEAR
            2 -> VIEW_TYPE_GRID
            else -> VIEW_TYPE_HORIZONTAL
        }
    }

    // ViewHolder sınıfları

    class ListViewHolder(val binding: ListRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataModel) {
            binding.apply {
                tvArtistName.text = data.artistName
                tvTrackName.text = data.trackName
                data.artworkUrl100?.let(ivPhoto::loadImage)
            }
        }
    }

    class GridViewHolderLayout(val binding: GridListRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataModel) {
            binding.apply {
                tvArtistName.text = data.artistName
                tvTrackName.text = data.trackName
                data.artworkUrl100?.let(ivPhoto::loadImage)
            }
        }
    }

    class HorizontalViewHolderLayout(val binding: HorizontalListRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataModel) {
            binding.apply {
                tvArtistName.text = data.artistName
                tvTrackName.text = data.trackName
                tvDate.text = binding.root.context.getString(
                    R.string.formatted_date_time,
                    data.formattedDate,
                    data.formattedTime
                )
                data.artworkUrl100?.let(ivPhoto::loadImage)
            }
        }
    }
}
