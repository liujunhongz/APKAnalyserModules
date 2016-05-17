package oicq.wlogin_sdk.request;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.Array;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import javax.crypto.KeyAgreement;
import oicq.wlogin_sdk.code2d.fetch_code;
import oicq.wlogin_sdk.code2d.fetch_code.QRCodeCustom;
import oicq.wlogin_sdk.devicelock.DevlockBase;
import oicq.wlogin_sdk.devicelock.DevlockInfo;
import oicq.wlogin_sdk.devicelock.DevlockRst;
import oicq.wlogin_sdk.devicelock.TLV_CommRsp;
import oicq.wlogin_sdk.devicelock.i;
import oicq.wlogin_sdk.devicelock.j;
import oicq.wlogin_sdk.report.report_t1;
import oicq.wlogin_sdk.report.report_t2;
import oicq.wlogin_sdk.sharemem.WloginLoginInfo;
import oicq.wlogin_sdk.sharemem.WloginSigInfo;
import oicq.wlogin_sdk.sharemem.WloginSimpleInfo;
import oicq.wlogin_sdk.tlv_type.ao;
import oicq.wlogin_sdk.tlv_type.at;
import oicq.wlogin_sdk.tlv_type.ay;
import oicq.wlogin_sdk.tlv_type.bc;
import oicq.wlogin_sdk.tlv_type.ck;
import oicq.wlogin_sdk.tools.EcdhCrypt;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.InternationMsg;
import oicq.wlogin_sdk.tools.InternationMsg.MSG_TYPE;
import oicq.wlogin_sdk.tools.MD5;
import oicq.wlogin_sdk.tools.RSACrypt;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

public class WtloginHelper
  extends WtloginListener
{
  static final Object __sync_top = new Object();
  static int __top = 0;
  private boolean isForLocal = false;
  private long mAysncSeq = 0L;
  private Context mContext = null;
  private u mG = new u(null);
  private Handler mHelperHandler = newHelperHandler();
  private WtloginListener mListener = null;
  private int mMainSigMap = 16724722;
  private int mMiscBitmap = 524156;
  private long mOpenAppid = 715019303L;
  private oicq.wlogin_sdk.register.h mRegStatus = new oicq.wlogin_sdk.register.h();
  private int mSubSigMap = 66560;
  
  public WtloginHelper(Context paramContext)
  {
    isForLocal = false;
    mContext = paramContext;
    mG.a(paramContext);
    RequestInit();
  }
  
  public WtloginHelper(Context paramContext, Object paramObject)
  {
    WtloginMsfListener.TicketMgr = paramObject;
    localInit(paramContext, isForLocal);
  }
  
  public WtloginHelper(Context paramContext, boolean paramBoolean)
  {
    localInit(paramContext, paramBoolean);
  }
  
  private void AsyncGenRSAKey()
  {
    if (isForLocal) {
      return;
    }
    new WtloginHelper.2(this, "AsyncGenRSAKey").start();
  }
  
  private int CheckPictureAndGetSt(String paramString, byte[] paramArrayOfByte, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte1, int paramInt)
  {
    if ((paramString == null) || (paramArrayOfByte == null) || (paramWUserSigInfo == null)) {
      return 64519;
    }
    if (paramInt == 0)
    {
      new HelperThread(this, mHelperHandler, paramString, paramArrayOfByte, paramWUserSigInfo, paramArrayOfByte1, "CheckPictureAndGetSt").RunReq(2);
      return 64535;
    }
    paramInt = 0;
    if (_seqence == 0L) {
      _seqence = mAysncSeq;
    }
    u localU = mG.a(_seqence);
    _seqence = h;
    async_context localAsync_context = u.b(_seqence);
    util.LOGI("user:" + paramString + " CheckPictureAndGetSt" + " Seq:" + h + " ...", paramString);
    g = paramString;
    _last_err_msg = new ErrMsg();
    long l1;
    if (!util.check_uin_account(paramString).booleanValue())
    {
      long l2 = localU.b(paramString);
      l1 = l2;
      if (l2 != 0L)
      {
        paramInt = 1;
        l1 = l2;
      }
    }
    int i;
    for (;;)
    {
      if (paramInt == 1)
      {
        f = l1;
        uin = (l1 + "");
      }
      int j = new o(localU).a(paramArrayOfByte, mMiscBitmap, mSubSigMap, _sub_appid_list, paramWUserSigInfo);
      i = j;
      if (j == 204) {
        i = new q(localU).a(mMiscBitmap, mSubSigMap, _sub_appid_list, paramWUserSigInfo);
      }
      if ((i == 0) || (i == 160)) {
        break;
      }
      paramInt = i;
      paramArrayOfByte = GetUserSigInfoTicket(paramWUserSigInfo, 128);
      if (paramArrayOfByte != null) {
        break label1382;
      }
      paramArrayOfByte = new Ticket();
      label343:
      u.ah.commit_t2(f, g, util.format_ret_code(paramInt), paramInt);
      if (paramInt != 0) {
        break label1354;
      }
      if ((_sig != null) && (_sig.length != 0)) {
        RequestReport(0, _sig, _sig_key, f, _appid);
      }
      label410:
      if ((d != null) && (d.f() != 0))
      {
        mG.d = d;
        RequestReportError(0, _sig, _sig_key, f, _appid, 1);
      }
      u.b();
      localU.h();
      util.LOGI("user:" + paramString + " CheckPictureAndGetSt" + " Seq:" + h + " ret=" + paramInt, "" + f);
      return paramInt;
      l1 = Long.parseLong(paramString);
      paramInt = 1;
    }
    if (!util.check_uin_account(paramString).booleanValue())
    {
      l1 = localU.b(paramString);
      if (l1 == 0L) {
        break label1385;
      }
      paramInt = 1;
    }
    label877:
    label1114:
    label1229:
    label1354:
    label1382:
    label1385:
    for (;;)
    {
      if ((_msalt == 0L) && (paramInt == 0))
      {
        paramInt = 64533;
        break;
        l1 = Long.parseLong(paramString);
        paramInt = 1;
        continue;
      }
      f = l1;
      uin = (l1 + "");
      paramInt = i;
      if (i == 160) {
        break;
      }
      if ((_reserveData != null) && (_reserveData.length > 3))
      {
        i = util.buf_to_int32(_reserveData, 0);
        util.LOGI("MSF SSO SEQ:" + i, paramString);
      }
      for (;;)
      {
        paramArrayOfByte = localU.a(l1, _appid);
        if (paramArrayOfByte == null) {
          break label877;
        }
        paramWUserSigInfo.get_clone(paramArrayOfByte);
        if ((_sub_appid_list == null) || (paramArrayOfByte1 == null) || (_sub_appid_list.length * 2 != paramArrayOfByte1.length)) {
          break;
        }
        paramInt = 0;
        while ((_sub_appid_list != null) && (paramInt < _sub_appid_list.length))
        {
          paramArrayOfByte = localU.a(l1, _sub_appid_list[paramInt]);
          if (paramArrayOfByte != null)
          {
            paramArrayOfByte1[(paramInt * 2)] = ((byte[])_userSt_Key.clone());
            paramArrayOfByte1[(paramInt * 2 + 1)] = ((byte[])_userStSig.clone());
          }
          paramInt += 1;
        }
        i = 0;
      }
      paramInt = 0;
      break;
      if ((_in_ksid != null) && (_in_ksid.length > 0)) {}
      for (paramArrayOfByte = (byte[])_in_ksid.clone();; paramArrayOfByte = u.Y)
      {
        if (_tmp_pwd_type == 0) {
          break label1114;
        }
        paramInt = new l(localU).a(_appid, _sub_appid, f, 0, u.ab, _tmp_pwd, null, mMiscBitmap, mSubSigMap, _sub_appid_list, _main_sigmap, _sub_appid, u.w, 0, 0, 1, paramArrayOfByte, paramWUserSigInfo);
        i = paramInt;
        if (paramInt == 204) {
          i = new q(localU).a(mMiscBitmap, mSubSigMap, _sub_appid_list, paramWUserSigInfo);
        }
        if (i != 0)
        {
          paramInt = i;
          if (i != 160) {
            break;
          }
        }
        l1 = localU.b(paramString);
        uin = (l1 + "");
        paramInt = i;
        if (i == 160) {
          break;
        }
        paramArrayOfByte = localU.a(l1, _appid);
        if (paramArrayOfByte != null) {
          break label1229;
        }
        paramInt = 64532;
        break;
      }
      byte[] arrayOfByte = new byte[4];
      util.int64_to_buf32(arrayOfByte, 0, System.currentTimeMillis() / 1000L + u.aa);
      if (_isSmslogin) {}
      for (paramInt = 3;; paramInt = 1)
      {
        paramInt = new l(localU).a(_appid, _sub_appid, f, 0, u.ab, arrayOfByte, _tmp_pwd, paramInt, mMiscBitmap, mSubSigMap, _sub_appid_list, _main_sigmap, _sub_appid, u.w, 0, 0, 1, paramArrayOfByte, paramWUserSigInfo);
        break;
      }
      paramWUserSigInfo.get_clone(paramArrayOfByte);
      if ((_sub_appid_list != null) && (paramArrayOfByte1 != null) && (_sub_appid_list.length * 2 == paramArrayOfByte1.length))
      {
        paramInt = 0;
        while ((_sub_appid_list != null) && (paramInt < _sub_appid_list.length))
        {
          paramArrayOfByte = localU.a(l1, _sub_appid_list[paramInt]);
          if (paramArrayOfByte != null)
          {
            paramArrayOfByte1[(paramInt * 2)] = ((byte[])_userSt_Key.clone());
            paramArrayOfByte1[(paramInt * 2 + 1)] = ((byte[])_userStSig.clone());
          }
          paramInt += 1;
        }
      }
      paramInt = 0;
      break;
      RequestReportError(0, _sig, _sig_key, f, _appid, 0);
      break label410;
      break label343;
    }
  }
  
  private int CheckSMSAndGetSt(String paramString, byte[] paramArrayOfByte, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte1, int paramInt)
  {
    if ((paramString == null) || (paramArrayOfByte == null) || (paramWUserSigInfo == null)) {
      return 64519;
    }
    if (paramInt == 0)
    {
      new HelperThread(this, mHelperHandler, paramString, paramArrayOfByte, paramWUserSigInfo, paramArrayOfByte1, "CheckSMSAndGetSt").RunReq(4);
      return 64535;
    }
    if (_seqence == 0L) {
      _seqence = mAysncSeq;
    }
    u localU = mG.a(_seqence);
    _seqence = h;
    async_context localAsync_context = u.b(_seqence);
    util.LOGI("user:" + paramString + " CheckSMSAndGetSt" + " Seq:" + h + " ...", paramString);
    g = paramString;
    f = 0L;
    _last_err_msg = new ErrMsg();
    if ((_reserveData != null) && (_reserveData.length > 3))
    {
      i = util.buf_to_int32(_reserveData, 0);
      util.LOGI("MSF SSO SEQ:" + i, paramString);
    }
    while (!util.check_uin_account(paramString).booleanValue())
    {
      l = localU.b(paramString);
      if (l != 0L) {
        break label779;
      }
      util.LOGI("user:" + paramString + " have not found uin record.", paramString);
      paramInt = 64533;
      paramArrayOfByte = GetUserSigInfoTicket(paramWUserSigInfo, 128);
      if (paramArrayOfByte != null) {
        break label776;
      }
      paramArrayOfByte = new Ticket();
      label305:
      u.ah.commit_t2(f, g, util.format_ret_code(paramInt), paramInt);
      if (paramInt != 0) {
        break label748;
      }
      if ((_sig != null) && (_sig.length != 0)) {
        RequestReport(0, _sig, _sig_key, f, _appid);
      }
      label372:
      if ((d != null) && (d.f() != 0))
      {
        mG.d = d;
        RequestReportError(0, _sig, _sig_key, f, _appid, 1);
      }
      u.b();
      localU.h();
      util.LOGI("user:" + paramString + " CheckSMSAndGetSt" + " Seq:" + h + " ret=" + paramInt, "" + f);
      return paramInt;
      i = 0;
    }
    long l = Long.parseLong(paramString);
    label748:
    label776:
    label779:
    for (;;)
    {
      f = l;
      uin = (l + "");
      int i = new p(localU).a(paramArrayOfByte, mMiscBitmap, mSubSigMap, _sub_appid_list, paramWUserSigInfo);
      paramInt = i;
      if (i != 0) {
        break;
      }
      paramArrayOfByte = localU.a(l, _appid);
      if (paramArrayOfByte == null)
      {
        paramInt = 64532;
        break;
      }
      paramWUserSigInfo.get_clone(paramArrayOfByte);
      if ((_sub_appid_list != null) && (paramArrayOfByte1 != null) && (_sub_appid_list.length * 2 == paramArrayOfByte1.length))
      {
        paramInt = 0;
        while ((_sub_appid_list != null) && (paramInt < _sub_appid_list.length))
        {
          paramArrayOfByte = localU.a(l, _sub_appid_list[paramInt]);
          if (paramArrayOfByte != null)
          {
            paramArrayOfByte1[(paramInt * 2)] = ((byte[])_userSt_Key.clone());
            paramArrayOfByte1[(paramInt * 2 + 1)] = ((byte[])_userStSig.clone());
          }
          paramInt += 1;
        }
      }
      paramInt = 0;
      break;
      RequestReportError(0, _sig, _sig_key, f, _appid, 0);
      break label372;
      break label305;
    }
  }
  
  private int CheckSMSVerifyLoginAccount(long paramLong1, long paramLong2, String paramString, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    oicq.wlogin_sdk.register.h.w = false;
    oicq.wlogin_sdk.register.h.x = 0L;
    if ((paramString == null) || (paramWUserSigInfo == null)) {
      return 64519;
    }
    if (paramInt == 0)
    {
      new HelperThread(this, mHelperHandler, paramLong1, paramLong2, paramString, paramWUserSigInfo, "CheckSMSVerifyLoginAccount").RunReq(12);
      return 64535;
    }
    Object localObject = mG.a(0L);
    _seqence = h;
    mAysncSeq = h;
    async_context localAsync_context = u.b(h);
    g = paramString;
    util.LOGI("user:" + paramString + " Seq:" + h + " CheckSMSVerifyLoginAccount ...", paramString);
    _login_bitmap = _login_bitmap;
    _last_err_msg = new ErrMsg();
    int i = new w((u)localObject).a(paramLong1, paramLong2, mMainSigMap, u.Y, paramString, mMiscBitmap, mSubSigMap, null, paramWUserSigInfo);
    paramInt = i;
    if (i == 208) {
      paramInt = 0;
    }
    localObject = new StringBuilder().append("user:").append(g).append(" Seq:").append(h).append(" CheckSMSVerifyLoginAccount ret=");
    if (paramInt > 0) {}
    for (paramWUserSigInfo = Integer.toHexString(paramInt);; paramWUserSigInfo = Integer.valueOf(paramInt))
    {
      util.LOGI(paramWUserSigInfo, paramString);
      return paramInt;
    }
  }
  
  private WloginSigInfo FindUserSig(long paramLong1, long paramLong2)
  {
    return mG.a(paramLong1, paramLong2);
  }
  
  private byte[] GetA1ByAccount(String paramString, long paramLong)
  {
    if (paramString == null) {
      return null;
    }
    long l1;
    Object localObject;
    if (!util.check_uin_account(paramString).booleanValue())
    {
      long l2 = mG.b(paramString);
      l1 = l2;
      if (l2 != 0L) {
        break label110;
      }
      localObject = null;
    }
    while ((localObject == null) || (_en_A1 == null) || (_en_A1.length <= 0))
    {
      util.LOGI("userAccount:" + paramString + " dwAppid:" + paramLong + " GetA1ByAccount return: null", paramString);
      return null;
      l1 = Long.parseLong(paramString);
      label110:
      WloginSigInfo localWloginSigInfo = mG.a(l1, paramLong);
      localObject = localWloginSigInfo;
      if (localWloginSigInfo == null) {
        localObject = localWloginSigInfo;
      }
    }
    util.LOGI("userAccount:" + paramString + " dwAppid:" + paramLong + " GetA1ByAccount return: not null", paramString);
    return (byte[])_en_A1.clone();
  }
  
  private int GetA1WithA1(String paramString, long paramLong1, long paramLong2, int paramInt1, byte[] paramArrayOfByte1, long paramLong3, long paramLong4, long paramLong5, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, WUserSigInfo paramWUserSigInfo, WFastLoginInfo paramWFastLoginInfo, int paramInt2)
  {
    if ((paramString == null) || (paramArrayOfByte1 == null) || (paramArrayOfByte2 == null) || (paramArrayOfByte3 == null) || (paramWUserSigInfo == null) || (paramWFastLoginInfo == null)) {
      return 64519;
    }
    int i = paramInt1 | 0xC0;
    if (paramInt2 == 0)
    {
      new HelperThread(this, mHelperHandler, paramString, paramLong1, paramLong2, i, paramArrayOfByte1, paramLong3, paramLong4, paramLong5, paramArrayOfByte2, paramArrayOfByte3, paramWUserSigInfo, paramWFastLoginInfo, "GetA1WithA1").RunReq(6);
      return 64535;
    }
    u localU = mG.a(0L);
    _seqence = h;
    Object localObject = u.b(h);
    util.LOGI("wtlogin login with GetA1WithA1:" + paramString + " dwSrcAppid:" + paramLong1 + " dwMainSigMap:" + i + " dwSubSrcAppid:" + paramLong2 + " dstAppName:" + new String(paramArrayOfByte1) + " dwDstAppid:" + paramLong4 + " dwSubDstAppid:" + paramLong5 + " Seq:" + h + " ...", paramString);
    paramInt1 = util.get_saved_network_type(mContext);
    u.B = util.get_network_type(mContext);
    if (paramInt1 != u.B)
    {
      util.set_net_retry_type(mContext, 0);
      util.save_network_type(mContext, u.B);
    }
    u.D = util.get_apn_string(mContext).getBytes();
    g = paramString;
    f = 0L;
    _sappid = paramLong1;
    _appid = paramLong1;
    _sub_appid = paramLong2;
    _main_sigmap = i;
    _last_err_msg = new ErrMsg();
    u.ah.add_t2(new report_t2("login", new String(u.A), System.currentTimeMillis(), paramLong4, paramLong5, null));
    long l1;
    if (!util.check_uin_account(paramString).booleanValue())
    {
      long l2 = localU.b(paramString);
      l1 = l2;
      if (l2 != 0L) {
        break label724;
      }
      util.LOGI("user:" + paramString + " have not found uin record.", paramString);
      paramInt1 = 64533;
      paramArrayOfByte2 = GetUserSigInfoTicket(paramWUserSigInfo, 128);
      if (paramArrayOfByte2 != null) {
        break label960;
      }
      paramArrayOfByte2 = new Ticket();
    }
    label724:
    label960:
    for (;;)
    {
      u.ah.commit_t2(f, g, util.format_ret_code(paramInt1), paramInt1);
      if (paramInt1 == 0) {
        if ((_sig != null) && (_sig.length != 0)) {
          RequestReport(0, _sig, _sig_key, f, paramLong1);
        }
      }
      for (;;)
      {
        if ((d != null) && (d.f() != 0))
        {
          mG.d = d;
          RequestReportError(0, _sig, _sig_key, f, paramLong1, 1);
        }
        u.b();
        localU.h();
        util.LOGI("wtlogin login with GetA1WithA1:" + paramString + " dwSrcAppid:" + paramLong1 + " dwMainSigMap:" + i + " dwSubSrcAppid:" + paramLong2 + " dstAppName:" + new String(paramArrayOfByte1) + " dwDstAppid:" + paramLong4 + " dwSubDstAppid:" + paramLong5 + " Seq:" + h + " ret=" + paramInt1, paramString);
        return paramInt1;
        l1 = Long.parseLong(paramString);
        f = l1;
        localU.j();
        localObject = GetA1ByAccount(paramString, paramLong1);
        byte[] arrayOfByte = GetNoPicSigByAccount(paramString, paramLong1);
        if ((localObject == null) || (localObject.length <= 0))
        {
          util.LOGI("user:" + paramString + " have no a1 or pic_sig.", paramString);
          paramInt1 = 64520;
          break;
        }
        util.LOGI("user:" + paramString + " login with A1 exchange A1.", paramString);
        paramInt2 = new m(localU).a(l1, paramLong1, paramLong2, 1, i, (byte[])localObject, arrayOfByte, mMiscBitmap, mSubSigMap, null, paramArrayOfByte1, paramLong3, paramLong4, paramLong5, paramArrayOfByte2, paramArrayOfByte3, paramWUserSigInfo);
        paramInt1 = paramInt2;
        if (paramInt2 != 0) {
          break;
        }
        paramArrayOfByte2 = localU.a(l1, paramLong1);
        if (paramArrayOfByte2 == null)
        {
          paramInt1 = 64532;
          break;
        }
        paramWUserSigInfo.get_clone(paramArrayOfByte2);
        paramWFastLoginInfo.get_clone(j);
        paramInt1 = paramInt2;
        break;
        RequestReportError(0, _sig, _sig_key, f, paramLong1, 0);
      }
    }
  }
  
  private int GetFastLoginInfo(byte[] paramArrayOfByte, async_context paramAsync_context)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 3) || (paramAsync_context == null)) {
      return 64519;
    }
    oicq.wlogin_sdk.tlv_type.h localH = new oicq.wlogin_sdk.tlv_type.h();
    oicq.wlogin_sdk.tlv_type.n localN = new oicq.wlogin_sdk.tlv_type.n();
    bc localBc = new bc();
    Object localObject = new ao();
    int i = paramArrayOfByte.length;
    if (localH.b(paramArrayOfByte, 3, i) < 0)
    {
      util.LOGI("fast login info no tgtgt data", "");
      return 64519;
    }
    if (localN.b(paramArrayOfByte, 3, i) < 0)
    {
      util.LOGI("fast login info no gtkey data", "");
      return 64519;
    }
    if (localBc.b(paramArrayOfByte, 3, i) < 0)
    {
      util.LOGI("fast login info no nopicsig data", "");
      return 64519;
    }
    if (((ao)localObject).b(paramArrayOfByte, 3, i) > 0)
    {
      paramArrayOfByte = ((ao)localObject).b();
      localObject = u.y;
      util.LOGD("new imei:" + util.buf_to_string(paramArrayOfByte) + " old imei:" + util.buf_to_string((byte[])localObject));
      if (!Arrays.equals(paramArrayOfByte, (byte[])localObject))
      {
        util.LOGI("fast login info imei not equal", "");
        util.save_file_imei(u.r, paramArrayOfByte);
        u.y = (byte[])paramArrayOfByte.clone();
        u.z = (byte[])paramArrayOfByte.clone();
      }
    }
    _tmp_pwd = k.b(localH.b(), localN.b());
    _tmp_no_pic_sig = localBc.b();
    return 0;
  }
  
  public static WFastLoginInfo GetFastLoginUrl(String paramString, long paramLong)
  {
    if (paramString != null) {
      try
      {
        if (paramString.length() != 0)
        {
          util.LOGI("packageName:" + paramString + " uin:" + paramLong, "");
          if (paramLong == 1689053018L) {}
          for (String str = "http://imgcache.qq.com/wtlogin" + "/test";; str = "http://imgcache.qq.com/wtlogin" + "/app")
          {
            paramString = paramString.split("\\.");
            int i = 0;
            while (i < paramString.length)
            {
              str = str + "/";
              str = str + paramString[i];
              i += 1;
            }
          }
          paramString = new WFastLoginInfo();
          iconUrl = (str + "/icon.png");
          adUrl = (str + "/ad_img.png");
          profileUrl = (str + "/profile.js");
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        return null;
      }
    }
    return null;
  }
  
  private byte[] GetNoPicSigByAccount(String paramString, long paramLong)
  {
    if (paramString == null) {
      return null;
    }
    long l1;
    Object localObject;
    if (!util.check_uin_account(paramString).booleanValue())
    {
      long l2 = mG.b(paramString);
      l1 = l2;
      if (l2 != 0L) {
        break label110;
      }
      localObject = null;
    }
    while ((localObject == null) || (_noPicSig == null) || (_noPicSig.length <= 0))
    {
      util.LOGI("userAccount:" + paramString + " dwAppid:" + paramLong + " GetNoPicSigByAccount return: null", paramString);
      return null;
      l1 = Long.parseLong(paramString);
      label110:
      WloginSigInfo localWloginSigInfo = mG.a(l1, paramLong);
      localObject = localWloginSigInfo;
      if (localWloginSigInfo == null) {
        localObject = localWloginSigInfo;
      }
    }
    util.LOGI("userAccount:" + paramString + " dwAppid:" + paramLong + " GetNoPicSigByAccount return: not null", paramString);
    return (byte[])_noPicSig.clone();
  }
  
  private int GetStWithPasswd(String paramString1, long paramLong1, int paramInt1, long paramLong2, long[] paramArrayOfLong, boolean paramBoolean1, String paramString2, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte, boolean paramBoolean2, int paramInt2)
  {
    if ((paramString1 == null) || (paramWUserSigInfo == null)) {
      return 64519;
    }
    int j = paramInt1 | 0xC0;
    if (paramInt2 == 0)
    {
      new HelperThread(this, mHelperHandler, paramString1, paramLong1, j, paramLong2, paramArrayOfLong, paramBoolean1, paramString2, paramWUserSigInfo, paramArrayOfByte, paramBoolean2, "GetStWithPasswd").RunReq(0);
      return 64535;
    }
    paramInt1 = 1;
    u localU;
    async_context localAsync_context;
    Object localObject;
    label488:
    long l2;
    long l1;
    if ((paramBoolean2) && (!oicq.wlogin_sdk.register.h.w))
    {
      if (_seqence == 0L) {
        _seqence = mAysncSeq;
      }
      localU = mG.a(_seqence);
      _seqence = h;
      localAsync_context = u.b(h);
      util.LOGI("wtlogin login with GetStWithPasswd:user:" + paramString1 + " dwAppid:" + paramLong1 + " dwMainSigMap:" + j + " dwSubAppid:" + paramLong2 + " Seq:" + h + " ...", paramString1);
      _isSmslogin = paramBoolean2;
      localObject = paramString2;
      if (paramBoolean2)
      {
        localObject = paramString2;
        if (paramString2.length() == 0) {
          localObject = _mpasswd;
        }
      }
      oicq.wlogin_sdk.register.h.w = false;
      oicq.wlogin_sdk.register.h.y = "";
      paramString2 = (String)localObject;
      if (localObject != null)
      {
        paramString2 = (String)localObject;
        if (((String)localObject).length() > 16) {
          paramString2 = ((String)localObject).substring(0, 16);
        }
      }
      paramInt2 = util.get_saved_network_type(mContext);
      u.B = util.get_network_type(mContext);
      if (paramInt2 != u.B)
      {
        util.set_net_retry_type(mContext, 0);
        util.save_network_type(mContext, u.B);
      }
      u.D = util.get_apn_string(mContext).getBytes();
      g = paramString1;
      f = 0L;
      _sappid = paramLong1;
      _appid = paramLong1;
      _sub_appid_list = null;
      _sub_appid = paramLong2;
      _main_sigmap = j;
      _login_bitmap = _login_bitmap;
      _last_err_msg = new ErrMsg();
      if (paramArrayOfLong != null) {
        _sub_appid_list = ((long[])paramArrayOfLong.clone());
      }
      if ((_reserveData == null) || (_reserveData.length <= 3)) {
        break label919;
      }
      i = util.buf_to_int32(_reserveData, 0);
      util.LOGI("MSF SSO SEQ:" + i, paramString1);
      u.ah.add_t2(new report_t2("login", new String(u.A), System.currentTimeMillis(), paramLong1, paramLong2, paramArrayOfLong));
      if (util.check_uin_account(paramString1).booleanValue()) {
        break label928;
      }
      l2 = localU.b(paramString1);
      l1 = l2;
      if (l2 == 0L)
      {
        paramInt1 = 0;
        l1 = l2;
      }
      label558:
      if ((paramString2 == null) || (paramString2.length() <= 0)) {
        break label960;
      }
      if (!paramBoolean1) {
        break label947;
      }
    }
    for (;;)
    {
      try
      {
        _tmp_pwd = ((byte[])paramString2.getBytes("ISO-8859-1").clone());
        _tmp_pwd_type = 0;
        if (paramInt1 != 0) {
          break label1248;
        }
        if (paramString1.length() <= util.MAX_NAME_LEN) {
          break label1156;
        }
        paramInt1 = 64528;
      }
      catch (Exception paramArrayOfLong)
      {
        label621:
        label645:
        label716:
        label919:
        label928:
        paramInt1 = 64523;
        continue;
      }
      paramArrayOfLong = GetUserSigInfoTicket(paramWUserSigInfo, 128);
      if (paramArrayOfLong != null) {
        break label1921;
      }
      paramArrayOfLong = new Ticket();
      u.ah.commit_t2(f, g, util.format_ret_code(paramInt1), paramInt1);
      if (paramInt1 != 0) {
        break label1877;
      }
      if ((_sig != null) && (_sig.length != 0)) {
        RequestReport(0, _sig, _sig_key, f, _appid);
      }
      if ((d != null) && (d.f() != 0))
      {
        mG.d = d;
        RequestReportError(0, _sig, _sig_key, f, _appid, 1);
      }
      u.b();
      localU.h();
      util.LOGI("wtlogin login with GetStWithPasswd:user:" + paramString1 + " dwAppid:" + paramLong1 + " dwMainSigMap:" + j + " dwSubAppid:" + paramLong2 + " Seq:" + h + " ret=" + paramInt1, "" + f);
      return paramInt1;
      localU = mG.a(0L);
      _seqence = h;
      mAysncSeq = h;
      break;
      i = 0;
      break label488;
      l1 = Long.parseLong(paramString1);
      break label558;
      label947:
      _tmp_pwd = MD5.toMD5Byte(paramString2);
      continue;
      label960:
      if ((_fastLoginBuf != null) && (_fastLoginBuf.length > 0))
      {
        util.LOGI("GetFastLoginInfo ...", paramString1);
        if (GetFastLoginInfo(_fastLoginBuf, localAsync_context) < 0)
        {
          util.LOGI("GetFastLoginInfo failed", paramString1);
          paramInt1 = 64519;
          continue;
        }
        if (!paramString1.matches("([0-9]{5,10})@qq\\.com")) {
          break label1924;
        }
        l1 = Long.valueOf(paramString1.replaceAll("([0-9]{5,10})@qq\\.com", "$1")).longValue();
        paramInt1 = 1;
        localU.a(paramString1, Long.valueOf(l1));
      }
    }
    label1156:
    label1248:
    label1598:
    label1747:
    label1877:
    label1921:
    label1924:
    for (;;)
    {
      if ((_tmp_pwd == null) || (_tmp_pwd.length < 16))
      {
        paramInt1 = 64520;
        break label621;
        if ((oicq.wlogin_sdk.code2d.c.q != null) && (oicq.wlogin_sdk.code2d.c.q.length > 0))
        {
          _tmp_pwd = oicq.wlogin_sdk.code2d.c.q;
          _tmp_no_pic_sig = oicq.wlogin_sdk.code2d.c.r;
          oicq.wlogin_sdk.code2d.c.q = null;
          oicq.wlogin_sdk.code2d.c.r = null;
          continue;
        }
        _tmp_pwd = GetA1ByAccount(paramString1, paramLong1);
        _tmp_no_pic_sig = GetNoPicSigByAccount(paramString1, paramLong1);
        continue;
      }
      _tmp_pwd_type = 1;
      break;
      paramInt2 = new t(localU).a(paramLong1, paramLong2, 1, j, paramString1.getBytes(), u.w, 0, 0, 1, mMiscBitmap, mSubSigMap, paramArrayOfLong, paramWUserSigInfo);
      paramInt1 = paramInt2;
      if (paramInt2 != 0) {
        break label621;
      }
      l2 = localU.b(paramString1);
      l1 = l2;
      if (_msalt == 0L)
      {
        l1 = l2;
        if (l2 == 0L)
        {
          paramInt1 = 64533;
          break label621;
        }
      }
      if (oicq.wlogin_sdk.register.h.x != 0L)
      {
        _msalt = oicq.wlogin_sdk.register.h.x;
        oicq.wlogin_sdk.register.h.x = 0L;
      }
      f = l1;
      uin = ("" + l1);
      if ((_in_ksid != null) && (_in_ksid.length > 0)) {}
      for (paramString2 = (byte[])_in_ksid.clone();; paramString2 = u.Y)
      {
        if (_tmp_pwd_type == 0) {
          break label1598;
        }
        util.LOGI("user:" + paramString1 + " login with saved A1.", "" + f);
        paramInt1 = new l(localU).a(paramLong1, paramLong2, f, 0, u.ab, _tmp_pwd, _tmp_no_pic_sig, mMiscBitmap, mSubSigMap, paramArrayOfLong, j, paramLong2, u.w, 0, 0, 1, paramString2, paramWUserSigInfo);
        paramInt2 = paramInt1;
        if (paramInt1 == 204) {
          paramInt2 = new q(localU).a(mMiscBitmap, mSubSigMap, paramArrayOfLong, paramWUserSigInfo);
        }
        if (paramInt2 != 0)
        {
          paramInt1 = paramInt2;
          if (paramInt2 != 160) {
            break;
          }
        }
        l2 = l1;
        if (l1 == 0L)
        {
          l2 = localU.b(paramString1);
          f = l2;
          uin = ("" + l2);
        }
        paramInt1 = paramInt2;
        if (paramInt2 == 160) {
          break;
        }
        paramString2 = localU.a(l2, paramLong1);
        if (paramString2 != null) {
          break label1747;
        }
        paramInt1 = 64532;
        break;
      }
      util.LOGI("user:" + paramString1 + " login with input password.", "" + f);
      localObject = new byte[4];
      util.int64_to_buf32((byte[])localObject, 0, System.currentTimeMillis() / 1000L + u.aa);
      if (paramBoolean2) {}
      for (paramInt1 = 3;; paramInt1 = 1)
      {
        paramInt1 = new l(localU).a(paramLong1, paramLong2, f, 0, u.ab, (byte[])localObject, _tmp_pwd, paramInt1, mMiscBitmap, mSubSigMap, paramArrayOfLong, j, paramLong2, u.w, 0, 0, 1, paramString2, paramWUserSigInfo);
        break;
      }
      paramWUserSigInfo.get_clone(paramString2);
      paramInt1 = paramInt2;
      if (paramArrayOfLong == null) {
        break label621;
      }
      paramInt1 = paramInt2;
      if (paramArrayOfByte == null) {
        break label621;
      }
      paramInt1 = paramInt2;
      if (paramArrayOfLong.length * 2 != paramArrayOfByte.length) {
        break label621;
      }
      int i = 0;
      for (;;)
      {
        paramInt1 = paramInt2;
        if (paramArrayOfLong == null) {
          break;
        }
        paramInt1 = paramInt2;
        if (i >= paramArrayOfLong.length) {
          break;
        }
        paramString2 = localU.a(l2, paramArrayOfLong[i]);
        if (paramString2 != null)
        {
          paramArrayOfByte[(i * 2)] = ((byte[])_userSt_Key.clone());
          paramArrayOfByte[(i * 2 + 1)] = ((byte[])_userStSig.clone());
        }
        i += 1;
      }
      if ((paramInt1 == 2) || (paramInt1 == 160)) {
        break label716;
      }
      RequestReportError(0, _sig, _sig_key, f, _appid, 0);
      break label716;
      break label645;
    }
  }
  
  private int GetStWithoutPasswd(String paramString, long paramLong1, long paramLong2, long paramLong3, int paramInt1, long paramLong4, long[] paramArrayOfLong, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte1, byte[][] paramArrayOfByte2, int paramInt2, WtTicketPromise paramWtTicketPromise)
  {
    if ((paramString == null) || (paramWUserSigInfo == null)) {
      return 64519;
    }
    int i = paramInt1 | 0xC0;
    if (paramInt2 == 0)
    {
      new HelperThread(this, mHelperHandler, paramWtTicketPromise, paramString, paramLong1, paramLong2, paramLong3, i, paramLong4, paramArrayOfLong, paramWUserSigInfo, paramArrayOfByte1, paramArrayOfByte2, "GetStWithoutPasswd").RunReq(5);
      return 64535;
    }
    paramWtTicketPromise = mG.a(0L);
    _seqence = h;
    async_context localAsync_context = u.b(h);
    util.LOGI("wtlogin login with GetStWithoutPasswd:user:" + paramString + " dwSrcAppid:" + paramLong1 + " dwDstAppid:" + paramLong2 + " dwDstAppPri:" + paramLong3 + " dwMainSigMap:" + i + " dwSubDstAppid:" + paramLong4 + " Seq:" + h + " ...", paramString);
    paramInt1 = util.get_saved_network_type(mContext);
    u.B = util.get_network_type(mContext);
    if (paramInt1 != u.B)
    {
      util.set_net_retry_type(mContext, 0);
      util.save_network_type(mContext, u.B);
    }
    u.D = util.get_apn_string(mContext).getBytes();
    g = paramString;
    f = 0L;
    _sappid = paramLong1;
    _appid = paramLong2;
    _sub_appid = paramLong4;
    _main_sigmap = i;
    _last_err_msg = new ErrMsg();
    if (paramArrayOfLong != null) {
      _sub_appid_list = ((long[])paramArrayOfLong.clone());
    }
    long l1;
    if ((_reserveData != null) && (_reserveData.length > 3))
    {
      i = util.buf_to_int32(_reserveData, 0);
      util.LOGI("MSF SSO SEQ:" + i, paramString);
      u.ah.add_t2(new report_t2("exchg", new String(u.A), System.currentTimeMillis(), paramLong2, paramLong4, paramArrayOfLong));
      if (util.check_uin_account(paramString).booleanValue()) {
        break label778;
      }
      long l2 = paramWtTicketPromise.b(paramString);
      l1 = l2;
      if (l2 != 0L) {
        break label784;
      }
      util.LOGI("user:" + paramString + " have not found uin record.", paramString);
      paramInt1 = 64533;
    }
    label481:
    label778:
    label784:
    label1685:
    label1823:
    for (;;)
    {
      paramArrayOfLong = GetUserSigInfoTicket(paramWUserSigInfo, 128);
      if (paramArrayOfLong == null) {
        paramArrayOfLong = new Ticket();
      }
      for (;;)
      {
        u.ah.commit_t2(f, g, util.format_ret_code(paramInt1), paramInt1);
        if (paramInt1 == 0) {
          if ((_sig != null) && (_sig.length != 0)) {
            RequestReport(0, _sig, _sig_key, f, _appid);
          }
        }
        for (;;)
        {
          if ((d != null) && (d.f() != 0))
          {
            mG.d = d;
            RequestReportError(0, _sig, _sig_key, f, _appid, 1);
          }
          u.b();
          paramWtTicketPromise.h();
          util.LOGI("wtlogin login with GetStWithoutPasswd:user:" + paramString + " dwSrcAppid:" + paramLong1 + " dwDstAppid:" + paramLong2 + " dwDstAppPri:" + paramLong3 + " dwMainSigMap:" + i + " dwSubDstAppid:" + paramLong4 + " Seq:" + h + " ret=" + paramInt1, "" + f);
          return paramInt1;
          i = 0;
          break;
          l1 = Long.parseLong(paramString);
          f = l1;
          uin = ("" + l1);
          if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length == 4) && (paramArrayOfByte2[0] != null) && (paramArrayOfByte2[0].length == 1) && (paramArrayOfByte2[0][0] == 1))
          {
            util.LOGI("user:" + paramString + " exchange A2 from A2/D2/KEY.", "" + f);
            if ((paramArrayOfByte2[1] == null) || (paramArrayOfByte2[1].length == 0) || (paramArrayOfByte2[2] == null) || (paramArrayOfByte2[2].length == 0) || (paramArrayOfByte2[3] == null) || (paramArrayOfByte2[3].length == 0))
            {
              paramInt1 = 64532;
              break label481;
            }
            b = MD5.toMD5Byte(paramArrayOfByte2[3]);
            paramInt1 = new n(paramWtTicketPromise).a(l1, paramLong2, paramLong4, 1, i, paramArrayOfByte2[1], mMiscBitmap, mSubSigMap, paramArrayOfLong, paramArrayOfByte2[2], paramWUserSigInfo);
          }
          for (;;)
          {
            if (paramInt1 != 0) {
              break label1823;
            }
            paramArrayOfByte2 = paramWtTicketPromise.a(l1, paramLong2);
            if (paramArrayOfByte2 != null) {
              break label1685;
            }
            paramInt1 = 64532;
            break;
            if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length == 3) && (paramArrayOfByte2[0] != null) && (paramArrayOfByte2[0].length == 1) && (paramArrayOfByte2[0][0] == 2))
            {
              util.LOGI("user:" + paramString + " exchange A2 from A2/A2KEY.", "" + f);
              if ((paramArrayOfByte2[1] == null) || (paramArrayOfByte2[1].length == 0) || (paramArrayOfByte2[2] == null) || (paramArrayOfByte2[2].length == 0))
              {
                paramInt1 = 64532;
                break;
              }
              b = paramArrayOfByte2[2];
              paramInt1 = new n(paramWtTicketPromise).a(l1, paramLong2, paramLong4, 1, i, paramArrayOfByte2[1], mMiscBitmap, mSubSigMap, paramArrayOfLong, null, paramWUserSigInfo);
              continue;
            }
            paramWtTicketPromise.j();
            byte[] arrayOfByte1 = GetA1ByAccount(String.valueOf(f), paramLong1);
            byte[] arrayOfByte2 = GetNoPicSigByAccount(String.valueOf(f), paramLong1);
            if ((arrayOfByte1 != null) && (arrayOfByte1.length > 0) && (arrayOfByte2 != null) && (arrayOfByte2.length > 0))
            {
              util.LOGI("user:" + paramString + " exchange A2 from A1.", "" + f);
              _tmp_pwd = arrayOfByte1;
              _tmp_no_pic_sig = arrayOfByte2;
              if ((_in_ksid != null) && (_in_ksid.length > 0)) {}
              for (paramArrayOfByte2 = (byte[])_in_ksid.clone();; paramArrayOfByte2 = u.Y)
              {
                paramInt2 = new z(paramWtTicketPromise).a(paramLong2, 1, f, 0, u.ab, arrayOfByte1, arrayOfByte2, mMiscBitmap, mSubSigMap, paramArrayOfLong, i, paramLong4, 1, u.w, 0, 0, 1, paramArrayOfByte2, paramLong1, paramWUserSigInfo);
                paramInt1 = paramInt2;
                if (paramInt2 == 204) {
                  paramInt1 = new q(paramWtTicketPromise).a(mMiscBitmap, mSubSigMap, paramArrayOfLong, paramWUserSigInfo);
                }
                break;
              }
            }
            util.LOGI("user:" + paramString + " exchange A2 from A2.", "" + f);
            paramArrayOfByte2 = paramWtTicketPromise.a(l1, paramLong1);
            if ((paramArrayOfByte2 == null) || (_TGT == null) || (_TGT.length == 0) || (paramArrayOfByte2.iSExpireA2(u.f())))
            {
              paramInt1 = 64532;
              break;
            }
            util.LOGI("user:" + paramString + " exchange A2 from A2 without Priority.", "" + f);
            b = _TGTKey;
            paramInt1 = new n(paramWtTicketPromise).a(l1, paramLong2, paramLong4, 1, i, _TGT, mMiscBitmap, mSubSigMap, paramArrayOfLong, null, paramWUserSigInfo);
          }
          paramWUserSigInfo.get_clone(paramArrayOfByte2);
          if ((paramArrayOfLong == null) || (paramArrayOfByte1 == null) || (paramArrayOfLong.length * 2 != paramArrayOfByte1.length)) {
            break label1823;
          }
          paramInt2 = 0;
          while (paramInt2 < paramArrayOfLong.length)
          {
            paramArrayOfByte2 = paramWtTicketPromise.a(l1, paramArrayOfLong[paramInt2]);
            if (paramArrayOfByte2 != null)
            {
              paramArrayOfByte1[(paramInt2 * 2)] = ((byte[])_userSt_Key.clone());
              paramArrayOfByte1[(paramInt2 * 2 + 1)] = ((byte[])_userStSig.clone());
            }
            paramInt2 += 1;
          }
          RequestReportError(0, _sig, _sig_key, f, _appid, 0);
        }
      }
    }
  }
  
  private int GetStWithoutPasswd(String paramString, long paramLong1, long paramLong2, long paramLong3, int paramInt, WUserSigInfo paramWUserSigInfo, WtTicketPromise paramWtTicketPromise)
  {
    return GetStWithoutPasswd(paramString, paramLong1, paramLong2, -1L, paramInt, paramLong3, null, paramWUserSigInfo, (byte[][])null, (byte[][])null, 0, paramWtTicketPromise);
  }
  
  public static byte[] GetTicketSig(WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    paramWUserSigInfo = GetUserSigInfoTicket(paramWUserSigInfo, paramInt);
    if (paramWUserSigInfo != null) {
      return _sig;
    }
    return new byte[0];
  }
  
  public static byte[] GetTicketSigKey(WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    if ((paramInt != 64) && (paramInt != 262144) && (paramInt != 128) && (paramInt != 16384) && (paramInt != 32768) && (paramInt != 16777216)) {
      throw null;
    }
    paramWUserSigInfo = GetUserSigInfoTicket(paramWUserSigInfo, paramInt);
    if (paramWUserSigInfo != null) {
      return _sig_key;
    }
    return new byte[0];
  }
  
  public static Ticket GetUserSigInfoTicket(WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    if (paramInt == 4194304)
    {
      util.LOGI("get lhsig", "");
      return new Ticket(4194304, WloginSigInfo._LHSig, null, u.f(), 0L);
    }
    if (paramInt == 67108864)
    {
      util.LOGI("get qrpushsig", "");
      return new Ticket(67108864, WloginSigInfo._QRPUSHSig, null, u.f(), 0L);
    }
    if (paramWUserSigInfo == null)
    {
      util.LOGI("userInfo is null " + Integer.toHexString(paramInt), "");
      return null;
    }
    if (_tickets == null)
    {
      util.LOGI("tickets is null " + Integer.toHexString(paramInt), uin);
      return null;
    }
    util.LOGI(" ticket type:" + Integer.toHexString(paramInt), "");
    if (_tickets != null)
    {
      int i = 0;
      while (i < _tickets.size())
      {
        Ticket localTicket = (Ticket)_tickets.get(i);
        if (_type == paramInt)
        {
          util.LOGI(" type:" + Integer.toHexString(paramInt) + " sig:" + util.buf_len(_sig) + " key:" + util.buf_len(_sig_key) + " create time:" + _create_time + " expire time:" + _expire_time, "");
          return localTicket;
        }
        i += 1;
      }
    }
    return null;
  }
  
  private void OnDeviceLockRequest(String paramString, long paramLong1, long paramLong2, TransReqContext paramTransReqContext, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    DevlockRst localDevlockRst = DevlockBase.rst;
    commRsp = new TLV_CommRsp();
    DevlockInfo localDevlockInfo = new DevlockInfo();
    ErrMsg localErrMsg = new ErrMsg(0, "", "", "");
    if (paramInt != 0)
    {
      util.LOGI("OnDeviceLockRequest ret:" + paramInt, paramString);
      localErrMsg.setMessage(util.get_error_msg(paramInt));
      localErrMsg.setTitle(InternationMsg.a(InternationMsg.MSG_TYPE.MSG_5));
    }
    int i;
    label626:
    int j;
    switch (paramTransReqContext.get_subcmd())
    {
    case 6: 
    case 9: 
    case 10: 
    case 11: 
    default: 
      return;
    case 5: 
      i = paramInt;
      if (paramInt == 0)
      {
        paramInt = new oicq.wlogin_sdk.devicelock.a().parse_rsp(paramTransReqContext.get_body());
        util.LOGI("CheckDevLockStatus ret:" + paramInt, paramString);
        i = paramInt;
        if (paramInt != 64527)
        {
          tlvCommRsp2ErrMsg(commRsp, localErrMsg);
          if ((devSetupInfo != null) && (devSetupInfo.get_data_len() > 0))
          {
            DevSetup = devSetupInfo.a;
            AllowSet = devSetupInfo.b;
            if ((devGuideInfo == null) || (devGuideInfo.get_data_len() <= 0)) {
              break label626;
            }
          }
        }
      }
      for (ProtectIntro = new String(devGuideInfo.a);; ProtectIntro = new String(devSetupInfo.d))
      {
        WarningInfo = new String(devSetupInfo.g);
        WarningTitle = new String(devSetupInfo.e);
        WarningMsg = new String(devSetupInfo.f);
        WarningInfoType = devSetupInfo.c;
        if ((mbMobileInfo != null) && (mbMobileInfo.get_data_len() > 0))
        {
          CountryCode = new String(mbMobileInfo.a);
          Mobile = new String(mbMobileInfo.b);
          MbItemSmsCodeStatus = mbMobileInfo.c;
          AvailableMsgCount = mbMobileInfo.d;
          TimeLimit = mbMobileInfo.e;
        }
        if ((mbGuideInfo != null) && (mbGuideInfo.get_data_len() > 0))
        {
          MbGuideType = mbGuideInfo.c;
          MbGuideInfoType = mbGuideInfo.d;
          MbGuideInfo = new String(mbGuideInfo.b);
          MbGuideMsg = new String(mbGuideInfo.a);
        }
        i = paramInt;
        if (transferInfo != null)
        {
          i = paramInt;
          if (transferInfo.get_data_len() > 0)
          {
            TransferInfo = transferInfo.get_data();
            i = paramInt;
          }
        }
        if (mListener == null) {
          break;
        }
        mListener.OnCheckDevLockStatus(paramWUserSigInfo, localDevlockInfo, i, localErrMsg);
        return;
      }
    case 12: 
      i = paramInt;
      if (paramInt == 0)
      {
        j = new oicq.wlogin_sdk.devicelock.b().parse_rsp(paramTransReqContext.get_body());
        util.LOGI("CloseDevLock ret:" + j, paramString);
        i = j;
        if (j != 64527) {
          tlvCommRsp2ErrMsg(commRsp, localErrMsg);
        }
      }
      break;
    }
    for (;;)
    {
      try
      {
        if (!util.check_uin_account(paramString).booleanValue())
        {
          paramLong2 = mG.b(paramString);
          mG.b(paramLong2, paramLong1);
          paramInt = 0;
          i = j;
          if (paramInt < _tickets.size())
          {
            if (_tickets.get(paramInt))._type != 33554432) {
              break label1093;
            }
            _tickets.remove(paramInt);
            break label1093;
          }
        }
        else
        {
          paramLong2 = Long.parseLong(paramString);
          continue;
        }
        if (mListener == null) {
          break;
        }
      }
      catch (Exception paramString)
      {
        util.printException(paramString);
        i = j;
      }
      mListener.OnCloseDevLock(paramWUserSigInfo, i, localErrMsg);
      return;
      i = paramInt;
      if (paramInt == 0)
      {
        paramInt = new oicq.wlogin_sdk.devicelock.d().parse_rsp(paramTransReqContext.get_body());
        util.LOGI("AskDevLockSms ret:" + paramInt, paramString);
        i = paramInt;
        if (paramInt != 64527)
        {
          tlvCommRsp2ErrMsg(commRsp, localErrMsg);
          i = paramInt;
          if (smsInfo != null)
          {
            AvailableMsgCount = smsInfo.a;
            TimeLimit = smsInfo.b;
            i = paramInt;
          }
        }
      }
      if (mListener == null) {
        break;
      }
      mListener.OnAskDevLockSms(paramWUserSigInfo, localDevlockInfo, i, localErrMsg);
      return;
      i = paramInt;
      if (paramInt == 0)
      {
        paramInt = new oicq.wlogin_sdk.devicelock.f().parse_rsp(paramTransReqContext.get_body());
        util.LOGI("CheckDevLockSms ret:" + paramInt, paramString);
        i = paramInt;
        if (paramInt != 64527)
        {
          tlvCommRsp2ErrMsg(commRsp, localErrMsg);
          i = paramInt;
        }
      }
      if (mListener == null) {
        break;
      }
      mListener.OnCheckDevLockSms(paramWUserSigInfo, i, localErrMsg);
      return;
      label1093:
      paramInt += 1;
    }
  }
  
  private void OnRequestCode2d(String paramString, long paramLong1, long paramLong2, TransReqContext paramTransReqContext, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    if (mListener == null) {
      return;
    }
    oicq.wlogin_sdk.code2d.c localC = oicq.wlogin_sdk.code2d.b._status;
    if (paramInt != 0) {
      util.LOGI("OnRequestCode2d ret:" + paramInt, paramString);
    }
    switch (paramTransReqContext.get_subcmd())
    {
    default: 
      util.LOGW("OnRequestName unhandle cmd", "", paramString);
      mListener.OnException(new ErrMsg(), 9, paramWUserSigInfo);
      return;
    case 19: 
      if (paramInt != 0)
      {
        mListener.OnVerifyCode(paramString, d, c, e, paramWUserSigInfo, f, paramInt);
        return;
      }
      b = new oicq.wlogin_sdk.code2d.e().a(paramTransReqContext.get_body());
      util.LOGI("VerifyCode ret:" + b, paramString);
      if ((b == 0) && (g != null) && (g.length > 0)) {
        mG.a(a, paramLong1, g);
      }
      mListener.OnVerifyCode(paramString, d, c, e, paramWUserSigInfo, f, b);
      return;
    case 20: 
      if (paramInt != 0)
      {
        mListener.OnCloseCode(paramString, d, c, paramWUserSigInfo, f, paramInt);
        return;
      }
      b = new oicq.wlogin_sdk.code2d.a().a(paramTransReqContext.get_body(), paramLong1, u.r);
      util.LOGI("CloseCode ret:" + b, paramString);
      oicq.wlogin_sdk.code2d.c.s = false;
      mListener.OnCloseCode(paramString, d, c, paramWUserSigInfo, f, b);
      return;
    case 49: 
      if (paramInt == 0)
      {
        paramInt = new fetch_code().get_response(paramTransReqContext.get_body());
        util.LOGI("FetchCodeSig ret:" + b, paramString);
      }
      break;
    }
    for (;;)
    {
      mListener.OnFetchCodeSig(j, k, l, paramWUserSigInfo, f, paramInt);
      return;
      if (paramInt == 0)
      {
        paramInt = new oicq.wlogin_sdk.code2d.d().a(paramTransReqContext.get_body());
        util.LOGI("QueryCodeResult ret:" + b, paramString);
      }
      for (;;)
      {
        mListener.OnQueryCodeResult(a, e, c, paramWUserSigInfo, f, paramInt);
        return;
      }
    }
  }
  
  private void OnRequestRegister(String paramString, long paramLong1, long paramLong2, TransReqContext paramTransReqContext, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    if (mListener == null) {}
    String str;
    oicq.wlogin_sdk.register.h localH;
    label1204:
    do
    {
      return;
      str = InternationMsg.a(InternationMsg.MSG_TYPE.MSG_3);
      if (paramInt != 0)
      {
        mListener.OnRegError(paramWUserSigInfo, paramInt, str.getBytes());
        return;
      }
      localH = mRegStatus;
      switch (paramTransReqContext.get_subcmd())
      {
      case 8: 
      case 9: 
      default: 
        util.LOGW("OnRequestRegister unhandle cmd:" + paramTransReqContext.get_subcmd(), "", paramString);
        mListener.OnRegError(paramWUserSigInfo, 64526, str.getBytes());
        return;
      case 10: 
        paramInt = oicq.wlogin_sdk.register.a.a(paramTransReqContext.get_body(), localH);
        if (paramInt != 0)
        {
          mListener.OnRegError(paramWUserSigInfo, paramInt, str.getBytes());
          return;
        }
        util.LOGI("reg cmd:" + paramTransReqContext.get_subcmd() + " ret:" + d, "");
        if (d == 0)
        {
          mListener.OnRegCheckDownloadMsg(paramWUserSigInfo, l, m);
          return;
        }
        if (d == 2)
        {
          mListener.OnRegCheckUploadMsg(paramWUserSigInfo, new String(p));
          return;
        }
        if (d == 3)
        {
          mListener.OnRegCheckValidUrl(paramWUserSigInfo, q);
          return;
        }
        if ((d == 6) || (d == 44))
        {
          mListener.OnRegCheckWebSig(paramWUserSigInfo, new String(q), new String(f));
          q = new byte[0];
          return;
        }
        util.LOGW("OnRequestRegister 0xa return code:", String.valueOf(d), paramString);
        mListener.OnRegError(paramWUserSigInfo, d, f);
        return;
      case 3: 
        paramInt = oicq.wlogin_sdk.register.a.a(paramTransReqContext.get_body(), localH);
        if (paramInt != 0)
        {
          mListener.OnRegError(paramWUserSigInfo, paramInt, str.getBytes());
          return;
        }
        util.LOGI("reg cmd:" + paramTransReqContext.get_subcmd() + " ret:" + d, "");
        if ((d == 0) || (d == 4) || (d == 31) || (d == 118))
        {
          mListener.OnRegQueryClientSentMsgStatus(paramWUserSigInfo, d, r, s, new String(f));
          return;
        }
        if (d == 3)
        {
          mListener.OnRegCheckValidUrl(paramWUserSigInfo, q);
          return;
        }
        util.LOGW("OnRequestRegister 0x3 return code:", String.valueOf(d), paramString);
        mListener.OnRegError(paramWUserSigInfo, d, f);
        return;
      case 4: 
        paramInt = oicq.wlogin_sdk.register.a.a(paramTransReqContext.get_body(), localH);
        if (paramInt != 0) {
          mListener.OnRegError(paramWUserSigInfo, paramInt, str.getBytes());
        }
        util.LOGI("reg cmd:" + paramTransReqContext.get_subcmd() + " ret:" + d, "");
        if (d == 0)
        {
          mListener.OnRegRequestServerResendMsg(paramWUserSigInfo, d, r, s);
          return;
        }
        if (d == 3)
        {
          mListener.OnRegCheckValidUrl(paramWUserSigInfo, q);
          return;
        }
        if (d == 5)
        {
          mListener.OnRegRequestServerResendMsg(paramWUserSigInfo, d, r, s);
          return;
        }
        util.LOGW("OnRequestRegister 0x4 return code:", String.valueOf(d), paramString);
        mListener.OnRegError(paramWUserSigInfo, d, f);
        return;
      case 5: 
        paramInt = oicq.wlogin_sdk.register.a.b(paramTransReqContext.get_body(), localH);
        if (paramInt != 0) {
          mListener.OnRegError(paramWUserSigInfo, paramInt, str.getBytes());
        }
        util.LOGI("reg cmd:" + paramTransReqContext.get_subcmd() + " ret:" + d, "");
        mListener.OnRegSubmitMsgChk(paramWUserSigInfo, d, f);
        return;
      case 6: 
        paramInt = oicq.wlogin_sdk.register.a.c(paramTransReqContext.get_body(), localH);
        if (paramInt != 0) {
          mListener.OnRegError(paramWUserSigInfo, paramInt, str.getBytes());
        }
        util.LOGI("reg cmd:" + paramTransReqContext.get_subcmd() + " ret:" + d, "");
        if ((b != null) && (b.indexOf("-") > 0))
        {
          paramInt = b.indexOf("-");
          paramString = b.substring(0, paramInt);
          if (!paramString.equals("86")) {
            break label1204;
          }
        }
        for (b = b.substring(paramInt + 1);; b = ("00" + paramString + b.substring(paramInt + 1)))
        {
          if ((b != null) && (b.length() != 0))
          {
            mG.d(b);
            mG.a(b, Long.valueOf(t));
          }
          util.LOGI("reg userAccount: " + b, t + "");
          if (oicq.wlogin_sdk.register.h.y.length() <= 0) {
            break;
          }
          mListener.OnRegGetSMSVerifyLoginAccount(paramWUserSigInfo, d, t, u, v, f);
          return;
        }
        mListener.OnRegGetAccount(paramWUserSigInfo, d, t, u, v, f);
        return;
      }
      paramInt = oicq.wlogin_sdk.register.a.d(paramTransReqContext.get_body(), localH);
      if (paramInt == 0) {
        break;
      }
    } while (mListener == null);
    mListener.OnRegError(paramWUserSigInfo, paramInt, str.getBytes());
    return;
    util.LOGI("reg cmd:" + paramTransReqContext.get_subcmd() + " ret:" + d, "");
    mListener.OnRegQueryAccount(paramWUserSigInfo, d, f);
  }
  
  private int RefreshPictureData(String paramString, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    int i = 0;
    if ((paramString == null) || (paramWUserSigInfo == null)) {
      return 64519;
    }
    if (paramInt == 0)
    {
      new HelperThread(this, mHelperHandler, paramString, paramWUserSigInfo, "RefreshPictureData").RunReq(1);
      return 64535;
    }
    if (_seqence == 0L) {
      _seqence = mAysncSeq;
    }
    u localU = mG.a(_seqence);
    _seqence = h;
    async_context localAsync_context = u.b(h);
    util.LOGI("user:" + paramString + " Seq:" + h + " RefreshPictureData ...", "" + paramString);
    g = paramString;
    _last_err_msg = new ErrMsg();
    long l;
    if (!util.check_uin_account(paramString).booleanValue())
    {
      l = localU.b(paramString);
      if (l == 0L) {
        break label326;
      }
      paramInt = 1;
    }
    for (;;)
    {
      if (paramInt == 1) {
        f = l;
      }
      paramInt = new r(localU).a(mMiscBitmap, mSubSigMap, _sub_appid_list, paramWUserSigInfo);
      if (paramInt == 2) {
        paramInt = i;
      }
      for (;;)
      {
        util.LOGI("user:" + paramString + " Seq:" + h + " RefreshPictureData ret=" + paramInt, "" + paramString);
        return paramInt;
        l = Long.parseLong(paramString);
        paramInt = 1;
        break;
      }
      label326:
      paramInt = 0;
    }
  }
  
  private int RefreshSMSData(String paramString, long paramLong, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    if ((paramString == null) || (paramWUserSigInfo == null)) {
      return 64519;
    }
    if (paramInt == 0)
    {
      new HelperThread(this, mHelperHandler, paramString, paramLong, paramWUserSigInfo, "RefreshSMSData").RunReq(3);
      return 64535;
    }
    paramInt = 0;
    if (_seqence == 0L) {
      _seqence = mAysncSeq;
    }
    u localU = mG.a(_seqence);
    _seqence = h;
    async_context localAsync_context = u.b(h);
    util.LOGI("user:" + paramString + " smsAppid:" + paramLong + " Seq:" + h + " RefreshSMSData ...", "" + paramString);
    g = paramString;
    _last_err_msg = new ErrMsg();
    long l1;
    if (!util.check_uin_account(paramString).booleanValue())
    {
      long l2 = localU.b(paramString);
      l1 = l2;
      if (l2 != 0L)
      {
        paramInt = 1;
        l1 = l2;
      }
    }
    for (;;)
    {
      if (paramInt == 1) {
        f = l1;
      }
      int i = new s(localU).a(paramLong, mMiscBitmap, mSubSigMap, _sub_appid_list, paramWUserSigInfo);
      paramInt = i;
      if (i == 160) {
        paramInt = 0;
      }
      util.LOGI("user:" + paramString + " smsAppid:" + paramLong + " Seq:" + h + " RefreshSMSData ret=" + paramInt, "" + paramString);
      return paramInt;
      l1 = Long.parseLong(paramString);
      paramInt = 1;
    }
  }
  
  private int RefreshSMSVerifyLoginCode(String paramString, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return 64519;
    }
    if (paramInt == 0)
    {
      new HelperThread(this, mHelperHandler, paramString, paramWUserSigInfo, "RefreshSMSVerifyLoginCode").RunReq(14);
      return 64535;
    }
    if (_seqence == 0L) {
      _seqence = mAysncSeq;
    }
    Object localObject = mG.a(_seqence);
    _seqence = h;
    async_context localAsync_context = u.b(h);
    util.LOGI("user:" + paramString + " Seq:" + h + " RefreshSMSVerifyLoginCode ...", paramString);
    g = paramString;
    _last_err_msg = new ErrMsg();
    paramInt = new x((u)localObject).a(mMiscBitmap, mSubSigMap, null, paramWUserSigInfo);
    localObject = new StringBuilder().append("user:").append(g).append(" Seq:").append(h).append(" RefreshSMSVerifyLoginCode ret=");
    if (paramInt > 0) {}
    for (paramWUserSigInfo = Integer.toHexString(paramInt);; paramWUserSigInfo = Integer.valueOf(paramInt))
    {
      util.LOGI(paramWUserSigInfo, paramString);
      return paramInt;
    }
  }
  
  private int RegSubmitMobile(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, WUserSigInfo paramWUserSigInfo)
  {
    if ((paramArrayOfByte2 == null) || (paramArrayOfByte2.length <= 0) || (paramArrayOfByte4 == null)) {
      return 64519;
    }
    if (paramArrayOfByte1 == null) {
      paramArrayOfByte1 = new byte[0];
    }
    for (;;)
    {
      byte[] arrayOfByte = u.C;
      long l1 = 0L;
      Object localObject1 = new byte[0];
      Object localObject2 = GetLastLoginInfo();
      paramArrayOfByte3 = (byte[])localObject1;
      if (localObject2 != null)
      {
        long l2 = mUin;
        localObject2 = GetLocalTicket(mAccount, paramLong1, 64);
        l1 = l2;
        paramArrayOfByte3 = (byte[])localObject1;
        if (localObject2 != null)
        {
          l1 = l2;
          paramArrayOfByte3 = (byte[])localObject1;
          if (_sig != null)
          {
            paramArrayOfByte3 = _sig;
            l1 = l2;
          }
        }
      }
      util.LOGI("has uin? " + l1 + ", a2: " + paramArrayOfByte3.length);
      util.LOGI("RegSubmitMobile mobile ..." + new String(paramArrayOfByte2) + " appname: " + new String(arrayOfByte) + "...", "");
      mRegStatus.c = new String(paramArrayOfByte2);
      localObject1 = new oicq.wlogin_sdk.register.f();
      localObject2 = new TransReqContext();
      oicq.wlogin_sdk.register.h localH = mRegStatus;
      k = paramArrayOfByte2;
      g = paramLong1;
      h = paramLong2;
      ((TransReqContext)localObject2).set_register_req();
      ((TransReqContext)localObject2).set_subcmd(((oicq.wlogin_sdk.register.f)localObject1).a());
      _body = ((oicq.wlogin_sdk.register.f)localObject1).a(paramArrayOfByte2, arrayOfByte, paramArrayOfByte4, paramInt1, paramInt2, paramInt3, paramLong1, paramLong2, null, util.get_IMEI(mContext), util.get_IMSI(mContext), u.Y, l1, paramArrayOfByte3, GetGuid(), paramArrayOfByte1);
      return RequestTransport(0, 1, null, 0L, i, (TransReqContext)localObject2, paramWUserSigInfo);
    }
  }
  
  private int RequestInit()
  {
    try
    {
      int i = util.get_saved_network_type(mContext);
      u.d();
      int j = ShareKeyInit();
      AsyncGenRSAKey();
      util.LOGI("WtloginHelper init ok, ret:" + j + " android version:" + new String(u.H) + " saved_network_type:" + i + " network_type:" + u.B + " release time:" + util.get_release_time() + " svn verion:" + 1521L + " at " + u.l(), "");
      return j;
    }
    finally {}
  }
  
  private int RequestReport(int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, long paramLong1, long paramLong2)
  {
    if (paramInt == 0)
    {
      new HelperThread(this, mHelperHandler, paramArrayOfByte1, paramArrayOfByte2, paramLong1, paramLong2, "RequestReport").RunReq(7);
      return 64535;
    }
    u localU = mG.a(0L);
    f = paramLong1;
    util.LOGI("user:" + paramLong1 + " appid:" + paramLong2 + " Seq:" + h + " RequestReport...", "" + paramLong1);
    paramInt = new aa(localU).a(paramLong1, null, paramArrayOfByte1, paramArrayOfByte2, paramLong2, new WUserSigInfo());
    localU.i();
    util.LOGI("user:" + paramLong1 + " appid:" + paramLong2 + " Seq:" + h + " RequestReport ret=" + paramInt, "" + paramLong1);
    return paramInt;
  }
  
  private int RequestReportError(int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, long paramLong1, long paramLong2, int paramInt2)
  {
    if (paramInt1 == 0)
    {
      new HelperThread(this, mHelperHandler, paramArrayOfByte1, paramArrayOfByte2, paramLong1, paramLong2, paramInt2, "RequestReportError").RunReq(8);
      return 64535;
    }
    u localU = mG.a(0L);
    d = mG.d;
    f = paramLong1;
    util.LOGI("user:" + paramLong1 + " appid:" + paramLong2 + " Seq:" + h + " RequestReportError...", "" + paramLong1);
    paramInt1 = new v(localU).a(paramLong1, null, paramArrayOfByte1, paramArrayOfByte2, paramLong2, paramInt2);
    util.LOGI("user:" + paramLong1 + " appid:" + paramLong2 + " Seq:" + h + " RequestReportError ret=" + paramInt1, "" + paramLong1);
    return paramInt1;
  }
  
  private int ShareKeyInit()
  {
    int j = 0;
    util.LOGI("Generate Shared Key Begin ...", "");
    int i;
    if (isForLocal) {
      i = ShareKeyInitDefault();
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (ShareKeyInitOpenSSL() == 0);
      i = j;
    } while (ShareKeyInitBC() == 0);
    return ShareKeyInitDefault();
  }
  
  private int ShareKeyInitBC()
  {
    try
    {
      Object localObject1 = KeyPairGenerator.getInstance("EC", "BC");
      ((KeyPairGenerator)localObject1).initialize(new ECGenParameterSpec("secp192k1"));
      localObject1 = ((KeyPairGenerator)localObject1).genKeyPair();
      Object localObject2 = ((KeyPair)localObject1).getPrivate();
      localObject1 = ((KeyPair)localObject1).getPublic().getEncoded();
      Object localObject3 = KeyFactory.getInstance("EC", "BC").generatePublic(new X509EncodedKeySpec(util.string_to_buf("3046301006072A8648CE3D020106052B8104001F03320004928D8850673088B343264E0C6BACB8496D697799F37211DEB25BB73906CB089FEA9639B4E0260498B51A992D50813DA8")));
      KeyAgreement localKeyAgreement = KeyAgreement.getInstance("ECDH", "BC");
      localKeyAgreement.init((Key)localObject2);
      localKeyAgreement.doPhase((Key)localObject3, true);
      localObject3 = localKeyAgreement.generateSecret();
      localObject2 = new byte[49];
      System.arraycopy(localObject1, 23, localObject2, 0, 49);
      localObject1 = MD5.toMD5Byte((byte[])localObject3);
      mG.n = ((byte[])localObject2);
      mG.o = ((byte[])localObject1);
      util.LOGI("client public key = " + util.buf_to_string(mG.n), "");
      util.LOGI("share key = " + util.buf_to_string(mG.o), "");
      util.LOGI("create key pair and shared key with bouncycastle OK", "");
      return 0;
    }
    catch (InvalidAlgorithmParameterException localInvalidAlgorithmParameterException)
    {
      util.LOGW("create key pair and shared key failed, " + localInvalidAlgorithmParameterException.getMessage(), "");
      return -1;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      util.LOGW("create key pair and shared key failed, " + localNoSuchAlgorithmException.getMessage(), "");
      return -2;
    }
    catch (NoSuchProviderException localNoSuchProviderException)
    {
      util.LOGW("create key pair and shared key failed, " + localNoSuchProviderException.getMessage(), "");
      return -3;
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      util.LOGW("create key pair and shared key failed, " + localInvalidKeySpecException.getMessage(), "");
      return -4;
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      util.LOGW("create key pair and shared key failed, " + localInvalidKeyException.getMessage(), "");
    }
    return -5;
  }
  
  private int ShareKeyInitDefault()
  {
    mG.n = util.string_to_buf("020b03cf3d99541f29ffec281bebbd4ea211292ac1f53d7128");
    mG.o = util.string_to_buf("4da0f614fc9f29c2054c77048a6566d7");
    StringBuilder localStringBuilder = new StringBuilder().append("android sdk ");
    u localU = mG;
    util.LOGW(u.ac + " using DEFAULT key", "");
    return 0;
  }
  
  private int ShareKeyInitOpenSSL()
  {
    Object localObject = new EcdhCrypt(mContext);
    if (((EcdhCrypt)localObject).GenereateKey() != 0) {
      return -1;
    }
    byte[] arrayOfByte = ((EcdhCrypt)localObject).get_c_pub_key();
    localObject = ((EcdhCrypt)localObject).get_g_share_key();
    if ((arrayOfByte != null) && (arrayOfByte.length > 0) && (localObject != null) && (localObject.length > 0))
    {
      mG.n = ((byte[])arrayOfByte.clone());
      mG.o = ((byte[])((byte[])localObject).clone());
      util.LOGI("client public key = " + util.buf_to_string(mG.n), "");
      util.LOGI("share key = " + util.buf_to_string(mG.o), "");
      util.LOGI("create key pair and shared key with OpenSSL OK", "");
      return 0;
    }
    util.LOGW("get client public key or shared key FAILED", "");
    return -2;
  }
  
  private int VerifySMSVerifyLoginCode(String paramString1, String paramString2, WUserSigInfo paramWUserSigInfo, int paramInt)
  {
    if ((paramString1 == null) || (paramString1.length() == 0) || (paramString2 == null) || (paramString2.length() == 0)) {
      return 64519;
    }
    if (paramInt == 0)
    {
      new HelperThread(this, mHelperHandler, paramString1, paramString2, paramWUserSigInfo, "VerifySMSVerifyLoginCode").RunReq(13);
      return 64535;
    }
    if (_seqence == 0L) {
      _seqence = mAysncSeq;
    }
    u localU = mG.a(_seqence);
    _seqence = h;
    async_context localAsync_context = u.b(h);
    util.LOGI("user:" + g + " code:" + paramString2 + " Seq:" + h + " VerifySMSVerifyLoginCode ...", paramString1);
    g = paramString1;
    _last_err_msg = new ErrMsg();
    _mpasswd = util.get_mpasswd();
    paramInt = new y(localU).a(paramString2, mMiscBitmap, mSubSigMap, null, paramWUserSigInfo);
    paramWUserSigInfo = new StringBuilder().append("user:").append(paramString1).append(" code:").append(paramString2).append(" Seq:").append(h).append(" VerifySMSVerifyLoginAccount ret=");
    if (paramInt > 0) {}
    for (paramString2 = Integer.toHexString(paramInt);; paramString2 = Integer.valueOf(paramInt))
    {
      util.LOGI(paramString2, paramString1);
      return paramInt;
    }
  }
  
  private int isPskeyExpired(int paramInt1, String[] paramArrayOfString, Ticket paramTicket, long paramLong, int paramInt2)
  {
    int i;
    String str2;
    int j;
    boolean bool1;
    label128:
    boolean bool2;
    label140:
    String str1;
    boolean bool3;
    if ((paramInt1 == 1048576) && (paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      paramInt1 = 0;
      int k = paramArrayOfString.length;
      i = 0;
      for (;;)
      {
        if (i < k)
        {
          str2 = paramArrayOfString[i];
          j = paramInt1;
          if (str2 != null)
          {
            if (str2.length() == 0) {
              j = paramInt1;
            }
          }
          else
          {
            i += 1;
            paramInt1 = j;
            continue;
          }
          int m = str2.indexOf('(');
          j = str2.indexOf(')');
          if ((m != 0) || (j <= 0)) {
            break label451;
          }
          m = Integer.valueOf(str2.substring(m + 1, j)).intValue();
          if ((0x100000 & m) > 0)
          {
            bool1 = true;
            if ((m & 0x8000000) <= 0) {
              break label384;
            }
            bool2 = true;
            str1 = str2.substring(j + 1);
            bool3 = bool2;
            bool2 = bool1;
            bool1 = bool3;
          }
        }
      }
    }
    for (;;)
    {
      label208:
      boolean bool4;
      if ((bool2) && ((_pskey_map.get(str1) == null) || (paramLong >= ((Long)_pskey_expire.get(str1)).longValue())))
      {
        bool3 = true;
        if ((!bool1) || ((_pt4token_map.get(str1) != null) && (paramLong < ((Long)_pt4token_expire.get(str1)).longValue()))) {
          break label396;
        }
        bool4 = true;
        label253:
        if ((!bool3) && (!bool4)) {
          break label448;
        }
        j = paramInt1 + 1;
        paramArrayOfString[paramInt1] = str2;
        util.LOGI("refresh " + str1 + " " + bool3 + "," + bool4, "");
        paramInt1 = j;
      }
      label384:
      label396:
      label448:
      for (;;)
      {
        util.LOGI("domain " + str1 + " " + bool2 + "," + bool1, "");
        j = paramInt1;
        break;
        bool1 = false;
        break label128;
        bool2 = false;
        break label140;
        bool3 = false;
        break label208;
        bool4 = false;
        break label253;
        i = paramInt1;
        if (paramInt1 == 0) {
          return 3;
        }
        while (i < paramArrayOfString.length)
        {
          paramArrayOfString[i] = null;
          i += 1;
        }
        if (paramInt2 == 1) {
          return 1;
        }
        RefreshMemorySig();
        return 2;
        return 0;
      }
      label451:
      bool1 = false;
      bool2 = true;
      str1 = str2;
    }
  }
  
  private void localInit(Context paramContext, boolean paramBoolean)
  {
    isForLocal = paramBoolean;
    try
    {
      mContext = paramContext.getApplicationContext();
      mG.a(paramContext);
      RequestInit();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        mContext = paramContext;
        util.printThrowable(localThrowable, "");
      }
    }
  }
  
  private Handler newHelperHandler()
  {
    try
    {
      if (Looper.myLooper() == null) {
        return null;
      }
      Handler localHandler = new Handler();
      return localHandler;
    }
    catch (Throwable localThrowable) {}
    return null;
  }
  
  private void tlvCommRsp2ErrMsg(TLV_CommRsp paramTLV_CommRsp, ErrMsg paramErrMsg)
  {
    if ((paramTLV_CommRsp == null) || (paramTLV_CommRsp.get_data_len() == 0)) {
      return;
    }
    paramErrMsg.setType(ErrInfoType);
    paramErrMsg.setOtherinfo(new String(ErrInfo));
    paramErrMsg.setTitle(new String(ErrTitle));
    paramErrMsg.setMessage(new String(ErrMsg));
  }
  
  public int AskDevLockSms(String paramString, long paramLong1, long paramLong2, WUserSigInfo paramWUserSigInfo)
  {
    if (paramString == null) {
      return 64519;
    }
    Object localObject = new WloginSimpleInfo();
    if (!GetBasicUserInfo(paramString, (WloginSimpleInfo)localObject).booleanValue()) {
      return 64533;
    }
    long l = _uin;
    util.LOGI("AskDevLockSms ...", paramString);
    localObject = new oicq.wlogin_sdk.devicelock.d();
    TransReqContext localTransReqContext = new TransReqContext();
    localTransReqContext.set_devlock_req();
    localTransReqContext.set_subcmd(((oicq.wlogin_sdk.devicelock.d)localObject).get_msgType());
    _body = ((oicq.wlogin_sdk.devicelock.d)localObject).a(l, paramLong1, paramLong2);
    if ((_body == null) || (_body.length == 0)) {
      return 64519;
    }
    return RequestTransport(0, 1, paramString, paramLong1, Role, localTransReqContext, paramWUserSigInfo);
  }
  
  public void CancelRequest()
  {
    mG.q = 1;
  }
  
  public int CheckDevLockSms(String paramString1, long paramLong1, long paramLong2, String paramString2, byte[] paramArrayOfByte, WUserSigInfo paramWUserSigInfo)
  {
    if (paramString1 == null) {
      return 64519;
    }
    Object localObject1 = new WloginSimpleInfo();
    if (!GetBasicUserInfo(paramString1, (WloginSimpleInfo)localObject1).booleanValue()) {
      return 64533;
    }
    long l = _uin;
    Object localObject2 = FindUserSig(l, paramLong1);
    if (localObject2 == null) {
      return 64532;
    }
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0)) {
      DevlockBase.rst.setSppKey(paramArrayOfByte);
    }
    util.LOGI("CheckDevLockSms ...", paramString1);
    oicq.wlogin_sdk.devicelock.f localF = new oicq.wlogin_sdk.devicelock.f();
    TransReqContext localTransReqContext = new TransReqContext();
    localObject1 = Build.VERSION.RELEASE;
    paramArrayOfByte = (byte[])localObject1;
    if (localObject1 == null) {
      paramArrayOfByte = "";
    }
    localTransReqContext.set_devlock_req();
    localTransReqContext.set_subcmd(localF.get_msgType());
    localObject1 = _TGT;
    localObject2 = u.y;
    byte[] arrayOfByte1 = u.C;
    byte[] arrayOfByte2 = "6.3.1.1521".getBytes();
    byte[] arrayOfByte3 = "android".getBytes();
    paramArrayOfByte = paramArrayOfByte.getBytes();
    if (paramString2 == null) {}
    for (paramString2 = null;; paramString2 = paramString2.getBytes())
    {
      _body = localF.a(l, paramLong1, paramLong2, (byte[])localObject1, (byte[])localObject2, arrayOfByte1, arrayOfByte2, arrayOfByte3, paramArrayOfByte, paramString2);
      if ((_body != null) && (_body.length != 0)) {
        break;
      }
      return 64519;
    }
    return RequestTransport(0, 1, paramString1, paramLong1, Role, localTransReqContext, paramWUserSigInfo);
  }
  
  public int CheckDevLockStatus(String paramString, long paramLong1, long paramLong2, WUserSigInfo paramWUserSigInfo)
  {
    if (paramString == null) {
      return 64519;
    }
    Object localObject = new WloginSimpleInfo();
    if (!GetBasicUserInfo(paramString, (WloginSimpleInfo)localObject).booleanValue()) {
      return 64533;
    }
    long l = _uin;
    localObject = FindUserSig(l, paramLong1);
    if (localObject == null) {
      return 64532;
    }
    util.LOGI("CheckDevLockStatus ...", paramString);
    DevlockBase.rst = new DevlockRst();
    oicq.wlogin_sdk.devicelock.a localA = new oicq.wlogin_sdk.devicelock.a();
    TransReqContext localTransReqContext = new TransReqContext();
    localTransReqContext.set_devlock_req();
    localTransReqContext.set_subcmd(localA.get_msgType());
    _body = localA.a(l, paramLong1, paramLong2, _TGT, u.y, u.C, "6.3.1.1521".getBytes(), u.I, u.H);
    if ((_body == null) || (_body.length == 0)) {
      return 64519;
    }
    return RequestTransport(0, 1, paramString, paramLong1, Role, localTransReqContext, paramWUserSigInfo);
  }
  
  public int CheckPictureAndGetSt(String paramString, byte[] paramArrayOfByte, WUserSigInfo paramWUserSigInfo)
  {
    o.D = false;
    return CheckPictureAndGetSt(paramString, paramArrayOfByte, paramWUserSigInfo, (byte[][])null, 0);
  }
  
  public int CheckPictureAndGetSt(String paramString, byte[] paramArrayOfByte, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte1)
  {
    o.D = false;
    return CheckPictureAndGetSt(paramString, paramArrayOfByte, paramWUserSigInfo, paramArrayOfByte1, 0);
  }
  
  public int CheckSMSAndGetSt(String paramString, byte[] paramArrayOfByte, WUserSigInfo paramWUserSigInfo)
  {
    return CheckSMSAndGetSt(paramString, paramArrayOfByte, paramWUserSigInfo, (byte[][])null, 0);
  }
  
  public int CheckSMSAndGetSt(String paramString, byte[] paramArrayOfByte, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte1)
  {
    return CheckSMSAndGetSt(paramString, paramArrayOfByte, paramWUserSigInfo, paramArrayOfByte1, 0);
  }
  
  public int CheckSMSVerifyLoginAccount(long paramLong1, long paramLong2, String paramString, WUserSigInfo paramWUserSigInfo)
  {
    return CheckSMSVerifyLoginAccount(paramLong1, paramLong2, paramString, paramWUserSigInfo, 0);
  }
  
  public int CheckWebsigAndGetSt(String paramString1, String paramString2, WUserSigInfo paramWUserSigInfo)
  {
    o.D = true;
    return CheckPictureAndGetSt(paramString1, paramString2.getBytes(), paramWUserSigInfo, (byte[][])null, 0);
  }
  
  public int CheckWebsigAndGetSt(String paramString1, String paramString2, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte)
  {
    o.D = true;
    return CheckPictureAndGetSt(paramString1, paramString2.getBytes(), paramWUserSigInfo, paramArrayOfByte, 0);
  }
  
  public void ClearPSkey(String paramString, long paramLong)
  {
    util.LOGI("user:" + paramString + " appid:" + paramLong + " ClearPSkey ...", paramString);
    if ((paramString == null) || (paramString.length() <= 0)) {
      return;
    }
    int i = 1;
    for (;;)
    {
      try
      {
        if (!util.check_uin_account(paramString).booleanValue())
        {
          long l2 = mG.b(paramString);
          l1 = l2;
          if (l2 == 0L)
          {
            i = 0;
            l1 = l2;
          }
          if (i != 0) {
            mG.c(l1, paramLong);
          }
          return;
        }
      }
      finally {}
      long l1 = Long.parseLong(paramString);
    }
  }
  
  /* Error */
  public Boolean ClearUserLoginData(String paramString, long paramLong)
  {
    // Byte code:
    //   0: new 138	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   7: ldc -115
    //   9: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   12: aload_1
    //   13: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   16: ldc_w 1441
    //   19: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: lload_2
    //   23: invokevirtual 152	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   26: ldc_w 1864
    //   29: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: aload_1
    //   36: invokestatic 164	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Ljava/lang/String;)V
    //   39: aload_1
    //   40: ifnull +10 -> 50
    //   43: aload_1
    //   44: invokevirtual 600	java/lang/String:length	()I
    //   47: ifgt +8 -> 55
    //   50: iconst_1
    //   51: invokestatic 1867	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   54: areturn
    //   55: aload_0
    //   56: monitorenter
    //   57: aload_1
    //   58: invokestatic 181	oicq/wlogin_sdk/tools/util:check_uin_account	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   61: invokevirtual 187	java/lang/Boolean:booleanValue	()Z
    //   64: ifne +72 -> 136
    //   67: aload_0
    //   68: getfield 52	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/u;
    //   71: aload_1
    //   72: invokevirtual 190	oicq/wlogin_sdk/request/u:b	(Ljava/lang/String;)J
    //   75: lstore 5
    //   77: lload 5
    //   79: lconst_0
    //   80: lcmp
    //   81: ifne +41 -> 122
    //   84: iconst_0
    //   85: istore 4
    //   87: iload 4
    //   89: iconst_1
    //   90: if_icmpne +13 -> 103
    //   93: aload_0
    //   94: getfield 52	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/u;
    //   97: lload 5
    //   99: lload_2
    //   100: invokevirtual 1869	oicq/wlogin_sdk/request/u:d	(JJ)V
    //   103: aload_0
    //   104: monitorexit
    //   105: iconst_0
    //   106: newarray byte
    //   108: putstatic 809	oicq/wlogin_sdk/sharemem/WloginSigInfo:_QRPUSHSig	[B
    //   111: iconst_0
    //   112: newarray byte
    //   114: putstatic 800	oicq/wlogin_sdk/sharemem/WloginSigInfo:_LHSig	[B
    //   117: iconst_1
    //   118: invokestatic 1867	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   121: areturn
    //   122: aload_0
    //   123: getfield 52	oicq/wlogin_sdk/request/WtloginHelper:mG	Loicq/wlogin_sdk/request/u;
    //   126: aload_1
    //   127: invokevirtual 1264	oicq/wlogin_sdk/request/u:d	(Ljava/lang/String;)V
    //   130: iconst_1
    //   131: istore 4
    //   133: goto -46 -> 87
    //   136: aload_1
    //   137: invokestatic 277	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   140: lstore 5
    //   142: iconst_1
    //   143: istore 4
    //   145: goto -58 -> 87
    //   148: astore_1
    //   149: aload_0
    //   150: monitorexit
    //   151: aload_1
    //   152: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	153	0	this	WtloginHelper
    //   0	153	1	paramString	String
    //   0	153	2	paramLong	long
    //   85	59	4	i	int
    //   75	66	5	l	long
    // Exception table:
    //   from	to	target	type
    //   57	77	148	finally
    //   93	103	148	finally
    //   103	105	148	finally
    //   122	130	148	finally
    //   136	142	148	finally
    //   149	151	148	finally
  }
  
  public int CloseCode(String paramString, long paramLong, byte[] paramArrayOfByte, int paramInt, List<byte[]> paramList, WUserSigInfo paramWUserSigInfo)
  {
    Object localObject = new WloginSimpleInfo();
    if (!GetBasicUserInfo(paramString, (WloginSimpleInfo)localObject).booleanValue()) {
      return 64533;
    }
    long l = _uin;
    localObject = FindUserSig(l, paramLong);
    if (localObject == null) {
      return 64532;
    }
    util.LOGI("user:" + paramString + " CloseCode ...", paramString);
    oicq.wlogin_sdk.code2d.a localA = new oicq.wlogin_sdk.code2d.a();
    TransReqContext localTransReqContext = new TransReqContext();
    localTransReqContext.set_code2d_func_req();
    localTransReqContext.set_subcmd(localA.get_cmd());
    _body = localA.a(l, paramLong, 1L, paramArrayOfByte, _TGT, u.y, paramInt, paramList, _en_A1, _noPicSig, mMiscBitmap, 0L);
    return RequestTransport(0, 1, paramString, paramLong, _role, localTransReqContext, paramWUserSigInfo);
  }
  
  public int CloseDevLock(String paramString, long paramLong1, long paramLong2, WUserSigInfo paramWUserSigInfo)
  {
    if (paramString == null) {
      return 64519;
    }
    Object localObject = new WloginSimpleInfo();
    if (!GetBasicUserInfo(paramString, (WloginSimpleInfo)localObject).booleanValue()) {
      return 64533;
    }
    long l = _uin;
    WloginSigInfo localWloginSigInfo = FindUserSig(l, paramLong1);
    if (localWloginSigInfo == null) {
      return 64532;
    }
    util.LOGI("CloseDevLock ...", paramString);
    oicq.wlogin_sdk.devicelock.b localB = new oicq.wlogin_sdk.devicelock.b();
    TransReqContext localTransReqContext = new TransReqContext();
    String str = Build.VERSION.RELEASE;
    localObject = str;
    if (str == null) {
      localObject = "";
    }
    localTransReqContext.set_devlock_req();
    localTransReqContext.set_subcmd(localB.get_msgType());
    _body = localB.a(l, paramLong1, paramLong2, _TGT, u.y, u.C, "6.3.1.1521".getBytes(), "android".getBytes(), ((String)localObject).getBytes());
    if ((_body == null) || (_body.length == 0)) {
      return 64519;
    }
    return RequestTransport(0, 1, paramString, paramLong1, Role, localTransReqContext, paramWUserSigInfo);
  }
  
  public int FetchCodeSig(long paramLong1, long paramLong2, fetch_code.QRCodeCustom paramQRCodeCustom, WUserSigInfo paramWUserSigInfo)
  {
    util.LOGI(" FetchCodeSig ...", "");
    fetch_code localFetch_code = new fetch_code();
    TransReqContext localTransReqContext = new TransReqContext();
    localTransReqContext.set_code2d_func_req();
    localTransReqContext.set_subcmd(localFetch_code.get_cmd());
    long l = mMiscBitmap;
    byte[] arrayOfByte = WloginSigInfo._QRPUSHSig;
    _body = localFetch_code.get_request(0L, paramLong1, paramLong2, new byte[0], paramQRCodeCustom, l, 0L, arrayOfByte);
    return RequestTransport(0, 1, null, paramLong1, _role, localTransReqContext, paramWUserSigInfo);
  }
  
  public int GetA1WithA1(String paramString, long paramLong1, long paramLong2, byte[] paramArrayOfByte1, long paramLong3, long paramLong4, long paramLong5, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, WUserSigInfo paramWUserSigInfo, WFastLoginInfo paramWFastLoginInfo)
  {
    return GetA1WithA1(paramString, paramLong1, paramLong2, mMainSigMap, paramArrayOfByte1, paramLong3, paramLong4, paramLong5, paramArrayOfByte2, paramArrayOfByte3, paramWUserSigInfo, paramWFastLoginInfo, 0);
  }
  
  public byte[] GetA2A2KeyBuf(String paramString, long paramLong)
  {
    Ticket localTicket = GetLocalTicket(paramString, paramLong, 64);
    if ((localTicket == null) || (_sig == null) || (_sig.length <= 0) || (_sig_key == null) || (_sig_key.length <= 0)) {}
    while ((u.z == null) || (u.z.length <= 0)) {
      return null;
    }
    byte[] arrayOfByte = new byte[paramString.getBytes().length + 2 + 8 + 2 + _sig.length + 2 + _sig_key.length];
    util.int16_to_buf(arrayOfByte, 0, paramString.getBytes().length);
    System.arraycopy(paramString.getBytes(), 0, arrayOfByte, 2, paramString.getBytes().length);
    int i = paramString.getBytes().length + 2;
    util.int64_to_buf(arrayOfByte, i, paramLong);
    i += 8;
    util.int16_to_buf(arrayOfByte, i, _sig.length);
    i += 2;
    System.arraycopy(_sig, 0, arrayOfByte, i, _sig.length);
    i += _sig.length;
    util.int16_to_buf(arrayOfByte, i, _sig_key.length);
    i += 2;
    System.arraycopy(_sig_key, 0, arrayOfByte, i, _sig_key.length);
    i = _sig_key.length;
    return cryptor.encrypt(arrayOfByte, 0, arrayOfByte.length, u.z);
  }
  
  public List<WloginLoginInfo> GetAllLoginInfo()
  {
    return mG.k();
  }
  
  public long GetAppidFromUrl(String paramString)
  {
    if (paramString == null) {
      return -1L;
    }
    int i = paramString.indexOf("f=");
    if ((i == -1) || (i + 2 >= paramString.length())) {
      return -1L;
    }
    i += 2;
    String str = "";
    for (;;)
    {
      if ((i >= paramString.length()) || (paramString.charAt(i) == '&')) {}
      try
      {
        long l = Long.parseLong(str);
        return l;
      }
      catch (Exception paramString) {}
      str = str + paramString.charAt(i);
      i += 1;
    }
    return -1L;
  }
  
  public Boolean GetBasicUserInfo(String paramString, WloginSimpleInfo paramWloginSimpleInfo)
  {
    if (paramString == null) {
      return Boolean.valueOf(false);
    }
    long l;
    int i;
    if (!util.check_uin_account(paramString).booleanValue())
    {
      l = mG.b(paramString);
      if (l != 0L) {
        break label140;
      }
      i = 0;
    }
    for (;;)
    {
      if (i == 1)
      {
        paramString = mG.d(l);
        if (paramString == null)
        {
          i = 0;
          label59:
          if (i != 1) {
            break label134;
          }
        }
      }
      label134:
      for (boolean bool = true;; bool = false)
      {
        return Boolean.valueOf(bool);
        l = Long.parseLong(paramString);
        i = 1;
        break;
        if (paramWloginSimpleInfo != null) {
          paramWloginSimpleInfo.get_clone(new WloginSimpleInfo(_uin, _face, _age, _gender, _nick, _img_type, _img_format, _img_url));
        }
        break label59;
      }
      label140:
      i = 1;
    }
  }
  
  public DevlockInfo GetDevLockInfo(String paramString)
  {
    return GetDevLockInfo(paramString, 0L);
  }
  
  public DevlockInfo GetDevLockInfo(String paramString, long paramLong)
  {
    long l = paramLong;
    if (paramLong <= 0L) {
      l = mAysncSeq;
    }
    return b_devlock_info;
  }
  
  public byte[] GetGuid()
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (u.y != null)
    {
      localObject1 = localObject2;
      if (u.y.length > 0)
      {
        localObject1 = new byte[u.y.length];
        System.arraycopy(u.y, 0, localObject1, 0, u.y.length);
      }
    }
    return localObject1;
  }
  
  public WloginLastLoginInfo GetLastLoginInfo()
  {
    Object localObject = mG.k();
    if (localObject == null) {}
    label124:
    label125:
    for (;;)
    {
      return null;
      Iterator localIterator = ((List)localObject).iterator();
      localObject = null;
      while (localIterator.hasNext())
      {
        WloginLoginInfo localWloginLoginInfo = (WloginLoginInfo)localIterator.next();
        if (localObject == null)
        {
          localObject = localWloginLoginInfo;
        }
        else
        {
          if (mCreateTime <= mCreateTime) {
            break label124;
          }
          localObject = localWloginLoginInfo;
        }
      }
      for (;;)
      {
        break;
        if (localObject == null) {
          break label125;
        }
        if ((mAccount != null) && (mAccount.length() > 0)) {
          return new WloginLastLoginInfo(mAccount, mUin);
        }
        return new WloginLastLoginInfo(String.valueOf(mUin), mUin);
      }
    }
  }
  
  public WUserSigInfo GetLocalSig(String paramString, long paramLong)
  {
    if (paramString == null)
    {
      util.LOGI("userAccount null", "");
      return null;
    }
    for (;;)
    {
      try
      {
        if (!util.check_uin_account(paramString).booleanValue())
        {
          l = mG.b(paramString);
          if (l == 0L) {
            break;
          }
          localWloginSigInfo = mG.a(l, paramLong);
          if (localWloginSigInfo == null) {
            continue;
          }
          localWUserSigInfo = new WUserSigInfo();
        }
      }
      catch (Exception localException1)
      {
        long l;
        WloginSigInfo localWloginSigInfo;
        WUserSigInfo localWUserSigInfo = null;
        util.printException(localException1, paramString);
        continue;
        localWUserSigInfo = null;
        continue;
      }
      try
      {
        uin = paramString;
        localWUserSigInfo.get_clone(localWloginSigInfo);
        return localWUserSigInfo;
      }
      catch (Exception localException2)
      {
        continue;
      }
      l = Long.parseLong(paramString);
    }
  }
  
  public Ticket GetLocalTicket(String paramString, long paramLong, int paramInt)
  {
    util.LOGI("GetLocalTicket appid=" + paramLong, paramString);
    if (paramString == null)
    {
      util.LOGI("userAccount null", "");
      return null;
    }
    return GetUserSigInfoTicket(GetLocalSig(paramString, paramLong), paramInt);
  }
  
  public int GetOpenKeyWithoutPasswd(String paramString, long paramLong1, long paramLong2, int paramInt, WUserSigInfo paramWUserSigInfo)
  {
    return GetStWithoutPasswd(paramString, paramLong1, mOpenAppid, -1L, paramInt, paramLong2, null, paramWUserSigInfo, (byte[][])null, (byte[][])null, 0, null);
  }
  
  public byte[] GetPictureData(String paramString)
  {
    return GetPictureData(paramString, 0L);
  }
  
  public byte[] GetPictureData(String paramString, long paramLong)
  {
    long l = paramLong;
    if (paramLong <= 0L) {
      l = mAysncSeq;
    }
    return b_t105.f();
  }
  
  @Deprecated
  public byte[] GetPicturePrompt(String paramString)
  {
    return GetPicturePrompt(paramString, 0L);
  }
  
  @Deprecated
  public byte[] GetPicturePrompt(String paramString, long paramLong)
  {
    long l = paramLong;
    if (paramLong <= 0L) {
      l = mAysncSeq;
    }
    return b_t165.b();
  }
  
  public String GetPicturePromptValue(String paramString)
  {
    return GetPicturePromptValue(paramString, 0L);
  }
  
  public String GetPicturePromptValue(String paramString, long paramLong)
  {
    paramString = GetPicturePrompt(paramString, paramLong);
    int k;
    int j;
    int i;
    if ((paramString != null) && (paramString.length > 3))
    {
      k = util.buf_to_int32(paramString, 0);
      j = 4;
      i = 0;
    }
    for (;;)
    {
      if ((i >= k) || (paramString.length < j + 1)) {}
      int m;
      String str1;
      do
      {
        do
        {
          do
          {
            return "";
            m = util.buf_to_int8(paramString, j);
            j += 1;
          } while (paramString.length < j + m);
          str1 = new String(paramString, j, m);
          m = j + m;
        } while (paramString.length < m + 2);
        j = util.buf_to_int32(paramString, m);
        m += 4;
      } while (paramString.length < m + j);
      String str2 = new String(paramString, m, j);
      if (str1.equals("pic_reason")) {
        return str2;
      }
      i += 1;
      j += m;
    }
  }
  
  public Ticket GetPskey(String paramString, long paramLong, String[] paramArrayOfString, WtTicketPromise paramWtTicketPromise)
  {
    Bundle localBundle = new Bundle();
    localBundle.putStringArray("domains", paramArrayOfString);
    return GetTicket(paramString, paramLong, 1048576, paramWtTicketPromise, localBundle);
  }
  
  public Ticket GetSkey(String paramString, long paramLong, WtTicketPromise paramWtTicketPromise)
  {
    return GetTicket(paramString, paramLong, 4096, paramWtTicketPromise, null);
  }
  
  public int GetStViaSMSVerifyLogin(String paramString, long paramLong1, long paramLong2, int paramInt, WUserSigInfo paramWUserSigInfo)
  {
    util.LOGI("user:" + paramString + " GetStViaSMSVerifyLogin ...", paramString);
    if (oicq.wlogin_sdk.register.h.w) {}
    for (String str = oicq.wlogin_sdk.register.h.y;; str = "") {
      return GetStWithPasswd(paramString, paramLong1, paramInt, paramLong2, null, false, str, paramWUserSigInfo, (byte[][])null, true, 0);
    }
  }
  
  public int GetStViaSMSVerifyLogin(String paramString, long paramLong1, long paramLong2, long[] paramArrayOfLong, int paramInt, WUserSigInfo paramWUserSigInfo)
  {
    byte[][] arrayOfByte = (byte[][])null;
    if ((paramArrayOfLong != null) && (paramArrayOfLong.length > 0))
    {
      int i = paramArrayOfLong.length;
      arrayOfByte = (byte[][])Array.newInstance(Byte.TYPE, new int[] { i, 0 });
    }
    for (;;)
    {
      util.LOGI("user:" + paramString + " GetStViaSMSVerifyLogin ...", paramString);
      if (oicq.wlogin_sdk.register.h.w) {}
      for (String str = oicq.wlogin_sdk.register.h.y;; str = "") {
        return GetStWithPasswd(paramString, paramLong1, paramInt, paramLong2, paramArrayOfLong, false, str, paramWUserSigInfo, arrayOfByte, true, 0);
      }
    }
  }
  
  public int GetStWithPasswd(String paramString1, long paramLong1, int paramInt, long paramLong2, long[] paramArrayOfLong, boolean paramBoolean, String paramString2, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte)
  {
    return GetStWithPasswd(paramString1, paramLong1, paramInt, paramLong2, paramArrayOfLong, paramBoolean, paramString2, paramWUserSigInfo, paramArrayOfByte, false, 0);
  }
  
  public int GetStWithPasswd(String paramString1, long paramLong1, long paramLong2, int paramInt, String paramString2, WUserSigInfo paramWUserSigInfo)
  {
    return GetStWithPasswd(paramString1, paramLong1, paramInt, paramLong2, null, false, paramString2, paramWUserSigInfo, (byte[][])null, false, 0);
  }
  
  @Deprecated
  public int GetStWithPasswd(String paramString1, long paramLong, String paramString2, WUserSigInfo paramWUserSigInfo)
  {
    return GetStWithPasswd(paramString1, paramLong, mMainSigMap, 1L, null, false, paramString2, paramWUserSigInfo, (byte[][])null, false, 0);
  }
  
  public int GetStWithPasswdMd5(String paramString1, long paramLong1, long paramLong2, int paramInt, String paramString2, WUserSigInfo paramWUserSigInfo)
  {
    return GetStWithPasswd(paramString1, paramLong1, paramInt, paramLong2, null, true, paramString2, paramWUserSigInfo, (byte[][])null, false, 0);
  }
  
  @Deprecated
  public int GetStWithPasswdMd5(String paramString1, long paramLong, String paramString2, WUserSigInfo paramWUserSigInfo)
  {
    return GetStWithPasswd(paramString1, paramLong, mMainSigMap, 1L, null, true, paramString2, paramWUserSigInfo, (byte[][])null, false, 0);
  }
  
  public int GetStWithoutPasswd(String paramString, long paramLong1, long paramLong2, long paramLong3, int paramInt, long paramLong4, long[] paramArrayOfLong, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte1, byte[][] paramArrayOfByte2)
  {
    return GetStWithoutPasswd(paramString, paramLong1, paramLong2, paramLong3, paramInt, paramLong4, paramArrayOfLong, paramWUserSigInfo, paramArrayOfByte1, paramArrayOfByte2, 0, null);
  }
  
  public int GetStWithoutPasswd(String paramString, long paramLong1, long paramLong2, long paramLong3, int paramInt, WUserSigInfo paramWUserSigInfo)
  {
    return GetStWithoutPasswd(paramString, paramLong1, paramLong2, -1L, paramInt, paramLong3, null, paramWUserSigInfo, (byte[][])null, (byte[][])null, 0, null);
  }
  
  public int GetStWithoutPasswd(String paramString, long paramLong1, long paramLong2, long paramLong3, int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, WUserSigInfo paramWUserSigInfo)
  {
    byte[][] arrayOfByte = new byte[4][];
    arrayOfByte[0] = new byte[1];
    arrayOfByte[0][0] = 1;
    arrayOfByte[1] = paramArrayOfByte1;
    arrayOfByte[2] = paramArrayOfByte2;
    arrayOfByte[3] = paramArrayOfByte3;
    return GetStWithoutPasswd(paramString, paramLong1, paramLong2, -1L, paramInt, paramLong3, null, paramWUserSigInfo, (byte[][])null, arrayOfByte, 0, null);
  }
  
  @Deprecated
  public int GetStWithoutPasswd(String paramString, long paramLong1, long paramLong2, WUserSigInfo paramWUserSigInfo)
  {
    return GetStWithoutPasswd(paramString, paramLong1, paramLong2, -1L, mMainSigMap, 1L, null, paramWUserSigInfo, (byte[][])null, (byte[][])null, 0, null);
  }
  
  public int GetStWithoutPasswd(byte[] paramArrayOfByte, long paramLong, int paramInt, WUserSigInfo paramWUserSigInfo)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0)) {
      return 64519;
    }
    Object localObject = cryptor.decrypt(paramArrayOfByte, 0, paramArrayOfByte.length, u.z);
    if ((localObject == null) || (localObject.length <= 0)) {
      return 64519;
    }
    if (2 > localObject.length) {
      return 64519;
    }
    int i = util.buf_to_int16((byte[])localObject, 0);
    if ((i <= 0) || (i + 2 > localObject.length)) {
      return 64519;
    }
    paramArrayOfByte = new String((byte[])localObject, 2, i);
    i += 2;
    if (i + 8 > localObject.length) {
      return 64519;
    }
    long l = util.buf_to_int64((byte[])localObject, i);
    int j = i + 8;
    if (j + 2 > localObject.length) {
      return 64519;
    }
    i = util.buf_to_int16((byte[])localObject, j);
    j += 2;
    if ((i <= 0) || (j + i > localObject.length)) {
      return 64519;
    }
    byte[] arrayOfByte1 = new byte[i];
    System.arraycopy(localObject, j, arrayOfByte1, 0, arrayOfByte1.length);
    j += i;
    if (j + 2 > localObject.length) {
      return 64519;
    }
    i = util.buf_to_int16((byte[])localObject, j);
    j += 2;
    if ((i <= 0) || (j + i > localObject.length)) {
      return 64519;
    }
    byte[] arrayOfByte2 = new byte[i];
    System.arraycopy(localObject, j, arrayOfByte2, 0, arrayOfByte2.length);
    localObject = new byte[3][];
    localObject[0] = new byte[1];
    localObject[0][0] = 2;
    localObject[1] = arrayOfByte1;
    localObject[2] = arrayOfByte2;
    return GetStWithoutPasswd(paramArrayOfByte, l, paramLong, -1L, paramInt, 1L, null, paramWUserSigInfo, (byte[][])null, (byte[][])localObject, 0, null);
  }
  
  public Ticket GetTicket(String paramString, long paramLong, int paramInt, WtTicketPromise paramWtTicketPromise, Bundle paramBundle)
  {
    Object localObject2 = new StringBuilder().append("GetTicket ").append(paramString).append(", ").append(paramLong).append(" sig ").append(Integer.toHexString(paramInt)).append(" ");
    if (paramBundle == null) {}
    int j;
    Object localObject3;
    for (Object localObject1 = "null";; localObject1 = Integer.valueOf(paramBundle.size()))
    {
      util.LOGI(localObject1, "");
      localObject1 = null;
      j = 2;
      localObject2 = GetLocalSig(paramString, paramLong);
      if (localObject2 != null) {
        break label196;
      }
      if (j != 1) {
        break;
      }
      localObject3 = localObject1;
      label103:
      if (!IsNeedLoginWithPasswd(paramString, paramLong).booleanValue()) {
        break label431;
      }
      paramString = new ErrMsg();
      paramString.setType(64532);
      if (paramWtTicketPromise != null) {
        paramWtTicketPromise.Failed(paramString);
      }
      localObject1 = null;
      label146:
      return localObject1;
    }
    RefreshMemorySig();
    int i = j - 1;
    localObject2 = localObject1;
    for (;;)
    {
      localObject1 = localObject2;
      j = i;
      if (i > 0) {
        break;
      }
      localObject3 = localObject2;
      break label103;
      label196:
      Ticket localTicket = GetUserSigInfoTicket((WUserSigInfo)localObject2, paramInt);
      if ((localTicket == null) || (_sig == null) || (_sig.length == 0))
      {
        localObject3 = localObject1;
        if (j == 1) {
          break label103;
        }
        RefreshMemorySig();
        i = j - 1;
        localObject2 = localObject1;
        continue;
      }
      long l = u.f();
      localObject2 = localObject1;
      if (paramBundle != null)
      {
        localObject2 = localObject1;
        if (paramInt == 1048576) {
          localObject2 = paramBundle.getStringArray("domains");
        }
      }
      i = isPskeyExpired(paramInt, (String[])localObject2, localTicket, l, j);
      localObject3 = localObject2;
      if (i == 1) {
        break label103;
      }
      if (i == 2)
      {
        i = j - 1;
      }
      else
      {
        localObject1 = localTicket;
        if (i == 3) {
          break label146;
        }
        util.LOGI(Integer.toHexString(paramInt) + " expires in " + (_expire_time - l) / 60L / 60L + "h");
        localObject1 = localTicket;
        if (l < _expire_time) {
          break label146;
        }
        localObject3 = localObject2;
        if (j == 1) {
          break label103;
        }
        RefreshMemorySig();
        i = j - 1;
      }
    }
    label431:
    localObject1 = new WUserSigInfo();
    if (paramBundle != null) {}
    for (i = paramBundle.getInt("subappid", 1);; i = 1)
    {
      if (localObject3 != null)
      {
        j = 0;
        int k = Math.min(20, localObject3.length);
        while (j < k)
        {
          localObject2 = localObject3[j];
          if ((localObject2 != null) && (((String)localObject2).length() > 0)) {
            _domains.add(localObject2);
          }
          j += 1;
        }
      }
      GetStWithoutPasswd(paramString, paramLong, paramLong, i, paramInt, (WUserSigInfo)localObject1, new WtloginHelper.1(this, paramWtTicketPromise, paramString, paramLong, paramInt, paramBundle));
      break;
    }
  }
  
  public long GetTimeDifference()
  {
    return u.Z;
  }
  
  public Boolean IsNeedLoginWithPasswd(String paramString, long paramLong)
  {
    boolean bool = false;
    int i = 1;
    if (paramString == null) {
      return Boolean.valueOf(true);
    }
    for (;;)
    {
      try
      {
        long l1;
        if (!util.check_uin_account(paramString).booleanValue())
        {
          long l2 = mG.b(paramString);
          l1 = l2;
          if (l2 == 0L)
          {
            bool = true;
            util.LOGI("user:" + paramString + " appid:" + paramLong + " need password:" + bool + " flag=" + i, paramString);
            return Boolean.valueOf(bool);
          }
        }
        else
        {
          l1 = Long.parseLong(paramString);
        }
        WloginSigInfo localWloginSigInfo = mG.a(l1, paramLong);
        if ((localWloginSigInfo != null) && (_en_A1 != null) && (_en_A1.length != 0) && (_noPicSig != null) && (_noPicSig.length != 0))
        {
          i = 2;
          continue;
        }
        if ((localWloginSigInfo == null) || (_TGT == null) || (_TGT.length == 0) || (localWloginSigInfo.iSExpireA2(u.f()))) {
          break label224;
        }
        i = 0;
      }
      finally {}
      continue;
      label224:
      bool = true;
      i = 3;
    }
  }
  
  public Boolean IsUserHaveA1(String paramString, long paramLong)
  {
    if (paramString == null) {
      return Boolean.valueOf(false);
    }
    long l1;
    Object localObject;
    if (!util.check_uin_account(paramString).booleanValue())
    {
      long l2 = mG.b(paramString);
      l1 = l2;
      if (l2 != 0L) {
        break label116;
      }
      localObject = null;
    }
    while ((localObject == null) || (_en_A1 == null) || (_en_A1.length <= 0))
    {
      util.LOGI("userAccount:" + paramString + " dwAppid:" + paramLong + " IsUserHaveA1 return: null", paramString);
      return Boolean.valueOf(false);
      l1 = Long.parseLong(paramString);
      label116:
      WloginSigInfo localWloginSigInfo = mG.a(l1, paramLong);
      localObject = localWloginSigInfo;
      if (localWloginSigInfo == null) {
        localObject = localWloginSigInfo;
      }
    }
    util.LOGI("userAccount:" + paramString + " dwAppid:" + paramLong + " IsUserHaveA1 return: not null", paramString);
    return Boolean.valueOf(true);
  }
  
  public boolean IsWtLoginUrl(String paramString)
  {
    if (paramString == null) {}
    do
    {
      int i;
      do
      {
        return false;
        i = paramString.indexOf("?k=");
      } while ((i == -1) || (i + 3 + 32 > paramString.length()));
      i += 3;
      paramString = paramString.substring(i, i + 32);
    } while (util.base64_decode_url(paramString.getBytes(), paramString.length()) == null);
    return true;
  }
  
  public Intent PrepareQloginIntent(long paramLong1, long paramLong2, String paramString)
  {
    Object localObject1 = "com.tencent.mobileqq";
    boolean bool1 = util.CheckMayFastLogin(mContext);
    boolean bool2 = util.CheckQQMiniHD(mContext);
    if (!bool1)
    {
      if (bool2) {
        localObject1 = "com.tencent.minihd.qq";
      }
    }
    else
    {
      Object localObject3 = util.get_rsa_pubkey(mContext);
      Object localObject2;
      if (localObject3 != null)
      {
        localObject2 = localObject3;
        if (localObject3.length != 0) {}
      }
      else
      {
        localObject2 = util.string_to_buf("30818902818100daaa2a418b271f3dfcf8f0a9120326d47f07618593d8d71d61a4fe987cc47740e491105bf8e68bd479bf51dfe19d3b06e12017df6d87a0f43bb82b57f59bd4220f2a3d8d68904a6ddb51197989e6e82512d8d8fa6c41b755a8ca962595d3e1e1be7ea01677249be4794cd7c6682d611c1bd81f0a16231fb83517515b94d13e5d0203010001");
      }
      localObject3 = new Intent();
      ((Intent)localObject3).setClassName((String)localObject1, "com.tencent.open.agent.AgentActivity");
      localObject1 = new Bundle();
      ((Bundle)localObject1).putLong("dstSsoVer", 1L);
      ((Bundle)localObject1).putLong("dstAppid", paramLong1);
      ((Bundle)localObject1).putLong("subDstAppid", paramLong2);
      ((Bundle)localObject1).putByteArray("dstAppVer", paramString.getBytes());
      ((Bundle)localObject1).putByteArray("publickey", (byte[])localObject2);
      ((Intent)localObject3).putExtra("key_params", (Bundle)localObject1);
      ((Intent)localObject3).putExtra("key_action", "action_quick_login");
      return localObject3;
    }
    return null;
  }
  
  public Intent PrepareQloginResult(String paramString, long paramLong1, long paramLong2, int paramInt, WFastLoginInfo paramWFastLoginInfo)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("quicklogin_uin", paramString);
    paramString = (byte[])_outA1.clone();
    if ((paramString != null) && (paramString.length > 0)) {
      localIntent.putExtra("quicklogin_buff", new RSACrypt(mContext).EncryptData(util.get_cp_pubkey(mContext, paramLong1, paramLong2), paramString));
    }
    localIntent.putExtra("quicklogin_ret", paramInt);
    return localIntent;
  }
  
  public Intent PrepareSilenceLoginIntent(long paramLong1, long paramLong2, String paramString)
  {
    Object localObject2 = util.get_rsa_pubkey(mContext);
    Object localObject1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (localObject2.length != 0) {}
    }
    else
    {
      localObject1 = util.string_to_buf("30818902818100daaa2a418b271f3dfcf8f0a9120326d47f07618593d8d71d61a4fe987cc47740e491105bf8e68bd479bf51dfe19d3b06e12017df6d87a0f43bb82b57f59bd4220f2a3d8d68904a6ddb51197989e6e82512d8d8fa6c41b755a8ca962595d3e1e1be7ea01677249be4794cd7c6682d611c1bd81f0a16231fb83517515b94d13e5d0203010001");
    }
    localObject2 = new Intent();
    Bundle localBundle = new Bundle();
    localBundle.putLong("dstSsoVer", 1L);
    localBundle.putLong("dstAppid", paramLong1);
    localBundle.putLong("subDstAppid", paramLong2);
    localBundle.putByteArray("dstAppVer", paramString.getBytes());
    localBundle.putByteArray("publickey", (byte[])localObject1);
    ((Intent)localObject2).putExtra("key_params", localBundle);
    ((Intent)localObject2).putExtra("key_action", "action_quick_login");
    return localObject2;
  }
  
  public int QueryCodeResult(long paramLong, WUserSigInfo paramWUserSigInfo)
  {
    util.LOGI(" QueryCodeResult ...", "");
    oicq.wlogin_sdk.code2d.d localD = new oicq.wlogin_sdk.code2d.d();
    TransReqContext localTransReqContext = new TransReqContext();
    localTransReqContext.set_code2d_func_req();
    localTransReqContext.set_subcmd(localD.get_cmd());
    _body = localD.a(0L, paramLong, oicq.wlogin_sdk.code2d.c.i, new byte[0]);
    return RequestTransport(0, 1, null, paramLong, _role, localTransReqContext, paramWUserSigInfo);
  }
  
  public void RefreshMemorySig()
  {
    mG.j();
  }
  
  public int RefreshPictureData(String paramString, WUserSigInfo paramWUserSigInfo)
  {
    WUserSigInfo localWUserSigInfo = paramWUserSigInfo;
    if (paramWUserSigInfo == null) {
      localWUserSigInfo = new WUserSigInfo();
    }
    return RefreshPictureData(paramString, localWUserSigInfo, 0);
  }
  
  public int RefreshSMSData(String paramString, long paramLong, WUserSigInfo paramWUserSigInfo)
  {
    if (paramWUserSigInfo == null) {
      paramWUserSigInfo = new WUserSigInfo();
    }
    for (;;)
    {
      return RefreshSMSData(paramString, paramLong, paramWUserSigInfo, 0);
    }
  }
  
  public int RefreshSMSVerifyLoginCode(String paramString, WUserSigInfo paramWUserSigInfo)
  {
    return RefreshSMSVerifyLoginCode(paramString, paramWUserSigInfo, 0);
  }
  
  public int RegGetAccount(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, int paramInt, WUserSigInfo paramWUserSigInfo)
  {
    if ((paramArrayOfByte3 == null) || (paramArrayOfByte3.length <= 0)) {
      return 64519;
    }
    util.LOGI("RegGetAccount ...", "");
    oicq.wlogin_sdk.register.b localB = new oicq.wlogin_sdk.register.b();
    TransReqContext localTransReqContext = new TransReqContext();
    oicq.wlogin_sdk.register.h localH = mRegStatus;
    if (paramArrayOfByte1 != null) {}
    for (j = ((byte[])paramArrayOfByte1.clone());; j = new byte[0])
    {
      if (paramInt == 4) {
        b = "";
      }
      localTransReqContext.set_register_req();
      localTransReqContext.set_subcmd(localB.a());
      _body = localB.a(e, paramArrayOfByte1, paramArrayOfByte3, paramArrayOfByte4, paramInt, b.getBytes(), paramArrayOfByte2, false, null, 0L, u.C, u.x);
      return RequestTransport(0, 1, null, 0L, i, localTransReqContext, paramWUserSigInfo);
    }
  }
  
  public int RegGetSMSVerifyLoginAccount(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, WUserSigInfo paramWUserSigInfo)
  {
    util.LOGI("RegGetSMSVerifyLoginAccount ...", "");
    oicq.wlogin_sdk.register.b localB = new oicq.wlogin_sdk.register.b();
    TransReqContext localTransReqContext = new TransReqContext();
    oicq.wlogin_sdk.register.h localH = mRegStatus;
    if (paramArrayOfByte1 != null) {}
    for (j = ((byte[])paramArrayOfByte1.clone());; j = new byte[0])
    {
      oicq.wlogin_sdk.register.h.w = true;
      oicq.wlogin_sdk.register.h.y = util.get_mpasswd();
      localTransReqContext.set_register_req();
      localTransReqContext.set_subcmd(localB.a());
      _body = localB.a(e, paramArrayOfByte1, oicq.wlogin_sdk.register.h.y.getBytes(), paramArrayOfByte3, 1, b.getBytes(), paramArrayOfByte2, true, GetGuid(), h, u.C, u.x);
      return RequestTransport(0, 1, null, 0L, i, localTransReqContext, paramWUserSigInfo);
    }
  }
  
  public int RegQueryAccount(int paramInt, byte[] paramArrayOfByte, long paramLong, WUserSigInfo paramWUserSigInfo)
  {
    byte[] arrayOfByte = paramArrayOfByte;
    if (paramArrayOfByte == null) {
      arrayOfByte = new byte[0];
    }
    util.LOGI("RegQueryAccount ...", "");
    mRegStatus = new oicq.wlogin_sdk.register.h();
    mRegStatus.b = new String(arrayOfByte);
    paramArrayOfByte = new oicq.wlogin_sdk.register.c();
    TransReqContext localTransReqContext = new TransReqContext();
    oicq.wlogin_sdk.register.h localH = mRegStatus;
    localTransReqContext.set_register_req();
    localTransReqContext.set_subcmd(paramArrayOfByte.a());
    _body = paramArrayOfByte.a(paramInt, arrayOfByte, paramLong);
    return RequestTransport(0, 1, null, 0L, i, localTransReqContext, paramWUserSigInfo);
  }
  
  public int RegQueryClientSentMsgStatus(WUserSigInfo paramWUserSigInfo)
  {
    util.LOGI("RegQueryClientSentMsgStatus ...", "");
    oicq.wlogin_sdk.register.d localD = new oicq.wlogin_sdk.register.d();
    TransReqContext localTransReqContext = new TransReqContext();
    oicq.wlogin_sdk.register.h localH = mRegStatus;
    localTransReqContext.set_register_req();
    localTransReqContext.set_subcmd(localD.a());
    _body = localD.b(e, mRegStatus.o);
    return RequestTransport(0, 1, null, 0L, i, localTransReqContext, paramWUserSigInfo);
  }
  
  public int RegRequestServerResendMsg(WUserSigInfo paramWUserSigInfo)
  {
    util.LOGI("RegRequestServerResendMsg ...", "");
    oicq.wlogin_sdk.register.e localE = new oicq.wlogin_sdk.register.e();
    TransReqContext localTransReqContext = new TransReqContext();
    oicq.wlogin_sdk.register.h localH = mRegStatus;
    localTransReqContext.set_register_req();
    localTransReqContext.set_subcmd(localE.a());
    _body = localE.b(e, null);
    return RequestTransport(0, 1, null, 0L, i, localTransReqContext, paramWUserSigInfo);
  }
  
  public int RegSubmitMobile(String paramString, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, WUserSigInfo paramWUserSigInfo)
  {
    if (paramString == null) {}
    for (paramString = new byte[0];; paramString = paramString.getBytes()) {
      return RegSubmitMobile(paramString, paramArrayOfByte1, null, paramArrayOfByte2, paramInt1, paramInt2, paramInt3, paramLong1, paramLong2, paramWUserSigInfo);
    }
  }
  
  public int RegSubmitMobile(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, WUserSigInfo paramWUserSigInfo)
  {
    return RegSubmitMobile(null, paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramInt1, paramInt2, paramInt3, paramLong1, paramLong2, paramWUserSigInfo);
  }
  
  public int RegSubmitMsgChk(byte[] paramArrayOfByte, WUserSigInfo paramWUserSigInfo)
  {
    if (paramArrayOfByte == null) {
      return 64519;
    }
    util.LOGI("RegSubmitMsgChk ...", "");
    oicq.wlogin_sdk.register.g localG = new oicq.wlogin_sdk.register.g();
    TransReqContext localTransReqContext = new TransReqContext();
    oicq.wlogin_sdk.register.h localH = mRegStatus;
    localTransReqContext.set_register_req();
    localTransReqContext.set_subcmd(localG.a());
    _body = localG.b(e, paramArrayOfByte);
    return RequestTransport(0, 1, null, 0L, i, localTransReqContext, paramWUserSigInfo);
  }
  
  public int RequestTransport(int paramInt1, int paramInt2, String paramString, long paramLong1, long paramLong2, TransReqContext paramTransReqContext, WUserSigInfo paramWUserSigInfo)
  {
    if (paramInt1 == 0)
    {
      new HelperThread(this, mHelperHandler, paramInt2, paramString, paramLong1, paramLong2, paramTransReqContext, paramWUserSigInfo, "RequestTransport").RunReq(9);
      return 64535;
    }
    u localU = mG.a(0L);
    util.LOGI("user:" + paramString + " encrypt:" + paramInt2 + " appid:" + paramLong1 + " role:" + paramLong2 + " Seq:" + h + " RequestTransport...", paramString);
    g = paramString;
    if (paramInt2 != 0) {
      if (paramString == null)
      {
        m = 0;
        paramInt1 = new aa(localU).a(0L, paramTransReqContext, null, null, paramLong1, paramLong2, paramWUserSigInfo);
      }
    }
    for (;;)
    {
      localU.i();
      util.LOGI("user:" + paramString + " encrypt:" + paramInt2 + " appid:" + paramLong1 + " role:" + paramLong2 + " Seq:" + h + " RequestTransport ret=" + paramInt1, paramString);
      return paramInt1;
      WloginSimpleInfo localWloginSimpleInfo = new WloginSimpleInfo();
      if ((paramString == null) || (!GetBasicUserInfo(paramString, localWloginSimpleInfo).booleanValue()))
      {
        paramInt1 = 64533;
      }
      else
      {
        WloginSigInfo localWloginSigInfo = localU.a(_uin, paramLong1);
        if (localWloginSigInfo == null)
        {
          paramInt1 = 64532;
        }
        else
        {
          f = _uin;
          paramInt1 = new aa(localU).a(_uin, paramTransReqContext, _userStSig, _userSt_Key, paramLong1, paramLong2, paramWUserSigInfo);
          continue;
          f = 0L;
          paramInt1 = new aa(localU).a(f, paramTransReqContext, null, null, paramLong1, paramLong2, paramWUserSigInfo);
        }
      }
    }
  }
  
  public int RequestTransportMsf(int paramInt1, int paramInt2, String paramString, long paramLong1, long paramLong2, TransReqContext paramTransReqContext)
  {
    if (paramInt1 == 0)
    {
      new HelperThread(this, mHelperHandler, paramInt2, paramString, paramLong1, paramLong2, paramTransReqContext, "RequestTransportMsf").RunReq(10);
      return 64535;
    }
    u localU = mG.a(0L);
    util.LOGI("user:" + paramString + " encrypt:" + paramInt2 + " appid:" + paramLong1 + " role:" + paramLong2 + " Seq:" + h + " RequestTransportMsf...", paramString);
    g = paramString;
    Object localObject1;
    if (paramInt2 != 0)
    {
      localObject1 = new WloginSimpleInfo();
      if ((paramString == null) || (!GetBasicUserInfo(paramString, (WloginSimpleInfo)localObject1).booleanValue())) {
        paramInt1 = 64533;
      }
    }
    for (;;)
    {
      localU.i();
      util.LOGI("user:" + paramString + " encrypt:" + paramInt2 + " appid:" + paramLong1 + " role:" + paramLong2 + " Seq:" + h + " RequestTransportMsf ret=" + paramInt1, paramString);
      return paramInt1;
      Object localObject2 = localU.a(_uin, paramLong1);
      if (localObject2 == null)
      {
        paramInt1 = 64532;
      }
      else
      {
        f = _uin;
        paramInt1 = new aa(localU).a(_uin, paramTransReqContext, _userStSig, _userSt_Key, _TGT, paramLong1, paramLong2, new WUserSigInfo());
        continue;
        if ((util.check_uin_account(paramString).booleanValue()) && (Long.parseLong(paramString) == 0L))
        {
          f = 0L;
          localObject1 = new aa(localU);
          localObject2 = new WUserSigInfo();
          paramInt1 = ((aa)localObject1).a(0L, paramTransReqContext, null, null, new byte[0], paramLong1, paramLong2, (WUserSigInfo)localObject2);
        }
        else
        {
          localObject1 = new WloginSimpleInfo();
          if ((paramString == null) || (!GetBasicUserInfo(paramString, (WloginSimpleInfo)localObject1).booleanValue()))
          {
            paramInt1 = 64533;
          }
          else
          {
            localObject2 = localU.a(_uin, paramLong1);
            if (localObject2 == null)
            {
              paramInt1 = 64532;
            }
            else
            {
              f = _uin;
              paramInt1 = new aa(localU).a(_uin, paramTransReqContext, null, null, _TGT, paramLong1, paramLong2, new WUserSigInfo());
            }
          }
        }
      }
    }
  }
  
  public WUserSigInfo ResolveQloginIntent(Intent paramIntent)
  {
    if (paramIntent == null) {}
    String str;
    do
    {
      do
      {
        return null;
      } while (paramIntent.getExtras().getInt("quicklogin_ret") != 0);
      str = paramIntent.getExtras().getString("quicklogin_uin");
      paramIntent = paramIntent.getExtras().getByteArray("quicklogin_buff");
    } while ((str == null) || (paramIntent == null));
    WUserSigInfo localWUserSigInfo = new WUserSigInfo();
    _fastLoginBuf = new RSACrypt(mContext).DecryptData(null, paramIntent);
    if (_fastLoginBuf == null)
    {
      util.LOGI("rsa decrypt failed", "");
      return null;
    }
    uin = str;
    return localWUserSigInfo;
  }
  
  public void SetAppClientVersion(int paramInt)
  {
    u.u = paramInt;
  }
  
  public void SetCanWebVerify(boolean paramBoolean)
  {
    l.D = paramBoolean;
  }
  
  public void SetDevlockMobileType(int paramInt)
  {
    s.D = paramInt;
  }
  
  public void SetImgType(int paramInt)
  {
    u.v = paramInt;
    mMiscBitmap |= 0x80;
  }
  
  public WtloginListener SetListener(WtloginListener paramWtloginListener)
  {
    WtloginListener localWtloginListener = mListener;
    mListener = paramWtloginListener;
    return localWtloginListener;
  }
  
  public void SetLocalId(int paramInt)
  {
    u.s = paramInt;
  }
  
  public void SetMsfTransportFlag(int paramInt)
  {
    mG.k = paramInt;
    if (paramInt != 0)
    {
      u.ab = new byte[4];
      u.aa = 0L;
      mG.l = 45000;
    }
  }
  
  public int SetNeedForPayToken(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    if ((paramString1 != null) && (paramString1.length() > 0))
    {
      l.E = paramString1.getBytes();
      if (paramArrayOfByte != null) {
        l.G = paramArrayOfByte;
      }
      if (paramString2 != null)
      {
        paramString1 = paramString2;
        if (paramString2.length() != 0) {}
      }
      else
      {
        paramString1 = util.getChannelId(mContext, null);
      }
      l.F = paramString1.getBytes();
      if ((paramString1 == null) || (paramString1.length() == 0)) {
        return -2;
      }
    }
    else
    {
      return -1;
    }
    return 0;
  }
  
  public void SetPicType(int paramInt)
  {
    u.w = paramInt;
  }
  
  public void SetRegDevLockFlag(int paramInt)
  {
    u.x = paramInt;
  }
  
  public void SetSigMap(int paramInt)
  {
    mMainSigMap = (paramInt | 0xC0);
  }
  
  public void SetTestHost(int paramInt, String paramString)
  {
    k.a(paramInt, paramString);
  }
  
  public void SetTimeOut(int paramInt)
  {
    mG.l = paramInt;
  }
  
  public int VerifyCode(String paramString, long paramLong, boolean paramBoolean, byte[] paramArrayOfByte, int[] paramArrayOfInt, int paramInt, WUserSigInfo paramWUserSigInfo)
  {
    Object localObject1 = new WloginSimpleInfo();
    if (!GetBasicUserInfo(paramString, (WloginSimpleInfo)localObject1).booleanValue()) {
      return 64533;
    }
    long l = _uin;
    WloginSigInfo localWloginSigInfo = FindUserSig(l, paramLong);
    if (localWloginSigInfo == null) {
      return 64532;
    }
    Object localObject3 = new ck();
    Object localObject2 = new byte[0];
    localObject1 = localObject2;
    if (_G != null)
    {
      localObject1 = localObject2;
      if (_G.length > 0)
      {
        localObject1 = localObject2;
        if (_dpwd != null)
        {
          localObject1 = localObject2;
          if (_dpwd.length > 0)
          {
            localObject1 = localObject2;
            if (_randseed != null)
            {
              localObject1 = localObject2;
              if (_randseed.length > 0)
              {
                ((ck)localObject3).a(_G, l, u.y, _dpwd, paramLong, 1L, _randseed);
                localObject1 = ((ck)localObject3).b();
              }
            }
          }
        }
      }
    }
    util.LOGI("user:" + paramString + " VerifyCode ...", paramString);
    localObject2 = new oicq.wlogin_sdk.code2d.e();
    localObject3 = new TransReqContext();
    ((TransReqContext)localObject3).set_code2d_func_req();
    ((TransReqContext)localObject3).set_subcmd(((oicq.wlogin_sdk.code2d.e)localObject2).get_cmd());
    _body = ((oicq.wlogin_sdk.code2d.e)localObject2).a(l, paramLong, paramBoolean, paramArrayOfByte, paramArrayOfInt, _TGT, u.y, u.C, paramInt, (byte[])localObject1);
    return RequestTransport(0, 1, paramString, paramLong, _role, (TransReqContext)localObject3, paramWUserSigInfo);
  }
  
  public int VerifySMSVerifyLoginCode(String paramString1, String paramString2, WUserSigInfo paramWUserSigInfo)
  {
    return VerifySMSVerifyLoginCode(paramString1, paramString2, paramWUserSigInfo, 0);
  }
  
  public boolean getHasPassword(long paramLong)
  {
    String str = mG.e(paramLong);
    util.LOGI("getHasPasswd ..." + String.valueOf(str), "" + paramLong);
    if (str == null) {}
    UinInfo localUinInfo;
    do
    {
      return true;
      localUinInfo = mG.c(str);
    } while (localUinInfo == null);
    boolean bool = localUinInfo.getHasPassword();
    util.LOGI("getHasPasswd userAccount: " + str + ", uin: " + paramLong + " hasPasswd: " + bool, "");
    return bool;
  }
  
  public void setHasPassword(long paramLong, boolean paramBoolean)
  {
    String str = mG.e(paramLong);
    util.LOGI("setHasPasswd ..." + String.valueOf(str), "");
    if (str == null) {
      return;
    }
    mG.a(str, Long.valueOf(paramLong), paramBoolean);
    util.LOGI("setHasPasswd userAccount: " + str + ", uin: " + paramLong + " hasPassword:" + paramBoolean, "");
  }
  
  public void setMsgType(int paramInt1, int paramInt2, int paramInt3)
  {
    oicq.wlogin_sdk.devicelock.DevlockBase.a.a = paramInt1;
    oicq.wlogin_sdk.devicelock.DevlockBase.a.b = paramInt2;
    oicq.wlogin_sdk.devicelock.DevlockBase.a.c = paramInt3;
  }
  
  public class HelperThread
    extends Thread
  {
    boolean isSelfLooper = false;
    byte[] mAppName2;
    byte[] mAppSign2;
    byte[] mAppVer2;
    long mAppid1;
    long mAppid2;
    long mDwAppid;
    long mDwDstAppPri;
    long mDwDstAppid;
    long[] mDwDstSubAppidList;
    int mDwMainSigMap;
    long[] mDwSubAppidList;
    long mDwSubDstAppid;
    int mEncrypt;
    WFastLoginInfo mFastLoginInfo;
    Handler mHandler;
    WtloginHelper mHelper;
    boolean mIsSmslogin = false;
    String mMsgCode;
    byte[] mPictureData;
    WtTicketPromise mPromise;
    boolean mPwdMd5;
    int mReportErrType;
    TransReqContext mReqContext;
    int mReqType;
    byte[][] mReserve;
    long mRole;
    byte[][] mST;
    byte[] mST1;
    byte[] mST1Key;
    long mSmsAppid;
    long mSsoVer2;
    long mSubAppid1;
    long mSubAppid2;
    long mUIN;
    String mUserAccount;
    byte[] mUserInput;
    String mUserPasswd;
    WUserSigInfo mUserSigInfo = null;
    
    HelperThread(WtloginHelper paramWtloginHelper, Handler paramHandler)
    {
      mHelper = paramWtloginHelper;
      mHandler = paramHandler;
    }
    
    HelperThread(WtloginHelper paramWtloginHelper, Handler paramHandler, int paramInt, String paramString1, long paramLong1, long paramLong2, TransReqContext paramTransReqContext, String paramString2)
    {
      mHelper = paramWtloginHelper;
      mHandler = paramHandler;
      mEncrypt = paramInt;
      mUserAccount = paramString1;
      mDwAppid = paramLong1;
      mRole = paramLong2;
      mReqContext = paramTransReqContext;
      setName(paramString2);
    }
    
    HelperThread(WtloginHelper paramWtloginHelper, Handler paramHandler, int paramInt, String paramString1, long paramLong1, long paramLong2, TransReqContext paramTransReqContext, WUserSigInfo paramWUserSigInfo, String paramString2)
    {
      mHelper = paramWtloginHelper;
      mHandler = paramHandler;
      mEncrypt = paramInt;
      mUserAccount = paramString1;
      mDwAppid = paramLong1;
      mRole = paramLong2;
      mReqContext = paramTransReqContext;
      mUserSigInfo = paramWUserSigInfo;
      setName(paramString2);
    }
    
    HelperThread(WtloginHelper paramWtloginHelper, Handler paramHandler, long paramLong1, long paramLong2, String paramString1, WUserSigInfo paramWUserSigInfo, String paramString2)
    {
      mHelper = paramWtloginHelper;
      mHandler = paramHandler;
      mUserAccount = paramString1;
      mAppid1 = paramLong1;
      mSubAppid1 = paramLong2;
      mUserSigInfo = paramWUserSigInfo;
      setName(paramString2);
    }
    
    HelperThread(WtloginHelper paramWtloginHelper, Handler paramHandler, String paramString1, long paramLong1, int paramInt, long paramLong2, long[] paramArrayOfLong, boolean paramBoolean1, String paramString2, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte, boolean paramBoolean2, String paramString3)
    {
      mHelper = paramWtloginHelper;
      mHandler = paramHandler;
      mUserAccount = paramString1;
      mDwAppid = paramLong1;
      mDwMainSigMap = paramInt;
      mDwSubDstAppid = paramLong2;
      mDwSubAppidList = paramArrayOfLong;
      mPwdMd5 = paramBoolean1;
      mUserPasswd = paramString2;
      mUserSigInfo = paramWUserSigInfo;
      mST = paramArrayOfByte;
      mIsSmslogin = paramBoolean2;
      setName(paramString3);
    }
    
    HelperThread(WtloginHelper paramWtloginHelper, Handler paramHandler, String paramString1, long paramLong1, long paramLong2, int paramInt, byte[] paramArrayOfByte1, long paramLong3, long paramLong4, long paramLong5, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, WUserSigInfo paramWUserSigInfo, WFastLoginInfo paramWFastLoginInfo, String paramString2)
    {
      mHelper = paramWtloginHelper;
      mHandler = paramHandler;
      mUserAccount = paramString1;
      mAppid1 = paramLong1;
      mSubAppid1 = paramLong2;
      mDwMainSigMap = paramInt;
      mAppName2 = paramArrayOfByte1;
      mSsoVer2 = paramLong4;
      mAppid2 = paramLong4;
      mSubAppid2 = paramLong5;
      mAppVer2 = paramArrayOfByte2;
      mAppSign2 = paramArrayOfByte3;
      mUserSigInfo = paramWUserSigInfo;
      mFastLoginInfo = paramWFastLoginInfo;
      setName(paramString2);
    }
    
    HelperThread(WtloginHelper paramWtloginHelper, Handler paramHandler, String paramString1, long paramLong, WUserSigInfo paramWUserSigInfo, String paramString2)
    {
      mHelper = paramWtloginHelper;
      mHandler = paramHandler;
      mUserAccount = paramString1;
      mSmsAppid = paramLong;
      mUserSigInfo = paramWUserSigInfo;
      setName(paramString2);
    }
    
    HelperThread(WtloginHelper paramWtloginHelper, Handler paramHandler, String paramString1, String paramString2, WUserSigInfo paramWUserSigInfo, String paramString3)
    {
      mHelper = paramWtloginHelper;
      mHandler = paramHandler;
      mUserAccount = paramString1;
      mMsgCode = paramString2;
      mUserSigInfo = paramWUserSigInfo;
      setName(paramString3);
    }
    
    HelperThread(WtloginHelper paramWtloginHelper, Handler paramHandler, String paramString1, WUserSigInfo paramWUserSigInfo, String paramString2)
    {
      mHelper = paramWtloginHelper;
      mHandler = paramHandler;
      mUserAccount = paramString1;
      mUserSigInfo = paramWUserSigInfo;
      setName(paramString2);
    }
    
    HelperThread(WtloginHelper paramWtloginHelper, Handler paramHandler, String paramString1, byte[] paramArrayOfByte, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte1, String paramString2)
    {
      mHelper = paramWtloginHelper;
      mHandler = paramHandler;
      mUserAccount = paramString1;
      mUserInput = paramArrayOfByte;
      mUserSigInfo = paramWUserSigInfo;
      mST = paramArrayOfByte1;
      setName(paramString2);
    }
    
    HelperThread(WtloginHelper paramWtloginHelper, Handler paramHandler, WtTicketPromise paramWtTicketPromise, String paramString1, long paramLong1, long paramLong2, long paramLong3, int paramInt, long paramLong4, long[] paramArrayOfLong, WUserSigInfo paramWUserSigInfo, byte[][] paramArrayOfByte1, byte[][] paramArrayOfByte2, String paramString2)
    {
      mHelper = paramWtloginHelper;
      mHandler = paramHandler;
      mPromise = paramWtTicketPromise;
      mUserAccount = paramString1;
      mDwAppid = paramLong1;
      mDwDstAppid = paramLong2;
      mDwDstAppPri = paramLong3;
      mDwMainSigMap = paramInt;
      mDwSubDstAppid = paramLong4;
      mDwDstSubAppidList = paramArrayOfLong;
      mUserSigInfo = paramWUserSigInfo;
      mST = paramArrayOfByte1;
      mReserve = paramArrayOfByte2;
      setName(paramString2);
    }
    
    HelperThread(WtloginHelper paramWtloginHelper, Handler paramHandler, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, long paramLong1, long paramLong2, int paramInt, String paramString)
    {
      mHelper = paramWtloginHelper;
      mHandler = paramHandler;
      mST1 = paramArrayOfByte1;
      mST1Key = paramArrayOfByte2;
      mUIN = paramLong1;
      mDwAppid = paramLong2;
      mReportErrType = paramInt;
      setName(paramString);
    }
    
    HelperThread(WtloginHelper paramWtloginHelper, Handler paramHandler, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, long paramLong1, long paramLong2, String paramString)
    {
      mHelper = paramWtloginHelper;
      mHandler = paramHandler;
      mST1 = paramArrayOfByte1;
      mST1Key = paramArrayOfByte2;
      mUIN = paramLong1;
      mDwAppid = paramLong2;
      setName(paramString);
    }
    
    private void quitSelfLooper()
    {
      try
      {
        if (isSelfLooper)
        {
          Looper localLooper = Looper.myLooper();
          if (localLooper != null) {
            localLooper.quit();
          }
          mHandler = null;
        }
        return;
      }
      catch (Exception localException)
      {
        util.printException(localException, "");
      }
    }
    
    public void RunReq(int paramInt)
    {
      mReqType = paramInt;
      if (mReqType == 7)
      {
        start();
        return;
      }
      synchronized (WtloginHelper.__sync_top)
      {
        Timer localTimer = new Timer();
        WtloginHelper.HelperThread.1 local1 = new WtloginHelper.HelperThread.1(this);
        paramInt = WtloginHelper.__top;
        WtloginHelper.__top = paramInt + 1;
        localTimer.schedule(local1, paramInt * 500);
        util.LOGI("push queue " + WtloginHelper.__top, "");
        return;
      }
    }
    
    public void run()
    {
      if ((mHelper.mListener == null) && (mPromise == null)) {}
      for (;;)
      {
        return;
        int i = mHelper.mG.q;
        boolean bool;
        if (mHandler == null)
        {
          bool = true;
          label38:
          isSelfLooper = bool;
          if (isSelfLooper)
          {
            Looper.prepare();
            mHandler = WtloginHelper.this.newHelperHandler();
          }
        }
        try
        {
          if (mHandler == null) {
            throw new Exception("Handler should not be null!");
          }
        }
        catch (Exception localException)
        {
          util.printException(localException, "");
          mHandler.post(new WtloginHelper.HelperThread.14(this, i));
          if (isSelfLooper) {
            Looper.loop();
          }
          if (mReqType == 7) {
            continue;
          }
          synchronized (WtloginHelper.__sync_top)
          {
            if (WtloginHelper.__top > 0) {
              WtloginHelper.__top -= 1;
            }
            util.LOGI("pop queue " + WtloginHelper.__top, "");
            return;
          }
          bool = false;
          break label38;
          if (mReqType == 0)
          {
            j = mHelper.GetStWithPasswd(mUserAccount, mDwAppid, mDwMainSigMap, mDwSubDstAppid, mDwSubAppidList, mPwdMd5, mUserPasswd, mUserSigInfo, mST, mIsSmslogin, 1);
            mHandler.post(new WtloginHelper.HelperThread.2(this, i, j));
          }
          for (;;)
          {
            if (isSelfLooper) {
              Looper.loop();
            }
            if (mReqType == 7) {
              break;
            }
            synchronized (WtloginHelper.__sync_top)
            {
              if (WtloginHelper.__top > 0) {
                WtloginHelper.__top -= 1;
              }
              util.LOGI("pop queue " + WtloginHelper.__top, "");
              return;
            }
            if (mReqType != 1) {
              break label468;
            }
            j = mHelper.RefreshPictureData(mUserAccount, mUserSigInfo, 1);
            mHandler.post(new WtloginHelper.HelperThread.3(this, i, j));
          }
        }
        finally
        {
          for (;;)
          {
            int j;
            if (isSelfLooper) {
              Looper.loop();
            }
            if (mReqType != 7) {}
            synchronized (WtloginHelper.__sync_top)
            {
              if (WtloginHelper.__top > 0) {
                WtloginHelper.__top -= 1;
              }
              util.LOGI("pop queue " + WtloginHelper.__top, "");
              throw localObject4;
              label468:
              if (mReqType == 2)
              {
                j = mHelper.CheckPictureAndGetSt(mUserAccount, mUserInput, mUserSigInfo, mST, 1);
                mHandler.post(new WtloginHelper.HelperThread.4(this, i, j));
                continue;
              }
              if (mReqType == 3)
              {
                j = mHelper.RefreshSMSData(mUserAccount, mSmsAppid, mUserSigInfo, 1);
                mHandler.post(new WtloginHelper.HelperThread.5(this, i, j));
                continue;
              }
              if (mReqType == 4)
              {
                j = mHelper.CheckSMSAndGetSt(mUserAccount, mUserInput, mUserSigInfo, mST, 1);
                mHandler.post(new WtloginHelper.HelperThread.6(this, i, j));
                continue;
              }
              if (mReqType == 5)
              {
                j = mHelper.GetStWithoutPasswd(mUserAccount, mDwAppid, mDwDstAppid, mDwDstAppPri, mDwMainSigMap, mDwSubDstAppid, mDwDstSubAppidList, mUserSigInfo, mST, mReserve, 1, mPromise);
                mHandler.post(new WtloginHelper.HelperThread.7(this, i, j));
                continue;
              }
              if (mReqType == 6)
              {
                j = mHelper.GetA1WithA1(mUserAccount, mAppid1, mSubAppid1, mDwMainSigMap, mAppName2, mSsoVer2, mAppid2, mSubAppid2, mAppVer2, mAppSign2, mUserSigInfo, mFastLoginInfo, 1);
                mHandler.post(new WtloginHelper.HelperThread.8(this, i, j));
                continue;
              }
              if (mReqType == 7)
              {
                mHelper.RequestReport(1, mST1, mST1Key, mUIN, mDwAppid);
                continue;
              }
              if (mReqType == 9)
              {
                j = mHelper.RequestTransport(1, mEncrypt, mUserAccount, mDwAppid, mRole, mReqContext, mUserSigInfo);
                mHandler.post(new WtloginHelper.HelperThread.9(this, i, j));
                continue;
              }
              if (mReqType == 10)
              {
                j = mHelper.RequestTransportMsf(1, mEncrypt, mUserAccount, mDwAppid, mRole, mReqContext);
                mHandler.post(new WtloginHelper.HelperThread.10(this, i, j));
                continue;
              }
              if (mReqType == 12)
              {
                j = mHelper.CheckSMSVerifyLoginAccount(mAppid1, mSubAppid1, mUserAccount, mUserSigInfo, 1);
                mHandler.post(new WtloginHelper.HelperThread.11(this, i, j));
                continue;
              }
              if (mReqType == 13)
              {
                j = mHelper.VerifySMSVerifyLoginCode(mUserAccount, mMsgCode, mUserSigInfo, 1);
                mHandler.post(new WtloginHelper.HelperThread.12(this, i, j));
                continue;
              }
              if (mReqType == 14)
              {
                j = mHelper.RefreshSMSVerifyLoginCode(mUserAccount, mUserSigInfo, 1);
                mHandler.post(new WtloginHelper.HelperThread.13(this, i, j));
                continue;
              }
              if (mReqType != 8) {
                continue;
              }
              mHelper.RequestReportError(1, mST1, mST1Key, mUIN, mDwAppid, mReportErrType);
            }
          }
        }
      }
    }
  }
  
  public static final class SigType
  {
    public static final int WLOGIN_A2 = 64;
    public static final int WLOGIN_A5 = 2;
    public static final int WLOGIN_A8 = 16;
    public static final int WLOGIN_AQSIG = 2097152;
    public static final int WLOGIN_D2 = 262144;
    public static final int WLOGIN_DA2 = 33554432;
    public static final int WLOGIN_LHSIG = 4194304;
    public static final int WLOGIN_LSKEY = 512;
    public static final int WLOGIN_OPENKEY = 16384;
    public static final int WLOGIN_PAYTOKEN = 8388608;
    public static final int WLOGIN_PF = 16777216;
    public static final int WLOGIN_PSKEY = 1048576;
    public static final int WLOGIN_PT4Token = 134217728;
    public static final int WLOGIN_QRPUSH = 67108864;
    public static final int WLOGIN_SID = 524288;
    public static final int WLOGIN_SIG64 = 8192;
    public static final int WLOGIN_SKEY = 4096;
    public static final int WLOGIN_ST = 128;
    public static final int WLOGIN_STWEB = 32;
    public static final int WLOGIN_TOKEN = 32768;
    public static final int WLOGIN_VKEY = 131072;
    
    public SigType() {}
  }
}