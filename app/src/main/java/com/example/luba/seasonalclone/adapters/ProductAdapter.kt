package com.example.luba.seasonalclone.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.luba.seasonalclone.models.ProduceItem
import com.example.luba.seasonalclone.R
import com.example.luba.seasonalclone.R.id.ivProduce
import com.example.luba.seasonalclone.utils.bindView
import java.io.Serializable


class ProductAdapter(private var items: List<ProduceItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val FRUIT_TYPE = "Fruit"
    val VEGETABLE_TYPE = "Vegetable"
    val HERB_TYPE = "Herb"
    val LEGUME_TYPE = "Legume"
    val NUT_TYPE = "Nut"

    private val itemsWithHeaders = sortItemsByHeader(items)

    init {
        notifyDataSetChanged()
    }

    private fun sortItemsByHeader(items: List<ProduceItem>): List<Any> {
        val displayItems = items.filter { item -> item.display }

        val sortedProduce = mutableListOf<Any>()
        if (displayItems.isNotEmpty()) {
            sortedProduce.add("Fruits")
            sortedProduce.addAll(displayItems.filter { displayItem -> displayItem.type == FRUIT_TYPE })
            sortedProduce.add("Vegetables")
            sortedProduce.addAll(displayItems.filter { displayItem ->
                listOf(VEGETABLE_TYPE, HERB_TYPE, LEGUME_TYPE, NUT_TYPE).contains(displayItem.type)
            })
        }

        return sortedProduce

    }

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        if (itemsWithHeaders[position] is ProduceItem) {
            return VIEW_TYPE_ITEM
        }
        return VIEW_TYPE_HEADER
    }


    inner class HeaderViewHolder(view: View) : viewProduct(view) {
        val headerLabel: TextView by bindView(R.id.tvHeader)
    }

    inner class BrowseItemViewHolder(itemView: View) : viewProduct (itemView) {

        val name: TextView by bindView(R.id.tvProductName)
        val type: TextView by bindView(R.id.tvProductType)
        val image: ImageView by bindView(R.id.ivProduce)

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        //val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produce, parent, false);
        //val viewHolder = viewProduct(view);
        //return viewHolder;

        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produce, parent, false)
                BrowseItemViewHolder(v)
            }
            else -> {
                val v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false)
                HeaderViewHolder(v)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemsWithHeaders.size
    }

    open inner class viewProduct(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            VIEW_TYPE_ITEM -> {
                //val name = items[position].name
                //val type = items[position].type
                //val image = items[position].image

                //val viewHolder = holder as BrowseItemViewHolder
                //viewHolder.name.text = name
                //viewHolder.type.text = type

                val itemHolder = holder as BrowseItemViewHolder
                val item = itemsWithHeaders[position] as ProduceItem
                itemHolder.name.text = item.name
                itemHolder.type.text = item.type

                Glide.with(holder.itemView)
                        .load(item.image)
                        .into(itemHolder.image)
            }
            VIEW_TYPE_HEADER -> {
                val headerViewHolder = holder as HeaderViewHolder
                val sectionTitle = itemsWithHeaders[position] as String
                headerViewHolder.headerLabel.text = sectionTitle
            }
        }

    }
}


