package com.example.firstCamundaTask;

import com.example.firstCamundaTask.model.JiraIssue;
import org.camunda.bpm.engine.delegate.VariableScope;

import java.util.List;

public class ProcessEnv {

	// main process

	public static final String ARE_NEED_ISSUES_PRESENT = "areNeedIssueIsPresent";

	public static final String JIRA_ISSUES = "jiraIssues";
	public static final String ISSUE = "issue";

	private final VariableScope variableScope;


	public ProcessEnv(VariableScope variableScope) {
		this.variableScope = variableScope;
	}

	// Main Process:

	public void setAreNeedIssuesPresent(boolean isSubTaskWithClonePresent) {
		variableScope.setVariable(ARE_NEED_ISSUES_PRESENT, isSubTaskWithClonePresent);
	}

	public JiraIssue getJiraIssues() {
		return (JiraIssue) variableScope.getVariable(ISSUE);
	}

	public void setJiraIssues(List<JiraIssue> jiraIssues) {
		variableScope.setVariable(JIRA_ISSUES, jiraIssues);
	}

}