package com.gwantong.project.list.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "AgeListDto (나이 리스트)", description = "나이 정보. age_tb의 내용")
@Data
public class AgeListDto {
    @Schema(description = "나이 번호. 데이터 주고 받을 때 이 코드 이용할 것")
    private int ageNo;

    @Schema(description = "나이 실제 값")
    private int ageValue;
}
