# Code to obtain the determinant of a 3x3 matrix

def get_nums(var): # Function to get the valors of the matrix
    print() # Only format
    l = [] # Temporal list to store the values
    for i in range(0,3):
        j = int(input(f"Ingrese valor {i} de {var}: ")) # Get the numbers 1 by 1
        l.append(j) # Append the valors to the temporal list.
    return l # Return the complete list to a variable or as result

# Getting the valors of the sistem
x = get_nums("x") 
y = get_nums("y")
z = get_nums("z")
r = get_nums("r")

# Check the content
print("\n",x,"\n",y,"\n",z,"\n",r)

def get_det(x,y,z): # Function to slove the determinant of each variable
    print()
    det_r = ((x[0]*y[1]*z[2])+(x[1]*y[2]*z[0])+(x[2]*y[0]*z[1]))-((z[0]*y[1]*x[2])+(z[1]*y[2]*x[0])+(z[2]*y[0]*x[1]))
    return det_r

# Use the function to det each determinant
det_x = get_det(r,y,z)
det_y = get_det(x,r,z)
det_z = get_det(x,y,r)
det_r = get_det(x,y,z)

# Return each determinant to the user
print("\nDeterminantes:")
print("X: ",det_x)
print("Y: ",det_y)
print("Z: ",det_z)
print("R: ",det_r)

# Obtain the value of each variable
xVAL = det_x/det_r
yVAL = det_y/det_r
zVAL = det_z/det_r

# Return each value
print("\nValores:")
print("X: ",xVAL)
print("Y: ",yVAL)
print("Z: ",zVAL)