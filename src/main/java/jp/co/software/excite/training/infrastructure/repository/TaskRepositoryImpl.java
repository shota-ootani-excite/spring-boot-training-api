package jp.co.software.excite.training.infrastructure.repository;

import java.util.List;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jp.co.software.excite.training.domain.object.TaskDto;
import jp.co.software.excite.training.domain.repository.TaskRepository;
import jp.co.software.excite.training.infrastructure.entity.Task;
import jp.co.software.excite.training.infrastructure.mapper.TaskMapper;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    private TaskMapper mapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TaskDto> getTasks() {

        var records = mapper.selectByExample(null);

        return modelMapper.map(records, new TypeToken<List<TaskDto>>() {}.getType());
    }

    @Override
    public TaskDto getTask(Integer id) {

        var record = mapper.selectByPrimaryKey(id);

        if (Objects.isNull(record)) {
            return null;
        }

        return modelMapper.map(record, TaskDto.class);
    }

    @Override
    public TaskDto createTask(TaskDto data) {

        var entity = modelMapper.map(data, Task.class);

        int cnt = mapper.insertSelective(entity);
        if (cnt == 0) {
            // TODO: 例外を返したい
            return null;
        }

        return modelMapper.map(entity, TaskDto.class);
    }

    @Override
    public TaskDto updateTask(TaskDto data) {

        var entity = modelMapper.map(data, Task.class);

        int cnt = mapper.updateByPrimaryKey(entity);
        if (cnt == 0) {
            // TODO: 例外を返したい
            return null;
        }

        return modelMapper.map(entity, TaskDto.class);
    }

    @Override
    public void deleteTask(Integer id) {

        int cnt = mapper.deleteByPrimaryKey(id);
        if (cnt == 0) {
            // TODO: 例外を返したい
        }
    }

}
