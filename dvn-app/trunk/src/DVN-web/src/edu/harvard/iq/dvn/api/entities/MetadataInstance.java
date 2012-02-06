package edu.harvard.iq.dvn.api.entities;

import java.io.File; 
import java.io.UnsupportedEncodingException; 
import javax.ejb.EJB;

import edu.harvard.iq.dvn.core.study.StudyExporterFactoryLocal;
import edu.harvard.iq.dvn.core.util.FileUtil;

/**
 *
 * @author leonidandreev
 */
public class MetadataInstance {
    @EJB StudyExporterFactoryLocal studyExporterFactory;

    private String globalStudyId; 
    private Long studyId;

    private String formatType; 
    
    private String parameterIncludeSection; 
    private String parameterExcludeSection; 

    private Boolean isAvailable = false; 
    private Boolean isCached = false; 
    private Boolean isByteArray = false; 
    
    private File cachedMetadataFile; 
    private byte[] generatedMetadataBytes; 
    
    
    
    public MetadataInstance(String globalId) {
        this(globalId, "ddi", null, null);
    }
    
    public MetadataInstance(String globalId, String format, String partialExclude, String partialInclude) {
        globalStudyId = globalId;
        formatType = format; 
        parameterIncludeSection = partialInclude; 
        parameterExcludeSection = partialExclude; 
        
        lookupMetadataFile(); 
        
    }
    
    public MetadataInstance(Long localId) {
        this(localId, "ddi", null, null);
    }

    public MetadataInstance(Long localId, String format, String partialExclude, String partialInclude) {
        studyId = localId;
        formatType = format;
        parameterIncludeSection = partialInclude; 
        parameterExcludeSection = partialExclude; 
       
        lookupMetadataFile(); 
    }
     
    public Long getStudyId() {
        return studyId;
    }
    
    public void setStudyId(Long localId) {
        studyId = localId; 
    }
    
    public String getGlobalStudtId() {
        return globalStudyId; 
    }
    
    public void setGlobalStudyId(String globalId) {
        globalStudyId = globalId; 
    }
    
    public Boolean isAvailable() {
        return isAvailable; 
    }
    
    public void setAvailability(Boolean availability) {
        isAvailable = availability; 
    }
    
    public Boolean isCached() {
        return isCached; 
    }
    
    public Boolean isByteArray() {
        return isByteArray; 
    }
    
    public File getCachedMetadataFile () {
        return cachedMetadataFile; 
    }
    
    public byte[] getByteArray() {
        return generatedMetadataBytes; 
    }
    
    private void lookupMetadataFile() {
        String cachedFileName = null; 
        
        if (this.formatType == null) {
            this.formatType = "ddi";
        }
        
        if (globalStudyId != null) {
            int index1 = globalStudyId.indexOf(':');
            int index2 = globalStudyId.indexOf('/');
            String idAuthority = globalStudyId.substring(index1 + 1, index2);
            String idToken = globalStudyId.substring(index2 + 1).toUpperCase();

            File studyFileDir = FileUtil.getStudyFileDir(idAuthority, idToken);

        
            if (studyFileDir != null && studyFileDir.exists()) {
                cachedFileName = studyFileDir.getAbsolutePath() + File.separator + "export_" + formatType +".xml";
            }
            
            File lookupFile = new File (cachedFileName);
            
            if (lookupFile.exists()) {
                this.isAvailable = true;
                this.isCached = true; 
                this.cachedMetadataFile = lookupFile; 
            }
        }
        
    }
    
    @Override
    public String toString() {
        if (generatedMetadataBytes != null) {
            try {
                return new String(generatedMetadataBytes, "UTF-8"); 
            } catch (UnsupportedEncodingException ex) {
                return null; 
            }
        }
        
        return null; 
    }
}