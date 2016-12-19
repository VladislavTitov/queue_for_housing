package entities;

import java.sql.Date;

public class Housing implements Entity{
    private final int condition;
    private final Date applicationDate;

    public static class Builder{
        private int condition;
        private Date applicationDate;


        public Builder setCondition(int condition) {
            this.condition = condition;
            return this;
        }

        public Builder setApplicationDate(Date date) {
            this.applicationDate = date;
            return this;
        }

        public Housing build(){
            return new Housing(this);
        }
    }

    private Housing(Builder builder){
        this.condition = builder.condition;
        this.applicationDate = builder.applicationDate;
    }


    public int getCondition() {
        return condition;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

}
