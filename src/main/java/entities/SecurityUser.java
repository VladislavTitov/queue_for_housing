package entities;

public class SecurityUser {
    private final String user_name;
    private final String dateRegistration;

    public static class Builder{
        private String user_name;
        private String dateRegistration;

        public Builder setUserName(String user_name){
            this.user_name = user_name;
            return this;
        }

        public Builder setDateRegistration(String dateRegistration) {
            this.dateRegistration = dateRegistration;
            return this;
        }

        public SecurityUser build(){
            return new SecurityUser(this);
        }

    }

    private SecurityUser(Builder builder){
        this.user_name = builder.user_name;
        this.dateRegistration = builder.dateRegistration;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getDateRegistration() {
        return dateRegistration;
    }
}
