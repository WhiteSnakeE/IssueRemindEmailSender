package com.example.issueRemindEmailSender.task;
import com.example.issueRemindEmailSender.ProcessEnv;
import com.example.issueRemindEmailSender.service.EmailMessage;
import com.example.issueRemindEmailSender.service.JiraServiceAPI;
import com.example.issueRemindEmailSender.service.SendEmailService;
import com.example.issueRemindEmailSender.model.JiraIssue;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("SendEmailToEmployeeTask")
public class SendEmailToEmployeeTask implements JavaDelegate {
    private final JiraServiceAPI jiraService;

    public SendEmailToEmployeeTask(JiraServiceAPI jiraService){
        this.jiraService = jiraService;
    }

    @Override
    public void execute(DelegateExecution execution)  {
        log.info("sending only to employee");
        ProcessEnv processEnv = new ProcessEnv(execution);
        JiraIssue jiraIssue = processEnv.getJiraIssues();
        SendEmailService sendEmailService = new SendEmailService();
        EmailMessage emailMessage = new EmailMessage(jiraService);
        String message = emailMessage.setMessageEmployee(jiraIssue) ;
        sendEmailService.send(jiraIssue.getEmail(), message);
    }
}
