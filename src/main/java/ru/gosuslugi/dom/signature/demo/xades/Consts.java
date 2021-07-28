package ru.gosuslugi.dom.signature.demo.xades;

/**
 * Имена используемых алгоритмов.
 */
public class Consts {
    private Consts() {
    }

    /**
     * Алгоритм электронной подписи
     */
    public static final String SIGNATURE_ALGORITHM = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34102012-gostr34112012-256";
    /**
     * Алгоритм каноникализации для подписи
     */
    public static final String CANONICALIZATION_ALGORITHM_FOR_SIGNATURE = "http://www.w3.org/2001/10/xml-exc-c14n#";
    /**
     * Алгоритм каноникализации для штампа времени
     */
    public static final String CANONICALIZATION_ALGORITHM_FOR_TIMESTAMP_PROPERTIES = "http://www.w3.org/2001/10/xml-exc-c14n#";
    /**
     * Алгоритм расчета хешей. Используется в XML-документе.
     */
    public static final String DIGEST_ALGORITHM_URI = "urn:ietf:params:xml:ns:cpxmlsec:algorithms:gostr34112012-256";
    /**
     * Алгоритм расчета хешей. Используется для создания экземпляра алгоритма.
     */
    public static final String DIGEST_ALGORITHM_NAME = "GOST3411_2012_256";

    /**
     * Ссылка на блок {@code <xades:SignedSignatureProperties>}
     */
    public static final String SIGNED_PROPS_TYPE_URI = "http://uri.etsi.org/01903#SignedProperties";
}
