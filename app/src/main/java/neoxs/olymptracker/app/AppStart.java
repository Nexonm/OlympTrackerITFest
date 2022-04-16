package neoxs.olymptracker.app;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import com.yandex.mapkit.MapKitFactory;

import neoxs.olymptracker.api.APIConfig;
import neoxs.olymptracker.data.db_room.database.AppDatabase;
import neoxs.olymptracker.data.repositoryImpl.ItemParseRepositoryImpl;
import neoxs.olymptracker.data.repositoryImpl.PeopleRoomRepositoryImpl;
import neoxs.olymptracker.domain.usecases.items.ItemsAddNewUseCase;
import neoxs.olymptracker.domain.usecases.items.ItemsGetAllUseCase;
import neoxs.olymptracker.domain.usecases.people.PeopleAddNewUseCases;
import neoxs.olymptracker.domain.usecases.people.PeopleDeleteByIdUseCase;
import neoxs.olymptracker.domain.usecases.people.PeopleGetByAllUseCase;
import neoxs.olymptracker.domain.usecases.people.PeopleGetByIdUseCase;
import neoxs.olymptracker.presentation.mainmap.MapFragment;

public class AppStart extends Application {
    public static final Boolean isLog = false;

    private PeopleGetByIdUseCase getById;
    private PeopleAddNewUseCases addNew;
    private PeopleDeleteByIdUseCase delete;
    private PeopleGetByAllUseCase getAll;

    private ItemsGetAllUseCase itemsGetAll;
    private ItemsAddNewUseCase itemsAddNew;

    private int displayHeight;
    private int displayWidth;

    public int getDisplayHeight() {
        return displayHeight;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public ItemsGetAllUseCase getItemsGetAll() {
        return itemsGetAll;
    }

    public ItemsAddNewUseCase getItemsAddNew() {
        return itemsAddNew;
    }

    public PeopleGetByIdUseCase getGetById() {
        return getById;
    }

    public PeopleAddNewUseCases getAddNew() {
        return addNew;
    }

    public PeopleDeleteByIdUseCase getDelete() {
        return delete;
    }

    public PeopleGetByAllUseCase getGetAll() {
        return getAll;
    }

    private static AppStart instance;

    private boolean initedMap = false;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppDatabase database = AppDatabase.getInstance(this);

        PeopleRoomRepositoryImpl peopleRoomRepositoryImpl =
                new PeopleRoomRepositoryImpl(database.roomPeopleDao());
        getById = new PeopleGetByIdUseCase(peopleRoomRepositoryImpl);
        addNew = new PeopleAddNewUseCases(peopleRoomRepositoryImpl);
        delete = new PeopleDeleteByIdUseCase(peopleRoomRepositoryImpl);
        getAll = new PeopleGetByAllUseCase(peopleRoomRepositoryImpl);

        ItemParseRepositoryImpl itemParseRepository =
                new ItemParseRepositoryImpl();
        itemsGetAll = new ItemsGetAllUseCase(itemParseRepository);
        itemsAddNew = new ItemsAddNewUseCase(itemParseRepository);

    }

    public static AppStart getInstance() {
        return instance;
    }

    public void initMap() {
        if (!initedMap) {
            MapKitFactory.setApiKey(APIConfig.MAPKIT_KEY);
            MapKitFactory.initialize(MapFragment.getContextMF());
            initedMap = true;
        }

    }
}
