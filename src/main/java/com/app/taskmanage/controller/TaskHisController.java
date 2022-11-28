package com.app.taskmanage.controller;

import com.app.taskmanage.service.TaskHisService;
import com.core.controller.BaseController;
import com.core.data.model.DataModel;
import com.core.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author DC Yuan
 * @version 1.0
 */
@RestController
@RequestMapping("/api/task/his")
public class TaskHisController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskHisService taskHisService;


    /***
     * query comm task
     * @param request
     * @param requestMap
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/query")
    public Map<String, Object> queryCommTaskHis(HttpServletRequest request, @RequestBody Map<String, Object> requestMap) {
        DataModel resultModel = new DataModel();
        try {
            DataModel queryModel = this.getInputData(requestMap);
            List<DataModel> userModel = taskHisService.queryCommTaskHis(queryModel);
            this.handleSuccess(userModel, resultModel);
        } catch (ValidationException ve) {
            this.handleValidationException(ve, resultModel);
        } catch (Exception e) {
            this.handleException(e, resultModel);
        }
        return resultModel;
    }
}
