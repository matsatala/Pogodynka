package Pogodynka.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "http_service")

public class HTTPService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @Column
    @NotNull
    private String url;

    public HTTPService(){}

    public HTTPService(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
