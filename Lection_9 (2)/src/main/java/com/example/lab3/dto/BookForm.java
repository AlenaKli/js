package com.example.lab3.dto;

/**
 * Binding object for the “Add Book” form.
 * <p>
 * All fields are Strings/boxed types so they can be left blank in the HTML form
 * and still bind successfully.
 */
public class BookForm {

    /* ------------ shared ------------- */
    private String key;          // optional map-key
    private String type;         // STORY | SCIFI | HORROR

    private String title;
    private String author;
    private Integer pages;
    private String publishDate;  // yyyy-MM-dd
    private String tags;         // comma-separated

    /* ------------ sci-fi specific ---- */
    private String subgenre;

    /* ------------ story specific ----- */
    private String ageGroup;

    /* ------------ horror specific ---- */
    private Integer scareRating;
    private Boolean containsSupernatural;

    /* -------------------------------------------------
     *  Constructors
     * ------------------------------------------------- */
    public BookForm() { }

    /* -------------------------------------------------
     *  Getters & setters
     * ------------------------------------------------- */
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPages() {
        return pages;
    }
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSubgenre() {
        return subgenre;
    }
    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

    public String getAgeGroup() {
        return ageGroup;
    }
    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Integer getScareRating() {
        return scareRating;
    }
    public void setScareRating(Integer scareRating) {
        this.scareRating = scareRating;
    }

    public Boolean getContainsSupernatural() {
        return containsSupernatural;
    }
    public void setContainsSupernatural(Boolean containsSupernatural) {
        this.containsSupernatural = containsSupernatural;
    }

    /* -------------------------------------------------
     *  Convenience
     * ------------------------------------------------- */
    @Override
    public String toString() {
        return "BookForm{" +
                "key='" + key + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
