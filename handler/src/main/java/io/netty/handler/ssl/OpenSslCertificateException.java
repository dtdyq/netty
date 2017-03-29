/*
 * Copyright 2016 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.handler.ssl;

import io.netty.internal.tcnative.CertificateVerifier;
import io.netty.util.collection.IntCollections;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;

import java.security.cert.CertificateException;

/**
 * A special {@link CertificateException} which allows to specify which error code is included in the
 * SSL Record. This only work when {@link SslProvider#OPENSSL} or {@link SslProvider#OPENSSL_REFCNT} is used.
 */
public final class OpenSslCertificateException extends CertificateException {
    private static final long serialVersionUID = 5542675253797129798L;
    private static final IntObjectMap<Boolean> ERROR_CODES;
    static {
        IntObjectMap<Boolean> errors = new IntObjectHashMap<Boolean>();
        errors.put(CertificateVerifier.X509_V_OK, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNSPECIFIED, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNABLE_TO_GET_ISSUER_CERT, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNABLE_TO_GET_CRL, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNABLE_TO_DECRYPT_CERT_SIGNATURE, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNABLE_TO_DECRYPT_CRL_SIGNATURE, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNABLE_TO_DECODE_ISSUER_PUBLIC_KEY, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_CERT_SIGNATURE_FAILURE, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_CRL_SIGNATURE_FAILURE, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_CERT_NOT_YET_VALID, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_CERT_HAS_EXPIRED, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_CRL_NOT_YET_VALID, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_CRL_HAS_EXPIRED, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_ERROR_IN_CERT_NOT_BEFORE_FIELD, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_ERROR_IN_CERT_NOT_AFTER_FIELD, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_ERROR_IN_CRL_LAST_UPDATE_FIELD, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_ERROR_IN_CRL_NEXT_UPDATE_FIELD, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_OUT_OF_MEM, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_DEPTH_ZERO_SELF_SIGNED_CERT, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_SELF_SIGNED_CERT_IN_CHAIN, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNABLE_TO_GET_ISSUER_CERT_LOCALLY, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNABLE_TO_VERIFY_LEAF_SIGNATURE, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_CERT_CHAIN_TOO_LONG, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_CERT_REVOKED, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_INVALID_CA, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_PATH_LENGTH_EXCEEDED, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_INVALID_PURPOSE, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_CERT_UNTRUSTED, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_CERT_REJECTED, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_SUBJECT_ISSUER_MISMATCH, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_AKID_SKID_MISMATCH, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_AKID_ISSUER_SERIAL_MISMATCH, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_KEYUSAGE_NO_CERTSIGN, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNABLE_TO_GET_CRL_ISSUER, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNHANDLED_CRITICAL_EXTENSION, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_KEYUSAGE_NO_CRL_SIGN, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNHANDLED_CRITICAL_CRL_EXTENSION, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_INVALID_NON_CA, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_PROXY_PATH_LENGTH_EXCEEDED, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_KEYUSAGE_NO_DIGITAL_SIGNATURE, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_PROXY_CERTIFICATES_NOT_ALLOWED, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_INVALID_EXTENSION, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_INVALID_POLICY_EXTENSION, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_NO_EXPLICIT_POLICY, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_DIFFERENT_CRL_SCOPE, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNSUPPORTED_EXTENSION_FEATURE, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNNESTED_RESOURCE, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_PERMITTED_VIOLATION, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_EXCLUDED_VIOLATION, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_SUBTREE_MINMAX, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_APPLICATION_VERIFICATION, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNSUPPORTED_CONSTRAINT_TYPE, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNSUPPORTED_CONSTRAINT_SYNTAX, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_UNSUPPORTED_NAME_SYNTAX, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_CRL_PATH_VALIDATION_ERROR, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_PATH_LOOP, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_SUITE_B_INVALID_VERSION, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_SUITE_B_INVALID_ALGORITHM, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_SUITE_B_INVALID_CURVE, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_SUITE_B_INVALID_SIGNATURE_ALGORITHM, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_SUITE_B_LOS_NOT_ALLOWED, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_SUITE_B_CANNOT_SIGN_P_384_WITH_P_256, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_HOSTNAME_MISMATCH, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_EMAIL_MISMATCH, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_IP_ADDRESS_MISMATCH, Boolean.TRUE);
        errors.put(CertificateVerifier.X509_V_ERR_DANE_NO_MATCH, Boolean.TRUE);

        ERROR_CODES = IntCollections.unmodifiableMap(errors);
    }

    private final int errorCode;

    /**
     * Construct a new exception with the
     * <a href="https://www.openssl.org/docs/manmaster/apps/verify.html">error code</a>.
     */
    public OpenSslCertificateException(int errorCode) {
        this((String) null, errorCode);
    }

    /**
     * Construct a new exception with the msg and
     * <a href="https://www.openssl.org/docs/manmaster/apps/verify.html">error code</a> .
     */
    public OpenSslCertificateException(String msg, int errorCode) {
        super(msg);
        this.errorCode = checkErrorCode(errorCode);
    }

    /**
     * Construct a new exception with the msg, cause and
     * <a href="https://www.openssl.org/docs/manmaster/apps/verify.html">error code</a> .
     */
    public OpenSslCertificateException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = checkErrorCode(errorCode);
    }

    /**
     * Construct a new exception with the cause and
     * <a href="https://www.openssl.org/docs/manmaster/apps/verify.html">error code</a> .
     */
    public OpenSslCertificateException(Throwable cause, int errorCode) {
        this(null, cause, errorCode);
    }

    /**
     * Return the <a href="https://www.openssl.org/docs/man1.0.2/apps/verify.html">error code</a> to use.
     */
    public int errorCode() {
        return errorCode;
    }

    private static int checkErrorCode(int errorCode) {
        if (!isValid(errorCode)) {
            throw new IllegalArgumentException("errorCode '" + errorCode +
                    "' invalid, see https://www.openssl.org/docs/man1.0.2/apps/verify.html.");
        }
        return errorCode;
    }

    // Package-private for testing
    static boolean isValid(int errorCode) {
        return ERROR_CODES.containsKey(errorCode);
    }
}
