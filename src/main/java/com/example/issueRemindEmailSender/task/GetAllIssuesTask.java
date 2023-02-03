package com.example.issueRemindEmailSender.task;



import com.example.issueRemindEmailSender.ProcessEnv;
import com.example.issueRemindEmailSender.model.JiraIssue;
import com.example.issueRemindEmailSender.service.JiraServiceAPI;
import lombok.extern.slf4j.Slf4j;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component("GetAllIssuesTask")
public class GetAllIssuesTask implements JavaDelegate {

    private final JiraServiceAPI jiraService;

    public GetAllIssuesTask(JiraServiceAPI jiraService){
        this.jiraService = jiraService;
    }

    @Override
    public void execute(DelegateExecution execution)  {
        List<JiraIssue> allIssuies = jiraService.getIssuesFields();
        boolean areAllIssuesNull = jiraService.areNeedIssuePresents(allIssuies);
        log.info("All Issues - {}", allIssuies);
        ProcessEnv processEnv = new ProcessEnv(execution);
        processEnv.setAreNeedIssuesPresent(areAllIssuesNull);
        processEnv.setJiraIssues(allIssuies);

    }

}