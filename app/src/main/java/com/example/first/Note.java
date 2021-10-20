package com.example.first;

import java.util.Objects;

public class Note {

    private String FirstLine;
    private String Header;
    private String SecondLine;
    private String Date;
    private int ResourceImage;


    public Note(String Header, String FirstLine, String SecondLine, String Date, int ResourceImage) {
        this.Header = Header;
        this.FirstLine = FirstLine;
        this.SecondLine = SecondLine;
        this.Date = Date;
        this.ResourceImage = ResourceImage;

    }

    public String getFirstLine() {
        return FirstLine;
    }

    public void setFirstLine(String firstLine) {
        FirstLine = firstLine;
    }

    public String getHeader() {
        return Header;
    }

    public void setHeader(String header) {
        Header = header;
    }

    public String getSecondLine() {
        return SecondLine;
    }

    public void setSecondLine(String secondLine) {
        SecondLine = secondLine;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getResourceImage() {
        return ResourceImage;
    }

    public void setResourceImage(int resourceImage) {
        ResourceImage = resourceImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return ResourceImage == note.ResourceImage && Objects.equals(FirstLine, note.FirstLine) && Objects.equals(Header, note.Header) && Objects.equals(SecondLine, note.SecondLine) && Objects.equals(Date, note.Date);
    }
}
