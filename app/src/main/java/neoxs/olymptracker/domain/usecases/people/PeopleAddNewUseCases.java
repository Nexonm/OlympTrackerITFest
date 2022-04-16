package neoxs.olymptracker.domain.usecases.people;


import neoxs.olymptracker.domain.entity.Olympian;
import neoxs.olymptracker.domain.repository.PeopleDomainRepository;

public class PeopleAddNewUseCases {
    private final PeopleDomainRepository repository;

    public PeopleAddNewUseCases(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public void peopleAddNew(Olympian people) {
        repository.peopleAddNew(people);
    }
}
