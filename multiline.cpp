#include <iostream>
#include <cmath>
#include <cstdlib>
#include <fstream>
#include <iomanip>

/**
 * Joshua Fernandes
 * ! test program
 * TODO: implement classes
 */
using namespace std;

int main()
{
	int num = 2;

	switch (num)
	{
		case 1:
			cout << num;
			break;
		case 2:
			cout << num;
			break;
		case 3:
			cout << num;
			break;
		case 4:
			cout << num;
			break;
	}

	int i[] = {1,2,3,4,5};



	for(int n : i)
	{
		cout << n;
	}




	return 0;
}
