package pers.xiaomuma.example.crud.param;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryAccountParam {

    private Integer status;

    private Integer pageNum;

    private Integer pageSize;

    public Integer getLimit() {
        if (this.pageSize > 0) {
            return (this.pageNum - 1) * this.pageSize;
        }
        return pageNum;
    }
}
