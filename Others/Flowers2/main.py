import pygame
import sys
import os
import random
import math
import time  # Necesario para la animación basada en tiempo

# --- Constantes y Configuración ---
WIDTH, HEIGHT = 1200, 800 # Tamaño inicial, se adaptará a pantalla completa si es posible
TITLE_TEXT = "Para la princesa de mi corazón"
TITLE_FONT_NAME = 'brushscriptmt' # Intenta usar esta, si no, usa un fallback
TITLE_FONT_FALLBACK = 'comicsansms' # O 'segoescript', o None para fuente por defecto
TITLE_FONT_SIZE = 50
TITLE_COLOR = (255, 105, 180)  # Hot Pink
SHADOW_COLOR = (50, 50, 50)    # Sombra gris oscura
BACKGROUND_COLOR = (0, 0, 0)     # Negro
FPS = 60 # Fotogramas por segundo para la animación
FLOWER_SIZE = (100, 100)
ANIMATION_DURATION_MS = 8000 # 8 segundos en milisegundos
FADE_IN_RATIO = 0.5 # 50% del tiempo para fade in, 50% para fade out

# --- Rutas de Recursos ---
# Obtiene la ruta del directorio donde está el script
script_dir = os.path.dirname(__file__)
RESOURCES_DIR = os.path.join(script_dir, "Resources")

# --- Clase para manejar cada flor animada ---
class AnimatedFlower:
    def __init__(self, image, pos, duration_ms):
        self.original_image = image # Guardar la imagen original sin escalar
        # Escalar la imagen al tamaño deseado UNA VEZ
        self.image = pygame.transform.scale(self.original_image, FLOWER_SIZE)
        self.rect = self.image.get_rect(center=pos)
        self.start_time = pygame.time.get_ticks() # Momento de creación en ms
        self.duration = duration_ms
        self.fade_in_duration = duration_ms * FADE_IN_RATIO
        self.fade_out_start_time = self.start_time + self.fade_in_duration
        self.end_time = self.start_time + duration_ms
        self.alive = True

    def update(self, current_time):
        if not self.alive:
            return

        elapsed_time = current_time - self.start_time

        if elapsed_time < 0: # Seguridad por si el tiempo retrocede (?)
             elapsed_time = 0

        opacity = 0.0 # Valor entre 0.0 (transparente) y 1.0 (opaco)

        if current_time < self.fade_out_start_time:
            # Fade In
            if self.fade_in_duration > 0:
                 opacity = min(1.0, elapsed_time / self.fade_in_duration)
            else:
                 opacity = 1.0 # Si la duración de fade in es 0, opacidad completa
        elif current_time < self.end_time:
            # Fade Out
            fade_out_duration = self.duration - self.fade_in_duration
            if fade_out_duration > 0:
                time_into_fade_out = current_time - self.fade_out_start_time
                opacity = max(0.0, 1.0 - (time_into_fade_out / fade_out_duration))
            else:
                opacity = 0.0 # Si la duración de fade out es 0, transparente
        else:
            # Tiempo terminado
            opacity = 0.0
            self.alive = False

        # Pygame usa alpha de 0 (transparente) a 255 (opaco)
        alpha_value = int(opacity * 255)
        # Aplicar alpha a una copia para no modificar la imagen base del objeto
        # (Aunque `set_alpha` lo hace bien)
        self.image.set_alpha(alpha_value)


    def draw(self, surface):
        if self.alive:
            surface.blit(self.image, self.rect)

# --- Inicialización de Pygame ---
pygame.init()
pygame.mixer.init() # Inicializar el mezclador de sonido

# Intentar pantalla completa, si falla, usar tamaño fijo
try:
    screen = pygame.display.set_mode((0, 0), pygame.FULLSCREEN)
    WIDTH, HEIGHT = screen.get_size() # Obtener tamaño real de pantalla completa
except pygame.error as e:
    print(f"No se pudo establecer pantalla completa: {e}. Usando tamaño {WIDTH}x{HEIGHT}.")
    screen = pygame.display.set_mode((WIDTH, HEIGHT), pygame.RESIZABLE)

pygame.display.set_caption(TITLE_TEXT)
clock = pygame.time.Clock()

# --- Carga de Recursos ---
# Sonido
click_sound = None
try:
    sound_path = os.path.join(RESOURCES_DIR, "click.wav")
    if os.path.exists(sound_path):
        click_sound = pygame.mixer.Sound(sound_path)
    else:
        print(f"Advertencia: No se encontró el archivo de sonido: {sound_path}")
except pygame.error as e:
    print(f"Error al cargar el sonido '{sound_path}': {e}")

# Imágenes de Flores
flower_images = []
try:
    # Asume que tienes 11 flores nombradas Flor1.png a Flor11.png
    for i in range(1, 9):
        img_name = f"Flor{i}.png"
        img_path = os.path.join(RESOURCES_DIR, img_name)
        if os.path.exists(img_path):
            try:
                # Cargar con soporte para transparencia alfa
                image = pygame.image.load(img_path).convert_alpha()
                flower_images.append(image)
            except pygame.error as e:
                print(f"Error al cargar la imagen '{img_path}': {e}")
        else:
            print(f"Advertencia: No se encontró el archivo de imagen: {img_path}")

    if not flower_images:
         print("¡Error Crítico! No se cargó ninguna imagen de flor. Asegúrate de que estén en la carpeta 'Resources'.")
         # Podrías salir aquí si las flores son esenciales
         # pygame.quit()
         # sys.exit()

except Exception as e:
    print(f"Error general al buscar imágenes de flores: {e}")


# Fuente para el título
title_font = None
try:
    try:
        # Intenta cargar la fuente preferida
        title_font = pygame.font.SysFont(TITLE_FONT_NAME, TITLE_FONT_SIZE, bold=True)
    except:
        print(f"Fuente '{TITLE_FONT_NAME}' no encontrada. Intentando fallback '{TITLE_FONT_FALLBACK}'...")
        # Intenta cargar la fuente de fallback
        title_font = pygame.font.SysFont(TITLE_FONT_FALLBACK, TITLE_FONT_SIZE, bold=True)

except Exception as e: # Captura cualquier error relacionado con fuentes
    print(f"No se pudo cargar ninguna fuente especificada. Usando fuente por defecto. Error: {e}")
    title_font = pygame.font.Font(None, TITLE_FONT_SIZE + 10) # Fuente por defecto un poco más grande


# Renderizar el texto del título (y sombra) una vez si la fuente se cargó
title_surface = None
shadow_surface = None
title_rect = None
shadow_rect = None

if title_font:
    try:
        title_surface = title_font.render(TITLE_TEXT, True, TITLE_COLOR)
        shadow_surface = title_font.render(TITLE_TEXT, True, SHADOW_COLOR)

        # Calcular posiciones centradas
        title_rect = title_surface.get_rect(center=(WIDTH // 2, TITLE_FONT_SIZE)) # Centrado arriba
        shadow_rect = shadow_surface.get_rect(center=(WIDTH // 2 + 3, TITLE_FONT_SIZE + 3)) # Desplazado para sombra
    except pygame.error as e:
        print(f"Error al renderizar el texto del título: {e}")
        title_font = None # Desactivar el título si falla el renderizado


# --- Variables del Bucle Principal ---
active_flowers = [] # Lista para guardar las flores activas
running = True

# --- Bucle Principal del Programa ---
while running:
    current_time = pygame.time.get_ticks() # Obtener tiempo actual en ms

    # --- Manejo de Eventos ---
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_ESCAPE: # Salir con la tecla Esc
                running = False
        if event.type == pygame.MOUSEBUTTONDOWN:
            if event.button == 1: # Botón izquierdo del ratón
                if click_sound:
                    click_sound.play()
                if flower_images: # Solo crea flores si se cargaron imágenes
                    # Crear una nueva flor en la posición del clic
                    random_image = random.choice(flower_images)
                    new_flower = AnimatedFlower(random_image, event.pos, ANIMATION_DURATION_MS)
                    active_flowers.append(new_flower)

        # Manejar redimensionamiento de ventana (si no es pantalla completa)
        if event.type == pygame.VIDEORESIZE:
             WIDTH, HEIGHT = event.w, event.h
             screen = pygame.display.set_mode((WIDTH, HEIGHT), pygame.RESIZABLE)
             # Recalcular posición del título si la ventana cambia
             if title_font and title_rect and shadow_rect:
                 title_rect.center = (WIDTH // 2, TITLE_FONT_SIZE)
                 shadow_rect.center = (WIDTH // 2 + 3, TITLE_FONT_SIZE + 3)


    # --- Actualizar Estado ---
    # Actualiza todas las flores y elimina las que ya no están vivas
    # Es más seguro iterar sobre una copia si modificas la lista mientras iteras
    # O usar una comprensión de listas para filtrar
    active_flowers = [flower for flower in active_flowers if flower.alive]
    for flower in active_flowers:
        flower.update(current_time)


    # --- Dibujar ---
    # 1. Fondo
    screen.fill(BACKGROUND_COLOR)

    # 2. Título (si se pudo renderizar)
    if title_font and title_surface and shadow_surface:
        screen.blit(shadow_surface, shadow_rect) # Dibuja sombra primero
        screen.blit(title_surface, title_rect)   # Dibuja texto encima

    # 3. Flores
    for flower in active_flowers:
        flower.draw(screen)

    # --- Actualizar Pantalla ---
    pygame.display.flip() # Muestra todo lo dibujado

    # --- Controlar FPS ---
    clock.tick(FPS)

# --- Salida Limpia ---
pygame.quit()
sys.exit()