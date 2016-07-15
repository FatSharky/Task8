package by.traning.task8.domain.type;

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
