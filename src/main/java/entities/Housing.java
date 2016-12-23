package entities;

import java.sql.Date;

public class Housing implements Entity{
    private final Integer condition;
    private final Date applicationDate;

    public static class Builder{
        private Integer condition;
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


    public Integer getCondition() {
        return condition;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

}
