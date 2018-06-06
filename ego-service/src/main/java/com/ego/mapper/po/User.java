package com.ego.mapper.po;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer id;

    private String username;

    private String loginname;

    private String loginpwd;

    private String coverimg;

    private String name;

    private Integer sex;

    private String phone;

    private Integer area;

    private Integer city;

    private Integer province;

    private String mail;

    private String zfpwd;

    private Date createdate;

    private Date modifidate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getLoginpwd() {
        return loginpwd;
    }

    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd == null ? null : loginpwd.trim();
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg == null ? null : coverimg.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getZfpwd() {
        return zfpwd;
    }

    public void setZfpwd(String zfpwd) {
        this.zfpwd = zfpwd == null ? null : zfpwd.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getModifidate() {
        return modifidate;
    }

    public void setModifidate(Date modifidate) {
        this.modifidate = modifidate;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getLoginname() == null ? other.getLoginname() == null : this.getLoginname().equals(other.getLoginname()))
            && (this.getLoginpwd() == null ? other.getLoginpwd() == null : this.getLoginpwd().equals(other.getLoginpwd()))
            && (this.getCoverimg() == null ? other.getCoverimg() == null : this.getCoverimg().equals(other.getCoverimg()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getMail() == null ? other.getMail() == null : this.getMail().equals(other.getMail()))
            && (this.getZfpwd() == null ? other.getZfpwd() == null : this.getZfpwd().equals(other.getZfpwd()))
            && (this.getCreatedate() == null ? other.getCreatedate() == null : this.getCreatedate().equals(other.getCreatedate()))
            && (this.getModifidate() == null ? other.getModifidate() == null : this.getModifidate().equals(other.getModifidate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getLoginname() == null) ? 0 : getLoginname().hashCode());
        result = prime * result + ((getLoginpwd() == null) ? 0 : getLoginpwd().hashCode());
        result = prime * result + ((getCoverimg() == null) ? 0 : getCoverimg().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getMail() == null) ? 0 : getMail().hashCode());
        result = prime * result + ((getZfpwd() == null) ? 0 : getZfpwd().hashCode());
        result = prime * result + ((getCreatedate() == null) ? 0 : getCreatedate().hashCode());
        result = prime * result + ((getModifidate() == null) ? 0 : getModifidate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", loginname=").append(loginname);
        sb.append(", loginpwd=").append(loginpwd);
        sb.append(", coverimg=").append(coverimg);
        sb.append(", name=").append(name);
        sb.append(", sex=").append(sex);
        sb.append(", phone=").append(phone);
        sb.append(", area=").append(area);
        sb.append(", city=").append(city);
        sb.append(", province=").append(province);
        sb.append(", mail=").append(mail);
        sb.append(", zfpwd=").append(zfpwd);
        sb.append(", createdate=").append(createdate);
        sb.append(", modifidate=").append(modifidate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public User(Integer id, String username, String loginname, String loginpwd, String coverimg, String name,
			Integer sex, String phone, Integer area, Integer city, Integer province, String mail, String zfpwd,
			Date createdate, Date modifidate) {
		super();
		this.id = id;
		this.username = username;
		this.loginname = loginname;
		this.loginpwd = loginpwd;
		this.coverimg = coverimg;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
		this.area = area;
		this.city = city;
		this.province = province;
		this.mail = mail;
		this.zfpwd = zfpwd;
		this.createdate = createdate;
		this.modifidate = modifidate;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}