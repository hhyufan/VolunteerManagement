package com.example.dto;

import com.example.entity.Event;
import com.example.util.DateToStringConverter;
import com.example.util.DurationToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private Long id;
    private String title;
    private String date; // Date as string
    private String location;
    private String duration; // Duration as string
    private String content;
    private String attachmentLink;
    private String imageUrl;

    public EventDTO(Event event) {
        this(
                event.getId(),
                event.getTitle(),
                DateToStringConverter.convertToString(event.getDate()),
                event.getLocation(),
                DurationToStringConverter.convertToString(Duration.ofSeconds(event.getDuration())),
                event.getContent(),
                event.getAttachmentLink(),
                event.getImageUrl()
        );
    }
}
