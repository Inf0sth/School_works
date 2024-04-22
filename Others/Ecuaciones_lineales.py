# Resolución de ecucaciones lineales por Gaus Jordan
from numpy import *

matris_R = zeros((3,3))
# Matriz 1 = (x,y,z)
# Matriz 2 = R1, R2,R3
for i in range(3):
        for j in range(3):
            pos = i * 3 + j
            if pos < 9:
                numero = int(input(f"Ingrese el numero para {pos+1}: "))
                matris_R[i, j] = numero
            else:
                break

print("\n",matris_R)

while (matris_R[0][0],matris_R[1][1],matris_R[2][2]) != 1:
    pass

"""
while y[1][0] != 0:
    if x[0][0] == -(y [1][0]):
        x + y
    elif x [0][0] == y [1][0]:
        x - y
    else:
        x*y - y*x or x*y + y*x
"""