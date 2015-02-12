package ca.ualberta.cs.lonelytwitter;


public abstract class Friendship {
	
	public String nickname;
	

	
	public abstract String getNickname();

	
	public abstract void setNickname(String nickname);


	public Friendship(String nickname)
	{

		super();
		this.nickname = nickname;
	}
	

	public Friendship()
	{

		super();
		this.nickname = "nickname";
	}
	
	
}
