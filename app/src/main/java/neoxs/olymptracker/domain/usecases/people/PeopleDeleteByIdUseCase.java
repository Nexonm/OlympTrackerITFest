package neoxs.olymptracker.domain.usecases.people;


import neoxs.olymptracker.domain.entity.Olympian;
import neoxs.olymptracker.domain.repository.PeopleDomainRepository;

public class PeopleDeleteByIdUseCase {
    private PeopleDomainRepository repository;

    public PeopleDeleteByIdUseCase(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public void peopleDeleteById(Olympian people) {
        repository.peopleDeleteById(people);
    }

}
