/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package xml.tests;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DOM4JPerfTest {

    public static void main(String[] args) throws Exception {

        DOM4JPerfTest test = new DOM4JPerfTest();
        byte[] encoded = Files.readAllBytes(Paths.get("/home/supun/Desktop/xml-samples/13kb.xml"));
        String json = new String(encoded, "UTF-8");

        Element nthElement = null;
        long startTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 1000000; i++) {

            // Change this accordingly
            nthElement = test.printLastElement(json);
            
            count++;
            if ((i % 100000) == 0) {
                long endTime = System.currentTimeMillis();
                double duration = (endTime - startTime);
                System.out.println(count + "/" + duration);
                System.out.println("Single Exection Time: " + duration / count);
                System.out.println("Traversal Rate: " + count / duration * 1000);
                startTime = System.currentTimeMillis();
                count = 0;
            }
        }
        System.out.println(nthElement.asXML());
    }


    /**
     * Read the first element of a array
     */
    private Element printFirstElement(String json) throws Exception {
        Reader stringReader = new StringReader(json);
        SAXReader reader = new SAXReader();
        Document root = reader.read(stringReader);
        return root.getRootElement().element("person");
    }

    /**
     * Read last element of a array
     * 
     * @return
     */
    private Element printLastElement(String json) throws Exception {
        Reader stringReader = new StringReader(json);
        SAXReader reader = new SAXReader();
        Document root = reader.read(stringReader);
        List<Element> children = root.getRootElement().elements();
        return children.get(children.size() -1 );
    }

    /**
     * Read last element of a array
     * 
     * @return
     */
    private Element printNthElement(String json, int n) throws IOException {
        return null;
    }
}
