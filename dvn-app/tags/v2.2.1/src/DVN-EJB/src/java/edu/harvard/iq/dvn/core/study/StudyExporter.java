/*
 * Dataverse Network - A web application to distribute, share and analyze quantitative data.
 * Copyright (C) 2007
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 *  along with this program; if not, see http://www.gnu.org/licenses
 * or write to the Free Software Foundation,Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */
package edu.harvard.iq.dvn.core.study;

import java.io.IOException;
import java.io.OutputStream;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Ellen Kraffmiller
 */
public interface StudyExporter  extends java.io.Serializable{
  
    
  // Will the study be exported to XML output? 
  // (Currently used to determine the filename extension when creating an OutputStream)   
  boolean isXmlFormat(); 
  
  void exportStudy(Study study, OutputStream out) throws IOException;
}
