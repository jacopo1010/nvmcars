package org.nvm.cars.dto;



/**
 * DTO generato automaticamente per Credenziali.
 */
public class CredenzialiDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Long possiedeId;

    public CredenzialiDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPossiedeId() {
        return this.possiedeId;
    }

    public void setPossiedeId(Long possiedeId) {
        this.possiedeId = possiedeId;
    }
}
