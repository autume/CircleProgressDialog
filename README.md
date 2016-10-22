# CircleProgressDialog
An Android circle progress, also can us as a dialog.
## Demo：
![](http://i.imgur.com/faMCeJD.gif)

## Using as a dependency

Add this to your build.gradle:
```java
 repositories {
        maven { url "https://jitpack.io" }
    }

    dependencies {
       compile 'com.github.autume:CircleProgressDialog:1.0.2'
    }
```
## Usage
### Use in xml
```java
<com.syd.oden.circleprogressdialog.view.RotateLoading
            oden:loading_color="@color/colorAccent"
            oden:loading_width="6dp"
            oden:shadow_offset="8dp"
            android:layout_width="100dp"
            android:layout_height="100dp" />
```

### Use as a dialog
#### Simple use
```java
CircleProgressDialog circleProgressDialog;
circleProgressDialog = new CircleProgressDialog(this);
circleProgressDialog.showDialog(); 
```
#### Use with setting
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
## Note on Releases
| Plugin Version | Detail | 
| ------------- | ----------- | ----------- | ----------- |
| 1.0.0 | origin |
| 1.0.2 | add broadcast when dialog dismiss |

   See detail in blog: http://blog.csdn.net/yaodong379/article/details/52081932
   
