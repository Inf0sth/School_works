#include <iostream>
#include <cstdlib>

int main(){
	std::string user;
	int option;
	std::string directory;
	std::cout << "Enter user: ";
	std::cin >> user;
	directory = "mkdir " + user;
	std::cout << "\nWelcome "+user;
	system(directory.c_str());
	std::cout << "\nWhat do you want to do?";
	std::cout << "\n1) Make a file.";
	std::cout << "\n2) See files.";
	std::cout << "\n3) Exit.";
	std::cout << "\n>> ";
	std::cin >> option;
	switch (option){
	case 1:{
		std::string file;
		std::cout << "\nEnter the file name: ";
		std::cin >> file;
		std::string command = "touch "+user+"/"+file;
		system(command.c_str());
		break;}
	case 2:{
		std::string command = "ls "+user;
		system(command.c_str());
		break;
	}
	default:
		exit(EXIT_SUCCESS);
	}
}
