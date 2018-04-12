package com.yangyang.apiplatform.entity;

public class Consumer {
String consumer_id;
String consumer_name;
String consumer_password;
String consumer_email;

    @Override
    public String toString() {
        return "Consumer{" +
                "consumer_id='" + consumer_id + '\'' +
                ", consumer_name='" + consumer_name + '\'' +
                ", consumer_password='" + consumer_password + '\'' +
                ", consumer_email='" + consumer_email + '\'' +
                ", consumer_tel='" + consumer_tel + '\'' +
                '}';
    }

    public String getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(String consumer_id) {
        this.consumer_id = consumer_id;
    }

    public String getConsumer_name() {
        return consumer_name;
    }

    public void setConsumer_name(String consumer_name) {
        this.consumer_name = consumer_name;
    }

    public String getConsumer_password() {
        return consumer_password;
    }

    public void setConsumer_password(String consumer_password) {
        this.consumer_password = consumer_password;
    }

    public String getConsumer_email() {
        return consumer_email;
    }

    public void setConsumer_email(String consumer_email) {
        this.consumer_email = consumer_email;
    }

    public String getConsumer_tel() {
        return consumer_tel;
    }

    public void setConsumer_tel(String consumer_tel) {
        this.consumer_tel = consumer_tel;
    }

    String consumer_tel;
}
