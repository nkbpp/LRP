package ru.pfr.xmlparsertemp;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Xml10kRequestParser {

    public Xml10kParentModel parse(File file) throws FileNotFoundException, BadFormatException {
        return parse(new FileInputStream(file));
    }

    public Xml10kParentModel parse(InputStream fis) throws BadFormatException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (Exception E) {
            throw new BadFormatException();
        }
        Document doc;
        try {
            doc = dBuilder.parse(fis);
        } catch (SAXException | IOException ex) {
            throw new BadFormatException();
        }
        Xml10kParentModel xml10kParentModel = new Xml10kParentModel();
        doc.getDocumentElement().normalize();
        NodeList elementsByTagName = doc.getElementsByTagName("ЭДПФР");
        if (elementsByTagName == null || elementsByTagName.getLength() == 0) {
            elementsByTagName = doc.getElementsByTagName("МЗПЕВ:ЭДПФР");
        }
        if (elementsByTagName == null || elementsByTagName.getLength() == 0) {
            elementsByTagName = doc.getElementsByTagName("НЗПЕВ:ЭДПФР");
        }
        NodeList root = elementsByTagName.item(0).getChildNodes();
        NodeList mainInformation = findNode(root, "НЗПЕВ", "МЗПЕВ", "МЗПЕВ:МЗПЕВ", "НЗПЕВ:НЗПЕВ");
        NodeList parentInformation = findNode(mainInformation, "Заявитель", "МЗПЕВ:Заявитель", "НЗПЕВ:Заявитель");
        NodeList parentFioBlock = findNode(parentInformation, "УТ2:ФИО", "УТ:ФИО");
        xml10kParentModel.setSurname(findValue(parentFioBlock, "УТ2:Фамилия", "УТ:Фамилия"));
        xml10kParentModel.setName(findValue(parentFioBlock, "УТ2:Имя", "УТ:Имя"));
        try {
            xml10kParentModel.setMiddlename(findValue(parentFioBlock, "УТ2:Отчество", "УТ:Отчество"));
        } catch (Exception E) {
            xml10kParentModel.setMiddlename("");
        }

        xml10kParentModel.setSnils(findValue(parentInformation, "УТ2:СНИЛС", "УТ:СНИЛС"));
        NodeList udDocumentBlock = findNode(parentInformation, "УТ2:УдостоверяющийДокумент", "УТ:УдостоверяющийДокумент");
        xml10kParentModel.setDocumentType(findValue(udDocumentBlock, "УТ2:Тип", "УТ:Тип"));
        xml10kParentModel.setDocumentSeria(findValue(udDocumentBlock, "УТ2:Серия", "УТ:Серия"));
        xml10kParentModel.setDocumentNumber(findValue(udDocumentBlock, "УТ2:Номер", "УТ:Номер"));
        xml10kParentModel.setDocumentDate(findValue(udDocumentBlock, "УТ2:ДатаВыдачи", "УТ:ДатаВыдачи"));
        xml10kParentModel.setDocumentWhom(findValue(udDocumentBlock, "УТ2:КемВыдан", "УТ:КемВыдан"));
        NodeList childrenNodeList = findNode(mainInformation, "Дети", "МЗПЕВ:Дети", "НЗПЕВ:Дети");
        List<Xml10kModel> children = IntStream.range(0, childrenNodeList.getLength())
                .mapToObj(nodeNumber -> {
                    Node item = childrenNodeList.item(nodeNumber);
                    if (item.getNodeName().equals("Ребенок") || item.getNodeName().equals("МЗПЕВ:Ребенок") || item.getNodeName().equals("НЗПЕВ:Ребенок")) {
                        try {
                            Xml10kModel child = new Xml10kModel();
                            NodeList childInformation = item.getChildNodes();
                            if (isNodeExist(childInformation, "АнкетныеДанные") || isNodeExist(childInformation, "МЗПЕВ:АнкетныеДанные")) {
                                childInformation = findNode(childInformation, "АнкетныеДанные", "МЗПЕВ:АнкетныеДанные");
                            }
                            NodeList childFioBlock = findNode(childInformation, "УТ2:ФИО", "УТ:ФИО");
                            child.setSurname(findValue(childFioBlock, "УТ2:Фамилия", "УТ:Фамилия"));
                            child.setName(findValue(childFioBlock, "УТ2:Имя", "УТ:Имя"));
                            try {
                                child.setMiddlename(findValue(childFioBlock, "УТ2:Отчество", "УТ:Отчество"));
                            } catch (Exception E) {
                                child.setMiddlename("");
                            }
                            child.setSnils(findValue(childInformation, "УТ2:СНИЛС", "УТ:СНИЛС"));
                            return child;
                        } catch (BadFormatException ex) {
                            return null;
                        }
                    }
                    return null;
                }).filter(child -> child != null).collect(Collectors.toList());
        if (children == null || children.isEmpty()) {
            throw new BadFormatException();
        }
        xml10kParentModel.setChildren(children);
        return xml10kParentModel;
    }

    private Boolean isNodeExist(NodeList nodeList, String nodeName) {
        return IntStream.range(0, nodeList.getLength()).mapToObj(nodeNumber -> {
            if (nodeList.item(nodeNumber).getNodeName().equals(nodeName)) {
                return true;
            }
            return null;
        }).filter(nodeValue -> nodeValue != null).findFirst().orElse(false);
    }

    private String findValue(NodeList nodeList, String... nodeName) throws BadFormatException {
        return IntStream.range(0, nodeList.getLength()).mapToObj(nodeNumber -> {
            for (String node : nodeName) {
                if (nodeList.item(nodeNumber).getNodeName().equals(node)) {
                    return nodeList.item(nodeNumber).getTextContent();
                }

            }
            return null;
        }).filter(nodeValue -> nodeValue != null).findFirst().orElseThrow(() -> new BadFormatException());
    }

    private NodeList findNode(NodeList nodeList, String... nodeName) throws BadFormatException {
        return IntStream.range(0, nodeList.getLength()).mapToObj(nodeNumber -> {
            Node item = nodeList.item(nodeNumber);
            for (String node : nodeName) {
                if (item.getNodeName().equals(node)) {
                    return item.getChildNodes();
                }
            }
            return null;
        }).filter(node -> node != null).findFirst().orElseThrow(() -> new BadFormatException());
    }

}
