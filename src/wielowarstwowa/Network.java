package wielowarstwowa;

public class Network
{
	Layer[] layers;
	int input_size, output_size;
	
	Network(int input_quantity, int layers_quantity, int[] layers_size, Activator[] activators)
	{
		input_size = input_quantity;
		output_size = layers_size[layers_quantity-1];

		layers = new Layer[layers_quantity];
		
		layers[0] = new Layer(input_quantity, layers_size[0], activators[0]);
		for(int i = 1; i < layers_quantity; i++)
		{
			layers[i] = new Layer(layers_size[i-1], layers_size[i], activators[0]);
		}
	}

	public double[] feed(double[] input)
	{
		for(Layer layer: layers)
		{
			input = layer.feed(input);
		}
		return input;
	}
	void learn(double[][] input, double[][] output, double learn_factor, int epoch)
	{
		for(int epoka=0; epoka<epoch; epoka++)
		{
			
			for(int set=0; set<input.length; set++)
			{
				double[][] layer_result = new double[1][output_size];
				layer_result[0] = feed(input[set]);
				for(int i=0; i<output[set].length; i++)
				{
					layer_result[0][i] = output[set][i] - layer_result[0][i] ;
				}
				for(int layer=layers.length-1; layer>=0; layer--)
				{
					layer_result = layers[layer].learn(layer_result, learn_factor);
				}
			}
		}
	}
}
