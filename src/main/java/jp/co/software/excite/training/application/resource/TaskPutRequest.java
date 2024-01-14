package jp.co.software.excite.training.application.resource;

import java.util.Date;

import lombok.Data;

@Data
public class TaskPutRequest {

    private String title;
    private String description;
    private String status;
    private Date deadline;
}
