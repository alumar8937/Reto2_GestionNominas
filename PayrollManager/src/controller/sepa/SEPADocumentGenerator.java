package controller.sepa;

import model.Payroll;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Pedro Marín Sanchis
 */
public class SEPADocumentGenerator {

    /**
     * Creates an XML SEPA document with the provided data.
     *
     * @param payrolls      List of payrolls to add to the batch.
     * @param msgId         Unique identifier of the message.
     * @param creDtTm       Date and time of message creation.
     * @param nbOfTxs       Total number of transactions.
     * @param ctrlSum       Total sum of the amounts.
     * @param initgPtyNm    Name of the payment initiator.
     */
    public static void createSEPADocument(ArrayList<Payroll> payrolls, File exportDirectory, String msgId, String creDtTm, int nbOfTxs, double ctrlSum, String initgPtyNm) {
        try {
            // Create new XML Document
            Document doc;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();

            // Add root element <Document>
            Element rootElement = doc.createElement("Document");
            rootElement.setAttribute("xmlns", "urn:iso:std:iso:20022:tech:xsd:pain.001.001.03");
            doc.appendChild(rootElement);

            // Add element <CstmrCdtTrfInitn>
            Element cstmrCdtTrfInitn = doc.createElement("CstmrCdtTrfInitn");
            rootElement.appendChild(cstmrCdtTrfInitn);

            // Add header element.
            createGrpHdr(doc, msgId, creDtTm, nbOfTxs, ctrlSum, initgPtyNm, cstmrCdtTrfInitn);
            for (Payroll p: payrolls) {
                createPmtInf(doc,"TRANSFERENCIA"+p.getId_name(), "TRF", false, p.getYear()+"-"+p.getMonth()+"-"+p.getDay(),
                        p.getCompany(), "00123456789012345678901", "ES0123456789012345678901", "BCOEESMMXXX",
                        "SLEV", "TRANSFERENCIA"+p.getId_name(), "EUR", p.getTotal_net(), "BCOEESMMXXX", p.getEmp_name(),
                        "ES9876543210987654321098", "Pago de nómina", cstmrCdtTrfInitn);
            }
            // Generate XML File.
            generateXMLFile(doc, exportDirectory.getAbsolutePath()+File.separatorChar+"sepa_document.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates and configures the <GrpHdr> element within the SEPA document.
     *
     * @param doc           The XML document.
     * @param msgId         The message identifier.
     * @param creDtTm       The creation date and time of the message in "yyyy-MM-dd'T'HH:mm:ss" format.
     * @param nbOfTxs       The total number of transactions in the group.
     * @param ctrlSum       The total sum of the transaction amounts in the group.
     * @param initgPtyNm    The name of the initiating party for the transfer.
     * @param parentElement The parent element to which the <GrpHdr> element will be added.
     */
    private static void createGrpHdr(Document doc, String msgId, String creDtTm, int nbOfTxs, double ctrlSum,
                                     String initgPtyNm, Element parentElement) {
        // Agregar el elemento <GrpHdr> dentro de <CstmrCdtTrfInitn>
        Element grpHdr = doc.createElement("GrpHdr");
        parentElement.appendChild(grpHdr);

        // Agregar el elemento <MsgId> dentro de <GrpHdr>
        Element msgIdElement = doc.createElement("MsgId");
        msgIdElement.appendChild(doc.createTextNode(msgId));
        grpHdr.appendChild(msgIdElement);

        // Agregar el elemento <CreDtTm> dentro de <GrpHdr>
        Element creDtTmElement = doc.createElement("CreDtTm");
        creDtTmElement.appendChild(doc.createTextNode(creDtTm));
        grpHdr.appendChild(creDtTmElement);

        // Agregar el elemento <NbOfTxs> dentro de <GrpHdr>
        Element nbOfTxsElement = doc.createElement("NbOfTxs");
        nbOfTxsElement.appendChild(doc.createTextNode(String.valueOf(nbOfTxs)));
        grpHdr.appendChild(nbOfTxsElement);

        // Agregar el elemento <CtrlSum> dentro de <GrpHdr>
        Element ctrlSumElement = doc.createElement("CtrlSum");
        ctrlSumElement.appendChild(doc.createTextNode(String.valueOf(ctrlSum)));
        grpHdr.appendChild(ctrlSumElement);

        // Agregar el elemento <InitgPty> dentro de <GrpHdr>
        Element initgPty = doc.createElement("InitgPty");
        grpHdr.appendChild(initgPty);

        // Agregar el elemento <Nm> dentro de <InitgPty>
        Element initgPtyNmElement = doc.createElement("Nm");
        initgPtyNmElement.appendChild(doc.createTextNode(initgPtyNm));
        initgPty.appendChild(initgPtyNmElement);
    }

    /**
     * @param pmtInfId      Unique payment identifier.
     * @param pmtMtd        Payment method.
     * @param btchBookg     Indicates whether payments should be batched.
     * @param reqdExctnDt   Requested execution date.
     * @param dbtrNm        Debtor's name.
     * @param dbtrAcctId    Debtor's account identifier.
     * @param dbtrAcctIBAN  Debtor's account IBAN.
     * @param dbtrAgtBIC    Debtor's bank BIC.
     * @param chrgBr        Charging bearer type.
     * @param endToEndId    Unique transaction identifier.
     * @param instdAmtCcy   Amount of Currency.
     * @param instdAmtValue Amount value.
     * @param cdtrAgtBIC    Creditor's bank BIC.
     * @param cdtrNm        Creditor's name.
     * @param cdtrAcctId    Creditor's account identifier.
     * @param rmtInfUstrd   Remittance information.
     */
    private static void createPmtInf(Document doc, String pmtInfId, String pmtMtd, boolean btchBookg,
                                     String reqdExctnDt, String dbtrNm, String dbtrAcctId, String dbtrAcctIBAN,
                                     String dbtrAgtBIC, String chrgBr, String endToEndId, String instdAmtCcy,
                                     double instdAmtValue, String cdtrAgtBIC, String cdtrNm, String cdtrAcctId,
                                     String rmtInfUstrd, Element parentElement) {
        // Agregar el elemento <PmtInf> dentro de <CstmrCdtTrfInitn>
        Element pmtInf = doc.createElement("PmtInf");
        parentElement.appendChild(pmtInf);

        // Agregar el elemento <PmtInfId> dentro de <PmtInf>
        Element pmtInfIdElement = doc.createElement("PmtInfId");
        pmtInfIdElement.appendChild(doc.createTextNode(pmtInfId));
        pmtInf.appendChild(pmtInfIdElement);

        // Agregar el elemento <PmtMtd> dentro de <PmtInf>
        Element pmtMtdElement = doc.createElement("PmtMtd");
        pmtMtdElement.appendChild(doc.createTextNode(pmtMtd));
        pmtInf.appendChild(pmtMtdElement);

        // Agregar el elemento <BtchBookg> dentro de <PmtInf>
        Element btchBookgElement = doc.createElement("BtchBookg");
        btchBookgElement.appendChild(doc.createTextNode(String.valueOf(btchBookg)));
        pmtInf.appendChild(btchBookgElement);

        // Agregar el elemento <ReqdExctnDt> dentro de <PmtInf>
        Element reqdExctnDtElement = doc.createElement("ReqdExctnDt");
        reqdExctnDtElement.appendChild(doc.createTextNode(reqdExctnDt));
        pmtInf.appendChild(reqdExctnDtElement);

        // Agregar el elemento <Dbtr> dentro de <PmtInf>
        Element dbtrElement = doc.createElement("Dbtr");
        pmtInf.appendChild(dbtrElement);

        // Agregar el elemento <Nm> dentro de <Dbtr>
        Element dbtrNmElement = doc.createElement("Nm");
        dbtrNmElement.appendChild(doc.createTextNode(dbtrNm));
        dbtrElement.appendChild(dbtrNmElement);

        // Agregar el elemento <DbtrAcct> dentro de <PmtInf>
        Element dbtrAcct = doc.createElement("DbtrAcct");
        pmtInf.appendChild(dbtrAcct);

        // Agregar el elemento <Id> dentro de <DbtrAcct>
        Element dbtrAcctIdElement = doc.createElement("Id");
        dbtrAcct.appendChild(dbtrAcctIdElement);

        // Agregar el elemento <IBAN> dentro de <Id>
        Element dbtrAcctIBANElement = doc.createElement("IBAN");
        dbtrAcctIBANElement.appendChild(doc.createTextNode(dbtrAcctIBAN));
        dbtrAcctIdElement.appendChild(dbtrAcctIBANElement);

        // Agregar el elemento <DbtrAgt> dentro de <PmtInf>
        Element dbtrAgt = doc.createElement("DbtrAgt");
        pmtInf.appendChild(dbtrAgt);

        // Agregar el elemento <FinInstnId> dentro de <DbtrAgt>
        Element dbtrAgtFinInstnId = doc.createElement("FinInstnId");
        dbtrAgt.appendChild(dbtrAgtFinInstnId);

        // Agregar el elemento <BIC> dentro de <FinInstnId>
        Element dbtrAgtBICElement = doc.createElement("BIC");
        dbtrAgtBICElement.appendChild(doc.createTextNode(dbtrAgtBIC));
        dbtrAgtFinInstnId.appendChild(dbtrAgtBICElement);

        // Agregar el elemento <ChrgBr> dentro de <PmtInf>
        Element chrgBrElement = doc.createElement("ChrgBr");
        chrgBrElement.appendChild(doc.createTextNode(chrgBr));
        pmtInf.appendChild(chrgBrElement);

        // Agregar el elemento <CdtTrfTxInf> dentro de <PmtInf>
        Element cdtTrfTxInf = doc.createElement("CdtTrfTxInf");
        pmtInf.appendChild(cdtTrfTxInf);

        // Agregar el elemento <PmtId> dentro de <CdtTrfTxInf>
        Element pmtId = doc.createElement("PmtId");
        cdtTrfTxInf.appendChild(pmtId);

        // Agregar el elemento <EndToEndId> dentro de <PmtId>
        Element endToEndIdElement = doc.createElement("EndToEndId");
        endToEndIdElement.appendChild(doc.createTextNode(endToEndId));
        pmtId.appendChild(endToEndIdElement);

        // Agregar el elemento <Amt> dentro de <CdtTrfTxInf>
        Element amt = doc.createElement("Amt");
        cdtTrfTxInf.appendChild(amt);

        // Agregar el elemento <InstdAmt> dentro de <Amt>
        Element instdAmt = doc.createElement("InstdAmt");
        instdAmt.setAttribute("Ccy", instdAmtCcy);
        instdAmt.appendChild(doc.createTextNode(String.valueOf(instdAmtValue)));
        amt.appendChild(instdAmt);

        // Agregar el elemento <CdtrAgt> dentro de <CdtTrfTxInf>
        Element cdtrAgt = doc.createElement("CdtrAgt");
        cdtTrfTxInf.appendChild(cdtrAgt);

        // Agregar el elemento <FinInstnId> dentro de <CdtrAgt>
        Element cdtrAgtFinInstnId = doc.createElement("FinInstnId");
        cdtrAgt.appendChild(cdtrAgtFinInstnId);

        // Agregar el elemento <BIC> dentro de <FinInstnId>
        Element cdtrAgtBICElement = doc.createElement("BIC");
        cdtrAgtBICElement.appendChild(doc.createTextNode(cdtrAgtBIC));
        cdtrAgtFinInstnId.appendChild(cdtrAgtBICElement);

        // Agregar el elemento <Cdtr> dentro de <CdtTrfTxInf>
        Element cdtr = doc.createElement("Cdtr");
        cdtTrfTxInf.appendChild(cdtr);

        // Agregar el elemento <Nm> dentro de <Cdtr>
        Element cdtrNmElement = doc.createElement("Nm");
        cdtrNmElement.appendChild(doc.createTextNode(cdtrNm));
        cdtr.appendChild(cdtrNmElement);

        // Agregar el elemento <CdtrAcct> dentro de <CdtTrfTxInf>
        Element cdtrAcct = doc.createElement("CdtrAcct");
        cdtTrfTxInf.appendChild(cdtrAcct);

        // Agregar el elemento <Id> dentro de <CdtrAcct>
        Element cdtrAcctIdElement = doc.createElement("Id");
        cdtrAcct.appendChild(cdtrAcctIdElement);

        // Agregar el elemento <IBAN> dentro de <Id>
        Element cdtrAcctIBANElement = doc.createElement("IBAN");
        cdtrAcctIBANElement.appendChild(doc.createTextNode(cdtrAcctId));
        cdtrAcctIdElement.appendChild(cdtrAcctIBANElement);

        // Agregar el elemento <RmtInf> dentro de <CdtTrfTxInf>
        Element rmtInf = doc.createElement("RmtInf");
        cdtTrfTxInf.appendChild(rmtInf);

        // Agregar el elemento <Ustrd> dentro de <RmtInf>
        Element ustrdElement = doc.createElement("Ustrd");
        ustrdElement.appendChild(doc.createTextNode(rmtInfUstrd));
        rmtInf.appendChild(ustrdElement);
    }

    private static void generateXMLFile(Document doc, String pathname) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // Generar el archivo XML
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(pathname));
        transformer.transform(source, result);
    }
}
