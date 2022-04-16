package neoxs.olymptracker.presentation.olympiansparse;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import neoxs.olymptracker.R;
import neoxs.olymptracker.app.AppStart;
import neoxs.olymptracker.domain.entity.ItemParse;

import com.squareup.picasso.Picasso;

public class ParseAdapter extends ListAdapter<ItemParse, ViewHolderParse> {

    public ParseAdapter(DiffCallbackParse diffCallbackParse){
        super(diffCallbackParse);
    }

    @NonNull
    @Override
    public ViewHolderParse onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_olympians_list_item_parse, parent, false);
        return new ViewHolderParse(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderParse holder, int position) {
        ItemParse itemParse = getItem(position);
        holder.tv_name.setText(itemParse.getName());
        holder.tv_sport_name.setText(itemParse.getSportName());
        holder.tv_sport_category.setText(itemParse.getSportCategory());
        holder.tv_year_of_birth.setText(itemParse.getYearOfBirth());
        Context context = holder.itemView.getContext();
        Log.v("size", "Size of screen, Height = " +AppStart.getInstance().getDisplayHeight()
                +" Width = "+AppStart.getInstance().getDisplayWidth());
        Picasso.with(context)
                .load(itemParse.getImgUrl())
                .resize(
                        AppStart.getInstance().getDisplayHeight()/2,
                      AppStart.getInstance().getDisplayHeight()/2
                ).into(holder.iv);
    }

    private void setImageSize(ViewHolderParse holder){
        holder.iv.setMinimumWidth(AppStart.getInstance().getDisplayWidth()-100);
        holder.iv.setMinimumHeight(AppStart.getInstance().getDisplayHeight()-100);
    }

}
