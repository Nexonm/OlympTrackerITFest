package neoxs.olymptracker.presentation.olympians;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import neoxs.olymptracker.domain.entity.Olympian;

public class DiffCallback extends DiffUtil.ItemCallback<Olympian> {

    @Override
    public boolean areItemsTheSame(@NonNull Olympian oldItem, @NonNull Olympian newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Olympian oldItem, @NonNull Olympian newItem) {
        return false;
    }
}
