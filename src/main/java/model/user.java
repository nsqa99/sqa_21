/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author admin
 */
public class user {
    private long idUser;
    private long areaId;
    private String username;
    private String password;
    private String fullname;
    private String sdt;
    private String address;
    private String email;
    private String role;
    private long identityNum;
    public user() {
    }
    
    


    public user(long idUser, long areaId, String username, String password, String fullname, String sdt, String address,
			 String role, long identityNum) {
		this.idUser = idUser;
		this.areaId = areaId;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.sdt = sdt;
		this.address = address;
		this.role = role;
		this.identityNum = identityNum;
	}
    
    

    


	public user(long idUser, long areaId, String fullname, String sdt, String address, String role, long identityNum) {
		super();
		this.idUser = idUser;
		this.areaId = areaId;
		this.fullname = fullname;
		this.sdt = sdt;
		this.address = address;
		this.role = role;
		this.identityNum = identityNum;
	}




	public user(long identityNum, String fullname, String address, String sdt, long areaId, String username,
			String password) {
		super();
		this.identityNum = identityNum;
		this.fullname = fullname;
		this.address = address;
		this.sdt = sdt;
		this.areaId = areaId;
		this.username = username;
		this.password = password;
	}




	public long getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(long identityNum) {
        this.identityNum = identityNum;
    }
    
    

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public boolean equals(Object obj) {
    	user other = (user) obj;
    	
    	return (this.idUser == other.idUser && this.areaId == other.areaId
    			&& this.fullname.equals(other.fullname) && this.address.equals(other.address)
    			&& this.sdt.equals(other.sdt) && this.identityNum == other.identityNum
    			&& this.role.equals(other.role));
    }
    
}
