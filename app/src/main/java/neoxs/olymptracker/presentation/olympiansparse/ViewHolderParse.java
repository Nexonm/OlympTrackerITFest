package neoxs.olymptracker.presentation.olympiansparse;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import neoxs.olymptracker.R;

public class ViewHolderParse extends RecyclerView.ViewHolder{

    ImageView iv;
    TextView tv_name;
    TextView tv_sport_name;
    TextView tv_sport_category;
    TextView tv_year_of_birth;

    public ViewHolderParse(View item) {
        super(item);
        iv = item.findViewById(R.id.iv_parse_item);
        tv_name = item.findViewById(R.id.tv_parse_name);
        tv_sport_name = item.findViewById(R.id.tv_parse_sport_name);
        tv_sport_category = item.findViewById(R.id.tv_parse_sport_category);
        tv_year_of_birth = item.findViewById(R.id.tv_parse_year_of_birth);
    }
}
