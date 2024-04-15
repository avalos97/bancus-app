package com.colsubsidio.microservicebankapi.components.util;

import java.math.BigDecimal;

public class Constants {

    private Constants() {
    }

    public static final String API_URL_PREFIX = "/api/administration/";
    public static final String MEDIA_TYPE = "application/json";

    public static final String GENERIC_RECORD_NOT_FOUND_MESSAGE = "Record not found with ID ";
    public static final String SUCCESSFULLY_MESSAGE = "Operation successfully completed";

    public static final BigDecimal PERCENTAGE_0_05 = BigDecimal.valueOf(0.05);
    public static final BigDecimal PERCENTAGE_0_03 = BigDecimal.valueOf(0.03);



}
