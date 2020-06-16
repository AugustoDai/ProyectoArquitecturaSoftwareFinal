package gui;

import java.util.ArrayList;
import java.util.Date;

import entidades.CDR;

public class FechaYCdrs {

	Date fecha;
	ArrayList<CDR> cdrs = null;
	
	public FechaYCdrs(Date fecha, ArrayList<CDR> cdrs) {
		this.fecha = fecha;
		this.cdrs = cdrs;
	}
	
	public Date getFecha() {
		return this.fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public ArrayList<CDR> getCDRs() {
		return this.cdrs;
	}
	public void setCDRs(ArrayList<CDR> cdrs) {
		this.cdrs = cdrs;
	}
	
}
