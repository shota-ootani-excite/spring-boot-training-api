package jp.co.software.excite.training.domain.object;

import java.util.Date;

import lombok.Data;

@Data
public class TaskDto {

    private Integer id;
    private String title;
    private String description;
    private String status;
    private Date deadline;
}
