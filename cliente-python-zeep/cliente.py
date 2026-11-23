from zeep import Client
from zeep.exceptions import Fault

wsdl_url = "http://localhost:8080/conversoap?wsdl"
client = Client(wsdl=wsdl_url)

temp_c = 25.0
res_f = client.service.celsiusFahrenheit(temp_c)
print(f"{temp_c}°C em Fahrenheit: {res_f}°F")

temp_f = 77.0
res_c = client.service.fahrenheitCelsius(temp_f)
print(f"{temp_f}°F em Celsius: {res_c}°C")


try:
    temp_invalida = -300.0
    client.service.celsiusFahrenheit(temp_invalida)
except Fault as erro:
    print(f"Erro 'Protex(SOAP)' capturado: {erro.message}")