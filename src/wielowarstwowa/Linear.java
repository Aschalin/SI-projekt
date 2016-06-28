package wielowarstwowa;

public class Linear implements Activator
{

	@Override
	public double Activate(double input)
	{
		return (input*0.5);
	}

	@Override
	public double Deactivate(double input)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}