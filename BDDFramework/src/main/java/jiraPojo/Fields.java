package jiraPojo;

import java.util.List;

public class Fields {

	private Project project;
	private String summary;
	private IssueType issuetype;
	private Reporter reporter;
	private String description;
	private Priority priority;
	private List<Versions> versions;
	
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public List<Versions> getVersions() {
		return versions;
	}
	public void setVersions(List<Versions> versions) {
		this.versions = versions;
	}
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public IssueType getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(IssueType issuetype) {
		this.issuetype = issuetype;
	}
	public Reporter getReporter() {
		return reporter;
	}
	public void setReporter(Reporter reporter) {
		this.reporter = reporter;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
