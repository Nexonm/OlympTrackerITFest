package neoxs.olymptracker.domain.usecases.items;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import neoxs.olymptracker.domain.repository.ItemParseRepository;
import neoxs.olymptracker.domain.entity.ItemParse;

public class ItemsGetAllUseCase {

    private final ItemParseRepository repository;

    public ItemsGetAllUseCase(ItemParseRepository repository){
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<ItemParse>> itemsGetByAll(){
        return repository.getAllParseItems();
    }
}
