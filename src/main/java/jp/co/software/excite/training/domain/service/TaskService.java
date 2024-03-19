package jp.co.software.excite.training.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jp.co.software.excite.training.domain.dto.TaskDto;
import jp.co.software.excite.training.domain.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    /**
     * タスクを取得する.
     * 
     * @return
     */
    public List<TaskDto> getTasks() {
        return repository.getTasks();
    }

    /**
     * 指定されたタスクを取得する.
     * 
     * @param id
     * @return
     */
    public TaskDto getTask(Integer id) {
        return repository.getTask(id);
    }

    /**
     * タスクを登録する.
     * 
     * @param data
     * @return
     */
    public TaskDto createTask(TaskDto data) {
        return repository.createTask(data);
    }

    /**
     * タスクを更新する.
     * 
     * @param data
     * @return
     */
    public TaskDto updateTask(TaskDto data) {
        return repository.updateTask(data);
    }

    /**
     * タスクを削除する.
     * 
     * @param id
     */
    public void deleteTask(Integer id) {
        repository.deleteTask(id);
    }

    /**
     * コンフリクト発生サンプル1
     */
    public void sample1() {}

}
