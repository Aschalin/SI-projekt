package wielowarstwowa;

public class Sigmoidal implements Activator
{

	@Override
	public double Activate(double input)
	{
		return (1/(1+Math.exp(-0.5*input)));
	}

	@Override
	public double Deactivate(double input)
	{
		double derivative = 0.5 * input * (1d - input) + 0.1;
		return derivative;
	}

}
