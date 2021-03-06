# 人工智能SDK接入指南-Android

## 工程配置

* 在项目build.gradle中添加仓库地址：

```js
allprojects {
    repositories {
        //添加下面两个仓库地址
        jcenter()
        maven { url 'https://cosmos1602.bintray.com/mediax/' }
    }
}
```

* 添加工程依赖

```js
//添加如下依赖
api "com.cosmos.mediax:mncertification:xxxxx"//xxxxx是版本号，如0.1.20190531.1916
```

* so架构支持

SDK目前只提供了armeabi-v7a架构，请在app/build.gradle文件中配置如下代码。在android/defaultConfig/结构下添加

```js
ndk {
    abiFilters "armeabi-v7a"
}
```

* 混淆配置

```js
-keepclasseswithmembernames class * {
     native <methods>;
}
-keep class com.momocv.** {*;}
```

## 初始化sdk

```java
 MNFCService.getInstance().init(context, "appId");//传入appId，进行初始化sdk
```

## 环境准备

> 为了提高用户体验，推荐在SD卡权限获取到或者其他适当的时机，提前对sdk所需要的环境进行准备：

```java
MNFCService.getInstance().preloadResource();
```

## 开始真人认证

> 开启普通真人认证

```java
//this为当前activity或者fragment，REQUEST_CODE为请求码，用于获取返回结果
MNFCService.getInstance().startSilentCertification(this, REQUEST_CODE);
```

> 开启配合式真人认证

```java
//this为当前activity或者fragment，REQUEST_CODE为请求码，用于获取返回结果
MNFCService.getInstance().startInteractiveCertification(this, REQUEST_CODE);
```

具体使用细节，参考[接口文档](https://cosmos.wemomo.com/cv/wiki/kai-fa-zhi-nan/zhen-ren-ren-zheng/android-sdkjie-ru-zhi-nan.html)

## 人脸比对及人脸搜索功能

参考[接口文档](https://cosmos.wemomo.com/cv/wiki/kai-fa-zhi-nan/zhen-ren-ren-zheng/android-sdkjie-ru-zhi-nan.html)

## 版本更新
**0.1.20190701.1749_release**
> 1. compare 人脸匹配接口返回结果中增加颜值分数
> 2. compare跟search 人脸匹配跟搜索接口返回结果中增加人脸质量code，对照常量在 com.immomo.mncertification.constance.MNFCQulityCode 中
> 3. 更新cv版本
> 4. 优化了点头摇头识别算法
> 5. 提供Config配置开启人脸扫描时的一些参数，以支持自定义扫脸页面title。详见demo