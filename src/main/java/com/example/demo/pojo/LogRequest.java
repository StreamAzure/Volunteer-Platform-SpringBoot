package com.example.demo.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogRequest {
    private String username;
    private int duration;
    private String date;

    @Override
    public String toString() {
        return "LogRequest{" +
                "username='" + username + '\'' +
                ", duration=" + duration +
                ", date='" + date + '\'' +
                '}';
    }
}
