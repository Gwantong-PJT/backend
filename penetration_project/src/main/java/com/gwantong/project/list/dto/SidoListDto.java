package com.gwantong.project.list.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "SidoListDto (시도 리스트)", description = "시도 정보. sidos의 내용")
@Data
public class SidoListDto {
    @Schema(description = "시도 번호, 사용 X, 왜 있는지 모름")
    private int sidoNo;

    @Schema(description = "시도 코드. 데이터 주고 받을 때 이 코드 이용할 것")
    private int sidoCode;

    @Schema(description = "시도 명")
    private String sidoName;
}
