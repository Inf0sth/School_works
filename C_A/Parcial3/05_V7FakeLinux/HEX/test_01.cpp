#include<iostream>
using namespace std;

int main ()
{
    string strInput;
    string key = "MVZMX-PVHA8-3YNHU-76AD8-YGGY5-NPWTH";
    string key = "PVHA8-3YNHU-76AD8-YGGY5-NPWTH";
    
    cout << "Ingresa password_: ";
    cin >> strInput;

    if (strInput == key)
    {
        cout << "Completed!" << endl;
    }
    else
    {
        cout << "Failed" << endl;
    }
    
    return 0;
}
