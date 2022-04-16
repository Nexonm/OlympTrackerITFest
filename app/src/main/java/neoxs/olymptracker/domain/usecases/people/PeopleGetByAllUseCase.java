package neoxs.olymptracker.domain.usecases.people;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import neoxs.olymptracker.domain.entity.Olympian;
import neoxs.olymptracker.domain.repository.PeopleDomainRepository;


public class PeopleGetByAllUseCase {

    private final PeopleDomainRepository repository;

    public PeopleGetByAllUseCase(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Olympian>> peopleGetByAll() {
        return repository.peopleGetAll();
    }
}