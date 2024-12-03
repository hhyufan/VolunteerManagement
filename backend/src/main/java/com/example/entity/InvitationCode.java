package com.example.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvitationCode {
    private int id;
    private String code;
    private LocalDateTime createdAt;
    private Boolean used;
}
