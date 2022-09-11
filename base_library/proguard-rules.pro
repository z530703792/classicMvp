-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# 保护注解
-keepattributes *Annotation*
-keep class * extends java.lang.annotation.Annotation {*;}

# 泛型与反射
-keepattributes Signature
-keepattributes EnclosingMethod
-keepattributes InnerClasses


# 混淆后生产映射文件 map 类名->转化后类名的映射
# 存放在app\build\outputs\mapping\release中
-verbose
# 混淆前后的映射
-printmapping mapping.txt
# apk 包内所有 class 的内部结构
-dump class_files.txt
# 未混淆的类和成员
-printseeds seeds.txt
# 列出从 apk 中删除的代码
-printusage unused.txt
-keepattributes SourceFile,LineNumberTable



-keep public class * extends android.view.View {*;}
-keep public class * extends android.widget.BaseAdapter {*;}


-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.support.**
-keep public class android.support.** {*;}

-keep class android.support.** { *; }
-dontwarn android.support.**

-keep class javax.** {*;}
-dontwarn  javax.**

-keep class org.** { *; }
-dontwarn  org.**

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被

    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.Í.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}


-keep public class [your_pkg].R$*{
    public static final int *;
}
# WebView使用javascript功能则需要开启
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
    }

-keepattributes *JavascriptInterface*
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String,android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebChromeClient{
    public void openFileChooser(...);
    public void onShowFileChooser(...);
}



# OkHttp  
-keepattributes Signature  
-keepattributes *Annotation*  
-keep class okhttp3.** {*;}
-keep interface okhttp3.** {*;}
-dontwarn okhttp3.**  

#okio
-dontwarn okio.**
-keep class okio.**{*;}
-keep interface okio.**{*;}

#rxJava
-dontwarn rx.**
-keep class rx.**{*;}
-dontwarn  io.reactivex.**
-keep class io.reactivex.** {*;}

#squareup
-keep class com.squareup.** { *; }
-dontwarn com.squareup.**

#gson
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

#glide
-keep class com.bumptech.glide.** { *; }
-dontwarn com.bumptech.glide.**

# ButterKnife 7  
-keep class butterknife.** {*;}
-dontwarn butterknife.internal.**  
-keep class **$$ViewBinder {*;}
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
       @butterknife.* <methods>;
}

#dagger
-dontwarn dagger.internal.codegen.**  
-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
    <init>();
}
-keep class dagger.* {*;}
-keep class javax.inject.* {*;}
-keep class * extends dagger.internal.Binding  
-keep class * extends dagger.internal.ModuleAdapter  
-keep class * extends dagger.internal.StaticInjection  

# VerticalRollingTextView
-dontwarn  com.xiaosu.**
-keep class com.xiaosu.** {*;}

#刷新 ---SmartRefreshLayout
-keep class com.scwang.smartrefresh.layout.** { *; }
-dontwarn com.scwang.smartrefresh.layout.**

#github
-keep class com.github.** { *; }
-dontwarn com.github.**

# BGABanner-Android
-dontwarn  cn.bingoogolapple.**
-keep class cn.bingoogolapple.** {*;}
-dontwarn  com.nineoldandroids.**
-keep class com.nineoldandroids.** {*;}

#photoview
-keep class uk.co.senab.photoview.** { *; }
-dontwarn uk.co.senab.photoview.**

#MagicIndicator
-keep class net.lucode.hackware.magicindicator.** { *; }
-dontwarn net.lucode.hackware.magicindicator.**

#PermissionsDispatcher
-keep class permissions.dispatcher.** { *; }
-dontwarn permissions.dispatcher.**

# CheckVersionLib
-dontwarn  com.allenliu.versionchecklib.**
-keep class com.allenliu.versionchecklib.** {*;}

#Architecture Componets
-dontwarn  android.arch.**
-keep class android.arch.** {*;}

#bean
-dontwarn com.zhongfen.base_library.model.bean.**
-keep class com.classic.base_library.model.bean.** {*;}
-dontwarn java.lang.invoke.**
-keep class java.lang.invoke.** {*;}

# StatusBarUtil
-dontwarn  com.jaeger.library.**
-keep class com.jaeger.library.** {*;}

# gif
-dontwarn  pl.droidsonroids.gif.**
-keep class pl.droidsonroids.gif.** {*;}

# lottie
-dontwarn  com.airbnb.lottie.**
-keep class com.airbnb.lottie.** {*;}

-dontwarn com.zhongfen.base_library.recyclerview.**
-keep class com.classic.base_library.recyclerview.** {*;}

#parallaxbacklayout
-dontwarn com.github.anzewei.parallaxbacklayout.**
-keep class com.github.anzewei.parallaxbacklayout.** {*;}

#网易云信
-dontwarn com.netease.**
-keep class com.netease.** {*;}


