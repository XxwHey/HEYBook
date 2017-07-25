package model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/2/27.
 * TbUser
 */
@Entity
@Table(name = "tb_user", schema = "heymanagement", catalog = "heymanagement")
public class TbUser {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String userToken;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "user_token", nullable = true, length = 255)
    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbUser tbUser = (TbUser) o;

        if (id != null ? !id.equals(tbUser.id) : tbUser.id != null) return false;
        if (username != null ? !username.equals(tbUser.username) : tbUser.username != null) return false;
        if (password != null ? !password.equals(tbUser.password) : tbUser.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
