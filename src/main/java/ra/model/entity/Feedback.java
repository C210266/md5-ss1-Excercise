package ra.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rate;
    private String author;
    private String content;
    private Date createDate ;
    private int likes;

    public Feedback(Long id, int rate, String author, String content, Date createDate, int likes) {
        this.id = id;
        this.rate = rate;
        this.author = author;
        this.content = content;
        this.createDate = createDate;
        this.likes = likes;
    }

    public Feedback() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void copy(Feedback copyFeedback) {
        this.id = copyFeedback.getId();
        this.rate = copyFeedback.getRate();
        this.author = copyFeedback.getAuthor();
        this.content = copyFeedback.getContent();
        this.createDate = copyFeedback.getCreateDate();
        this.likes = copyFeedback.getLikes();
    }
}
