package ua.nechay.transfermaker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.transfermaker.domain.TaskKey;
import ua.nechay.transfermaker.dto.input.request.TaskDeactivatingRequestTO;
import ua.nechay.transfermaker.dto.input.request.TaskRegisterRequestTO;
import ua.nechay.transfermaker.dto.input.response.TaskDeactivatingResponseTO;
import ua.nechay.transfermaker.dto.input.response.TaskRegisterResponseTO;
import ua.nechay.transfermaker.internal.TaskRegisteringResult;
import ua.nechay.transfermaker.logic.TaskScheduler;

/**
 * @author anechaev
 * @since 25.01.2023
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired private TaskScheduler taskScheduler;

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
}
