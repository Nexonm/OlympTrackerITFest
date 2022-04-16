package neoxs.olymptracker.presentation.olympiansparse;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import neoxs.olymptracker.domain.entity.ItemParse;
import neoxs.olymptracker.domain.usecases.items.ItemsAddNewUseCase;
import neoxs.olymptracker.domain.usecases.items.ItemsGetAllUseCase;

public class OlympiansParseViewModel extends ViewModel {

    private MutableLiveData<ArrayList<ItemParse>> itemsLiveData;

    private ItemsGetAllUseCase getAll;
    private ItemsAddNewUseCase addNew;

    public OlympiansParseViewModel(ItemsGetAllUseCase getAll, ItemsAddNewUseCase addNew) {
        this.getAll = getAll;
        this.addNew = addNew;
    }

    protected MutableLiveData<ArrayList<ItemParse>> getItemsList(){
        itemsLiveData = getAll.itemsGetByAll();
        return itemsLiveData;
    }

    protected void addNewItems(){
        addNew.itemsAddNew();
    }
}
