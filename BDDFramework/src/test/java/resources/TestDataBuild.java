package resources;

import java.util.ArrayList;
import java.util.List;

import jiraPojo.Fields;
import jiraPojo.IssueType;
import jiraPojo.NewIssue;
import jiraPojo.Priority;
import jiraPojo.Project;
import jiraPojo.Reporter;
import jiraPojo.Versions;

public class TestDataBuild {

	public NewIssue addDefectPayload() {
		NewIssue bug = new NewIssue();
		Fields fields = new Fields();
		Project project = new Project();
		IssueType issuetype = new IssueType();
		Reporter reporter = new Reporter();
		Priority priority = new Priority();
		Versions affectedVersion = new Versions();
		List<Versions> versions = new ArrayList<Versions>();
		
		reporter.setName("nirvanich");
		issuetype.setName("Bug");
		project.setKey("RAT");
		priority.setName("Highest");
		affectedVersion.setName("1.0");
		versions.add(affectedVersion);
				
		fields.setReporter(reporter);
		fields.setIssuetype(issuetype);
		fields.setProject(project);
		fields.setSummary("Defect made of Serialized Json");
		fields.setDescription("Test description");
		fields.setPriority(priority);
		fields.setVersions(versions);
		bug.setFields(fields);
		
		return bug;
	}
}
