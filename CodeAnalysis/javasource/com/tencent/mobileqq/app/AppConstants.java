package com.tencent.mobileqq.app;

import android.os.Environment;
import com.tencent.mobileqq.hotpatch.NotVerifyClass;
import java.io.File;

public abstract interface AppConstants
{
  public static final long A = 9984L;
  public static final long B = 9983L;
  public static final long C = 9982L;
  public static final long D = 9981L;
  public static final long E = 9980L;
  public static final long F = 9979L;
  public static final long G = 9978L;
  public static final long H = 9977L;
  public static final long I = 9976L;
  public static final long J = 9975L;
  public static final long K = 9974L;
  public static final long L = 9973L;
  public static final long M = 9972L;
  public static final long N = 9971L;
  public static final long O = 9970L;
  public static final long P = 9969L;
  public static final String P = "sc.account.info.update";
  public static final long Q = 9968L;
  public static final String Q = "req_pb_protocol_flag";
  public static final long R = 9967L;
  public static final String R = "mobileQQ";
  public static final long S = 9966L;
  public static final String S = "statistic";
  public static final long T = 9965L;
  public static final String T = "share";
  public static final long U = 9964L;
  public static final String U = "screen_shot";
  public static final long V = 9963L;
  public static final String V = "history_chat_msg_search_key";
  public static final long W = 9962L;
  public static final String W = "free_call";
  public static final long X = 9961L;
  public static final String X = "aio_plus_panel_red_point";
  public static final long Y = 9960L;
  public static final String Y = "troop_new_guid";
  public static final long Z = 9957L;
  public static final String Z = "troop_nearby_list";
  public static final String aA;
  public static final String aB;
  public static final String aC;
  public static final String aD;
  public static final String aE;
  public static final String aF;
  public static final String aG;
  public static final String aH;
  public static final String aI;
  public static final String aJ;
  public static final String aK;
  public static final String aL;
  public static final String aM;
  public static final String aN;
  public static final String aO;
  public static final String aP;
  public static final String aQ;
  public static final String aR;
  public static final String aS;
  public static final String aT;
  public static final String aU;
  public static final String aV;
  public static final String aW;
  public static final String aX;
  public static final String aY = "mobileqq.service";
  public static final String aZ = "qcenter.service";
  public static final boolean a_ = false;
  public static final long aa = 9959L;
  public static final String aa = "troop_key_nearby_mem_list";
  public static final long ab = 9958L;
  public static final String ab = "banner_and_splash";
  public static final long ac = 3338705755L;
  public static final String ac = "local_html";
  public static final long ad = 2171946401L;
  public static final String ad;
  public static final long ae = 1000L;
  public static final String ae;
  public static final long af = 2592000000L;
  public static final String af;
  public static final long ag = 160L;
  public static final String ag;
  public static final long ah = 19922944L;
  public static final String ah;
  public static final long ai = 19922944L;
  public static final String ai;
  public static final long aj = 4294967295L;
  public static final String aj;
  public static final String ak;
  public static final String al;
  public static final String am;
  public static final String an;
  public static final String ao;
  public static final String ap;
  public static final String aq;
  public static final String ar;
  public static final String as;
  public static final String at;
  public static final String au;
  public static final String av;
  public static final String aw;
  public static final String ax;
  public static final String ay;
  public static final String az;
  public static final String[] b = { "NONE", "WIFI", "2G", "3G", "4G", "CABLE" };
  public static final String bA;
  public static final String bB = "head/_hd/";
  public static final String bC = "head/_thd/";
  public static final String bD;
  public static final String bE = "head/_stranger/";
  public static final String bF = ".emotionsm/";
  public static final int bG = -1;
  public static final String bG = ".starHead/";
  public static final int bH = -1001;
  public static final String bH = ".emoQFace/";
  public static final int bI = -1002;
  public static final String bI = ".pendant/";
  public static final int bJ = -1003;
  public static final String bJ = ".signatureTemplate/";
  public static final int bK = -1004;
  public static final String bK = ".secmsgPic/";
  public static final int bL = -1005;
  public static final String bL;
  public static final int bM = -1006;
  public static final String bM;
  public static final int bN = -1007;
  public static final String bN;
  public static final int bO = -1;
  public static final String bO;
  public static final int bP = -1008;
  public static final String bP;
  public static final int bQ = 0;
  public static final String bQ;
  public static final int bR = 1;
  public static final String bR;
  public static final int bS = 2;
  public static final String bS;
  public static final int bT = 3;
  public static final String bT;
  public static final int bU = 500;
  public static final String bU;
  public static final int bV = -1;
  public static final String bV;
  public static final int bW = 1;
  public static final String bW;
  public static final int bX = 2;
  public static final String bX = "custom_background/";
  public static final int bY = 40;
  public static final String bY = "data/diy_screenshot/";
  public static final int bZ = 90;
  public static final String bZ = "diskcache";
  public static final String ba = "/data/media/";
  public static final String bb;
  public static final String bc = "/mnt/extSdCard/";
  public static final String bd;
  public static final String be;
  public static final String bf;
  public static final String bg;
  public static final String bh;
  public static final String bi;
  public static final String bj;
  public static final String bk;
  public static final String bl;
  public static final String bm;
  public static final String bn;
  public static final String bo;
  public static final String bp;
  public static final String bq;
  public static final String br;
  public static final String bs;
  public static final String bt;
  public static final String bu;
  public static final String bv;
  public static final String bw;
  public static final String bx;
  public static final String by = "head/";
  public static final String bz;
  public static final String cA;
  public static final String cB;
  public static final String cC;
  public static final String cD;
  public static final String cE = "mobileqq.web";
  public static final String cF = "http://maps.google.com/maps?q=";
  public static final String cG;
  public static final String cH;
  public static final String cI;
  public static final String cJ = "head/_dhd/";
  public static final String cK;
  public static final String cL = ".nearby_flower/";
  public static final String cM;
  public static final String cN = "none";
  public static final String cO = "null";
  public static final String cP = "chatbgBroadcast";
  public static final String cQ = "chat_backgournd_nickname_color.";
  public static final String cR = "2010741172";
  public static final String cS = "OneWayMsgRecentUinList";
  public static final String cT = "_OneWayMsgDateRecentUinList";
  public static final String cU = "_OneWayMsgLBSFriendRecentUinList";
  public static final String cV = "short_video";
  public static final String cW = "raw_photo";
  public static final String cX = "raw_photo_4_test";
  public static final String cY = "plugin_is_show_mengban";
  public static final String cZ = "notifcation_taget";
  public static final int ca = 20;
  public static final String ca = "mqq";
  public static final int cb = 5;
  public static final String cb;
  public static final int cc = 5;
  public static final String cc;
  public static final int cd = 4;
  public static final String cd = "data/theme_night/";
  public static final int ce = 3;
  public static final String ce = ".gift/";
  public static final int cf = 2;
  public static final String cf;
  public static final String cg;
  public static final String ch = "nearby_people_photo/";
  public static final String ci = "business_card_photo/";
  public static final String cj = "turnbrand/";
  public static final String ck;
  public static final String cl = "crashinfo/";
  public static final String cm = "QQThemePkg";
  public static final String cn;
  public static final String co;
  public static final String cp;
  public static final String cq;
  public static final String cr;
  public static final String cs = "QQThemePkgConfig.xml";
  public static final String ct;
  public static final String cu;
  public static final String cv = "mobileqq_theme";
  public static final String cw = "using_theme_res";
  public static final String cx = ".emtion_urldrawable";
  public static final String cy;
  public static final String cz = "uniform_background.jpg";
  public static final String dA = "pcactive_has_notice";
  public static final String dB = "pcactive_config";
  public static final String dC = "picCu";
  public static final String dD = "picCd";
  public static final String dE = "picGu";
  public static final String dF = "picGd";
  public static final String dG = "picDu";
  public static final String dH = "picDd";
  public static final String dI = "pttCu";
  public static final String dJ = "pttCd";
  public static final String dK = "pttGu";
  public static final String dL = "pttGd";
  public static final String dM = "pttDu";
  public static final String dN = "pttDd";
  public static final String dO = "qb_offline";
  public static final String dP = "qb_qrcode";
  public static final String dQ = "qb_share";
  public static final String dR = "qb_troop_bar";
  public static final String dS = "qb_other";
  public static final String dT = "StarBlessLink";
  public static final String dU = "qzPicd";
  public static final String dV = "qzViod";
  public static final String dW = "qzPicu";
  public static final String dX = "qzEmod";
  public static final String dY = "qzFsPicd";
  public static final String dZ = "qzCvPicd";
  public static final String da = "qqsetting_sharedpref";
  public static final String db = "qqsetting_notify_icon_key";
  public static final String dc = "qqsetting_auto_receive_pic_key";
  public static final String dd = "qqsetting_auto_receive_magic_face_key";
  public static final String de = "qqsetting_receivemsg_whenexit_key";
  public static final String df = "qqsetting_lock_screen_whenexit_key";
  public static final String dg = "qqsetting_pcactive_key";
  public static final String dh = "qqsetting_notify_showcontent_key";
  public static final String di = "qqsetting_nodisturb_mode_key";
  public static final String dj = "qqsetting_notify_blncontrol_key";
  public static final String dk = "qqsetting_notify_myfeedpush_key";
  public static final String dl = "qqsetting_musicgene_canshow_key";
  public static final String dm = "qqsetting_musicgene_exist_key";
  public static final String dn = "qqsetting_bothonline_key";
  public static final String jdField_do = "qqsetting_security_scan_key";
  public static final String dp = "qqsetting_notify_soundtype_key";
  public static final String dq = "qqsetting_screenshot_key";
  public static final String dr = "qqsetting_enter_sendmsg_key";
  public static final String ds = "qqsetting_all_contacts_key";
  public static final String dt = "qqsetting_clear_memory_key";
  public static final String du = "qqsetting_deviceplugin_bind_flag";
  public static final String dv = "qqsetting_calltab_show_key";
  public static final String dw = "qqsetting_calltab_show_key_version";
  public static final String dx = "qqsetting_antilost_key";
  public static final String dy = "qqsetting_kandian_key";
  public static final String dz = "pcactive_notice_key";
  public static final String ea = "qzBigPicd";
  public static final String eb = "qzSignPicd";
  public static final String ec = "qzAvatar";
  public static final String ed = "multimsgCu";
  public static final String ee = "multimsgCd";
  public static final String ef = "multimsgGu";
  public static final String eg = "multimsgGd";
  public static final String eh = "multimsgDu";
  public static final String ei = "multimsgDd";
  public static final String ej = "k_resend_cnt";
  public static final long i = 1787740092L;
  public static final long j = 2068467417L;
  public static final boolean j = false;
  public static final long k = 10000L;
  public static final long l = 9999L;
  public static final long m = 9998L;
  public static final long n = 9997L;
  public static final long o = 9996L;
  public static final long p = 9995L;
  public static final long q = 9994L;
  public static final long r = 9993L;
  public static final long s = 9992L;
  public static final long t = 9991L;
  public static final long u = 9990L;
  public static final long v = 9989L;
  public static final long w = 9988L;
  public static final long x = 9987L;
  public static final long y = 9986L;
  public static final long z = 9985L;
  
  static
  {
    boolean bool = NotVerifyClass.DO_VERIFY_CLASS;
    ad = String.valueOf(2068467417L);
    ae = String.valueOf(9990L);
    af = String.valueOf(9991L);
    ag = String.valueOf(9992L);
    ah = String.valueOf(9971L);
    ai = String.valueOf(9993L);
    aj = String.valueOf(9962L);
    ak = String.valueOf(9978L);
    al = String.valueOf(9994L);
    am = String.valueOf(9995L);
    an = String.valueOf(9996L);
    ao = String.valueOf(9997L);
    ap = String.valueOf(9998L);
    aq = String.valueOf(9999L);
    ar = String.valueOf(10000L);
    as = String.valueOf(9989L);
    at = String.valueOf(9988L);
    au = String.valueOf(9987L);
    av = String.valueOf(9986L);
    aw = String.valueOf(9985L);
    ax = String.valueOf(9984L);
    ay = String.valueOf(9983L);
    az = String.valueOf(9982L);
    aA = String.valueOf(9981L);
    aB = String.valueOf(9980L);
    aC = String.valueOf(9976L);
    aD = String.valueOf(9979L);
    aE = String.valueOf(9977L);
    aF = String.valueOf(9972L);
    aG = String.valueOf(9974L);
    aH = String.valueOf(9975L);
    aI = String.valueOf(9973L);
    aJ = String.valueOf(9970L);
    aK = String.valueOf(9969L);
    aL = String.valueOf(9968L);
    aM = String.valueOf(9967L);
    aN = String.valueOf(9966L);
    aO = String.valueOf(9965L);
    aP = String.valueOf(9957L);
    aQ = String.valueOf(9964L);
    aR = String.valueOf(9963L);
    aS = String.valueOf(9961L);
    aT = String.valueOf(9960L);
    aU = String.valueOf(9959L);
    aV = String.valueOf(9958L);
    aW = String.valueOf(3338705755L);
    aX = String.valueOf(2171946401L);
    bb = Environment.getExternalStorageDirectory().getAbsolutePath();
    bd = bb + "/Tencent/MobileQQ/";
    be = bb + "/Tencent/MobileQQ/ChatRecord/";
    bf = bd + "emoji/";
    bg = bb + "/Tencent/blob/";
    bh = bd + "log/";
    bi = bb + "/Tencent/QQ_Images/";
    bj = bb + "/DCIM/QQPhoto/";
    bk = bb + "/Tencent/QQ_Shortvideos/";
    bl = bb + "/Tencent/AIO_FORWARD/";
    bm = bb + "/Tencent/QQ_Favorite/";
    bn = bb + "/DCIM/Camera/";
    bo = bb + "/Tencent/QQ_Collection/";
    bp = bb + "/Tencent/QQfile_recv/";
    bq = bb + "/Tencent/QQfile_recv/.thumbnails/";
    br = bb + "/Tencent/QQfile_recv/.tmp/";
    bs = bb + "/Tencent/QQfile_recv/.trooptmp/";
    bt = bb + "/Tencent/QQ_Secretfile/";
    bu = bb + "/Tencent/QQ_Video/";
    bv = bb + "/Tencent/QQfile_share/";
    bw = bb + "/Tencent/QQHomework_recv/";
    bx = bb + "/Tencent/QQHomework_attach/";
    bz = bd + "head/" + "_hd/";
    bA = bd + "head/" + "_thd/";
    bD = bd + "head/" + "_stranger/";
    bL = bd + ".secmsgPic/";
    bM = bd + ".emotionsm/";
    bN = bd + ".emoQFace/";
    bO = bd + ".starHead/";
    bP = bd + ".pendant/";
    bQ = bd + ".signatureTemplate/";
    bR = bd + "foward_urldrawable/";
    bS = bd + ".billd_urldrawable/";
    bT = bd + ".map_roam/";
    bU = bd + "device/";
    bV = bd + "head/" + "_SSOhd/";
    bW = bd + "location/";
    cb = bd + "system_background/";
    cc = cb + "QQPicConfig.xml";
    cf = cb + "thumbnail/";
    cg = cb + "resource/";
    ck = bd + "turnbrand/";
    cn = bd + "theme_pkg/";
    co = cn + "QQThemePkg" + "/";
    cp = co + "QQThemePkgConfig.xml";
    cq = co + "QQThemePkgTempConfig.xml";
    cr = co + "QQThemePkgOldConfig.xml";
    ct = co + "cover/";
    cu = co + "pkg/";
    cy = bd + "profilecard" + "/";
    cA = bd + "card/";
    cB = bd + "card/starfans/";
    cC = bd + "card/individbanners/";
    cD = bd + "data/";
    cG = bd + "troop/activity/";
    cH = bd + "photo/watermark_temp.jpg";
    cI = bd + "head/" + "_dhd/";
    cK = bd + ".gift/";
    cM = bd + ".nearby_flower/";
  }
}