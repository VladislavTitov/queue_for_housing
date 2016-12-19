package entities;

import java.time.LocalDate;

public class Promotions implements Entity{
    public static final int MAX_ROOMS_COUNT = 5;
    public static final int MAX_CONDITION_VALUE = 4;

    boolean isEmpty = true;

    private final Boolean promotions;
    private final Boolean outOfQueue;
    private final Boolean firstOfQueue;

    public static class Builder{
        private Boolean promotions;
        private Boolean outOfQueue;
        private Boolean firstOfQueue;

        public Builder setPromotions(boolean promotions) {
            this.promotions = promotions;
            return this;
        }

        public Builder setOutOfQueue(boolean outOfQueue) {
            this.outOfQueue = outOfQueue;
            return this;
        }

        public Builder setFirstOfQueue(boolean firstOfQueue) {
            this.firstOfQueue = firstOfQueue;
            return this;
        }

        public Promotions build(){
            return new Promotions(this);
        }
    }

    private Promotions(Builder builder){
        this.promotions = builder.promotions;
        this.outOfQueue = builder.outOfQueue;
        this.firstOfQueue = builder.firstOfQueue;
        if (promotions != null || outOfQueue != null || firstOfQueue != null){
            this.isEmpty = false;
        }
    }

    public boolean isPromotions() {
        return promotions;
    }

    public boolean isOutOfQueue() {
        return outOfQueue;
    }

    public boolean isFirstOfQueue() {
        return firstOfQueue;
    }

    public boolean isEmpty(){
        return this.isEmpty;
    }
}
