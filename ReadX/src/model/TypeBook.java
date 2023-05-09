package model;

public enum TypeBook{

    SCIENCE_FICTION("Science Fiction"),
    FANTASY("Fantasy"),
    HISTORICAL_NOVEL("Historical Novel");

    private final String value;

    TypeBook(String value){
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}