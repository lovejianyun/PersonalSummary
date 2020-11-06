package com.qijy.jsoniter;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.qijy.fastjson.AbstractMonData;
import com.qijy.fastjson.UserInfo;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * @ Description   :  jsoniter测试
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/2 9:41
 */
public class JsoniterTest {
    public static void main(String[] args) throws Exception{

        //String xx = "[{ \"USERID\":\"H10337\", \"JTYPE\":\"0\", \"LINKNUM\":\"2\", \"IP\":\"121.51.32.101:57408,121.51.32.101:57409\", \"STATUS\":\"0\", \"INTM\":\"2020-07-09 03:03:22\", \"OUTTM\":\"2020-07-09 03:03:22\", \"LASTVST\":\"2020-07-20 14:48:00\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"2\", \"FEE\":\"99\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9713\", \"MT\":\"488\", \"MTSD\":\"938\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"2\", \"MTSPD\":\"0\", \"MTMINDLY\":\"6571\", \"MTMAXDLY\":\"6571\", \"MTAVGDLY\":\"6571\", \"MO\":\"0\", \"MORV\":\"0\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"481\", \"OPTRPT\":\"0\", \"DTRPT\":\"479\", \"OPTDTRPT\":\"0\", \"RPTRV\":\"481\", \"OPTRPTRV\":\"0\", \"RPTDTRV\":\"479\", \"OPTRPTDTRV\":\"0\", \"RPTDTRVSUCC\":\"474\", \"OPTRPTDTRVSUCC\":\"0\", \"RPTDTRVFAIL\":\"5\", \"OPTRPTDTRVFAIL\":\"0\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"H10600\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-03-23 06:57:44\", \"OUTTM\":\"2018-03-23 06:57:44\", \"LASTVST\":\"2020-07-20 14:47:59\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"64268060\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9705\", \"MT\":\"252529\", \"MTSD\":\"253452\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"3\", \"MTMINDLY\":\"1\", \"MTMAXDLY\":\"316\", \"MTAVGDLY\":\"49\", \"MO\":\"159\", \"MORV\":\"159\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"6\", \"RPT\":\"1050\", \"OPTRPT\":\"250446\", \"DTRPT\":\"1047\", \"OPTDTRPT\":\"247511\", \"RPTRV\":\"1048\", \"OPTRPTRV\":\"250444\", \"RPTDTRV\":\"1047\", \"OPTRPTDTRV\":\"247511\", \"RPTDTRVSUCC\":\"72\", \"OPTRPTDTRVSUCC\":\"245029\", \"RPTDTRVFAIL\":\"975\", \"OPTRPTDTRVFAIL\":\"2482\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"6\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"H11396\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-03-23 06:57:44\", \"OUTTM\":\"2018-03-23 06:57:44\", \"LASTVST\":\"2020-07-20 14:47:58\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"2\", \"FEE\":\"2461386\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"8581\", \"MT\":\"11876\", \"MTSD\":\"12011\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"1\", \"MTMAXDLY\":\"200\", \"MTAVGDLY\":\"63\", \"MO\":\"5773\", \"MORV\":\"5773\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"11743\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"11048\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"11354\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"11048\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"10191\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"857\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"H10515\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-03-23 06:57:44\", \"OUTTM\":\"2018-03-23 06:57:44\", \"LASTVST\":\"2020-07-20 14:46:33\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"11467337\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9625\", \"MT\":\"347\", \"MTSD\":\"347\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"1\", \"MTMAXDLY\":\"88\", \"MTAVGDLY\":\"34\", \"MO\":\"0\", \"MORV\":\"0\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"348\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"342\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"348\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"342\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"334\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"8\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"H11666\", \"JTYPE\":\"0\", \"LINKNUM\":\"1\", \"IP\":\"116.31.114.4:50798\", \"STATUS\":\"0\", \"INTM\":\"2020-07-20 11:20:58\", \"OUTTM\":\"2020-07-20 11:20:56\", \"LASTVST\":\"2020-07-20 14:47:59\", \"OUTCNT\":\"4\", \"FEEFLAG\":\"2\", \"FEE\":\"26980673\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9596\", \"MT\":\"89660\", \"MTSD\":\"89605\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"1\", \"MTMAXDLY\":\"200\", \"MTAVGDLY\":\"12\", \"MO\":\"10\", \"MORV\":\"11\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"88908\", \"OPTRPT\":\"0\", \"DTRPT\":\"87420\", \"OPTDTRPT\":\"0\", \"RPTRV\":\"88895\", \"OPTRPTRV\":\"7\", \"RPTDTRV\":\"87415\", \"OPTRPTDTRV\":\"0\", \"RPTDTRVSUCC\":\"86046\", \"OPTRPTDTRVSUCC\":\"0\", \"RPTDTRVFAIL\":\"1374\", \"OPTRPTDTRVFAIL\":\"0\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"H10319\", \"JTYPE\":\"0\", \"LINKNUM\":\"1\", \"IP\":\"121.51.32.101:50214\", \"STATUS\":\"0\", \"INTM\":\"2020-07-15 00:17:11\", \"OUTTM\":\"2020-07-09 03:03:23\", \"LASTVST\":\"2020-07-20 14:48:00\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"2\", \"FEE\":\"91\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9587\", \"MT\":\"388\", \"MTSD\":\"394\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"2\", \"MO\":\"2\", \"MORV\":\"2\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"396\", \"OPTRPT\":\"0\", \"DTRPT\":\"386\", \"OPTDTRPT\":\"0\", \"RPTRV\":\"396\", \"OPTRPTRV\":\"0\", \"RPTDTRV\":\"386\", \"OPTRPTDTRV\":\"0\", \"RPTDTRVSUCC\":\"372\", \"OPTRPTDTRVSUCC\":\"0\", \"RPTDTRVFAIL\":\"14\", \"OPTRPTDTRVFAIL\":\"0\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"H10853\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\"218.17.39.34:38614\", \"STATUS\":\"7\", \"INTM\":\"2018-12-03 14:13:35\", \"OUTTM\":\"2018-12-03 14:13:41\", \"LASTVST\":\"2020-07-20 14:47:50\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"7816140\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"8403\", \"MT\":\"8345\", \"MTSD\":\"8500\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"84\", \"MTAVGDLY\":\"31\", \"MO\":\"12\", \"MORV\":\"12\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"269\", \"OPTRPT\":\"7869\", \"DTRPT\":\"269\", \"OPTDTRPT\":\"7475\", \"RPTRV\":\"269\", \"OPTRPTRV\":\"7607\", \"RPTDTRV\":\"269\", \"OPTRPTDTRV\":\"7475\", \"RPTDTRVSUCC\":\"8\", \"OPTRPTDTRVSUCC\":\"7005\", \"RPTDTRVFAIL\":\"261\", \"OPTRPTDTRVFAIL\":\"470\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"H10321\", \"JTYPE\":\"0\", \"LINKNUM\":\"2\", \"IP\":\"101.91.0.170:30873,101.91.0.170:30876\", \"STATUS\":\"0\", \"INTM\":\"2020-07-20 13:52:50\", \"OUTTM\":\"2020-07-01 05:32:56\", \"LASTVST\":\"2020-07-20 14:48:00\", \"OUTCNT\":\"1\", \"FEEFLAG\":\"2\", \"FEE\":\"98\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9380\", \"MT\":\"3710\", \"MTSD\":\"3711\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"1\", \"MTMINDLY\":\"1\", \"MTMAXDLY\":\"454\", \"MTAVGDLY\":\"43\", \"MO\":\"16\", \"MORV\":\"16\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"3698\", \"OPTRPT\":\"0\", \"DTRPT\":\"3619\", \"OPTDTRPT\":\"0\", \"RPTRV\":\"3698\", \"OPTRPTRV\":\"5\", \"RPTDTRV\":\"3619\", \"OPTRPTDTRV\":\"5\", \"RPTDTRVSUCC\":\"3480\", \"OPTRPTDTRVSUCC\":\"0\", \"RPTDTRVFAIL\":\"139\", \"OPTRPTDTRVFAIL\":\"0\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"H11132\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-03-23 06:57:44\", \"OUTTM\":\"2018-03-23 06:57:44\", \"LASTVST\":\"2020-07-20 14:47:56\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"5104927\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9635\", \"MT\":\"8850\", \"MTSD\":\"10178\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"148\", \"MTAVGDLY\":\"70\", \"MO\":\"0\", \"MORV\":\"0\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"74\", \"OPTRPT\":\"8763\", \"DTRPT\":\"67\", \"OPTDTRPT\":\"8697\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"0\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"0\", \"RPTDTRVSUCC\":\"8\", \"OPTRPTDTRVSUCC\":\"8519\", \"RPTDTRVFAIL\":\"59\", \"OPTRPTDTRVFAIL\":\"178\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"H10698\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-03-23 06:57:44\", \"OUTTM\":\"2018-03-23 06:57:44\", \"LASTVST\":\"2020-07-20 14:47:50\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"53378369\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9592\", \"MT\":\"4559\", \"MTSD\":\"4469\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"2\", \"MO\":\"0\", \"MORV\":\"0\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"4557\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"4535\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"4546\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"4535\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"4373\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"162\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"H10198\", \"JTYPE\":\"0\", \"LINKNUM\":\"4\", \"IP\":\"14.116.136.24:62967,14.116.136.24:62968,14.116.136.24:36890,14.116.136.24:36162\", \"STATUS\":\"0\", \"INTM\":\"2020-07-15 03:54:33\", \"OUTTM\":\"2020-07-10 14:30:06\", \"LASTVST\":\"2020-07-20 14:48:00\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"2\", \"FEE\":\"98\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9458\", \"MT\":\"388\", \"MTSD\":\"388\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"30\", \"MTAVGDLY\":\"0\", \"MO\":\"1\", \"MORV\":\"1\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"391\", \"OPTRPT\":\"0\", \"DTRPT\":\"379\", \"OPTDTRPT\":\"0\", \"RPTRV\":\"391\", \"OPTRPTRV\":\"0\", \"RPTDTRV\":\"379\", \"OPTRPTDTRV\":\"0\", \"RPTDTRVSUCC\":\"367\", \"OPTRPTDTRVSUCC\":\"0\", \"RPTDTRVFAIL\":\"12\", \"OPTRPTDTRVFAIL\":\"0\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"H11001\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-03-23 06:57:44\", \"OUTTM\":\"2018-03-23 06:57:44\", \"LASTVST\":\"2020-07-20 14:47:48\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"25287018\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9327\", \"MT\":\"6964\", \"MTSD\":\"6975\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"29\", \"MTMAXDLY\":\"108\", \"MTAVGDLY\":\"60\", \"MO\":\"1\", \"MORV\":\"1\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"7094\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"6712\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"0\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"0\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"6496\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"216\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" }]";
//        String xx = "{\"USERID\":\"TJ308-C111\",\"JTYPE\":\"1\",\"LINKNUM\":\"0\",\"IP\":\"192.169.1.183\",\"STATUS\":\"1\",\"INTM\":\"2020-05-29 09:23:23.270\",\"OUTTM\":\"2020-05-29 09:23:23.270\",\"LASTVST\":\"2020-05-29 09:23:23.270\",\"OUTCNT\":\"2\",\"FEEFLAG\":\"1\",\"FEE\":\"0\",\"GLBFEE\":\"0\",\"SUCCRATE\":\"40\",\"MTSD\":\"123456\",\"MTNOSD\":\"100000\",\"MTMEMNOSD\":\"10000\",\"MTFILENOSD\":\"90000\",\"MTVFYNOSD\":\"3000\",\"MTLVL0NOSD\":\"2500\",\"MTSPD\":\"1000\",\"MTMINDLY\":\"1\",\"MTMAXDLY\":\"10000\",\"MTAVGDLY\":\"5000\",\"MO\":\"0\",\"MORV\":\"0\",\"MONORV\":\"0\",\"MOMEMNORV\":\"90\",\"MOFILENORV\":\"0\",\"MODBNORV\":\"0\",\"MOSPD\":\"0\",\"RPT\":\"1000000\",\"OPTRPT\":\"1000000\",\"OPTDTRPT\":\"1000000\",\"RPTRV\":\"990000\",\"OPTRPTRV\":\"990000\",\"OPTRPTDTRV\":\"990000\",\"OPTRPTDTRVSUCC\":\"990000\",\"RPTDTRVFAIL\":\"0\",\"OPTRPTDTRVFAIL\":\"0\",\"RPTNORV\":\"100000\",\"RPTMEMNORV\":\"100000\",\"RPTFILENORV\":\"100000\",\"RPTDBNORV\":\"100000\",\"RPTSPD\":\"5000\",\"MTLVLNOSD\":\"1/11/22/33/44/55/66/77/88/99\",\"RPTDTRV\":\"990000\",\"DTRPT\":\"1000000\",\"MT\":\"200000\",\"RPTDTRVSUCC\":\"990000\"}";
        String xx = "{\"USERID\":\"23490－C000\",\"JTYPE\":\"1\",\"LINKNUM\":\"0\",\"IP\":\"192.169.1.183\",\"STATUS\":\"1\",\"INTM\":\"2020-06-05 15:51:29.315\",\"OUTTM\":\"2020-06-05 15:51:29.315\",\"LASTVST\":\"2020-06-05 15:51:29.315\",\"OUTCNT\":\"2\",\"FEEFLAG\":\"1\",\"FEE\":\"1000\",\"GLBFEE\":\"1000\",\"SUCCRATE\":\"40\",\"MTSD\":\"123456\",\"MTNOSD\":\"100000\",\"MTMEMNOSD\":\"10000\",\"MTFILENOSD\":\"90000\",\"MTVFYNOSD\":\"3000\",\"MTLVL0NOSD\":\"2500\",\"MTSPD\":\"1000\",\"MTMINDLY\":\"1\",\"MTMAXDLY\":\"10000\",\"MTAVGDLY\":\"5000\",\"MO\":\"0\",\"MORV\":\"0\",\"MONORV\":\"0\",\"MOMEMNORV\":\"90\",\"MOFILENORV\":\"0\",\"MODBNORV\":\"0\",\"MOSPD\":\"0\",\"RPT\":\"1000000\",\"OPTRPT\":\"1000000\",\"OPTDTRPT\":\"1000000\",\"RPTRV\":\"990000\",\"OPTRPTRV\":\"990000\",\"OPTRPTDTRV\":\"990000\",\"OPTRPTDTRVSUCC\":\"990000\",\"RPTDTRVFAIL\":\"0\",\"OPTRPTDTRVFAIL\":\"0\",\"RPTNORV\":\"100000\",\"RPTMEMNORV\":\"100000\",\"RPTFILENORV\":\"100000\",\"RPTDBNORV\":\"100000\",\"RPTSPD\":\"5000\",\"MTLVLNOSD\":\"1/11/22/33/44/55/66/77/88/99\",\"RPTDTRV\":\"990000\",\"DTRPT\":\"1000000\",\"MT\":\"200000\",\"RPTDTRVSUCC\":\"990000\"}";
//      List<UserInfo> userInfos = JSONArray.parseArray(xx, UserInfo.class);
//        long start = System.currentTimeMillis();
//        Any anys = JsonIterator.deserialize(xx);
//        Class<UserInfo> clazz = UserInfo.class;
//        Field[] declaredFields = clazz.getDeclaredFields();
//        List<UserInfo> userInfos = new ArrayList<>();
//        for (Any any : anys){
//            UserInfo userInfo = new UserInfo();
//            for (Field field : declaredFields){
//                field.setAccessible(true);
//                String s = field.getName().toUpperCase();
//                String typeName = field.getGenericType().getTypeName();
//                Any any1 = any.get(s);
//                String s1 = any1.toString();
//                if(typeName.contains("int")){
//                    if(StringUtils.isBlank(s1)){
//                        continue;
//                    }
//                    field.set(userInfo,Integer.valueOf(s1));
//                }else if (typeName.contains("String")){
//                    field.set(userInfo,String.valueOf(s1));
//                }
//                field.setAccessible(false);
//            }
//            userInfos.add(userInfo);
//        }
//
//        long end = System.currentTimeMillis();
//        System.out.println("耗时:"+(end-start));
//        getBeanFromStrJson(xx,UserInfo.class);

        getBeanFromStrJson1(xx,UserInfo.class);

    }

    public static List<AbstractMonData> getBeanFromStrJson1(String json ,Class<?> clz) throws Exception{
        List<AbstractMonData> resut = new LinkedList<AbstractMonData>();
        Map<String,Object> deserialize = JsonIterator.deserialize(json, Map.class);
        Object o = clz.newInstance();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            String s = field.getName().toUpperCase();
            String typeName = field.getGenericType().getTypeName();
            if(typeName.contains("int")){
                if(null == deserialize.get(s)){
                    continue;
                }
                field.set(o,Integer.valueOf(deserialize.get(s).toString()));
            }else if (typeName.contains("String")){
                field.set(o,String.valueOf(deserialize.get(s)));
            }
            field.setAccessible(false);
        }
        resut.add((AbstractMonData)o);
        return resut;
    }

    public static List<AbstractMonData> getBeanFromStrJson(String json, Class clz) throws Exception{

        List<AbstractMonData> resut = new LinkedList<AbstractMonData>();
        //公共类事件

        try {
            Any anys = JsonIterator.deserialize(json);
            Field[] declaredFields = clz.getDeclaredFields();
            for (Any any : anys){
                Object o = clz.newInstance();
                for (Field field : declaredFields){
                    field.setAccessible(true);
                    String s = field.getName().toUpperCase();
                    String typeName = field.getGenericType().getTypeName();
                    Any any1 = any.get(s);
                    String s1 = any1.toString();
                    if(typeName.contains("int")){
                        if(StringUtils.isBlank(s1)){
                            continue;
                        }
                        field.set(o,Integer.valueOf(s1));
                    }else if (typeName.contains("String")){
                        field.set(o,String.valueOf(s1));
                    }
                    field.setAccessible(false);
                }
                resut.add((AbstractMonData)o);
            }


        } catch (Exception e) {
            throw new Exception("json转换成对应实体失败！  json: " + json + " 类型： " + clz, e);
        }

        return resut;
    }
}
