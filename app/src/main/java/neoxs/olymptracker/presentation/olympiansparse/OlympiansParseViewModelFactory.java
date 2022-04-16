package neoxs.olymptracker.presentation.olympiansparse;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import neoxs.olymptracker.domain.usecases.items.ItemsAddNewUseCase;
import neoxs.olymptracker.domain.usecases.items.ItemsGetAllUseCase;

public class OlympiansParseViewModelFactory implements ViewModelProvider.Factory {

    private ItemsGetAllUseCase _getAll;
    private ItemsAddNewUseCase _addNew;

    public OlympiansParseViewModelFactory(ItemsGetAllUseCase _getAll, ItemsAddNewUseCase _addNew) {
        this._getAll = _getAll;
        this._addNew = _addNew;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
        return (T) new OlympiansParseViewModel(_getAll, _addNew);
    }
}
