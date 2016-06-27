package wielowarstwowa;

public class Layer
{
	Neuron[] neurons;
	int quantity, input_size;
	
	public Layer(int input_quantity, int neurons_quantity)
	{
		input_size = input_quantity;
		quantity = neurons_quantity;
		neurons = new Neuron[neurons_quantity];
		for(int i = 0; i < neurons_quantity; i++)
		{
			neurons[i] = new Neuron(input_quantity);
		}
	}
	
	public double[] feed(double[] input)
	{
		double[] result = new double[quantity];
		
		for(int i=0; i<quantity; i++)
		{
			result[i]=neurons[i].feed(input);
		}
		return result;
	}
	
	public double[][] learn(double[][] input_errors_matrix, double learn_factor)
	{
		double[][] output_errors_matrix = new double[quantity][input_size];
		for(int i=0; i<quantity; i++)
		{
			double neuron_error[] = new double[input_errors_matrix.length];
			for(int j=0; j<input_errors_matrix.length; j++)
			{
				neuron_error[j] = input_errors_matrix[j][i];
			}
			output_errors_matrix[i] = neurons[i].learn(neuron_error, learn_factor);
		}
		return output_errors_matrix;
	}
	
}
