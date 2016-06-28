package wielowarstwowa;

import java.util.Scanner;

public class Main
{
	int input_size = 2;
	int layers_quantity = 2;
	int[] layers_size = { 2, 1};
    int[] layers_activator = {0, 0};
	
	Network net = new Network(input_size, layers_quantity, layers_size, layers_activator);
	
}
