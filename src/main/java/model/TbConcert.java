package model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/10.
 */
@Entity
@Table(name = "tb_concert", schema = "heymanagement", catalog = "heymanagement")
public class TbConcert {
    private int id;
    private String name;
    private String date;
    private String address;
    private Integer price;
    private String description;
    private Byte status;
    private String cover;
    private String from;
    private String fromAddress;
    private String detailAddress;

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
    @Column(name = "date", nullable = true, length = 0)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
    @Column(name = "price", nullable = true, precision = 0)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "cover", nullable = true, length = 255)
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Basic
    @Column(name = "from", nullable = true, length = 255)
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Basic
    @Column(name = "from_address", nullable = true, length = 255)
    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    @Basic
    @Column(name = "detail_address", nullable = true, length = 255)
    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbConcert tbConcert = (TbConcert) o;

        if (id != tbConcert.id) return false;
        if (name != null ? !name.equals(tbConcert.name) : tbConcert.name != null) return false;
        if (date != null ? !date.equals(tbConcert.date) : tbConcert.date != null) return false;
        if (address != null ? !address.equals(tbConcert.address) : tbConcert.address != null) return false;
        if (price != null ? !price.equals(tbConcert.price) : tbConcert.price != null) return false;
        if (description != null ? !description.equals(tbConcert.description) : tbConcert.description != null)
            return false;
        if (status != null ? !status.equals(tbConcert.status) : tbConcert.status != null) return false;
        if (cover != null ? !cover.equals(tbConcert.cover) : tbConcert.cover != null) return false;
        if (from != null ? !from.equals(tbConcert.from) : tbConcert.from != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        return result;
    }
}
