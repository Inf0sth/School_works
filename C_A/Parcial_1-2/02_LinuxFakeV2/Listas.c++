#include <iostream>
#include <list>

/**struct user
{
    std::string user;
    std::list <std::string> files;
};*/

int main(){
    std::list<int> gqlist{12,45,8,6};
    for(auto i : gqlist){
        std::cout << i << ' ';
    }
    std::cout << "\n\n";
    gqlist.push_back(99);
    gqlist.push_front(0);
    for(auto i : gqlist){
        std::cout << i << ' ';
    }
    gqlist.pop_back();
    std::cout << "\n\n";
    for(auto i : gqlist){
        std::cout << i << ' ';
    }
    gqlist.pop_front();
    std::cout << "\n\n";
    for(auto i : gqlist){
        std::cout << i << ' ';
    }
    return 0;
}
