package com.gwantong.project.list.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "GugunListDto (구군 리스트)", description = "시도 정보. guguns의 내용")
@Data
public class GugunListDto {
    @Schema(description = "구군 번호, 사용 X, 왜 있는지 모름")
    private int gugunNo;

    @Schema(description = "구군 코드. 데이터 주고 받을 때 이 코드 이용할 것")
    private int gugunCode;

    @Schema(description = "구군 명")
    private String gugunName;

    @Schema(description = "소속 시도. sido 테이블의 sidoCode")
    private String sidoCode;

}
