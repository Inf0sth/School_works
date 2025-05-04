from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from smtplib import SMTP

mensaje = MIMEMultipart("plain")
mensaje["From"] = "pruebashakingeticoamk@gmail.com"
mensaje["To"] = "evazquez@amerike.edu.mx"
mensaje["Subjet"] = "Correo del log de Lister"

adjunto = MIMEBase("application", "octect-stream")
adjunto.set_payload(open('log.txt', 'rb').read())
adjunto.add_header("content-Disposition", 'attacment; filename="log.txt"')
mensaje.attach(adjunto)

server = SMTP("smtp.gmail.com:587")
server.startls()
server.login("pruebashakingeticoamk@gmail.com", "GGXkB#fMq$iG4#a#")
server.sendmail("pruebashakingeticoamk@gmail.com", "evazquez@amerike.edu.mx", mensaje.as_string().encode('utf-8'))

server.quit()