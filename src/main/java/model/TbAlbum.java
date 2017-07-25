package model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/2.
 */
@Entity
@Table(name = "tb_album", schema = "heymanagement", catalog = "heymanagement")
public class TbAlbum {
    private int id;
    private String name;
    private String language;
    private String company;
    private String createTime;
    private String description;
    private String address;
    private Integer quantity;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "language", nullable = true, length = 255)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "company", nullable = true, length = 255)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "create_time", nullable = true, length = 255)
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "quantity", nullable = true)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbAlbum tbAlbum = (TbAlbum) o;

        if (id != tbAlbum.id) return false;
        if (name != null ? !name.equals(tbAlbum.name) : tbAlbum.name != null) return false;
        if (language != null ? !language.equals(tbAlbum.language) : tbAlbum.language != null) return false;
        if (company != null ? !company.equals(tbAlbum.company) : tbAlbum.company != null) return false;
        if (createTime != null ? !createTime.equals(tbAlbum.createTime) : tbAlbum.createTime != null) return false;
        if (description != null ? !description.equals(tbAlbum.description) : tbAlbum.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
