package com.lfonta.littleowl.types.interfaces;

import com.lfonta.littleowl.types.MappableAuthUserData;

public interface OAuthClient {

    MappableAuthUserData exchangeCode(String code, String codeVerifier);
}
