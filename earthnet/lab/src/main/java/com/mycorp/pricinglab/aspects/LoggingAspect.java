package com.vzwcorp.pricinglab.aspects;


import com.vzwcorp.pricinglab.vo.PlabCust;
import com.vzwcorp.pricinglab.vo.repository.PlabCustRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by menonka on 12/12/2016.
 */
@Aspect
@Component
public class LoggingAspect {

    private static Logger logger = LogManager.getLogger(LoggingAspect.class);

    public static final String EVENT_PROP_ACCOUNT_NUMBER = "AccountNumber";
    public static final String EVENT_PROP_CUSTOMER_ID = "CustomerId";
    public static final String EVENT_PROP_MDN = "mdn";
    public static final String EVENT_PROP_IP_ADDRESS = "IPAddress";

    @Autowired
    PlabCustRepository plabCustRepository;

    /**
     * @Deprecated
     * set the customer details.
     * @param joinPoint
     */
    //@Before("execution(* com.vzwcorp.pricinglab.rest.controller..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        try {
            String signatureName = joinPoint.getSignature().getName();
            logger.debug("method call (before): {}", signatureName);

            //Can be used to retrieve the get IP Address.
            /*
            try {
                if (RequestContextHolder.currentRequestAttributes() != null) {
                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
                    ThreadContext.put(EVENT_PROP_IP_ADDRESS,request.getRemoteAddr());
                }
            } catch (IllegalStateException iex) {
                logger.trace("Illegalstateexception : {} ", iex.getMessage());
            }
            */

            // get signature printed
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            String [] paramNames = methodSignature.getParameterNames();
            List<String> paramNamesList = Arrays.asList(paramNames);

            Object[] methodParams = joinPoint.getArgs();

            String mdn = null;

            if (methodParams != null && methodParams.length != 0) {

                //filter for mdn here.
                for (int i = 0; i < methodParams.length; i++) {
                    if (paramNamesList.contains("mdn")) {
                        if (methodParams[i].toString().length() == 10)
                            if (methodParams[i] instanceof String || methodParams[i] instanceof Long){
                            mdn = methodParams[i].toString();
                        }
                    }
                    //TODO : add more conditions here.
                }
            }

            if (mdn != null && !mdn.isEmpty()) {
                ThreadContext.clearAll();

                //TODO : DB call made since it cached. Else use GridService.
                List<PlabCust> custList = plabCustRepository.findByMdnForLogging(mdn);

                if (custList != null && !custList.isEmpty()) {
                    PlabCust cust = (PlabCust) custList.get(0);
                    ThreadContext.put(EVENT_PROP_CUSTOMER_ID, cust.getCustIdNo() != null ? cust.getCustIdNo().toString() : null);
                    ThreadContext.put(EVENT_PROP_ACCOUNT_NUMBER, cust.getAcctNo() != null ? cust.getAcctNo().toString() : null);
                    ThreadContext.put(EVENT_PROP_MDN, mdn);
                }
            }

        } catch (Exception ex) {
            logger.error("Error setting the log4j with Customer details", ex.getMessage());
        }

    }

    /**
     * @Deprecated
     * clear the thread context
     * @param joinPoint
     */
    //@After("execution(* com.vzwcorp.pricinglab.rest.controller..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        try {

            logger.debug("method call (after): {}", joinPoint.getSignature().getName());

            ThreadContext.clearAll();

        } catch (Exception ex) {
            logger.error("Error setting the log4j with Customer details", ex.getMessage());
        }
    }
}
