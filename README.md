# ShapeView
![](https://img.shields.io/badge/platform-android-orange.svg)
![](https://img.shields.io/badge/language-java-yellow.svg)
![](https://jitpack.io/v/com.iwdael/shapeview.svg)
![](https://img.shields.io/badge/build-passing-brightgreen.svg)
![](https://img.shields.io/badge/license-apache--2.0-green.svg)
![](https://img.shields.io/badge/api-19+-green.svg)

常用布局支持阴影、背景、边框、进度，形状包含圆角、椭圆、圆形。
## 说明文档
|属性|功能|值|默认状态|
|:------:|:------:|:------:|:------:|
|backgroundColor|背景|color|透明|
|borderWidth|边框宽度|dimen|0dp|
|borderColor|边框颜色|color|透明|
|shadowColor|阴影颜色|color|透明|
|shadowRadius|阴影半径|float|0|
|shadowPadding|阴影边距|dimen|0dp|
|leftShadowPadding|阴影左边距|dimen|shadowPadding|
|topShadowPadding|阴影上边距|dimen|shadowPadding|
|rightShadowPadding|阴影右边距|dimen|shadowPadding|
|bottomShadowPadding|阴影下边距|dimen|shadowPadding|
|shadowDx|X轴阴影偏移量|dimen|0dp|
|shadowDy|Y轴阴影偏移量|dimen|0dp|
|radius|圆角|dimen/enum|0dp|
|ltRadius|左上圆角|dimen|radius|
|lbRadius|左下圆角|dimen|radius|
|rtRadius|右上圆角|dimen|radius|
|rbRadius|右下圆角|dimen|radius|
|progressStyle|进度条样式|enum|UNKNOW|
|progressReachColor|进度颜色|color|透明|
|progressUnReachColor|进度默认颜色|color|透明|
|progressStrokeWidth|进度条边框宽度|dimen|0dp|
|progressStrokeColor|进度条边框颜色|color|透明|
|progressSolidWidth|进度条宽度|dimen|0dp|
|progress|当前进度|float|0.0|
|progressMax|最大进度|float|100|
|enableDragProgress|进度条是否可拖动|boolean|false|
#### 快速引入项目
```Java
	dependencies {
                ...
	        implementation 'com.iwdael:shapeview:$version'
	}
```
