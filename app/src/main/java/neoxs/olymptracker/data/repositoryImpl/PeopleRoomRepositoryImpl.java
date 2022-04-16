package neoxs.olymptracker.data.repositoryImpl;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import neoxs.olymptracker.app.AppStart;
import neoxs.olymptracker.data.db_room.dao.RoomPeopleDao;
import neoxs.olymptracker.data.db_room.entity.RoomPeople;
import neoxs.olymptracker.data.mapper.EntityMapper;
import neoxs.olymptracker.domain.entity.Olympian;
import neoxs.olymptracker.domain.repository.PeopleDomainRepository;

public class PeopleRoomRepositoryImpl implements PeopleDomainRepository {
    private final RoomPeopleDao roomPeopleDao;
    private final MutableLiveData<ArrayList<Olympian>> peoplesListLiveData;
    private final MutableLiveData<Olympian> peopleLiveData;

    private static int autoIncrementId = 0;

    public PeopleRoomRepositoryImpl(
            RoomPeopleDao roomPeopleDao
    ) {
        this.roomPeopleDao = roomPeopleDao;
        initAutoIncrementId();
        peoplesListLiveData = new MutableLiveData<>();
        peopleLiveData = new MutableLiveData<>();
    }


    private void initAutoIncrementId() {
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                autoIncrementId = roomPeopleDao.roomPeopleGetMaxId();
                if (AppStart.isLog) {
                    Log.w("update", "in AsyncTask: autoIncrementId = " + autoIncrementId);
                }
            }
        });
        updatePeopleListAsyncTask();
    }

    @Override
    public void peopleAddNew(Olympian people) {
        if (people.getId() == Olympian.UNDEFINED_ID)
            people.setId(++autoIncrementId);
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                RoomPeople rp = EntityMapper.toRoomPeople(people);
                Log.w("PeopleRoomRepositoryImpl", (new Gson()).toJson(rp));
                roomPeopleDao.roomPeopleAddNew(rp);
            }
        });
        updatePeopleListAsyncTask();
    }

    @Override
    public void peopleEditById(Olympian people) {
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                roomPeopleDao.roomPeopleEditById(
                        EntityMapper.toRoomPeople(people)
                );
            }
        });
        updatePeopleListAsyncTask();
    }

    @Override
    public void peopleDeleteById(Olympian people) {
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                roomPeopleDao.roomPeopleDeleteById(
                        EntityMapper.toRoomPeople(people)
                );
            }
        });
        updatePeopleListAsyncTask();
    }

    private void updatePeopleListAsyncTask() {
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                List<RoomPeople> roomPeopleData = roomPeopleDao.roomPeopleGetAll();
                ArrayList<Olympian> data = new ArrayList<>();
                for (RoomPeople roomPeople : roomPeopleData)
                    data.add(EntityMapper.toPeople(roomPeople));
                peoplesListLiveData.postValue(new ArrayList<>(data));
            }
        });
    }


    private void findPeopleById(int _id) {
        AsyncTask.execute(() -> {
            synchronized (roomPeopleDao) {
                Olympian olympian = EntityMapper.toPeople(
                        roomPeopleDao.roomPeopleGetById(_id)
                );
                peopleLiveData.postValue(olympian);
            }
        });

    }

    @Override
    public MutableLiveData<ArrayList<Olympian>> peopleGetAll() {
        return peoplesListLiveData;
    }

    @Override
    public MutableLiveData<Olympian> peopleGetById(int _id) {
        findPeopleById(_id);
        return peopleLiveData;
    }
}
