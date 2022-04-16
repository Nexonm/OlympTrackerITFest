package neoxs.olymptracker.domain.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Olympian {
    public static final int UNDEFINED_ID;
    private int id;
    private String name;
    private String sportName;
    private String photoPath;
    private int yearOfBirth;
    private ArrayList<Integer> medals;
    private String description;

    static {
        UNDEFINED_ID = -1;
    }

    public Olympian(
            String name,
            String sportName,
            String photoPath,
            int yearOfBirth,
            String medals,
            String description
    ) {
        this.name = name;
        this.sportName = sportName;
        this.photoPath = photoPath;
        this.yearOfBirth = yearOfBirth;
        this.medals = splitStr(medals);
        this.description = description;
    }

    public Olympian(
            int id,
            String name,
            String sportName,
            String photoPath,
            int yearOfBirth,
            ArrayList<Integer> medals,
            String description
    ) {
        this.id = id;
        this.name = name;
        this.sportName = sportName;
        this.photoPath = photoPath;
        this.yearOfBirth = yearOfBirth;
        this.medals = (medals);
        this.description = description;
    }

    public Olympian(
            int id,
            String name,
            String sportName,
            String photoPath,
            int yearOfBirth,
            String medals,
            String description
    ) {
        this.id = id;
        this.name = name;
        this.sportName = sportName;
        this.photoPath = photoPath;
        this.yearOfBirth = yearOfBirth;
        this.medals = splitStr(medals);
        this.description = description;
    }

    private ArrayList<Integer> splitStr(String str) {
        ArrayList<Integer> list = new ArrayList<>();
        if (!"null".equals(str) && str.length() > 1) {
            String[] med = str.split(" ");
            for (int i = 0; i < str.length(); i++) {
                try {
                    list.add(Integer.parseInt(med[i]));
                } catch (NumberFormatException e) {
                    list.add(-2);
                } catch (ArrayIndexOutOfBoundsException e) {
                    list.add(-3);
                }
            }
        } else {
            list.add(-1);
            list.add(-1);
            list.add(-1);
        }
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSportName() {
        return sportName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public ArrayList<Integer> getMedals() {
        return medals;
    }

    public String getMedalsString(ArrayList<Integer> list) {
        return list.get(0) + " " + list.get(1) + " " + list.get(2);
    }

    public String getMedalsStringFormat() {
        return ("\uD83E\uDD47" + medals.get(0) + " \uD83E\uDD48" + medals.get(1) + " \uD83E\uDD49" + medals.get(2));
    }

    public String getDescription() {
        return description;
    }
}
