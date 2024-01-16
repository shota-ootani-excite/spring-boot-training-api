package jp.co.software.excite.training.application.controller;

import java.util.List;
import java.util.Objects;
import org.springframework.web.bind.annotation.RestController;
import jp.co.software.excite.training.application.resource.TaskPostRequest;
import jp.co.software.excite.training.application.resource.TaskPutRequest;
import jp.co.software.excite.training.application.resource.TaskResponse;
import jp.co.software.excite.training.domain.dto.TaskDto;
import jp.co.software.excite.training.domain.service.TaskService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponse> postTask(@RequestBody TaskPostRequest req) {

        var reqData = modelMapper.map(req, TaskDto.class);

        var model = service.createTask(reqData);

        var resp = modelMapper.map(model, TaskResponse.class);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskResponse> putTask(@PathVariable Integer id,
            @RequestBody TaskPutRequest req) {

        var reqData = modelMapper.map(req, TaskDto.class);
        reqData.setId(id);

        var model = service.updateTask(reqData);

        var resp = modelMapper.map(model, TaskResponse.class);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {

        service.deleteTask(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
