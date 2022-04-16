package neoxs.olymptracker.domain.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import neoxs.olymptracker.domain.entity.ItemParse;

public interface ItemParseRepository {

    void itemParseAddNew();

    MutableLiveData<ArrayList<ItemParse>> getAllParseItems();
}
