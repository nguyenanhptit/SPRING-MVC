package edu.java.spring.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;

import edu.java.spring.model.JavaClazz;

public class XSLTUtils {
	public static DOMSource clazzToDomSource(JavaClazz clazz) {
		
		try {
			JAXBContext jaxbContext	= JAXBContext.newInstance(JavaClazz.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(clazz, outputStream);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(outputStream.toByteArray()));
			return new DOMSource(document);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
