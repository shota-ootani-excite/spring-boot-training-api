package jp.co.software.excite.training.application.controller;

import java.util.List;
import java.util.Objects;
import org.springframework.web.bind.annotation.RestController;
import jp.co.software.excite.training.application.resource.TaskResponse;
import jp.co.software.excite.training.domain.service.TaskService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@Validated
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponse>> getTasks() {

        var models = service.getTasks();

        List<TaskResponse> resp =
                modelMapper.map(models, new TypeToken<List<TaskResponse>>() {}.getType());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Integer id) {

        var model = service.getTask(id);

        if (Objects.isNull(model)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var resp = modelMapper.map(model, TaskResponse.class);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
