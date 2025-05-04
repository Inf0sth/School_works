"""
Control de Sensores IoT con Interfaz Gráfica

Este programa permite controlar y monitorear diversos sensores y actuadores IoT:
- Sónico
- Fotoresistencia
- Temperatura
- Humedad
- LED ultra brillante
- 10 LEDs individuales
- Buzzer
- RFID

Los datos se envían cada 2 segundos por puerto serial en formato CSV:
sonico,fotoresistencia,temperatura,humedad,led_ultra,leds_binario,buzzer,rfid

Autor: [Tu nombre]
Versión: 1.0
Fecha: [Fecha]
"""

import tkinter as tk
from tkinter import ttk, messagebox, scrolledtext
import serial
import threading
import queue
import time

# ===================== CONFIGURACIÓN INICIAL =====================
# Configuración del puerto serial (ajustar según necesidad)
SERIAL_PORT = 'COM2'    # Puerto serial para enviar datos
BAUD_RATE = 9600        # Velocidad en baudios

class EnhancedSensorUI:
    """Clase principal que maneja la interfaz gráfica y la lógica de control"""
    
    def __init__(self, root):
        """Inicializa la aplicación con la ventana principal"""
        self.root = root
        self.root.title("Control de Sensores IoT - Mejorado")
        self.root.geometry("900x700")
        
        # ========== CONFIGURACIÓN DE ESTILOS ==========
        self.setup_styles()
        
        # ========== VARIABLES DE ESTADO ==========
        # Valores iniciales realistas para los sensores/actuadores
        self.sonico = tk.IntVar(value=0)          # 0=Inactivo, 1=Activo
        self.fotoresistencia = tk.IntVar(value=1) # 1=Luz detectada (activado)
        self.temperatura = tk.DoubleVar(value=22.5) # Temperatura ambiente
        self.humedad = tk.DoubleVar(value=45.0)   # Humedad relativa
        self.led_ultra = tk.IntVar(value=0)       # LED apagado por defecto
        self.leds = [tk.IntVar(value=0) for _ in range(10)] # Todos los LEDs apagados
        self.buzzer = tk.IntVar(value=0)          # Buzzer apagado
        self.rfid = tk.StringVar(value="ID0001ABC") # ID RFID de ejemplo
        self.sending_active = True                 # Control para el envío de datos
        
        # ========== CONFIGURACIÓN DE LA INTERFAZ ==========
        self.setup_main_frames()       # Frames principales
        self.setup_sensor_controls()   # Controles para sensores
        self.setup_actuator_controls() # Controles para actuadores
        self.setup_console_system()    # Sistema de consolas
        self.setup_status_bar()        # Barra de estado
        
        # ========== SISTEMA DE COLAS ==========
        self.data_queue = queue.Queue()    # Para datos serial
        self.update_queue = queue.Queue(maxsize=10) # Para actualizaciones de UI
        
        # ========== INICIO DE SERVICIOS ==========
        try:
            self.serial_port = serial.Serial(SERIAL_PORT, BAUD_RATE, timeout=1)
            self.running = True
            self.start_services()  # Inicia el hilo de envío de datos
        except Exception as e:
            messagebox.showerror("Error", f"No se pudo abrir el puerto serial:\n{str(e)}")
            self.root.destroy()
            return
        
        # ========== CONFIGURACIÓN ADICIONAL ==========
        self.setup_tooltips()      # Tooltips para controles
        self.root.after(100, self.process_updates) # Inicia el procesamiento de actualizaciones

    # ===================== MÉTODOS DE CONFIGURACIÓN =====================
    
    def setup_styles(self):
        """Configura los estilos visuales de la interfaz (tema claro)"""
        self.style = ttk.Style()
        self.style.theme_use('clam')  # Tema claro con bordes definidos
        
        # Configuración de colores y estilos
        self.style.configure('.', background='#f0f0f0', foreground='black')
        self.style.configure('TFrame', background='#f0f0f0')
        self.style.configure('TLabel', background='#f0f0f0', foreground='black')
        self.style.configure('TLabelframe', background='#f0f0f0', foreground='black')
        self.style.configure('TLabelframe.Label', background='#f0f0f0', foreground='black')
        self.style.configure('TButton', background='#e1e1e1', foreground='black')
        self.style.configure('TEntry', fieldbackground='white', foreground='black')
        self.style.configure('TSpinbox', fieldbackground='white', foreground='black')
        self.style.configure('Status.TLabel', background='#e0e0e0', relief=tk.SUNKEN)
    
    def setup_main_frames(self):
        """Configura los frames principales de la interfaz"""
        # Frame principal que contiene todo
        self.main_frame = ttk.Frame(self.root, padding="10")
        self.main_frame.pack(fill=tk.BOTH, expand=True)
        
        # Frame para sensores
        self.sensor_frame = ttk.LabelFrame(self.main_frame, text="Sensores", padding=10)
        self.sensor_frame.grid(row=0, column=0, padx=5, pady=5, sticky="nsew")
        
        # Frame para actuadores
        self.actuator_frame = ttk.LabelFrame(self.main_frame, text="Actuadores", padding=10)
        self.actuator_frame.grid(row=1, column=0, padx=5, pady=5, sticky="nsew")
        
        # Configuración de grid responsive
        self.main_frame.grid_rowconfigure(0, weight=1)
        self.main_frame.grid_rowconfigure(1, weight=1)
        self.main_frame.grid_columnconfigure(0, weight=1)
    
    def setup_sensor_controls(self):
        """Configura los controles para los sensores"""
        # Sónico - Checkbutton
        ttk.Label(self.sensor_frame, text="Sónico:").grid(row=0, column=0, sticky=tk.W)
        self.sonico_btn = ttk.Checkbutton(
            self.sensor_frame, 
            variable=self.sonico,
            command=lambda: self.log_action(f"Sónico cambiado a {self.sonico.get()}")
        )
        self.sonico_btn.grid(row=0, column=1, sticky=tk.W)
        
        # Fotoresistencia - Checkbutton
        ttk.Label(self.sensor_frame, text="Fotoresistencia:").grid(row=1, column=0, sticky=tk.W)
        self.fotoresistencia_btn = ttk.Checkbutton(
            self.sensor_frame,
            variable=self.fotoresistencia,
            command=self.update_fotoresistencia
        )
        self.fotoresistencia_btn.grid(row=1, column=1, sticky=tk.W)
        
        # Temperatura - Spinbox con valores decimales
        ttk.Label(self.sensor_frame, text="Temperatura (°C):").grid(row=2, column=0, sticky=tk.W)
        self.temp_spin = ttk.Spinbox(
            self.sensor_frame,
            from_=-10.0,
            to=50.0,
            increment=0.1,
            textvariable=self.temperatura,
            width=8,
            command=lambda: self.log_action(f"Temperatura ajustada a {self.temperatura.get()}°C")
        )
        self.temp_spin.grid(row=2, column=1, sticky=tk.W)
        
        # Humedad - Spinbox con valores decimales
        ttk.Label(self.sensor_frame, text="Humedad (%):").grid(row=3, column=0, sticky=tk.W)
        self.hum_spin = ttk.Spinbox(
            self.sensor_frame,
            from_=0.0,
            to=100.0,
            increment=0.5,
            textvariable=self.humedad,
            width=8,
            command=lambda: self.log_action(f"Humedad ajustada a {self.humedad.get()}%")
        )
        self.hum_spin.grid(row=3, column=1, sticky=tk.W)
        
        # RFID - Entry con validación
        ttk.Label(self.sensor_frame, text="RFID:").grid(row=4, column=0, sticky=tk.W)
        self.rfid_entry = ttk.Entry(
            self.sensor_frame,
            textvariable=self.rfid,
            validate="key",
            validatecommand=(self.root.register(self.validate_rfid), '%P'),
            width=15
        )
        self.rfid_entry.grid(row=4, column=1, sticky=tk.W)
    
    def setup_actuator_controls(self):
        """Configura los controles para los actuadores"""
        # LED Ultra Brillante
        ttk.Label(self.actuator_frame, text="LED Ultra:").grid(row=0, column=0, sticky=tk.W)
        
        # Botón para detener manualmente el LED
        self.led_btn = ttk.Button(
            self.actuator_frame,
            text="Detener LED",
            command=self.stop_led,
            state=tk.DISABLED  # Inicialmente desactivado
        )
        self.led_btn.grid(row=0, column=1, sticky=tk.W, padx=5)
        
        # Indicador visual del LED (canvas con círculo)
        self.led_canvas = tk.Canvas(
            self.actuator_frame, 
            width=30, 
            height=30, 
            bg='white', 
            highlightbackground='black',
            highlightthickness=1
        )
        self.led_canvas.grid(row=0, column=2, sticky=tk.W)
        self.led_indicator = self.led_canvas.create_oval(5, 5, 25, 25, fill='gray', outline='black')
        
        # Estado textual del LED
        self.led_status = ttk.Label(self.actuator_frame, text="Apagado (Fotoresistencia activa)")
        self.led_status.grid(row=0, column=3, sticky=tk.W, padx=5)
        
        # 10 LEDs individuales
        ttk.Label(self.actuator_frame, text="10 LEDs:").grid(row=1, column=0, sticky=tk.W)
        self.led_frame = ttk.Frame(self.actuator_frame)
        self.led_frame.grid(row=1, column=1, columnspan=3, sticky=tk.W)
        
        for i in range(10):
            led = ttk.Checkbutton(
                self.led_frame,
                text=str(i+1),
                variable=self.leds[i],
                command=lambda i=i: self.log_action(f"LED {i+1} cambiado a {self.leds[i].get()}")
            )
            led.grid(row=0, column=i, padx=2)
        
        # Buzzer - Checkbutton
        ttk.Label(self.actuator_frame, text="Buzzer:").grid(row=2, column=0, sticky=tk.W)
        self.buzzer_btn = ttk.Checkbutton(
            self.actuator_frame,
            variable=self.buzzer,
            command=lambda: self.log_action(f"Buzzer cambiado a {self.buzzer.get()}")
        )
        self.buzzer_btn.grid(row=2, column=1, sticky=tk.W)
        
        # Frame para botones de control general
        self.control_frame = ttk.Frame(self.actuator_frame)
        self.control_frame.grid(row=3, column=0, columnspan=4, pady=10)
        
        # Botón para detener/reanudar el envío de datos
        self.stop_btn = ttk.Button(
            self.control_frame,
            text="Detener Envío",
            command=self.toggle_sending,
            style='TButton'
        )
        self.stop_btn.pack(side=tk.LEFT, padx=5)
    
    def setup_console_system(self):
        """Configura el sistema de consolas divididas"""
        console_frame = ttk.Frame(self.main_frame)
        console_frame.grid(row=2, column=0, padx=5, pady=5, sticky="nsew")
        
        # Consola superior: Muestra solo los datos enviados
        data_console_frame = ttk.LabelFrame(console_frame, text="Datos Enviados", padding=5)
        data_console_frame.pack(side=tk.TOP, fill=tk.BOTH, expand=True)
        
        self.data_console = scrolledtext.ScrolledText(
            data_console_frame,
            height=5,
            state=tk.DISABLED,
            bg='white',
            fg='black',
            font=('Consolas', 9),
            insertbackground='black'
        )
        self.data_console.pack(fill=tk.BOTH, expand=True)
        
        # Consola inferior: Muestra los eventos del sistema
        event_console_frame = ttk.LabelFrame(console_frame, text="Eventos del Sistema", padding=5)
        event_console_frame.pack(side=tk.TOP, fill=tk.BOTH, expand=True)
        
        self.event_console = scrolledtext.ScrolledText(
            event_console_frame,
            height=5,
            state=tk.DISABLED,
            bg='white',
            fg='black',
            font=('Consolas', 9),
            insertbackground='black'
        )
        self.event_console.pack(fill=tk.BOTH, expand=True)
    
    def setup_status_bar(self):
        """Configura la barra de estado en la parte inferior"""
        self.status_var = tk.StringVar(value="Sistema iniciado - Enviando datos...")
        status_bar = ttk.Label(
            self.root,
            textvariable=self.status_var,
            relief=tk.SUNKEN,
            anchor=tk.W,
            style='Status.TLabel'
        )
        status_bar.pack(side=tk.BOTTOM, fill=tk.X)
    
    def setup_tooltips(self):
        """Configura los tooltips para los controles"""
        self.create_tooltip(self.sonico_btn, "Activa/desactiva el sensor sónico (1=ON, 0=OFF)")
        self.create_tooltip(self.fotoresistencia_btn, "Controla el sensor de luz. Cuando está activo, el LED Ultra se apaga automáticamente")
        self.create_tooltip(self.temp_spin, "Ajuste la temperatura actual en grados Celsius (-10 a 50°C)")
        self.create_tooltip(self.hum_spin, "Ajuste el porcentaje de humedad relativa (0 a 100%)")
        self.create_tooltip(self.rfid_entry, "Ingrese el código RFID (máx. 10 caracteres alfanuméricos)")
        self.create_tooltip(self.led_canvas, "Estado del LED Ultra Brillante (controlado por fotoresistencia)")
        self.create_tooltip(self.buzzer_btn, "Activa/desactiva el buzzer")
    
    # ===================== MÉTODOS DE FUNCIONALIDAD =====================
    
    def create_tooltip(self, widget, text):
        """Crea un tooltip para un widget específico"""
        widget.bind("<Enter>", lambda e: self.show_tooltip(text))
        widget.bind("<Leave>", lambda e: self.hide_tooltip())
    
    def show_tooltip(self, text):
        """Muestra el tooltip con información contextual"""
        self.tooltip = tk.Toplevel(self.root)
        self.tooltip.wm_overrideredirect(True)  # Elimina la decoración de ventana
        self.tooltip.wm_geometry(f"+{self.root.winfo_pointerx()+10}+{self.root.winfo_pointery()+10}")
        
        label = ttk.Label(
            self.tooltip,
            text=text,
            background="#ffffe0",
            foreground="#000000",
            relief=tk.SOLID,
            borderwidth=1,
            padding=5,
            wraplength=200
        )
        label.pack()
    
    def hide_tooltip(self):
        """Oculta el tooltip cuando el mouse sale del widget"""
        if hasattr(self, 'tooltip'):
            self.tooltip.destroy()
    
    def start_services(self):
        """Inicia los servicios en segundo plano (hilo de envío serial)"""
        self.serial_thread = threading.Thread(target=self.send_data_loop, daemon=True)
        self.serial_thread.start()
        self.update_status("Servicios iniciados - Conectado a COM2")
    
    def stop_led(self):
        """Detiene el LED ultra brillante manualmente"""
        self.led_ultra.set(0)
        self.update_led_display()
        self.led_btn.config(state=tk.DISABLED)
        self.log_action("LED detenido manualmente")
    
    def update_fotoresistencia(self):
        """Actualiza el estado del LED basado en la fotoresistencia"""
        state = self.fotoresistencia.get()
        
        if state == 1:  # Fotoresistencia ACTIVADA
            self.led_ultra.set(0)
            self.led_btn.config(state=tk.DISABLED)
            self.log_action("Fotoresistencia ACTIVADA - LED apagado automáticamente")
        else:  # Fotoresistencia DESACTIVADA
            self.led_ultra.set(1)  # Se enciende automáticamente
            self.led_btn.config(state=tk.NORMAL)  # Habilita el botón de detener
            self.log_action("Fotoresistencia DESACTIVADA - LED encendido automáticamente")
        
        self.update_led_display()
    
    def update_led_display(self):
        """Actualiza el indicador visual del LED ultra brillante"""
        color = 'green' if self.led_ultra.get() else 'gray'
        
        if self.fotoresistencia.get() == 1:
            text = "Apagado (Fotoresistencia activa)"
        else:
            if self.led_ultra.get() == 1:
                text = "Encendido"
            else:
                text = "Apagado (Detenido manualmente)"
        
        self.led_canvas.itemconfig(self.led_indicator, fill=color)
        self.led_status.config(text=text)
    
    def validate_rfid(self, new_value):
        """Valida que el RFID ingresado sea válido (alfanumérico y <= 10 caracteres)"""
        if len(new_value) > 10:
            self.show_error("RFID muy largo", "Máximo 10 caracteres permitidos")
            return False
        return new_value.isalnum()
    
    def show_error(self, title, message):
        """Muestra un mensaje de error en un cuadro de diálogo"""
        messagebox.showerror(title, message)
        self.update_status(f"Error: {message}")
    
    def log_action(self, message):
        """Registra una acción en la consola de eventos con timestamp"""
        timestamp = time.strftime("%H:%M:%S")
        self.update_event_console(f"[{timestamp}] {message}")
    
    def update_event_console(self, message):
        """Agrega un mensaje a la cola para actualizar la consola de eventos"""
        self.update_queue.put(lambda: self._update_event_console(message))
    
    def _update_event_console(self, message):
        """Actualiza la consola de eventos (ejecutado en el hilo principal)"""
        self.event_console.config(state=tk.NORMAL)
        self.event_console.insert(tk.END, message + "\n")
        self.event_console.see(tk.END)
        self.event_console.config(state=tk.DISABLED)
    
    def update_data_console(self, message):
        """Agrega un mensaje a la cola para actualizar la consola de datos"""
        self.update_queue.put(lambda: self._update_data_console(message))
    
    def _update_data_console(self, message):
        """Actualiza la consola de datos (ejecutado en el hilo principal)"""
        self.data_console.config(state=tk.NORMAL)
        self.data_console.insert(tk.END, message + "\n")
        self.data_console.see(tk.END)
        self.data_console.config(state=tk.DISABLED)
    
    def update_status(self, message):
        """Actualiza el mensaje en la barra de estado"""
        self.status_var.set(message)
        self.log_action(f"Estado: {message}")
    
    def generate_data_string(self):
        """Genera la cadena de datos en formato CSV para enviar por serial"""
        return (
            f"{self.sonico.get()},{self.fotoresistencia.get()},"
            f"{self.temperatura.get():.2f},{self.humedad.get():.2f},"
            f"{self.led_ultra.get()},{self.get_leds_binary()},"
            f"{self.buzzer.get()},{self.rfid.get()}"
        )
    
    def get_leds_binary(self):
        """Devuelve el estado de los 10 LEDs como cadena binaria"""
        return ''.join([str(led.get()) for led in self.leds])
    
    def send_data_loop(self):
        """Bucle principal para enviar datos periódicamente por puerto serial"""
        while True:
            if self.sending_active:
                try:
                    data = self.generate_data_string()
                    self.serial_port.write((data + "\n").encode())
                    self.update_data_console(data)
                    self.update_status(f"\nDatos enviados a {SERIAL_PORT}")
                except Exception as e:
                    self.update_status(f"\nError serial: {str(e)}")
                    break
            
            time.sleep(2)  # Espera 2 segundos entre envíos
    
    def toggle_sending(self):
        """Alterna el estado de envío de datos (activado/desactivado)"""
        self.sending_active = not self.sending_active
        
        if self.sending_active:
            self.stop_btn.config(text="Detener Envío")
            self.update_status("Reanudando envío de datos...")
        else:
            self.stop_btn.config(text="Reanudar Envío")
            self.update_status("Envío de datos pausado")
        
        self.log_action(f"Envio de datos {'ACTIVADO' if self.sending_active else 'DESACTIVADO'}")
    
    def process_updates(self):
        """Procesa las actualizaciones pendientes en la cola (ejecutado en el hilo principal)"""
        try:
            while not self.update_queue.empty():
                update_fn = self.update_queue.get_nowait()
                update_fn()
        except queue.Empty:
            pass
        
        self.root.after(100, self.process_updates)  # Programa la próxima verificación
    
    def stop(self):
        """Detiene la aplicación de forma segura, cerrando recursos"""
        self.running = False
        self.update_status("Deteniendo servicios...")
        
        try:
            if self.serial_port.is_open:
                self.serial_port.close()
        except:
            pass
        
        self.log_action("Aplicación detenida correctamente")
        self.root.after(1000, self.root.quit)  # Da tiempo a registrar el mensaje antes de cerrar

# ===================== PUNTO DE ENTRADA =====================
if __name__ == "__main__":
    root = tk.Tk()
    app = EnhancedSensorUI(root)
    root.protocol("WM_DELETE_WINDOW", app.stop)  # Manejar cierre de ventana
    root.mainloop()