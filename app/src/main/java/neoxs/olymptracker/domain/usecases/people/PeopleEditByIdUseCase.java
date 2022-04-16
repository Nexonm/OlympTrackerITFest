package neoxs.olymptracker.domain.usecases.people;


import neoxs.olymptracker.domain.entity.Olympian;
import neoxs.olymptracker.domain.repository.PeopleDomainRepository;

public class PeopleEditByIdUseCase {
    private PeopleDomainRepository repository;

    public PeopleEditByIdUseCase(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public void peopleEditById(Olympian people) {
        repository.peopleEditById(people);
    }

}
