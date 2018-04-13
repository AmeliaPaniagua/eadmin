package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import es.fpdual.eadmin.eadmin.mapper.DocumentoMapper;
import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;

@Repository
public class RepositorioDocumentoImpl implements RepositorioDocumento {

	private DocumentoMapper mapper;
	
	@Autowired
	public RepositorioDocumentoImpl (DocumentoMapper mapper) {
		this.mapper = mapper;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(RepositorioDocumentoImpl.class);

//	private final List<Documento> documentos = new ArrayList<>();
//
//	// getter
//	public List<Documento> getDocumentos() {
//		return documentos;
//	}

	@Override
	public void altaDocumento(Documento documento) {

		logger.info("Entrando en altaDocumento");
		

		this.mapper.insertarDocumento(documento);
		

		escribirAltaDocFichero(documento);

		this.exportExcel("Alta", documento, "Documentos.xlsx");

		logger.info(documento.toString() + " creado correctamente");

	}

	@Override
	public void modificarDocumento(Documento documento) {

		logger.info("Entrando en modificarDocumento");

		this.mapper.modificarDocumento(documento);
		
		//documentos.set(documentos.indexOf(documento), documento);

		escribirModificacionDocFichero(documento);

		this.exportExcel("Modificar", documento, "Documentos.xlsx");

		logger.info("Saliendo de modificarDocumento");
	}

	@Override
	public void eliminarDocumento(Integer codigo) {

		// Documento documentoEncontrado = null;

		// for (int i = 0; i < documentos.size(); i++) {
		// if (documentos.get(i).getCodigo().equals(codigo)) {
		// documentoEncontrado = documentos.get(i);
		// break;
		// }
		// }

		// Comentamos el for porque según Java 8 es mejor buscar en un array utilizando
		// stream

		logger.info("Entrando en eliminarDocumento()");

		Optional<Documento> documentoEncontrado = documentos.stream().filter(d -> tieneIgualCodigo(d, codigo))
				.findFirst();
		if (documentoEncontrado.isPresent()) { // comprueba si el objeto es nulo o no
			escribirEliminarDocFichero(documentoEncontrado);

			this.exportExcel("Eliminar", documentoEncontrado.get(), "Documentos.xlsx");

			documentos.remove(documentoEncontrado.get());
		}

		logger.info("Saliendo de eliminarDocumento()");

	}

	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {

		logger.info("Entrando en obtenerDocumentoPorCodigo");

		Optional<Documento> documentoEncontrado = documentos.stream().filter(d -> tieneIgualCodigo(d, codigo))
				.findFirst();
		if (documentoEncontrado.isPresent()) {
			logger.info("Saliendo en obtenerDocumentoPorCodigo");
			return documentoEncontrado.get();
		}
		logger.info("Saliendo en obtenerDocumentoPorCodigo");
		return null;

		// Esto es otra opción de poner lo mismo que de arriba pero en una sola línea y
		// sería lo mismo
		// return documentos.stream().filter(d ->tieneIgualCodigo(d,
		// codigo)).findFirst().orElseNull;

	}

	@Override
	public List<Documento> obtenerTodosLosDocumentos() {

		logger.info("Entrando en obtenerTodosLosDocumentos");

		for (Documento doc : documentos) {
			logger.info("**************");
			logger.info("Documento:" + doc.getCodigo());
			logger.info("Nombre:" + doc.getNombre());
			logger.info("Fecha Creación: " + doc.getFechaCreacion());
			logger.info("**************");
		}
		logger.info("Saliendo de obtenerTodosLosDocumentos");

		return this.documentos;
	}

	protected boolean tieneIgualCodigo(Documento documento, Integer codigo) {

		// devuelve un booleano de si es igual o no
		return documento.getCodigo().equals(codigo);

	}

	public void crearFicheroDocumentos() {

		FileWriter file = null;
		PrintWriter pw = null;

		logger.info("Iniciando...");
		String nombreFichero = "ficherodocumentos.txt";

		try {

			file = new FileWriter(nombreFichero, true);
			pw = new PrintWriter(file);

			for (Documento doc : documentos) {

				pw.println("Documento:" + doc.getCodigo());
				pw.println("Nombre:" + doc.getNombre());
				pw.println("Fecha Creación: " + doc.getFechaCreacion());
			}

			pw.close();

			logger.info("Escribiendo en el fichero");

		} catch (IOException e) {
			System.out.println("Ha habido un error");
			e.printStackTrace();
			pw.close();
		}

		logger.info("Escritura terminada");

	}

	public void escribirAltaDocFichero(Documento doc) {

		FileWriter file = null;
		PrintWriter pw = null;

		logger.info("Iniciando...");
		String nombreFichero = "Alta.txt";

		try {

			file = new FileWriter(nombreFichero, true);
			pw = new PrintWriter(file);

			pw.println("Documento:" + doc.getCodigo());
			pw.println("Nombre:" + doc.getNombre());
			pw.println("Fecha Creación: " + doc.getFechaCreacion());

			pw.close();

			logger.info("Escribiendo en el fichero el alta");

		} catch (IOException e) {
			System.out.println("Ha habido un error");
			e.printStackTrace();
			pw.close();
		}

		logger.info("Escritura terminada");

	}

	public void escribirModificacionDocFichero(Documento doc) {

		FileWriter file = null;
		PrintWriter pw = null;

		logger.info("Iniciando...");
		String nombreFichero = "Modificar.txt";

		try {

			file = new FileWriter(nombreFichero, true);
			pw = new PrintWriter(file);

			pw.println("Documento:" + doc.getCodigo());
			pw.println("Nombre:" + doc.getNombre());
			pw.println("Fecha Creación: " + doc.getFechaCreacion());

			pw.close();

			logger.info("Escribiendo en el fichero el documento modificado");

		} catch (IOException e) {
			System.out.println("Ha habido un error");
			e.printStackTrace();
			pw.close();
		}

		logger.info("Escritura terminada");

	}

	public void escribirEliminarDocFichero(Optional<Documento> doc) {

		FileWriter file = null;
		PrintWriter pw = null;

		logger.info("Iniciando...");
		String nombreFichero = "Eliminar.txt";

		try {

			file = new FileWriter(nombreFichero, true);
			pw = new PrintWriter(file);

			// pw.println(documentos);
			pw.println("Documento:" + doc.get().getCodigo());
			pw.println("Nombre:" + doc.get().getNombre());
			pw.println("Fecha Creación: " + doc.get().getFechaCreacion());

			pw.close();

			logger.info("Escribiendo en el fichero la eliminación del documento");

		} catch (IOException e) {
			System.out.println("Ha habido un error");
			e.printStackTrace();
			pw.close();
		}

		logger.info("Escritura terminada");

	}

	// No sirve

	public static boolean crearExcelDocumentos(String nombreHoja, Documento documento, String fileName,
			Integer numHoja) {
		// Datos a escribir en map(Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();

		Integer numeroLineas = 0;
		File archivoExcel = new File(fileName);

		if (!archivoExcel.exists()) {
			// Cabecera

			data.put("0", new Object[] { "Código", "Nombre", "Fecha", "Publico" });
			numeroLineas++;
		} else {
			// el 4 es el párametro que indica el nº de columnas
			ArrayList<String[]> datosExcel = importExcel(fileName, 4, numHoja);// numHoja=0
			ListIterator<String[]> it = datosExcel.listIterator();

			while (it.hasNext()) {
				numeroLineas++;
				String[] datos = it.next();
				data.put(numeroLineas.toString(), datos);
			}

		}

		numeroLineas++;

		data.put(numeroLineas.toString(),
				new Object[] { documento.getCodigo().toString(), documento.getNombre().toString(),
						documento.getFechaCreacion().toString(), documento.getPublico().toString() });

		// if(correcto){
		//
		// ArrayList<String[]> datosExcel = importExcel("Excel.xlsx",4);
		// ListIterator<String[]> it = datosExcel.listIterator();
		//
		// while (it.hasNext()) {
		// String[] datos = it.next();
		// String personaInfo = "";
		// for (String fila : datos) {
		// personaInfo += fila + " ";
		// }
		// System.out.println(personaInfo+"\n");
		// }
		// }

		// String excelFilePath = "JavaBooks.xls";
		//
		// try {
		// FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		// Workbook workbook = WorkbookFactory.create(inputStream);
		//
		// Sheet sheet = workbook.getSheetAt(0);
		//
		// Object[][] bookData = {
		// {"The Passionate Programmer", "Chad Fowler", 16},
		// {"Software Craftmanship", "Pete McBreen", 26},
		// {"The Art of Agile Development", "James Shore", 32},
		// {"Continuous Delivery", "Jez Humble", 41},
		// };
		//
		// int rowCount = sheet.getLastRowNum();
		//
		// for (Object[] aBook : bookData) {
		// Row row = sheet.createRow(++rowCount);
		//
		// int columnCount = 0;
		//
		// Cell cell = row.createCell(columnCount);
		// cell.setCellValue(rowCount);
		//
		// for (Object field : aBook) {
		// cell = row.createCell(++columnCount);
		// if (field instanceof String) {
		// cell.setCellValue((String) field);
		// } else if (field instanceof Integer) {
		// cell.setCellValue((Integer) field);
		// }
		// }
		//
		// }
		//
		// inputStream.close();
		//
		// FileOutputStream outputStream = new FileOutputStream("JavaBooks.xls");
		// workbook.write(outputStream);
		// workbook.close();
		// outputStream.close();
		//
		// } catch (IOException | EncryptedDocumentException
		// | InvalidFormatException ex) {
		// ex.printStackTrace();
		// }

		//
		// HSSFWorkbook workbook = null;
		// File file = new File(context.getExternalFilesDir(null), "Alta.xls");
		// FileOutputStream fileOut = new FileOutputStream(file);
		// if (file.exists()) {
		// try {
		// workbook = (HSSFWorkbook)WorkbookFactory.create(file);
		// } catch (InvalidFormatException e) {
		// e.printStackTrace();
		// }
		// HSSFSheet sheet = workbook.createSheet("ModificarDocumentos");
		// } else{
		// workbook = new HSSFWorkbook();
		// HSSFSheet sheet = workbook.createSheet("AltaDocumentos");
		// }
		// workbook.write(fileOut);
		// fileOut.close();
		//

		// Creamos el libro de trabajo
		XSSFWorkbook libro = new XSSFWorkbook();

		// Creacion de Hoja
		XSSFSheet hoja = libro.createSheet(nombreHoja);

		// Iteramos el map e insertamos los datos
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			// cramos la fila
			Row row = hoja.createRow(rownum++);
			// obtenemos los datos de la fila
			Object[] objArr = data.get(key);
			int cellnum = 0;
			// iteramos cada dato de la fila
			for (Object obj : objArr) {
				// Creamos la celda
				Cell cell = row.createCell(cellnum++);
				// Setteamos el valor con el tipo de dato correspondiente
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		try {
			// Escribimos en fichero
			FileOutputStream out = new FileOutputStream(new File(fileName));
			libro.write(out);
			// cerramos el fichero y el libro
			out.close();
			libro.close();
			System.out.println("Excel exportado correctamente\n");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static void exportExcel(String nombreHoja, Documento documento, String fileName) {

		try {

			FileInputStream inputStream = new FileInputStream(new File(fileName));

			Workbook workbook = WorkbookFactory.create(inputStream);

			int numeroHoja;

			if (nombreHoja.equals("Documentos")) {

				numeroHoja = 0;

			} else if (nombreHoja.equals("Alta")) {

				numeroHoja = 1;

			} else if (nombreHoja.equals("Modificar")) {

				numeroHoja = 2;

			} else {

				numeroHoja = 3;

			}

			Sheet sheet = workbook.getSheetAt(numeroHoja);

			Object[] bookData = { documento.getCodigo(), documento.getNombre(), documento.getFechaCreacion().toString(),

					documento.getEstado().toString() };

			int rowCount = sheet.getLastRowNum();

			Row row = sheet.createRow(++rowCount);

			int columnCount = 0;

			Cell cell = row.createCell(columnCount);

			cell.setCellValue(rowCount);

			for (Object field : bookData) {

				cell = row.createCell(++columnCount);

				if (field instanceof String) {

					cell.setCellValue((String) field);

				} else if (field instanceof Integer) {

					cell.setCellValue((Integer) field);

				}

			}

			inputStream.close();

			FileOutputStream outputStream = new FileOutputStream(fileName);

			workbook.write(outputStream);

			workbook.close();

			outputStream.close();

		} catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {

			ex.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<String[]> importExcel(String fileName, int numColums, Integer numHoja) {

		// ArrayList donde guardaremos todos los datos del excel
		ArrayList<String[]> data = new ArrayList<>();

		try {
			// Acceso al fichero xlsx
			FileInputStream file = new FileInputStream(new File(fileName));

			// Creamos la referencia al libro del directorio dado
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Obtenemos la primera hoja
			// XSSFSheet sheet = workbook.getSheetAt(0);

			XSSFSheet sheet = workbook.getSheetAt(numHoja);

			// Iterador de filas
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// Iterador de celdas
				Iterator<Cell> cellIterator = row.cellIterator();
				// contador para el array donde guardamos los datos de cada fila
				int contador = 0;
				// Array para guardar los datos de cada fila
				// y añadirlo al ArrayList
				String[] fila = new String[numColums];
				// iteramos las celdas de la fila
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					// Guardamos los datos de la celda segun su tipo
					switch (cell.getCellType()) {
					// si es numerico
					case Cell.CELL_TYPE_NUMERIC:
						fila[contador] = (int) cell.getNumericCellValue() + "";
						break;
					// si es cadena de texto
					case Cell.CELL_TYPE_STRING:
						fila[contador] = cell.getStringCellValue() + "";
						break;
					}
					// Si hemos terminado con la ultima celda de la fila
					if ((contador + 1) % numColums == 0) {
						// Añadimos la fila al ArrayList con todos los datos
						data.add(fila);
					}
					// Incrementamos el contador
					// con cada fila terminada al redeclarar arriba el contador,
					// no obtenemos excepciones de ArrayIndexOfBounds
					contador++;
				}
			}
			// Cerramos el fichero y workbook
			file.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Excel importado correctamente\n");

		return data;
	}

}
// http://www.codejava.net/coding/java-example-to-update-existing-excel-files-using-apache-poi
