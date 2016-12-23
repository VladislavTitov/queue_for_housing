package entities;

import java.sql.Date;

public class SecurityUser {
    private final String user_name;
    private final Date dateRegistration;
    private final Boolean isGranted;

    public static class Builder{
        private String user_name;
        private Date dateRegistration;
        private Boolean isGranted;

        public Builder setUserName(String user_name){
            this.user_name = user_name;
            return this;
        }

        public Builder setDateRegistration(Date dateRegistration) {
            this.dateRegistration = dateRegistration;
            return this;
        }

        public Builder setGranted(Boolean granted) {
            isGranted = granted;
            return this;
        }

        public SecurityUser build(){
            return new SecurityUser(this);
        }

    }

    private SecurityUser(Builder builder){
        this.user_name = builder.user_name;
        this.dateRegistration = builder.dateRegistration;
        this.isGranted = builder.isGranted;
    }

    public String getUser_name() {
        return user_name;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public Boolean isGranted() {
        return isGranted;
    }
}
