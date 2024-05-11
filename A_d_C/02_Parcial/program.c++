#include <iostream>
#include <cstdlib>

int main(){
	std::string user;
	std::cout << "Enter user\n";
	std::cin >> user;
	std::cout << "Welcome "+user;
	int option;
	std::cout << "What do you want to do?";
	std::cout << "1) Make a file.";
	std::cout << "2) See files.";
	std::cout << "3) Exit.";
	std::cout << ">> ";
	std::cin >> option;
	system("mkdir "+user);
}
