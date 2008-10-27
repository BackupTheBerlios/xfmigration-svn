package xfmigration.web.util;

import xfmigration.domain.Forge;

/**
 * A class that represents backing bean of {@link ForgeDetailsFormController}.
 */
public class ForgeDetailsForm {

    /**
     * A reference to {@link Forge}.
     */
    private Forge forge;

    /**
     * A byte array representation of individuals exporting OWLS sequence file.
     */
    private byte[] expIndividualsOwlsSeq;

    /**
     * A byte array representation of object properties exporting OWLS sequence
     * file.
     */
    private byte[] expPropertiesOwlsSeq;

    /**
     * A byte array representation of project metadata importing OWLS file.
     */
    private byte[] impDataOwls;

    /**
     * A default constructor.
     */
    public ForgeDetailsForm() {
    }

    /**
     * A constructor.
     * 
     * @param f
     *            A reference to {@link Forge}.
     */
    public ForgeDetailsForm(Forge f) {
        setForge(f);
    }

    /**
     * A setter method of {@link Forge}.
     * 
     * @param forgeReference
     *            A reference to {@link Forge}.
     */
    public void setForge(Forge forgeReference) {
        this.forge = forgeReference;
    }

    /**
     * A getter method of {@link Forge} reference.
     * 
     * @return A reference of {@link Forge}.
     */
    public Forge getForge() {
        return forge;
    }

    /**
     * Setter method for expIndividualsOwlsSeq field.
     * 
     * @param expIndividualsOwlsSequenceFile
     *            byte array representation of a file.
     */
    public void setExpIndividualsOwlsSeq(byte[] expIndividualsOwlsSequenceFile) {
        this.expIndividualsOwlsSeq = expIndividualsOwlsSequenceFile;
    }

    /**
     * Getter method for exporting individuals sequence OWLS file.
     * 
     * @return byte array representation of an exporting sequence.
     */
    public byte[] getExpIndividualsOwlsSeq() {
        return expIndividualsOwlsSeq;
    }

    /**
     * Setter method for expPropertiesOwlsSeq field.
     * 
     * @param expPropertiesOwlsSequenceFile
     *            byte array representation of obj exporting sequence OWLS file.
     */
    public void setExpPropertiesOwlsSeq(byte[] expPropertiesOwlsSequenceFile) {
        this.expPropertiesOwlsSeq = expPropertiesOwlsSequenceFile;
    }

    /**
     * Getter method for byte array representation of OWLS Obj props exporting
     * sequence file.
     * @return byte array represention of obj props exporting sequence file.
     */
    public byte[] getExpPropertiesOwlsSeq() {
        return expPropertiesOwlsSeq;
    }

    /**
     * Setter method for byte array representation of importing OWLS sequence for project metadata.
     * @param importingDataOwls byte array value of importing OWLS file.
     */
    public void setImpDataOwls(byte[] importingDataOwls) {
        this.impDataOwls = importingDataOwls;
    }

    /**
     * Getter method for byte array representation of OWLS project meta data importing file.
     * @return byte array value of OWLS file.
     */
    public byte[] getImpDataOwls() {
        return impDataOwls;
    }
}
