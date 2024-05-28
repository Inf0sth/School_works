#include <iostream>
#include <fstream>

int main(){
  std::ofstream myfile;
  myfile.open("Example.txt");
  myfile << "Writing this to a file.\n";
  myfile.close();

  return 0;
}
