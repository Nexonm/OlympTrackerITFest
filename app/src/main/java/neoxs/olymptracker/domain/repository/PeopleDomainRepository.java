package neoxs.olymptracker.domain.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import neoxs.olymptracker.domain.entity.Olympian;


public interface PeopleDomainRepository {

    void peopleAddNew(Olympian people);

    void peopleEditById(Olympian people);

    void peopleDeleteById(Olympian people);

    MutableLiveData<ArrayList<Olympian>> peopleGetAll();

    MutableLiveData<Olympian> peopleGetById(int _id);

}
