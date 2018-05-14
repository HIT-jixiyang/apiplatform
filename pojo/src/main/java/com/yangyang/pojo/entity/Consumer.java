package com.yangyang.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Consumer {
String consumer_id;
String consumer_name;
String consumer_password;
String consumer_email;
String consumer_tel;
Integer consumer_type;//0：内部用户,1:外部用户

}
