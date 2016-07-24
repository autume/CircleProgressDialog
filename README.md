# CircleProgressDialog
## demo：
![](http://i.imgur.com/faMCeJD.gif)

## Using as a dependency

Add this to your build.gradle:
```java
 repositories {
        maven { url "https://jitpack.io" }
    }

    dependencies {
       compile 'com.github.autume:CircleProgressDialog:1.0.0'
    }
```
## usage
### use in xml
```java
<com.syd.oden.circleprogressdialog.view.RotateLoading
            oden:loading_color="@color/colorAccent"
            oden:loading_width="6dp"
            oden:shadow_offset="8dp"
            android:layout_width="100dp"
            android:layout_height="100dp" />
```

### use as a dialog
#### simple use
```java
CircleProgressDialog circleProgressDialog;
circleProgressDialog = new CircleProgressDialog(this);
circleProgressDialog.showDialog(); 
```
#### use with setting
```java
    CircleProgressDialog circleProgressDialog;
    circleProgressDialog = new CircleProgressDialog(this);

    //可对对话框的大小、进度条的颜色、宽度、文字的颜色、内容等属性进行设置
    circleProgressDialog.setDialogSize(15);
    circleProgressDialog.setProgressColor(Color.parseColor("#FFFFFF"));
    circleProgressDialog.setTextColor(Color.parseColor("#FFFFFF"));
    ...
    circleProgressDialog.showDialog();  //显示对话框
    
    //显示过程中可根据状态改变文字内容及颜色
    circleProgressDialog.changeText("erro:...");
    circleProgressDialog.changeTextColor(Color.parseColor("##EB0000"));

    circleProgressDialog.dismiss();//关闭对话框
```
   
