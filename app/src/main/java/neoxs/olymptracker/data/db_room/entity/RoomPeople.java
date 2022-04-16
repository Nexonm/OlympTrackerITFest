package neoxs.olymptracker.data.db_room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = RoomPeople.NAME_TABLE,
        indices = {@Index(RoomPeople.COL_OLYMPIANS_ID)}
)
public class RoomPeople {

    public static final String NAME_TABLE = "Olympians";

    public static final String COL_OLYMPIANS_ID = "Olympians_id";
    public static final String COL_NAME = "First_name";
    public static final String COL_YEAR_OF_BIRTH = "Year";
    public static final String COL_PATH_TO_PHOTO = "Path_to_photo";
    public static final String COL_ARRAY_OF_MEDALS = "Array_of_medals";
    public static final String COL_SPORT_NAME = "Sport_name";
    public static final String COL_DESCRIPTION = "Description";

    public static final int DEFAULT_ROW_ID = 1;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_OLYMPIANS_ID)
    public int peopleId;

    @ColumnInfo(name = COL_NAME)
    public String firstName;

    @ColumnInfo(name = COL_SPORT_NAME)
    public String sportName;

    @ColumnInfo(name = COL_YEAR_OF_BIRTH)
    public int yearOfBirth;

    @ColumnInfo(name = COL_PATH_TO_PHOTO)
    public String pathToPhoto;

    @ColumnInfo(name = COL_ARRAY_OF_MEDALS)
    public String arrayOfMedals;

    @ColumnInfo(name = COL_DESCRIPTION)
    public String description;

    public RoomPeople(
            String firstName,
            String sportName,
            int yearOfBirth,
            String pathToPhoto,
            String arrayOfMedals,
            String description
    ) {
        this.firstName = firstName;
        this.sportName = sportName;
        this.yearOfBirth = yearOfBirth;
        this.pathToPhoto = pathToPhoto;
        this.arrayOfMedals = arrayOfMedals;
        this.description = description;
        this.peopleId = DEFAULT_ROW_ID;
    }

    @Ignore
    public RoomPeople(
            String firstName,
            String sportName,
            int yearOfBirth,
            String pathToPhoto,
            String arrayOfMedals,
            String description,
            int peopleId
    ) {
        this.firstName = firstName;
        this.sportName = sportName;
        this.yearOfBirth = yearOfBirth;
        this.pathToPhoto = pathToPhoto;
        this.arrayOfMedals = arrayOfMedals;
        this.description = description;
        this.peopleId = peopleId;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSportName() {
        return sportName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

    public String getArrayOfMedals() {
        return arrayOfMedals;
    }

    public String getDescription() {
        return description;
    }
}
