package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRecord {
    private Long id;              // 记录的唯一标识符
    private Long volunteerId;     // 外键，指向志愿者
    private Long eventId;         // 外键，指向活动
    private boolean completed;     // 活动是否完成
}
