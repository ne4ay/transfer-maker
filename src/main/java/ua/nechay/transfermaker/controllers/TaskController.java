package ua.nechay.transfermaker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.transfermaker.domain.TaskKey;
import ua.nechay.transfermaker.dto.input.RegisteredTaskInfoTO;
import ua.nechay.transfermaker.dto.input.request.TaskDeactivatingRequestTO;
import ua.nechay.transfermaker.dto.input.request.TaskRegisterRequestTO;
import ua.nechay.transfermaker.dto.input.response.TaskDeactivatingResponseTO;
import ua.nechay.transfermaker.dto.input.response.TaskRegisterResponseTO;
import ua.nechay.transfermaker.internal.ScheduledTask;
import ua.nechay.transfermaker.internal.TaskRegisteringResult;
import ua.nechay.transfermaker.logic.TaskPool;
import ua.nechay.transfermaker.logic.TaskScheduler;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author anechaev
 * @since 25.01.2023
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired private TaskScheduler taskScheduler;
    @Autowired private TaskPool taskPool;

    @PostMapping("/register")
    public @ResponseBody TaskRegisterResponseTO registerTask(@RequestBody TaskRegisterRequestTO request) {
        TaskKey key = TaskKey.fromRequest(request);
        TaskRegisteringResult result = taskScheduler.registerTask(key, request.getToken());
        return TaskRegisterResponseTO.fromResult(result);
    }

    @PostMapping("/deactivate")
    public @ResponseBody TaskDeactivatingResponseTO deactivateTask(@RequestBody TaskDeactivatingRequestTO request) {
        TaskKey key = TaskKey.fromRequest(request);
        TaskRegisteringResult result = taskScheduler.deactivateTask(key);
        return TaskDeactivatingResponseTO.fromResult(result);
    }

    @GetMapping("/all")
    public @ResponseBody List<RegisteredTaskInfoTO> getAllTasks() {
        return taskPool.getAllTasks()
            .entrySet()
            .stream()
            .map(this::fromEntry)
            .collect(Collectors.toList());
    }

    private RegisteredTaskInfoTO fromEntry(@Nonnull Map.Entry<TaskKey, ScheduledTask> entry) {
        var task = entry.getValue();
        return RegisteredTaskInfoTO.fromKey(entry.getKey(), task.getToken(), task.getRegistered());
    }
}
