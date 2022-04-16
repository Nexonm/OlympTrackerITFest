package neoxs.olymptracker.data.mapper;


import neoxs.olymptracker.data.db_room.entity.RoomPeople;
import neoxs.olymptracker.domain.entity.Olympian;

public class EntityMapper {

    public static RoomPeople toRoomPeople(Olympian olympian) {
        if (olympian.getId() == Olympian.UNDEFINED_ID)
            return new RoomPeople(
                    olympian.getName() + "",
                    olympian.getSportName() + "",
                    olympian.getYearOfBirth() + 0,
                    olympian.getPhotoPath() + 0,
                    olympian.getMedalsString(olympian.getMedals()) + "",
                    olympian.getDescription()
            );
        else
            return new RoomPeople(
                    olympian.getName() + "",
                    olympian.getSportName() + "",
                    olympian.getYearOfBirth() + 0,
                    olympian.getPhotoPath() + 0,
                    olympian.getMedalsString(olympian.getMedals()),
                    olympian.getDescription(),
                    olympian.getId() + 0
            );
    }

    public static Olympian toPeople(RoomPeople roomPeople) {
        return new Olympian(
                roomPeople.getPeopleId() + 0,
                roomPeople.getFirstName() + "",
                roomPeople.getSportName() + "",
                roomPeople.getPathToPhoto() + "",
                roomPeople.getYearOfBirth() + 0,
                roomPeople.getArrayOfMedals(),
                roomPeople.getDescription()
        );
    }

}