package Password;

public class SingletonPassword 
{
	private static final SingletonPassword instance = new SingletonPassword();
	private String password = "";
	private SingletonPassword() {
	}
	public static String getInstance() 
	{
		return instance.password;
	}
	public static void setInstance(String value)
	{
		instance.password = value;
	}
}
