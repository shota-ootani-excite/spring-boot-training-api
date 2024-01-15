package jp.co.software.excite.training.domain.repository;

import java.util.List;
import jp.co.software.excite.training.domain.dto.TaskDto;

public interface TaskRepository {

    /**
     * タスクを取得する.
     * 
     * @return
     */
    public List<TaskDto> getTasks();

    /**
     * 指定されたタスクを取得する.
     * 
     * @param id
     * @return
     */
    public TaskDto getTask(Integer id);

    /**
     * タスクを登録する.
     * 
     * @param data
     * @return
     */
    public TaskDto createTask(TaskDto data);

    /**
     * タスクを更新する.
     * 
     * @param data
     * @return
     */
    public TaskDto updateTask(TaskDto data);

    /**
     * タスクを削除する.
     * 
     * @param id
     */
    public void deleteTask(Integer id);

}
