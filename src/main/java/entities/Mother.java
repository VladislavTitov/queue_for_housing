package entities;

public class Mother implements Entity{
    private final String surname;
    private final String name;
    private final String patronymic;

    public static class Builder{

        private String surname;
        private String name;
        private String patronymic;

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPatronymic(String patronymic) {
            this.patronymic = patronymic;
            return this;
        }

        public Mother build(){
            return new Mother(this);
        }
    }

    private Mother(Builder builder) {
        this.surname = builder.surname;
        this.name = builder.name;
        this.patronymic = builder.patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }
}
