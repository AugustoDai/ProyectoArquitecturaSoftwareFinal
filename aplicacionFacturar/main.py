from flask import Flask
from flask import render_template,request, redirect, url_for
import json
import requests
import pandas as pd
from openpyxl import Workbook

#para guardar pdf
import pdfkit
from io import open
import os
from jinja2 import Environment, FileSystemLoader

env = Environment(loader=FileSystemLoader("templates"))
template = env.get_template("factura.html")

#hasta aqui


app = Flask(__name__)


json_data=""
json_Usuario_Facturado=""


@app.route("/", methods=["GET", "POST"])
def index():
    if request.method == 'POST':
        data_json=convertirAJson(request.form['telefono'],request.form['fecha'])
        
        global json_data
        json_data=data_json
    
    return render_template("index.html")


@app.route("/facturarUsuario")
def facturarUsuario():
    resp=""
    recibirFacturacionUsuario()
    print(json_Usuario_Facturado)
    if(json_Usuario_Facturado == None):
        resp="Datos Erroneos."
    elif(json_Usuario_Facturado != None):
        guardarExcel()
        guardarPDF() 
        resp="factura realizada en un archivo Excel y PDF"
    return resp


def convertirAJson(telefono,fecha):
    datosFactura = {
    "numero telefonico": telefono,
    "fecha": fecha
    }

    jsonDatosFactura = json.dumps(datosFactura)
    return jsonDatosFactura

def recibirFacturacionUsuario():
    direccion = 'http://localhost:4567/recibirJson'

    print(json_data)
    r = requests.post(url = direccion, data = json_data) 
  
    jsonContent = r.text 
    response_json = json.loads(jsonContent)
    global json_Usuario_Facturado
    json_Usuario_Facturado= response_json
    

def guardarExcel():
    nombre = []
    plan = []
    fecha = []
    numeroTelefonico = []
    ci = []
    montoAPagar = []

    nombre.append(json_Usuario_Facturado['nombre'])
    plan.append(json_Usuario_Facturado['plan'])
    fecha.append(json_Usuario_Facturado['fecha'])
    numeroTelefonico.append(json_Usuario_Facturado['numeroTelefonico'])
    ci.append(json_Usuario_Facturado['ci'])
    montoAPagar.append(json_Usuario_Facturado['montoAPagar'])


    data = {'nombre': nombre,
            'plan': plan,
            'fecha': fecha,
            'numeroTelefonico': numeroTelefonico,
            'ci': ci,
            'montoAPagar': montoAPagar}

    df = pd.DataFrame(data, columns = [ 'nombre', 'plan', 'fecha', 'numeroTelefonico', 'ci','montoAPagar'])

    df.to_excel('FacturaUsuario.xlsx', sheet_name='FacturaUsuario')

def guardarPDF():
    nombre = json_Usuario_Facturado['nombre']
    plan = json_Usuario_Facturado['plan']
    fecha = json_Usuario_Facturado['fecha']
    numeroTelefonico = json_Usuario_Facturado['numeroTelefonico']
    ci = json_Usuario_Facturado['ci']
    montoAPagar = json_Usuario_Facturado['montoAPagar']


    UsuarioFacturadoParaHTML = {
    'nombre' : nombre,
    'plan' : plan,
    'fecha' : fecha,
    'numeroTelefonico' : numeroTelefonico,
    'ci' : ci ,
    'montoAPagar': montoAPagar
    }

    print(UsuarioFacturadoParaHTML)
    html = template.render(UsuarioFacturadoParaHTML)

    f = open('nuevo_html.html', 'w')
    f.write(html)
    f.close()

    
    pdfkit.from_file('nuevo_html.html','facturaUsuario.pdf')
    os.remove('nuevo_html.html')

if __name__ == '__main__':
    app.run(debug=True, port=4000)
    