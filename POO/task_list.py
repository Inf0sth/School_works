""" 
Tasklist
Joel Albert Araiza López
Basic practice.
"""

# Import os to work with files 
import os

def create_file():
    if not os.path.exists("tasklist.txt"):
            task = input("\nEnter first task: ")
            with open("tasklist.txt", "w") as file:
                file.write(task)
    else:
        print("\nFile is already created!!")

def write_task():
    task = input("\nEnter new task: ")
    with open("tasklist.txt", "a") as file:
        file.write(f"\n{task}")

def read_tasks():
    print("""Select order: \n1)First to last\n2)Last to first""")
    order = int(input("> "))
    if order == 1:
        with open("tasklist.txt") as file:
            tasks = file.readlines()
            index = []
            y = 0
            for task in tasks:
                index.append(y)
                y += 1
            for ind in index:
                print(ind, ": ", tasks[ind])
    elif order == 2:
        with open("tasklist.txt") as file:
            tasks = file.readlines()
            index = []
            y = 0
            for task in tasks:
                index.append(y)
                y += 1
            ind = index[-1]
            while ind >= 0:
                print(ind, ": ", tasks[ind])
                ind -= 1
        pass
    else:
        print("That option does not exist!!")

def task_complete():
    with open("tasklist.txt") as file:
        tasks = file.readlines()
        index = []
        y = 0
        for task in tasks:
            index.append(y)
            y += 1
        for ind in index:
            print(ind, ": ", tasks[ind])
    task = int(input("Enter the completed task number: "))
    complete = open("tasklist.txt", "r")
    tasks = complete.readlines()
    tasks = list(tasks)
    del tasks[task]

    writes = open("tasklist.txt", "w")
    for new_task in tasks:
        writes.write(f"{new_task}")

def delete_file():
    if os.path.exists("tasklist.txt"):
        os.remove("tasklist.txt")
        print("File removed!!")
    else:
        print("The file does not exist.")

x = 1
while x == 1:
    print("""\nSelect option:
1) Create new file.
2) Enter new task.
3) Read tasks.
4) Mark task completed.
5) Delete file.
6) Exit.
""")
    option = int(input("> "))
    if option == 1:
        create_file()
        x = int(input("\nAnother operation? \n1) YES \n2) NO\n> "))
    elif option == 2:
        write_task()
        x = int(input("\nAnother operation? \n1) YES \n2) NO\n> "))
    elif option == 3:
        read_tasks()
        x = int(input("\nAnother operation? \n1) YES \n2) NO\n> "))
    elif option == 4:
        task_complete()
        x = int(input("\nAnother operation? \n1) YES \n2) NO\n> "))
    elif option == 5:
        delete_file()
        x = int(input("\nAnother operation? \n1) YES \n2) NO\n> "))
    elif option == 6:
        exit()
    else:
        print("\nInvalid option")
        x = int(input("\nAnother operation? \n1) YES \n2) NO\n> "))