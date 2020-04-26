package com.uca.capas.tarea3.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	
	@RequestMapping("/ingresar")
	public String index() {
		return "ingresar";
	}
	
	@RequestMapping("/resultado")
	public ModelAndView resultado(HttpServletRequest request) {
		String usuario = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String fechaNac = request.getParameter("fechaNac").toString();
		String lugarNac = request.getParameter("lugarNac");
		String colegio = request.getParameter("colegio");
		String telFijo = request.getParameter("telFijo").toString();
		String telMovil = request.getParameter("telMovil").toString();
		
		ModelAndView mav = new ModelAndView();
		List<String> errores = new ArrayList<>();
		List<String> errorShow  = new ArrayList<>();
		errores.add("El nombre no puede exceder los 25 caracteres"); 
		errores.add("El nombre no puede quedar vacío");		
		
		errores.add("El apellido no puede exceder los 25 caracteres");
		errores.add("El apellido no puede quedar vacío");
		
		errores.add("No puede ser menor al 1 de enero de 2003");
		
		errores.add("El lugar de nacimiento no puede exceder los 25 caracteres");
		errores.add("El lugar de nacimiento no puede quedar vacío");
		
		errores.add("El Centro de estudio no puede exceder los 100 caracteres");
		errores.add("El Centro de estudio no puede quedar vacío");
		
		errores.add("El tel fijo tiene que tener 8 números exactamente");
		errores.add("El tel movil tiene que tener 8 números exactamente");
		
		revisaCondiciones(usuario, 25, errorShow, errores, 0, 1);
		revisaCondiciones(apellido, 25, errorShow, errores, 2, 3);
		revisaFecha(fechaNac, errorShow, errores, 4);
		revisaCondiciones(lugarNac, 25, errorShow, errores, 5, 6);
		revisaCondiciones(colegio, 100, errorShow, errores, 7, 8);
		revisaCel(telFijo, errorShow, errores,9);
		revisaCel(telMovil, errorShow, errores,10);
		
		if(errorShow.size() == 0) {
			mav.setViewName("resultadoB");
			return mav;
		}
		else {
			mav.addObject("errores", errorShow);
			mav.setViewName("resultadoM");
			return mav;
		}
		
		
	}
	
	void revisaCondiciones(String cadena, int minCarac, List<String> errorShow, List<String> errores, int indexE1,int indexE2) {
		if(cadena.isEmpty()) errorShow.add(errores.get(indexE2));
		else {
			cadena = cadena.replaceAll("\\s", "");
			if(cadena.length() > minCarac) errorShow.add(errores.get(indexE1));
		}
	}
	void revisaFecha(String entrada, List<String> errorShow, List<String> errores, int indexE1) {
		int annioC = 2003;
		
		String annio = entrada.substring(0, 4);
		int annioInt = Integer.parseInt(annio);
		
		if(annioInt < annioC) errorShow.add(errores.get(indexE1));
	}
	
	void revisaCel(String numero, List<String> errorShow, List<String> errores, int indexE1) {
		if(numero.length() < 8 || numero.length() > 8) errorShow.add(errores.get(indexE1));
	}
}

