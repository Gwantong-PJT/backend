package com.gwantong.project.attraction.dto;

import lombok.Data;

@Data
public class AttractionDto {
    private int hotplaceNo;
    private int userNo;
    private String userName;
    private String hotplaceTitle;
    private String hotplaceText;
    private String hotplaceDate;
    private int hotplaceViews;
}
