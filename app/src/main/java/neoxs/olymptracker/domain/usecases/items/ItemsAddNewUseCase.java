package neoxs.olymptracker.domain.usecases.items;

import neoxs.olymptracker.domain.entity.ItemParse;
import neoxs.olymptracker.domain.repository.ItemParseRepository;

public class ItemsAddNewUseCase {

    private final ItemParseRepository repository;

    public ItemsAddNewUseCase(ItemParseRepository repository) {
        this.repository = repository;
    }

    public void itemsAddNew(){
        repository.itemParseAddNew();
    }
}
