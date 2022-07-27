package pers.xiaomuma.example.web.dto.wrapper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.Min;

@Data
@ApiModel("查询用户")
public class QueryAccountReq {

    @ApiModelProperty("status")
    private Integer status;

    @ApiModelProperty("page_num")
    @Min(1)
    private Integer pageNum = 1;

    @ApiModelProperty("page_size")
    @Min(1)
    private Integer pageSize = 10;

}
