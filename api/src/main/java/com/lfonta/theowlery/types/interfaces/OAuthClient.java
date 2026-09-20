package com.lfonta.theowlery.types.interfaces;

import com.lfonta.theowlery.types.MappableAuthUserData;

public interface OAuthClient {

    MappableAuthUserData exchangeCode(String code, String codeVerifier);
}
