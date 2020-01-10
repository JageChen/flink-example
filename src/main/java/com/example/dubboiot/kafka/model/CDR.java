package com.example.dubboiot.kafka.model;

import lombok.Data;
import org.apache.flink.api.common.Plan;

/**
 * description: CDR <br>
 * date: 2020/1/7 16:22 <br>
 * author: EDZ <br>
 * version: 1.0 <br>
 */
@Data
public class CDR {
    private  String cdr_Id;
    private  String device_Id;
    private  String account_Id;
    private  String subscription_Id;
    private  String commercial_Plan;
    private  String subscription_Status;
    private  String billable;
    private  String start_time;
    private  String duration;
    private  String end_time;
    private  String reason_For_Closing;
    private  String bill_Id;
    private  String customer_Id;
    private  String tax_Id;
    private  String mcc;
    private  String data_Downlink;
    private  String data_Uplink;
    private  String total_Data;
    private  String apn;
    private  String device_Ip_Address;
    private  String device_Imei;
    //目标ip/应用/协议
    private  String onfig;
    //底层vSIM和rSIM支持(ICCID / IMSI)
    private  String sustain;

}
