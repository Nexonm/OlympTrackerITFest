package neoxs.olymptracker.presentation.olympiansparse;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import neoxs.olymptracker.domain.entity.ItemParse;

public class DiffCallbackParse extends DiffUtil.ItemCallback<ItemParse> {
    @Override
    public boolean areItemsTheSame(@NonNull ItemParse oldItem, @NonNull ItemParse newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull ItemParse oldItem, @NonNull ItemParse newItem) {
        return false;
    }
}
