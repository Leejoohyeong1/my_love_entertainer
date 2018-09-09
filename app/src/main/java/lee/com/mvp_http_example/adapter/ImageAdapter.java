package lee.com.mvp_http_example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;
import java.util.List;

import lee.com.mvp_http_example.Listener.OnItemClickListener;
import lee.com.mvp_http_example.R;
import lee.com.mvp_http_example.adapter.adapterConteact.ImageAdapterConteact;
import lee.com.mvp_http_example.adapter.holder.ImageViewItemHolder;
import lee.com.mvp_http_example.retrofitintro.ImageItem;
public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ImageAdapterConteact.view,ImageAdapterConteact.Model{

    Context context;
    ArrayList<ImageItem> items;
    OnItemClickListener clickLisetner;

    public ImageAdapter(Context context){
        this.context = context;
        items = new ArrayList<ImageItem>();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview, viewGroup, false);
        ImageViewItemHolder holder = new ImageViewItemHolder(v,context,clickLisetner);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder == null) return;
        ((ImageViewItemHolder) viewHolder).onBind(items.get(i),i);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public void notfyAdaoter() {
        this.notifyDataSetChanged();
    }

    @Override
    public void setOnClickLisetner(OnItemClickListener clickLisetner) {
        this.clickLisetner = clickLisetner;
    }

    @Override
    public void addItems(ArrayList<ImageItem> imageItem) {
        this.items.addAll(imageItem);


    }

    @Override
    public ImageItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public void clearItems() {
        if(items != null){
            items.clear();
            items = null;
        }

    }



}
