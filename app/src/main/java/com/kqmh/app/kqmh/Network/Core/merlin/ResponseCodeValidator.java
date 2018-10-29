package com.kqmh.app.kqmh.Network.Core.merlin;

public interface ResponseCodeValidator {

    boolean isResponseCodeValid(int responseCode);

    class DefaultEndpointResponseCodeValidator implements ResponseCodeValidator {
        @Override
        public boolean isResponseCodeValid(int responseCode) {
            return responseCode == 204;
        }
    }
}
