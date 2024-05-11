#include <iostream>
#include <cstdlib>

int main(){
	std::string user;
	int option;
	int filecount;
	std::string directory;
	std::cout << "Enter user\n";
	std::cin >> user;
	directory = "mkdir " + user;
	std::cout << "Welcome "+user;
	system(directory.c_str());
	std::cout << "What do you want to do?";
	std::cout << "1) Make a file.";
	std::cout << "2) See files.";
	std::cout << "3) Exit.";
	std::cout << ">> ";
	std::cin >> option;
	switch (option){
	case 1:{
		std::string command = "touch "+user+"/file"+std::to_string(filecount);
		system(command.c_str());
		filecount++;
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
