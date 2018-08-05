package com.venkat.es.esdemo.service;

import com.venkat.es.esdemo.model.Employee;
import org.springframework.stereotype.Service;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class StaxReaderService {
    //https://www.geeksforgeeks.org/stax-xml-parser-java/

    public List<Employee> parseXML(String fileName){

        Employee emp = null;
        List<Employee> empList = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        String tagContent = null;
        try{
            XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));
            while(reader.hasNext()){
                int event = reader.next();

                switch (event){

                    case XMLEvent.START_DOCUMENT:
                        System.out.println("Start Document...");
                        break;
                    case XMLStreamConstants.START_ELEMENT:

                        String qName = reader.getLocalName();

                        if(qName.equalsIgnoreCase("employees")){
                            empList = new ArrayList<>();
                        }

                        if(qName.equalsIgnoreCase("employee")) {
                            emp = new Employee();
                            emp.setId(Integer.parseInt(reader.getAttributeValue(0)));
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        tagContent = reader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch (reader.getLocalName()){
                            case "employee":
                                empList.add(emp);
                                break;
                            case "firstName":
                                emp.setFirstName(tagContent);
                                break;
                            case "lastName":
                                emp.setLastName(tagContent);
                                break;
                            case "age":
                                emp.setAge(Integer.parseInt(tagContent));
                                break;
                            case "gender":
                                emp.setGender(tagContent);
                                break;
                        }
                        break;
                    case XMLEvent.END_DOCUMENT:
                        System.out.println("End Document..");
                        break;
                }
            }

        }catch (FileNotFoundException | XMLStreamException e){
            e.printStackTrace();
        }
        return empList;
    }
    public List<Employee> parseXML2(String fileName){

        List<Employee> empList = new ArrayList<>();
        Employee emp = null;

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        boolean firstName = false;
        boolean lastName = false;
        boolean age = false;
        boolean gender = false;
        try{
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while(xmlEventReader.hasNext()){
                XMLEvent event = xmlEventReader.nextEvent();

                switch (event.getEventType()){

                    case XMLEvent.START_DOCUMENT:
                        System.out.println("Start Document...");
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        emp = new Employee();
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if(qName.equalsIgnoreCase("employee")){
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String id = attributes.next().getValue();
                            emp.setId(Integer.parseInt(id));
                        }else if(qName.equalsIgnoreCase("firstName")){
                            firstName = true;
                        }else if(qName.equalsIgnoreCase("lastName")){
                            lastName = true;
                        }else if(qName.equalsIgnoreCase("age")){
                            age = true;
                        }else if(qName.equalsIgnoreCase("gender")){
                            gender = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if(firstName){
                            emp.setFirstName(characters.getData());
                        }
                        if(lastName){
                            emp.setFirstName(characters.getData());
                        }
                        if(age){
                            emp.setAge(Integer.parseInt(characters.getData()));
                        }
                        if(gender){
                            emp.setGender(characters.getData());
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        //if Employee end element is reached, add employee object to list

                        EndElement endElement = event.asEndElement();
                        if(endElement.getName().getLocalPart().equalsIgnoreCase("employee")){
                            empList.add(emp);
                        }

                        break;
                    case XMLEvent.END_DOCUMENT:
                        System.out.println("End Document..");
                        break;
                }
            }

        }catch (FileNotFoundException | XMLStreamException e){
            e.printStackTrace();
        }
        return empList;
    }

}
