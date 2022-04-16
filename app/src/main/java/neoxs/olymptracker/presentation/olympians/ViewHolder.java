package neoxs.olymptracker.presentation.olympians;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import neoxs.olymptracker.R;

public class ViewHolder extends RecyclerView.ViewHolder{


    TextView tv_olympian_name;
    TextView tv_sport_name;
    TextView tv_year_of_birth;
    TextView tv_medals;
    TextView tv_description;

    public ViewHolder(View item){
        super(item);
        tv_olympian_name = item.findViewById(R.id.tv_olympian_name);
        tv_sport_name = item.findViewById(R.id.tv_sport_name);
        tv_year_of_birth = item.findViewById(R.id.tv_year_of_birth);
        tv_medals = item.findViewById(R.id.tv_medals);
        tv_description = item.findViewById(R.id.tv_description);
    }
}
