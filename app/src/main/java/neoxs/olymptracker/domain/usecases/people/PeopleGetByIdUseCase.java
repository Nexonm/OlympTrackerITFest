package neoxs.olymptracker.domain.usecases.people;

import androidx.lifecycle.MutableLiveData;

import neoxs.olymptracker.domain.entity.Olympian;
import neoxs.olymptracker.domain.repository.PeopleDomainRepository;


public class PeopleGetByIdUseCase {

    private final PeopleDomainRepository repository;

    public PeopleGetByIdUseCase(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<Olympian> peopleGetById(int _id) {
        return repository.peopleGetById(_id);
    }
}