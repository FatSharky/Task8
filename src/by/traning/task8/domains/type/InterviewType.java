package by.traning.task8.domains.type;

public enum InterviewType {

	TECHICAL {
		{
			interviewType = "techical";
		}
	},
	PRELIMINARY {
		{
			interviewType = "preliminary";
		}
	};

	String interviewType;

	public String getInterviewType() {
		return interviewType;
	}

}
