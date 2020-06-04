package com.med.hospital.dto;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;																																																																																																																																																																																																																							


public class HousePrac {
	
	    // tag값의 정보를 가져오는 메소드
		private static String getTagValue(String tag, Element eElement) {
		    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		    Node nValue = (Node) nlList.item(0);
		    if(nValue == null) 
		        return null;
		    return nValue.getNodeValue();
		}

		public static void main(String[] args) {
			
			try{
				while(true){
					// parsing할 url 지정(API 키 포함해서)
					String url = "http://openapi.seoul.go.kr:8088/65554673656d697338326e4f5a4172/xml/octastatapi10995/1/100/";
					
					DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
					Document doc = dBuilder.parse(url);
					
					// root tag 
					doc.getDocumentElement().normalize();
					System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					
					// 파싱할 tag
					NodeList nList = doc.getElementsByTagName("row");
					//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
					
					for(int temp = 0; temp < nList.getLength(); temp++){
						Node nNode = nList.item(temp);
						if(nNode.getNodeType() == Node.ELEMENT_NODE){
							
							Element eElement = (Element) nNode;
							System.out.println("######################");
							//System.out.println(eElement.getTextContent());
							System.out.println("지역  : " + getTagValue("GUBUN", eElement));
							System.out.println("성별  : " + getTagValue("SEONGBYEOL", eElement));
							System.out.println(Integer.parseInt(getTagValue("HAPGYE", eElement)));//합계
							System.out.println(Integer.parseInt(getTagValue("N_20SEMIMAN", eElement)));//20세미만
							System.out.println(Integer.parseInt(getTagValue("N_2024SE", eElement))+ Integer.parseInt(getTagValue("N_2529SE", eElement)));//20대
							System.out.println(Integer.parseInt(getTagValue("N_3034SE", eElement))+ Integer.parseInt(getTagValue("N_3539SE", eElement)));//30대
							System.out.println(Integer.parseInt(getTagValue("N_4044SE", eElement))+ Integer.parseInt(getTagValue("N_4549SE", eElement)));//40대
							System.out.println(Integer.parseInt(getTagValue("N_5054SE", eElement))+ Integer.parseInt(getTagValue("N_5559SE", eElement)));//50대
							System.out.println(Integer.parseInt(getTagValue("N_6064SE", eElement))+ Integer.parseInt(getTagValue("N_6569SE", eElement)));//60대
							System.out.println(Integer.parseInt(getTagValue("N_7074SE", eElement))+ Integer.parseInt(getTagValue("N_7579SE", eElement)));//70대
							System.out.println(Integer.parseInt(getTagValue("N_8084SE", eElement))+ Integer.parseInt(getTagValue("N_85SEISANG", eElement)));//80세이상
							
						}	// for end
					}	// if end
					
					
				}	// while end
				
			} catch (Exception e){	
				e.printStackTrace();
			}	// try~catch end
		}	// main end
	}	// class end




