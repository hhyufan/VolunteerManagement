package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private Long id;
    private String title;
    private Date date;
    private String location;
    private Long duration;
    private String content;
    private String attachmentLink;
    private String imageUrl;
}
