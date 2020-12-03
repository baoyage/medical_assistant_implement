package syr.project.medical_assistant_implement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.squareup.picasso.Picasso

class PrescriptionListAdapter(var modelClass: Class<PrescriptionData>, var query: Query):
    FirebaseRecyclerAdapter<PrescriptionData, PrescriptionListAdapter.PrescriptionViewHolder>(
        FirebaseRecyclerOptions.Builder<PrescriptionData>()
            .setQuery(query,modelClass)
            .build()
    ){
    var myListener: MyItemClickListener? = null
    interface MyItemClickListener {
        //fun onItemClickedFromAdapter(position: Int)
        //fun onItemLongClickedFromAdapter(position: Int)
    }

    fun setMyItemClickListener(listener: MyItemClickListener) {
        this.myListener = listener
    }

    inner class PrescriptionViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val rVMovieTitle= view?.rVTitle
        val rVOverview= view?.rVOverview

        val rVposterid= view?.rVPosterid
        val rVRating= view?.rVRating
        val rVCheckBox= view?.rVCheckBox
        val overflow = view?.overflow
        init{
            overflow?.setOnClickListener {
                if(myListener != null){
                    if(adapterPosition != androidx.recyclerview.widget.RecyclerView.NO_POSITION){
//                        myListener!!.onOverflowMenuClickedFromAdapter(it, adapterPosition)
                    }
                }
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionListAdapter.PrescriptionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view:View
        view=when(viewType){
            1 -> {
                layoutInflater.inflate(R.layout.movie_list_item_right,parent,false)
            }
            2 -> {
                layoutInflater.inflate(R.layout.movie_list_item_left,parent,false)
            }
            else->{
                layoutInflater.inflate(R.layout.movie_list_item_right,parent,false)
            }
        }

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyFirebaseRecyclerAdapter.MovieViewHolder, position: Int, movie: MovieData) {
//        val movie=movieList[position]
        holder.rVMovieTitle!!.text =movie.title
        holder.rVOverview!!.text=movie.overview
//        holder.rVposterid!!.setImageResource(posterTable[movie.title]!!)

        val url = "https://image.tmdb.org/t/p/w185/" + movie.poster_path!!

        val picasso = Picasso.Builder(holder.itemView.context).listener { _, _, e -> e.printStackTrace() }.build()
        picasso.load(url).into(holder.rVposterid)
        Picasso.get().load(url).error(R.mipmap.ic_launcher).into(holder.rVposterid)
        holder.rVRating!!.text= movie.vote_average.toString()
        holder.rVCheckBox!!.isChecked= movie.checked!!
    }
    private val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val mRef = mDatabase.child("movies")
//    fun duplicateMovie(position: Int){
//
//
//
//        var movie=movieList[position].copy()
//
//        movieList.add(position+1,movie)
//        notifyItemInserted(position+1)
//        mRef.child("position").setValue(movie).addOnSuccessListener {
//            Log.d(TAG, "Insert a new Movie: ${movie.title}")
//        }
//
////        notifyDataSetChanged()
//
//    }

}
