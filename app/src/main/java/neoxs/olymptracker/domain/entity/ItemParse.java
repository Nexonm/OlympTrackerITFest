package neoxs.olymptracker.domain.entity;

public class ItemParse {
    private String imgUrl;
    private String name;
    private String sportName;
    private String sportCategory;
    private String yearOfBirth;

    public ItemParse() {
    }

    public ItemParse(
            String imgUrl,
            String name,
            String sportName,
            String sportCategory,
            String yearOfBirth
    ) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.sportName = sportName;
        this.sportCategory = sportCategory;
        this.yearOfBirth = yearOfBirth;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSportCategory() {
        return sportCategory;
    }

    public void setSportCategory(String sportCategory) {
        this.sportCategory = sportCategory;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
