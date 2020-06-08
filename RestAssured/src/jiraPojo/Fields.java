package jiraPojo;

public class Fields {

	private Project project;
	private String summary;
	private IssueType issuetype;
	private Reporter reporter;
	private String description;
	
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
