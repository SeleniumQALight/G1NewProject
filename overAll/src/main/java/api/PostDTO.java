package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;


//@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDTO   {
    public PostDTO(String title, String body, String select1, AutorDTO author,
                   Boolean isVisitorOwner) {
        this.title = title;
        this.body = body;
        this.select1 = select1;
        this.author = author;
        this.isVisitorOwner = isVisitorOwner;
    }

    public PostDTO() {

    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", select1='" + select1 + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", author=" + author +
                ", isVisitorOwner=" + isVisitorOwner +
                '}';
    }

    @SerializedName("_id")
    String _id;
    @SerializedName("title")
    String title;
    @SerializedName("body")
    String body;

    @SerializedName("select1")
    String select1;

    @SerializedName("createdDate")
    String createdDate;

    @SerializedName("author")
    AutorDTO author;

    @SerializedName("isVisitorOwner")
    Boolean isVisitorOwner;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public AutorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AutorDTO author) {
        this.author = author;
    }

    public Boolean getIsVisitorOwner() {
        return isVisitorOwner;
    }

    public void setIsVisitorOwner(Boolean visitorOwner) {
        isVisitorOwner = visitorOwner;
    }


    public String getSelect1() {
        return select1;
    }

    public void setSelect1(String select1) {
        this.select1 = select1;
    }

    public Boolean getVisitorOwner() {
        return isVisitorOwner;
    }

    public void setVisitorOwner(Boolean visitorOwner) {
        isVisitorOwner = visitorOwner;
    }


}
