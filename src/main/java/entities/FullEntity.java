package entities;

import java.sql.Date;

public class FullEntity {

    private final String user_name;
    private final Date dateRegistration;
    private final int roomsCount;
    private final boolean kindergarden;
    private final boolean school;
    private final String district;

    public static class Builder{

        private String user_name;
        private Date dateRegistration;
        private int roomsCount;
        private boolean kindergarden;
        private boolean school;
        private String district;

        public Builder setUserName(String user_name){
            this.user_name = user_name;
            return this;
        }

        public Builder setDateRegistration(Date dateRegistration) {
            this.dateRegistration = dateRegistration;
            return this;
        }

        public Builder setRoomsCount(int roomsCount) {
            this.roomsCount = roomsCount;
            return this;
        }

        public Builder setKindergarden(boolean kindergarden) {
            this.kindergarden = kindergarden;
            return this;
        }

        public Builder setSchool(boolean school) {
            this.school = school;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public FullEntity build(){
            return new FullEntity(this);
        }

    }

    private FullEntity(Builder builder) {
        this.user_name = builder.user_name;
        this.dateRegistration = builder.dateRegistration;
        this.roomsCount = builder.roomsCount;
        this.kindergarden = builder.kindergarden;
        this.school = builder.school;
        this.district = builder.district;
    }

    public String getUserName() {
        return user_name;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public boolean isKindergarden() {
        return kindergarden;
    }

    public boolean isSchool() {
        return school;
    }

    public String getDistrict() {
        return district;
    }
}
