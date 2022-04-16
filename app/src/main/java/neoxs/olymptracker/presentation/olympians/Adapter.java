package neoxs.olymptracker.presentation.olympians;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import neoxs.olymptracker.domain.entity.Olympian;
import neoxs.olymptracker.R;
import neoxs.olymptracker.app.AppStart;

public class Adapter extends ListAdapter<Olympian, ViewHolder> {

    public static final int MAX_POOL_SIZE = 20;
    public static final int VIEW_TYPE_CARD_VISITOR = 100;

    private static int count = 0;


    public OnCardClickListener cardClickListener = null;

    public Adapter(DiffCallback diffCallback) {
        super(diffCallback);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (AppStart.isLog)
            Log.w("onCreateViewHolder", "onCreateViewHolder, count = " + (++count));
        int layout;
        switch (viewType) {
            case VIEW_TYPE_CARD_VISITOR:
                layout = R.layout.fragment_olympians_list_item;
                break;

            default:
                if (AppStart.isLog)
                    Log.wtf("onCreateViewHolder", "onCreateViewHolder, THERE IS NO SUCH TYPE!!!");

                //TODO find how to do it smartly without errors
                layout = R.layout.fragment_olympians_list_item;
        }

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public int getItemViewType(int position){
        return VIEW_TYPE_CARD_VISITOR;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Olympian olympian = getItem(position);
        viewHolder.tv_olympian_name.setText(olympian.getName());
        viewHolder.tv_sport_name.setText(olympian.getSportName());
        viewHolder.tv_year_of_birth.setText(String.valueOf(olympian.getYearOfBirth()));
        viewHolder.tv_medals.setText(olympian.getMedalsStringFormat());
        viewHolder.tv_description.setText(olympian.getDescription());
    }

    interface OnCardClickListener {
        void onCardClick(Olympian olympian);
    }
}
