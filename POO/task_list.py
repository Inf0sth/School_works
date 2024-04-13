""" 
Class: Object oriented programing
Poroject: Tasklist
Student: Joel Albert Araiza López
Languaje: Python
"""

# Import os to work with files 
import os

# Function to create a file
def create_file():
    # In this file we will save our tasks
    if not os.path.exists("tasklist.txt"): # Verify whit os if the file exists
            # If the file does not exists, we create it, and write the first task
            task = input("\nEnter first task: ") # We request the task
            # We create the file and write the task
            with open("tasklist.txt", "w") as file:
                file.write(task)
    else:
        # If the file is alreadey exists, we say it to the user.
        print("\nFile is already created!!")

# Function to write a new task in the file
def write_task():
    # We request the task to the user
    task = input("\nEnter new task: ")
    # Open the file and write the task
    with open("tasklist.txt", "a") as file:
        file.write(f"\n{task}")

# Function to read the tasks in the file and show it to the user
def read_tasks():
    # Display a menu to choose the order of tasks
    print("""Select order: \n1)First to last\n2)Last to first""")
    order = int(input("> ")) # We request to the user the option selected
    if order == 1: # First option (Normal order, by add date)
        # Acces to the file to read the content
        with open("tasklist.txt") as file:
            tasks = file.readlines()
            index = [] # List to add the content of tasks file
            y = 0 # Counter to index the list of tasks
            for task in tasks: # We iterate the content of the tasks
                index.append(y) # We add the content of the file to a list
                y += 1 # We increment the counter to index the task file content
            for ind in index: # Iterate the content of the list to show it to the user
                print(ind, ": ", tasks[ind]) # Output the content
    elif order == 2: # Second option (to the last added to the first)
        # Acces to the file to read the content and do the same proces to first case
        with open("tasklist.txt") as file:
            tasks = file.readlines()
            index = [] 
            y = 0
            for task in tasks:
                index.append(y)
                y += 1
            ind = index[-1] # The only change is we index to the last to first at moment of the output
            # We use the cycle while because we don't know the lenght in reverse
            while ind >= 0: # We check that the index is greater than zero
                print(ind, ": ", tasks[ind]) # Output the content
                ind -= 1 # We subtract from the counter
    else:
        print("That option does not exist!!") # Check the correct input user

# Function to mark a task as ready
def task_complete():
    # Open the file to read it and show the content to the user
    with open("tasklist.txt") as file:
        # We repeat the proces to list all the content of the file
        tasks = file.readlines()
        index = []
        y = 0
        for task in tasks:
            index.append(y)
            y += 1
        for ind in index:
            print(ind, ": ", tasks[ind]) # Output the content
    task = int(input("Enter the completed task number: ")) # The user select the task to delete it
    complete = open("tasklist.txt", "r") # Open the file in read mode
    tasks = complete.readlines() # We store the content
    tasks = list(tasks) # Conver the content in a list
    del tasks[task] # Del the selected task by the user

    writes = open("tasklist.txt", "w") # Open the file in write mode
    for new_task in tasks: # We rewrite the file by a cycle
        writes.write(f"{new_task}") # We rewrite indexing the list of the contetn altered by the deliting action

# Function to delete the file
def delete_file():
    # Verify whit os if the file exists
    if os.path.exists("tasklist.txt"):
        # If it exist we remove it
        os.remove("tasklist.txt")
        print("File removed!!") # Confirm to the user that the file has been deleated
    else:
        # If the file does not exist, notify to the user
        print("The file does not exist.")

# variable to make the program cycle
x = 1
while x == 1: # While the variable x is equal to 1 the program is active
    # Print a menu of actions to the user
    print("""\nSelect option:
1) Create new file.
2) Enter new task.
3) Read tasks.
4) Mark task completed.
5) Delete file.
6) Exit.
""")
    option = int(input("> ")) # Request a option to the user to proced with the program
    if option == 1:# If the option is equal to 1, the program proced to exec "create_file" option
        create_file()
        # This is to decide if the program continues executing or not, by the user
        x = int(input("\nAnother operation? \n1) YES \n2) NO\n> "))
    elif option == 2:# If the option is equal to 2, the program proced to exec "write_task" option
        write_task()
        x = int(input("\nAnother operation? \n1) YES \n2) NO\n> "))
    elif option == 3:# If the option is equal to 3, the program proced to exec "read_tasks" option
        read_tasks()
        x = int(input("\nAnother operation? \n1) YES \n2) NO\n> "))
    elif option == 4:# If the option is equal to 4, the program proced to exec "task_complete" option
        task_complete()
        x = int(input("\nAnother operation? \n1) YES \n2) NO\n> "))
    elif option == 5:# If the option is equal to 5, the program proced to exec "delete_file" option
        delete_file()
        x = int(input("\nAnother operation? \n1) YES \n2) NO\n> "))
    elif option == 6:# If the option is equal to 6, the program proced to exec "exit" option
        exit() # This is a default function to stop the program
    else:
        print("\nInvalid option") # If the option is'nt valid we notify it to the user
        x = int(input("\nAnother operation? \n1) YES \n2) NO\n> "))