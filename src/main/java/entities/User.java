package entities;

import java.sql.Date;
import java.time.LocalDate;

public class User {
    private final String user_name;
    private final String password;
    private final Date dateRegistration;

    public static class Builder{
        private String user_name;
        private String password;
        private Date dateRegistration;

        public Builder setUserName(String user_name){
            this.user_name = user_name;
            return this;
        }

        public Builder setPassword(String password){
            this.password = password;
            return this;
        }

        public Builder setDateRegistration(Date dateRegistration) {
            this.dateRegistration = dateRegistration;
            return this;
        }

        public User build(){
            return new User(this);
        }

    }

    private User(Builder builder){
        this.user_name = builder.user_name;
        this.password = builder.password;
        this.dateRegistration = builder.dateRegistration;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }
}
