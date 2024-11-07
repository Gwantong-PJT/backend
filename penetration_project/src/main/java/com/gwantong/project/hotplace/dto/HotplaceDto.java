package com.gwantong.project.hotplace.dto;

import lombok.Data;

@Data
public class HotplaceDto {
    private int hotplaceNo;
    private int userNo;
    private String hotplaceTitle;
    private String hotplaceText;
    private String hotplaceDate;
    private int hotplaceViews;
}
