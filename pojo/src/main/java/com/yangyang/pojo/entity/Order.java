package com.yangyang.pojo.entity;

public class Order {
  private String order_id;
  private String consumer_id;
  private String api_id;
  private java.sql.Timestamp order_create_time;
  private Long order_remain;
  private Long order_total;

  public String getOrder_id() {
    return order_id;
  }

  @Override
  public String toString() {
    return "Order{" +
            "order_id='" + order_id + '\'' +
            ", consumer_id='" + consumer_id + '\'' +
            ", api_id='" + api_id + '\'' +
            ", order_create_time=" + order_create_time +
            ", order_remain=" + order_remain +
            ", order_total=" + order_total +
            '}';
  }

  public void setOrder_id(String order_id) {
    this.order_id = order_id;
  }

  public String getConsumer_id() {
    return consumer_id;
  }

  public void setConsumer_id(String consumer_id) {
    this.consumer_id = consumer_id;
  }

  public String getApi_id() {
    return api_id;
  }

  public void setApi_id(String api_id) {
    this.api_id = api_id;
  }

  public java.sql.Timestamp getOrder_create_time() {
    return order_create_time;
  }

  public void setOrder_create_time(java.sql.Timestamp order_create_time) {
    this.order_create_time = order_create_time;
  }

  public Long getOrder_remain() {
    return order_remain;
  }

  public void setOrder_remain(Long order_remain) {
    this.order_remain = order_remain;
  }

  public Long getOrder_total() {
    return order_total;
  }

  public void setOrder_total(Long order_total) {
    this.order_total = order_total;
  }
}
