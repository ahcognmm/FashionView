//package com.example.ahcogn.fashionview.cardsview;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.model.stream.HttpUrlGlideUrlLoader;
//import com.example.ahcogn.fashionview.R;
//
//import java.util.List;
//
//
///**
// * Created by ahcogn on 19/09/2017.
// */
//
//public class FashionAdapter extends RecyclerView.Adapter<FashionAdapter.ViewHolder> {
//
//    private Context context;
//    private List<Fashion> fashionList;
//
//    public FashionAdapter(android.content.Context context, List<Fashion> fashionList) {
//        this.context = context;
//        this.fashionList = fashionList;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        Fashion fs = fashionList.get(position);
//        holder.tvcId.setText(String.valueOf(fs.getId()));
//        holder.tvcCost.setText(String.valueOf(fs.getCost()));
//        holder.tvcContent.setText(fs.getContent());
//
//        Glide.with(context).load(fs.getImage()).into(holder.imvCard);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        public TextView tvcId, tvcContent, tvcCost;
//        public ImageView imvCard;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            tvcId = itemView.findViewById(R.id.tvcId);
//            tvcContent = itemView.findViewById(R.id.tvcContent);
//            tvcCost = itemView.findViewById(R.id.tvcCost);
//            imvCard = itemView.findViewById(R.id.imvCard);
//        }
//    }
//}
