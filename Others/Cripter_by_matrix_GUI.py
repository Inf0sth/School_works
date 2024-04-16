"""
Proyecto: Cifrador y descifrador de palabras

Conocimiento aplicado:
Multiplicación de matrices
Matrices transpuestas
Determinantes de matrices de 2x2: para generar claves
GUI
Raices cuadradas con metodos numericos
Criptografia basica
"""

# Librerias
import tkinter as tk
from tkinter import messagebox
from numpy import *
import random

# Función para cifrar el mensaje
def cifrar():
    # Obtener el mensaje del cuadro de texto
    mensaje = mensaje_entry.get()

    # Matriz secundaria para operar la principal:
    matriz = zeros((2, 2)) # Matriz 2 x 2

    # Rellenamos la matriz:
    for i in range(2):
        for j in range(2):
            rannum = random.randint(10, 99) # Generamos los valores aleatoriamente
            matriz[i, j] = rannum

    # Obtener determinante de la matriz 2 x 2: 
    a = matriz[0, 0]
    b = matriz[0, 1]
    c = matriz[1, 0]
    d = matriz[1, 1]

    clave = a * d - b * c # Formula para el det 2x2
    # Enviamos el resultado a la GUI:
    clave_label.config(text="Clave: " + str(clave))

    # Creacion de la matriz cuadrada principal
    x = 1.5 * (float(len(mensaje))) # Multiplicamos por 1.5 para no tener sobrantes
    er_r = 0.0001  # Margen de error tolerable
    y = x / 2  # Suposición inicial

    while abs(y * y - x) >= er_r:
        y = (y + x / y) / 2  # Cálculo de la nueva suposición

    # "Y" es la raiz de la longitud del mensaje multiplicado por 1.5
    y = round(y) # Obtenemos un valor redondeado de para trabajar valores exactos.

    array_to_fill = zeros((y, y)) # Creamos una nueva matriz de ceros de las dimensiones de "Y"

    for i in range(y):
        for j in range(y):
            pos = i * y + j
            if pos < len(mensaje):
                letra = mensaje[pos]
                array_to_fill[i, j] = ord(letra)

    array_transposed = zeros((y, y))

    for i in range(y):
        for j in range(y):
            array_transposed[i, j] = array_to_fill[j, i]

    for i in range(y):
        for j in range(y):
            array_transposed[i, j] *= clave
    
    matriz_cifrada = array_transposed.tolist()  # Convertir a lista
    
    matriz_completa = [[format(num, ".0f") for num in row] for row in matriz_cifrada]  # Formatear números sin decimales

    # Limpiar el widget Text
    matriz_cifrada_text.delete(1.0, tk.END)
    
    # Insertar los valores de la matriz en el widget Text
    for row in matriz_completa:
        matriz_cifrada_text.insert(tk.END, " ".join(row) + "\n")

    # Ajustar la altura del widget Text al contenido
    lines = len(matriz_completa)
    matriz_cifrada_text.config(height=lines)

    # Actualizar la ventana
    window.update_idletasks()


# Función para descifrar el mensaje
def descifrar():
    matriz2_values = matriz2_entry.get().strip("[]")  # Eliminar corchetes al inicio y al final
    matriz2_values = matriz2_values.replace(",", " ")  # Reemplazar comas por espacios
    matriz2_values = matriz2_values.split()  # Convertir la cadena en una lista de valores

    matriz2 = []

    for value in matriz2_values:
        try:
            matriz2.append(int(value))  # Convertir el valor a un entero y agregarlo a la matriz2
        except ValueError:
            messagebox.showerror("Error", "Los valores de la matriz deben ser enteros.")
            return

    y = int(sqrt(len(matriz2)))
    sublists = [matriz2[i:i+y] for i in range(0, len(matriz2), y)]
    array_divided = array(sublists)
    det_inv = float(clave_entry.get())
    array_divided = array_divided / det_inv
    array_to_fill = zeros((y, y))

    for i in range(y):
        for j in range(y):
            array_to_fill[i, j] = array_divided[j, i]

    original_message = ""

    for i in range(y):
        for j in range(y):
            num = array_to_fill[i, j]
            if num != 0:
                original_message += chr(int(num))

    original_message_label.config(text="Mensaje original:\n" + original_message)

def encript_active():
    cifrado_frame.pack(pady=10, padx=10, fill="both", expand=True)
    descifrado_frame.pack_forget()
    welcome_frame.pack_forget()
    window.config(menu=menu)
    window.geometry("400x420")

def decript_active():
    descifrado_frame.pack(pady=10, padx=10, fill="both", expand=True)
    cifrado_frame.pack_forget()
    welcome_frame.pack_forget()
    window.config(menu=menu)
    window.geometry("400x320")

# Decición para poner el menu.
des_menu = False

# Crear ventana
window = tk.Tk()
window.title("Mess-change")
window.geometry("400x400")
window.resizable(0,1)

# Crear marcos para las operaciones
frame1 = tk.Frame(window, bg="#705a1b")
frame1.pack(fill="both",expand=True)

# Welcome to the user
welcome_frame = tk.Frame(frame1, bg="#333231")
welcome_frame.pack(pady=10, padx=10, fill="both", expand=True)
welcome_text = tk.Label(welcome_frame, text="WELCOME", font=("Arial", 12, "bold"), bg="#333231", fg="#c7c7c7")
welcome_text.pack(expand=True)
options_text= tk.Label(welcome_frame, text="Seleccione una opción:", font=("Arial", 12), bg="#333231", fg="#c7c7c7")
options_text.pack()
boton_cifrar = tk.Button(welcome_frame, text="Cifrar Mensaje", bg="#1c2e5e", font=("Arial", 12, "bold"), command=encript_active)
boton_cifrar.pack()
boton_descifrar = tk.Button(welcome_frame, text="Descifrar Mensaje", bg="#1c2e5e", font=("Arial", 12, "bold"), command=decript_active)
boton_descifrar.pack()


# Configuración del menu:
menu = tk.Menu() # Menu principal
menu.config(background="#c7c7c7")
# Sub-menus:
menu_topics = tk.Menu(menu, tearoff=0)
menu.add_cascade(label="Operación", menu=menu_topics)
menu_topics.add_command(label="Encriptar", command=encript_active)
menu_topics.add_command(label="Descifrar", command=decript_active)


# Frame del cifrado
cifrado_frame = tk.Frame(frame1, bg="#333231")


# Etiqueta y cuadro de texto para el mensaje a cifrar
cifrado_frame_1_borde = tk.Frame(cifrado_frame, bg="#1c2e5e")
cifrado_frame_1_borde.pack( pady=10, padx=10, fill="both")
cifrado_frame_1 = tk.Frame(cifrado_frame_1_borde, bg="#1c2e5e")
cifrado_frame_1.pack( pady=10, padx=10, fill="both")
mensaje_label = tk.Label(cifrado_frame_1, text="Mensaje a cifrar:", bg="#aaabad", font=("Arial", 12))
mensaje_label.pack(pady=5)
mensaje_entry = tk.Entry(cifrado_frame_1)
mensaje_entry.pack()

# Text box:
# Crear widget Text para la matriz cifrada
matriz_cifrada_text = tk.Text(cifrado_frame_1, width=20, height=10)
matriz_cifrada_text.pack()

# Botón para cifrar
cifrado_frame_2_borde = tk.Frame(cifrado_frame, bg="#1c2e5e")
cifrado_frame_2_borde.pack( pady=10, padx=10, fill="both")
cifrado_frame_2 = tk.Frame(cifrado_frame_2_borde, bg="#1c2e5e")
cifrado_frame_2.pack( pady=10, padx=10, fill="both")
cifrar_button = tk.Button(cifrado_frame_2, text="Cifrar", command=cifrar)
cifrar_button.pack()

# Etiqueta para la clave generada
clave_label = tk.Label(cifrado_frame_2, text="Clave:", bg="#aaabad", font=("Arial", 12))
clave_label.pack(pady=5)


# Crear marco para el descifrado
descifrado_frame = tk.Frame(frame1, bg="#333231")


# Etiqueta y cuadro de texto para la matriz cifrada
descifrado_frame_1_borde = tk.Frame(descifrado_frame, bg="#1c2e5e")
descifrado_frame_1_borde.pack( pady=10, padx=10, fill="both")
descifrado_frame_1 = tk.Frame(descifrado_frame_1_borde, bg="#1c2e5e")
descifrado_frame_1.pack( pady=10, padx=10, fill="both")
aviso_label = tk.Label(descifrado_frame_1, text="NOTA: Separar valores solo con espacios", bg="#1c2e5e", fg="red")
aviso_label.pack()
matriz2_label = tk.Label(descifrado_frame_1, text="Mensaje Cifrado:", bg="#aaabad", font=("Arial", 12))
matriz2_label.pack(pady=5)
matriz2_entry = tk.Entry(descifrado_frame_1)
matriz2_entry.pack()

# Etiqueta y cuadro de texto para la clave
descifrado_frame_2_borde = tk.Frame(descifrado_frame, bg="#1c2e5e")
descifrado_frame_2_borde.pack( pady=10, padx=10, fill="both")
descifrado_frame_2 = tk.Frame(descifrado_frame_2_borde, bg="#1c2e5e")
descifrado_frame_2.pack( pady=10, padx=10, fill="both")
clave_label2 = tk.Label(descifrado_frame_1, text="Clave:", bg="#aaabad", font=("Arial", 12))
clave_label2.pack(pady=5)
clave_entry = tk.Entry(descifrado_frame_1)
clave_entry.pack()

# Botón para descifrar
descifrar_button = tk.Button(descifrado_frame_2, text="Descifrar", command=descifrar)
descifrar_button.pack()

# Etiqueta para el mensaje original
original_message_label = tk.Label(descifrado_frame_2, text="Mensaje original:", bg="#aaabad", font=("Arial", 12))
original_message_label.pack(pady=5)

# Ejecutar ventana
window.mainloop()