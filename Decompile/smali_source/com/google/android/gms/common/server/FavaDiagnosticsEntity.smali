.class public Lcom/google/android/gms/common/server/FavaDiagnosticsEntity;
.super Ljava/lang/Object;

# interfaces
.implements Lcom/google/android/gms/common/internal/safeparcel/SafeParcelable;


# static fields
.field public static final CREATOR:Lcom/google/android/gms/common/server/zza;


# instance fields
.field final mVersionCode:I

.field public final zzaeI:Ljava/lang/String;

.field public final zzaeJ:I


# direct methods
.method static constructor <clinit>()V
    .registers 1

    new-instance v0, Lcom/google/android/gms/common/server/zza;

    invoke-direct {v0}, Lcom/google/android/gms/common/server/zza;-><init>()V

    sput-object v0, Lcom/google/android/gms/common/server/FavaDiagnosticsEntity;->CREATOR:Lcom/google/android/gms/common/server/zza;

    return-void
.end method

.method public constructor <init>(ILjava/lang/String;I)V
    .registers 4
    .param p1, "versionCode"    # I
    .param p2, "namespace"    # Ljava/lang/String;
    .param p3, "typeNum"    # I

    .prologue
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lcom/google/android/gms/common/server/FavaDiagnosticsEntity;->mVersionCode:I

    iput-object p2, p0, Lcom/google/android/gms/common/server/FavaDiagnosticsEntity;->zzaeI:Ljava/lang/String;

    iput p3, p0, Lcom/google/android/gms/common/server/FavaDiagnosticsEntity;->zzaeJ:I

    return-void
.end method


# virtual methods
.method public describeContents()I
    .registers 2

    const/4 v0, 0x0

    return v0
.end method

.method public writeToParcel(Landroid/os/Parcel;I)V
    .registers 3
    .param p1, "out"    # Landroid/os/Parcel;
    .param p2, "flags"    # I

    .prologue
    invoke-static {p0, p1, p2}, Lcom/google/android/gms/common/server/zza;->zza(Lcom/google/android/gms/common/server/FavaDiagnosticsEntity;Landroid/os/Parcel;I)V

    return-void
.end method