package model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/22.
 */
@Entity
@Table(name = "tb_photos", schema = "heymanagement", catalog = "heymanagement")
public class TbPhotos {
    private int id;
    private String name;
    private String address;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbPhotos tbPhotos = (TbPhotos) o;

        if (id != tbPhotos.id) return false;
        if (name != null ? !name.equals(tbPhotos.name) : tbPhotos.name != null) return false;
        if (address != null ? !address.equals(tbPhotos.address) : tbPhotos.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
