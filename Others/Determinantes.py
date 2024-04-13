def get_nums(var):
    print()
    l = []
    for i in range(0,3):
        j = int(input(f"Ingrese valor {i} de {var}: "))
        l.append(j)
    return l

x = get_nums("x")
y = get_nums("y")
z = get_nums("z")
r = get_nums("r")

print("\n",x,"\n",y,"\n",z,"\n",r)

def get_det(x,y,z):
    print()
    det_r = ((x[0]*y[1]*z[2])+(x[1]*y[2]*z[0])+(x[2]*y[0]*z[1]))-((z[0]*y[1]*x[2])+(z[1]*y[2]*x[0])+(z[2]*y[0]*x[1]))
    return det_r

det_x = get_det(r,y,z)
det_y = get_det(x,r,z)
det_z = get_det(x,y,r)
det_r = get_det(x,y,z)

print("\nDeterminantes:")
print("X: ",det_x)
print("Y: ",det_y)
print("Z: ",det_z)
print("R: ",det_r)

xVAL = det_x/det_r
yVAL = det_y/det_r
zVAL = det_z/det_r

print("\nValores:")
print("X: ",xVAL)
print("Y: ",yVAL)
print("Z: ",zVAL)