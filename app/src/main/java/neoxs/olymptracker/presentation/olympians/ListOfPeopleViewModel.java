package neoxs.olymptracker.presentation.olympians;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import neoxs.olymptracker.domain.entity.Olympian;
import neoxs.olymptracker.domain.usecases.people.PeopleAddNewUseCases;
import neoxs.olymptracker.domain.usecases.people.PeopleDeleteByIdUseCase;
import neoxs.olymptracker.domain.usecases.people.PeopleGetByAllUseCase;


public class ListOfPeopleViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Olympian>> peoplesListLiveData;

    private PeopleDeleteByIdUseCase delete;
    private PeopleAddNewUseCases addNew;
    private PeopleGetByAllUseCase getAll;


    public ListOfPeopleViewModel(
            PeopleDeleteByIdUseCase delete,
            PeopleAddNewUseCases addNew,
            PeopleGetByAllUseCase getAll
    ) {
        this.delete = delete;
        this.addNew = addNew;
        this.getAll = getAll;
    }

    protected MutableLiveData<ArrayList<Olympian>> getPeopleList() {
        peoplesListLiveData = getAll.peopleGetByAll();
        return peoplesListLiveData;
    }


    protected void addNewPeople(Olympian people) {
        addNew.peopleAddNew(people);
    }

    protected void deletePeople(Olympian people) {
        delete.peopleDeleteById(people);
    }

}