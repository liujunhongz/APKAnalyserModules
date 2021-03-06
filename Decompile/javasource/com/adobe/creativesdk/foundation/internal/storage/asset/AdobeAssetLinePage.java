package com.adobe.creativesdk.foundation.internal.storage.asset;

import com.adobe.creativesdk.foundation.adobeinternal.storage.dcx.AdobeDCXComponent;
import com.adobe.creativesdk.foundation.storage.AdobeAssetFile;
import com.adobe.creativesdk.foundation.storage.AdobeAssetPackagePages;
import java.net.URI;

public class AdobeAssetLinePage
  extends AdobeAssetFile
{
  public AdobeAssetPackagePages _package;
  public int pageNumber;
  
  public AdobeAssetLinePage(String paramString, int paramInt, AdobeDCXComponent paramAdobeDCXComponent, AdobeAssetPackagePages paramAdobeAssetPackagePages)
  {
    name = paramString;
    pageNumber = paramInt;
    GUID = paramAdobeDCXComponent.getComponentId();
    if ((paramAdobeAssetPackagePages.getHref() != null) && (paramAdobeDCXComponent.getComponentId() != null)) {}
    for (paramString = URI.create(paramAdobeAssetPackagePages.getHref().getPath() + paramAdobeDCXComponent.getComponentId());; paramString = null)
    {
      href = paramString;
      parentHref = paramAdobeAssetPackagePages.getHref();
      type = paramAdobeDCXComponent.getType();
      etag = paramAdobeDCXComponent.getEtag();
      md5Hash = paramAdobeDCXComponent.getMd5();
      creationDate = paramAdobeAssetPackagePages.getCreationDate();
      modificationDate = paramAdobeAssetPackagePages.getModificationDate();
      _package = paramAdobeAssetPackagePages;
      return;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof AdobeAssetLinePage)) {
      return super.equals(paramObject);
    }
    return false;
  }
}
