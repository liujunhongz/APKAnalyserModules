.class final Lcom/google/android/gms/internal/zzhs$4;
.super Lcom/google/android/gms/internal/zzhs$zza;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/google/android/gms/internal/zzhs;->zzb(Landroid/content/Context;Lcom/google/android/gms/internal/zzhs$zzb;)Ljava/util/concurrent/Future;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation


# instance fields
.field final synthetic zzHw:Lcom/google/android/gms/internal/zzhs$zzb;

.field final synthetic zzrn:Landroid/content/Context;


# direct methods
.method constructor <init>(Landroid/content/Context;Lcom/google/android/gms/internal/zzhs$zzb;)V
    .registers 4

    iput-object p1, p0, Lcom/google/android/gms/internal/zzhs$4;->zzrn:Landroid/content/Context;

    iput-object p2, p0, Lcom/google/android/gms/internal/zzhs$4;->zzHw:Lcom/google/android/gms/internal/zzhs$zzb;

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/zzhs$zza;-><init>(Lcom/google/android/gms/internal/zzhs$1;)V

    return-void
.end method


# virtual methods
.method public zzdG()V
    .registers 6

    iget-object v0, p0, Lcom/google/android/gms/internal/zzhs$4;->zzrn:Landroid/content/Context;

    invoke-static {v0}, Lcom/google/android/gms/internal/zzhs;->zzH(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v0

    new-instance v1, Landroid/os/Bundle;

    invoke-direct {v1}, Landroid/os/Bundle;-><init>()V

    const-string v2, "webview_cache_version"

    const-string v3, "webview_cache_version"

    const/4 v4, 0x0

    invoke-interface {v0, v3, v4}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v0

    invoke-virtual {v1, v2, v0}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    iget-object v0, p0, Lcom/google/android/gms/internal/zzhs$4;->zzHw:Lcom/google/android/gms/internal/zzhs$zzb;

    if-eqz v0, :cond_20

    iget-object v0, p0, Lcom/google/android/gms/internal/zzhs$4;->zzHw:Lcom/google/android/gms/internal/zzhs$zzb;

    invoke-interface {v0, v1}, Lcom/google/android/gms/internal/zzhs$zzb;->zzd(Landroid/os/Bundle;)V

    :cond_20
    return-void
.end method