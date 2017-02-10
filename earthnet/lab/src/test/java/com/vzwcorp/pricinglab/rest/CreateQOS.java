package com.vzwcorp.pricinglab.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.vo.RefQoS;
import com.vzwcorp.pricinglab.vo.Views;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by menonka on 8/7/2016.
 */
public class CreateQOS {

    public static void main(String[] args) {
        createApplications();
        getQoSList();

    }

    public static void createApplications() {
        createQoS("Fast","FAST", PlabConstants.QOS_RULETYPE_BANDWIDTH,"#82CEAC");
        createQoS("Faster","FASTER", PlabConstants.QOS_RULETYPE_BANDWIDTH,"#ABE0F9");
        createQoS("Fastest","FASTEST", PlabConstants.QOS_RULETYPE_BANDWIDTH,"#FBD362");
        createQoS("SD","SD", PlabConstants.QOS_RULETYPE_DV_FORMAT,"#82CEAC");
        createQoS("HD","HD", PlabConstants.QOS_RULETYPE_DV_FORMAT,"#ABE0F9");
        createQoS("UHD","UHD", PlabConstants.QOS_RULETYPE_DV_FORMAT,"#FBD362");
    }

    private static void createQoS(String name, String vispName, String type,String defaultColorCode) {
        try {
            RefQoS refQoS = new RefQoS(name,vispName,type);
            refQoS.setCreateDate(new Date());
            refQoS.setCreatedBy(PlabConstants.DEFAULT_CREATED_BY_ADMIN);
            refQoS.setDefaultColorCodeHex(defaultColorCode);

            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer= mapper.writerWithView(Views.InternalView.class);
            String jsonProduct= writer.writeValueAsString(refQoS);

            Map<String, String> params = new HashMap<String, String>();
            params.put("qosObject", jsonProduct);
            String s= RestClient.postRequest(CreateOffers.HostUrl+"/service/qos/create", params);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static void getQoSList() {
        try {
            String s= RestClient.getRequest(CreateOffers.HostUrl+"/service/qos/list");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
